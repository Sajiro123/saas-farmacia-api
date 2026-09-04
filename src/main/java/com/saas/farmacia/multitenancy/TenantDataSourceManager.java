package com.saas.farmacia.multitenancy;

import com.saas.farmacia.security.EncryptionService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TenantDataSourceManager {

    private static final Logger log = LoggerFactory.getLogger(TenantDataSourceManager.class);
    private final Map<String, HikariDataSource> tenantDataSources = new ConcurrentHashMap<>();
    private final EncryptionService encryptionService;

    @Value("${saas.master.datasource.url}")
    private String masterUrl;

    @Value("${saas.master.datasource.username}")
    private String masterUser;

    @Value("${saas.master.datasource.password}")
    private String masterPassword;

    public TenantDataSourceManager(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    public DataSource getDataSource(String tenantId) {
        if (tenantId == null || tenantId.isBlank()) {
            throw new IllegalStateException("No se ha especificado un tenant_id en el contexto actual.");
        }

        return tenantDataSources.computeIfAbsent(tenantId, this::createTenantDataSource);
    }

    private HikariDataSource createTenantDataSource(String tenantId) {
        log.info("🔍 Resolviendo conexión cifrada para el Tenant ID: {} en la Base Central...", tenantId);

        String sql = "SELECT host_bd, puerto_bd, nombre_bd, usuario_bd, password_bd_cifrado, modo_ssl, pool_min_conexiones, pool_max_conexiones " +
                     "FROM credenciales_bd_negocio WHERE negocio_id = ?::uuid";

        try (Connection masterConn = DriverManager.getConnection(masterUrl, masterUser, masterPassword);
             PreparedStatement ps = masterConn.prepareStatement(sql)) {

            ps.setString(1, tenantId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new IllegalArgumentException("No existen credenciales de base de datos para el Tenant ID: " + tenantId);
                }

                String host = rs.getString("host_bd");
                int port = rs.getInt("puerto_bd");
                String dbName = rs.getString("nombre_bd");
                String user = rs.getString("usuario_bd");
                String encryptedPassword = rs.getString("password_bd_cifrado");
                String sslMode = rs.getString("modo_ssl");
                int poolMin = rs.getInt("pool_min_conexiones");
                int poolMax = rs.getInt("pool_max_conexiones");

                // Descifrado seguro con AES-256 en memoria
                String password = encryptionService.decrypt(encryptedPassword);

                String jdbcUrl = String.format("jdbc:postgresql://%s:%d/%s?sslmode=%s", host, port, dbName, sslMode != null ? sslMode : "require");

                log.info("🔌 Creando nuevo Pool HikariCP para Tenant [{}] hacia {} con credenciales descifradas", tenantId, host);

                HikariConfig config = new HikariConfig();
                config.setJdbcUrl(jdbcUrl);
                config.setUsername(user);
                config.setPassword(password);
                config.setDriverClassName("org.postgresql.Driver");
                config.setPoolName("TenantPool-" + tenantId.substring(0, 8));
                config.setMinimumIdle(poolMin > 0 ? poolMin : 2);
                config.setMaximumPoolSize(poolMax > 0 ? poolMax : 8);
                config.setConnectionTimeout(20000);
                config.setIdleTimeout(300000);
                config.setMaxLifetime(600000);
                config.setLeakDetectionThreshold(30000);

                return new HikariDataSource(config);
            }
        } catch (Exception e) {
            log.error("❌ Error al crear la fuente de datos para el Tenant ID: {}", tenantId, e);
            throw new RuntimeException("No se pudo conectar a la base de datos del cliente: " + e.getMessage(), e);
        }
    }

    /**
     * Invalida y cierra el pool de conexiones de un tenant específico.
     * Se utiliza cuando se actualiza la contraseña, host o límites de pool (min/max).
     */
    public synchronized void evictTenantDataSource(String tenantId) {
        HikariDataSource ds = tenantDataSources.remove(tenantId);
        if (ds != null && !ds.isClosed()) {
            log.info("♻️ Cerrando pool de conexiones obsoleto para Tenant [{}]...", tenantId);
            ds.close();
        }
    }

    /**
     * Invalida y cierra todos los pools activos.
     */
    public synchronized void evictAll() {
        log.info("♻️ Purgando todos los pools de conexiones de tenants...");
        tenantDataSources.forEach((id, ds) -> {
            if (ds != null && !ds.isClosed()) {
                ds.close();
            }
        });
        tenantDataSources.clear();
    }
}

package com.saas.farmacia.multitenancy;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    private final TenantDataSourceManager tenantDataSourceManager;

    public DynamicRoutingDataSource(TenantDataSourceManager tenantDataSourceManager) {
        this.tenantDataSourceManager = tenantDataSourceManager;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return TenantContext.getTenantId();
    }

    @Override
    protected DataSource determineTargetDataSource() {
        String tenantId = (String) determineCurrentLookupKey();
        if (tenantId == null) {
            throw new IllegalStateException("Acceso no autorizado: No se encontró el claim tenant_id en la petición.");
        }
        return tenantDataSourceManager.getDataSource(tenantId);
    }

    @Override
    public void afterPropertiesSet() {
        // No es necesario inicializar un mapa estático ya que la resolución es 100% dinámica
    }
}

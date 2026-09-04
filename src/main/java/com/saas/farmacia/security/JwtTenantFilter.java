package com.saas.farmacia.security;

import com.saas.farmacia.multitenancy.TenantContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtTenantFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtTenantFilter.class);
    private final SecretKey secretKey;

    public JwtTenantFilter(@Value("${jwt.secret}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = getJwtFromRequest(request);

            if (StringUtils.hasText(token)) {
                Claims claims = Jwts.parser()
                        .verifyWith(secretKey)
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();

                String email = claims.getSubject();
                String tenantId = claims.get("tenant_id", String.class);
                String subdomain = claims.get("subdominio", String.class);
                String vertical = claims.get("vertical", String.class);

                // Validar que el token corresponda a la vertical de FARMACIA o Superadmin
                Boolean isSuperadmin = claims.get("is_superadmin", Boolean.class);
                if (!"FARMACIA".equalsIgnoreCase(vertical) && !Boolean.TRUE.equals(isSuperadmin)) {
                    log.warn("⛔ Intento de acceso a Farmacia API con vertical no autorizada: {}", vertical);
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "El token no corresponde a la vertical de Farmacias");
                    return;
                }

                // Inyectar en el contexto de ejecución del hilo (ThreadLocal)
                TenantContext.setTenantId(tenantId);
                TenantContext.setSubdomain(subdomain);

                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                if (Boolean.TRUE.equals(isSuperadmin)) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_SUPERADMIN"));
                }
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(email, null, authorities);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            log.error("Error al validar el token JWT en Farmacia API: {}", ex.getMessage());
            SecurityContextHolder.clearContext();
            filterChain.doFilter(request, response);
        } finally {
            // Limpieza crítica para evitar memory leak en el pool de hilos de Tomcat
            TenantContext.clear();
        }
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

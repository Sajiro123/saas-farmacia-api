package com.saas.farmacia.multitenancy;

public class TenantContext {

    private static final ThreadLocal<String> CURRENT_TENANT = new ThreadLocal<>();
    private static final ThreadLocal<String> CURRENT_SUBDOMAIN = new ThreadLocal<>();

    public static void setTenantId(String tenantId) {
        CURRENT_TENANT.set(tenantId);
    }

    public static String getTenantId() {
        return CURRENT_TENANT.get();
    }

    public static void setSubdomain(String subdomain) {
        CURRENT_SUBDOMAIN.set(subdomain);
    }

    public static String getSubdomain() {
        return CURRENT_SUBDOMAIN.get();
    }

    public static void clear() {
        CURRENT_TENANT.remove();
        CURRENT_SUBDOMAIN.remove();
    }
}

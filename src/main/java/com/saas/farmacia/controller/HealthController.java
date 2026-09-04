package com.saas.farmacia.controller;

import com.saas.farmacia.common.dto.ApiResponse;
import com.saas.farmacia.multitenancy.TenantContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {

    @GetMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> healthCheck() {
        Map<String, Object> status = new HashMap<>();
        status.put("status", "UP");
        status.put("service", "saas-farmacia-api");
        status.put("current_tenant_id", TenantContext.getTenantId() != null ? TenantContext.getTenantId() : "No tenant context");
        status.put("current_subdomain", TenantContext.getSubdomain() != null ? TenantContext.getSubdomain() : "Public request");
        
        return ResponseEntity.ok(ApiResponse.ok("Servicio Farmacia Operativo", status));
    }
}

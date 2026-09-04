package com.saas.farmacia.domain.dashboard.controller;

import com.saas.farmacia.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    @GetMapping("/kpi")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getKpi() {
        Map<String, Object> kpi = new HashMap<>();
        kpi.put("ventasHoy", 1540.50);
        kpi.put("ticketsEmitidos", 42);
        kpi.put("lotesPorVencer", 5);
        kpi.put("stockBajo", 12);
        
        return ResponseEntity.ok(ApiResponse.ok("KPIs del Dashboard", kpi));
    }
}

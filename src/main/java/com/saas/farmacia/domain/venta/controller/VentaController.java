package com.saas.farmacia.domain.venta.controller;

import com.saas.farmacia.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1/ventas")
public class VentaController {

    @PostMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> registrarVenta(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("ticketId", UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        response.put("fecha", new Date());
        response.put("total", request.get("total"));
        response.put("estado", "COMPLETADO");
        
        return ResponseEntity.ok(ApiResponse.ok("Venta registrada exitosamente", response));
    }
}

package com.saas.farmacia.domain.sucursal.controller;

import com.saas.farmacia.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1/sedes")
public class SedeController {

    @GetMapping
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> listarSedes() {
        List<Map<String, Object>> sedes = new ArrayList<>();
        
        Map<String, Object> sede1 = new HashMap<>();
        sede1.put("id", UUID.randomUUID());
        sede1.put("nombre", "Sede Principal");
        sede1.put("direccion", "Av. Central 123");
        sede1.put("activa", true);
        
        Map<String, Object> sede2 = new HashMap<>();
        sede2.put("id", UUID.randomUUID());
        sede2.put("nombre", "Sucursal Norte");
        sede2.put("direccion", "Av. Norte 456");
        sede2.put("activa", true);
        
        sedes.add(sede1);
        sedes.add(sede2);
        
        return ResponseEntity.ok(ApiResponse.ok("Sedes listadas", sedes));
    }
}

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
        sede1.put("id", "11111111-1111-1111-1111-111111111111");
        sede1.put("nombre", "Sede Cajamarca Central");
        sede1.put("direccion", "Av. Central 123, Cajamarca");
        sede1.put("telefono", "987654321");
        sede1.put("encargado", "Lic. Carlos Alberto Mendoza Ramos (Q.F. Regente)");
        sede1.put("activa", true);
        
        sedes.add(sede1);
        
        return ResponseEntity.ok(ApiResponse.ok("Sedes listadas", sedes));
    }
}

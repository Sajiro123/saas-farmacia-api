package com.saas.farmacia.domain.inventario.controller;

import com.saas.farmacia.common.dto.ApiResponse;
import com.saas.farmacia.domain.inventario.dto.LoteFefoDTO;
import com.saas.farmacia.domain.inventario.service.InventarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inventario")
@Tag(name = "Motor FEFO e Inventarios", description = "Despacho inteligente de lotes ordenados por fecha de vencimiento")
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping("/lotes-fefo")
    @Operation(summary = "Sugerir lotes por algoritmo FEFO", description = "Calcula los lotes a despachar priorizando el vencimiento más cercano y bloqueando vencidos.")
    public ResponseEntity<ApiResponse<List<LoteFefoDTO>>> sugerirLotesFefo(
            @RequestParam UUID almacenId,
            @RequestParam UUID productoId,
            @RequestParam int cantidad) {
        List<LoteFefoDTO> lotes = inventarioService.calcularDespachoFefo(almacenId, productoId, cantidad);
        return ResponseEntity.ok(ApiResponse.ok("Lotes FEFO sugeridos para venta", lotes));
    }
}

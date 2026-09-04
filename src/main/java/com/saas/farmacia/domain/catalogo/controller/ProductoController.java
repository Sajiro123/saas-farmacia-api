package com.saas.farmacia.domain.catalogo.controller;

import com.saas.farmacia.common.dto.ApiResponse;
import com.saas.farmacia.domain.catalogo.dto.ProductoDTO;
import com.saas.farmacia.domain.catalogo.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/productos")
@Tag(name = "Catálogo Farmacéutico", description = "Búsqueda predictiva y consulta de medicamentos DIGEMID")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    @Operation(summary = "Buscar productos", description = "Busca medicamentos por nombre comercial, genérico, código de barras o código interno.")
    public ResponseEntity<ApiResponse<List<ProductoDTO>>> buscarProductos(@RequestParam(required = false) String query) {
        List<ProductoDTO> list = productoService.listarOFiltrar(query);
        return ResponseEntity.ok(ApiResponse.ok("Catálogo de medicamentos", list));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalle de producto", description = "Obtiene el detalle completo de un medicamento.")
    public ResponseEntity<ApiResponse<ProductoDTO>> obtenerPorId(@PathVariable UUID id) {
        ProductoDTO dto = productoService.obtenerPorId(id);
        return ResponseEntity.ok(ApiResponse.ok("Medicamento encontrado", dto));
    }
}

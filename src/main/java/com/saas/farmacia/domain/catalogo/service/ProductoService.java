package com.saas.farmacia.domain.catalogo.service;

import com.saas.farmacia.domain.catalogo.dto.ProductoDTO;
import com.saas.farmacia.domain.catalogo.entity.Producto;
import com.saas.farmacia.domain.catalogo.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductoDTO> listarOFiltrar(String query) {
        List<Producto> productos = (query != null && !query.isBlank())
                ? productoRepository.buscarProductos(query)
                : productoRepository.findAll();

        return productos.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductoDTO obtenerPorId(UUID id) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return mapToDTO(p);
    }

    private ProductoDTO mapToDTO(Producto p) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(p.getId());
        dto.setCodigoBarras(p.getCodigoBarras());
        dto.setCodigoInterno(p.getCodigoInterno());
        dto.setNombreComercial(p.getNombreComercial());
        dto.setNombreGenerico(p.getNombreGenerico());
        if (p.getPrincipioActivo() != null) dto.setPrincipioActivo(p.getPrincipioActivo().getNombre());
        if (p.getLaboratorio() != null) dto.setLaboratorio(p.getLaboratorio().getNombre());
        dto.setConcentracion(p.getConcentracion());
        dto.setRegistroSanitario(p.getRegistroSanitario());
        dto.setTipoReceta(p.getTipoReceta());
        dto.setEsFraccionable(p.getEsFraccionable());
        dto.setUnidadesPorCaja(p.getUnidadesPorCaja());
        dto.setPrecioVenta(p.getPrecioVenta());
        dto.setPrecioVentaFraccion(p.getPrecioVentaFraccion());
        return dto;
    }
}

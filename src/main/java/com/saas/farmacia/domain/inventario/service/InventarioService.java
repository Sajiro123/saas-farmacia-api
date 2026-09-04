package com.saas.farmacia.domain.inventario.service;

import com.saas.farmacia.domain.inventario.dto.LoteFefoDTO;
import com.saas.farmacia.domain.inventario.entity.StockInventario;
import com.saas.farmacia.domain.inventario.repository.StockInventarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class InventarioService {

    private final StockInventarioRepository stockRepository;

    public InventarioService(StockInventarioRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional(readOnly = true)
    public List<LoteFefoDTO> calcularDespachoFefo(UUID almacenId, UUID productoId, int cantidadRequerida) {
        List<StockInventario> lotes = stockRepository.findLotesFefoDisponibles(almacenId, productoId);
        List<LoteFefoDTO> resultado = new ArrayList<>();

        int restante = cantidadRequerida;
        for (StockInventario s : lotes) {
            if (restante <= 0) break;

            int disponible = s.getCantidad();
            int aTomar = Math.min(disponible, restante);

            resultado.add(new LoteFefoDTO(
                    s.getLote().getId(),
                    s.getLote().getNumeroLote(),
                    s.getLote().getFechaVencimiento(),
                    disponible,
                    aTomar
            ));

            restante -= aTomar;
        }

        if (restante > 0) {
            throw new IllegalArgumentException("Stock insuficiente para el despacho FEFO. Faltan " + restante + " unidades.");
        }

        return resultado;
    }
}

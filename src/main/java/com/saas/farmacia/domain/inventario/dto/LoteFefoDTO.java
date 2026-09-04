package com.saas.farmacia.domain.inventario.dto;

import java.time.LocalDate;
import java.util.UUID;

public class LoteFefoDTO {
    private UUID loteId;
    private String numeroLote;
    private LocalDate fechaVencimiento;
    private Integer stockDisponible;
    private Integer cantidadSugerida;

    public LoteFefoDTO() {}

    public LoteFefoDTO(UUID loteId, String numeroLote, LocalDate fechaVencimiento, Integer stockDisponible, Integer cantidadSugerida) {
        this.loteId = loteId;
        this.numeroLote = numeroLote;
        this.fechaVencimiento = fechaVencimiento;
        this.stockDisponible = stockDisponible;
        this.cantidadSugerida = cantidadSugerida;
    }

    public UUID getLoteId() { return loteId; }
    public void setLoteId(UUID loteId) { this.loteId = loteId; }
    public String getNumeroLote() { return numeroLote; }
    public void setNumeroLote(String numeroLote) { this.numeroLote = numeroLote; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
    public Integer getStockDisponible() { return stockDisponible; }
    public void setStockDisponible(Integer stockDisponible) { this.stockDisponible = stockDisponible; }
    public Integer getCantidadSugerida() { return cantidadSugerida; }
    public void setCantidadSugerida(Integer cantidadSugerida) { this.cantidadSugerida = cantidadSugerida; }
}

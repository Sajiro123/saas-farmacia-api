package com.saas.farmacia.domain.catalogo.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductoDTO {
    private UUID id;
    private String codigoBarras;
    private String codigoInterno;
    private String nombreComercial;
    private String nombreGenerico;
    private String principioActivo;
    private String laboratorio;
    private String concentracion;
    private String registroSanitario;
    private String tipoReceta;
    private Boolean esFraccionable;
    private Integer unidadesPorCaja;
    private BigDecimal precioVenta;
    private BigDecimal precioVentaFraccion;

    public ProductoDTO() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getCodigoBarras() { return codigoBarras; }
    public void setCodigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; }
    public String getCodigoInterno() { return codigoInterno; }
    public void setCodigoInterno(String codigoInterno) { this.codigoInterno = codigoInterno; }
    public String getNombreComercial() { return nombreComercial; }
    public void setNombreComercial(String nombreComercial) { this.nombreComercial = nombreComercial; }
    public String getNombreGenerico() { return nombreGenerico; }
    public void setNombreGenerico(String nombreGenerico) { this.nombreGenerico = nombreGenerico; }
    public String getPrincipioActivo() { return principioActivo; }
    public void setPrincipioActivo(String principioActivo) { this.principioActivo = principioActivo; }
    public String getLaboratorio() { return laboratorio; }
    public void setLaboratorio(String laboratorio) { this.laboratorio = laboratorio; }
    public String getConcentracion() { return concentracion; }
    public void setConcentracion(String concentracion) { this.concentracion = concentracion; }
    public String getRegistroSanitario() { return registroSanitario; }
    public void setRegistroSanitario(String registroSanitario) { this.registroSanitario = registroSanitario; }
    public String getTipoReceta() { return tipoReceta; }
    public void setTipoReceta(String tipoReceta) { this.tipoReceta = tipoReceta; }
    public Boolean getEsFraccionable() { return esFraccionable; }
    public void setEsFraccionable(Boolean esFraccionable) { this.esFraccionable = esFraccionable; }
    public Integer getUnidadesPorCaja() { return unidadesPorCaja; }
    public void setUnidadesPorCaja(Integer unidadesPorCaja) { this.unidadesPorCaja = unidadesPorCaja; }
    public BigDecimal getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(BigDecimal precioVenta) { this.precioVenta = precioVenta; }
    public BigDecimal getPrecioVentaFraccion() { return precioVentaFraccion; }
    public void setPrecioVentaFraccion(BigDecimal precioVentaFraccion) { this.precioVentaFraccion = precioVentaFraccion; }
}

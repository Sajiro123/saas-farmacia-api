package com.saas.farmacia.domain.catalogo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "codigo_barras", unique = true, length = 50)
    private String codigoBarras;

    @Column(name = "codigo_interno", nullable = false, unique = true, length = 50)
    private String codigoInterno;

    @Column(name = "nombre_comercial", nullable = false)
    private String nombreComercial;

    @Column(name = "nombre_generico")
    private String nombreGenerico;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "principio_activo_id")
    private PrincipioActivo principioActivo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "laboratorio_id")
    private Laboratorio laboratorio;

    @Column(name = "concentracion", length = 100)
    private String concentracion;

    @Column(name = "registro_sanitario", length = 100)
    private String registroSanitario;

    @Column(name = "tipo_receta", length = 30)
    private String tipoReceta = "VENTA_LIBRE";

    @Column(name = "es_fraccionable")
    private Boolean esFraccionable = false;

    @Column(name = "unidades_por_caja")
    private Integer unidadesPorCaja = 1;

    @Column(name = "precio_costo", nullable = false, precision = 12, scale = 4)
    private BigDecimal precioCosto = BigDecimal.ZERO;

    @Column(name = "precio_venta", nullable = false, precision = 12, scale = 2)
    private BigDecimal precioVenta;

    @Column(name = "precio_venta_fraccion", precision = 12, scale = 2)
    private BigDecimal precioVentaFraccion;

    @Column(name = "tipo_afectacion_igv", length = 10)
    private String tipoAfectacionIgv = "10";

    @Column(name = "stock_minimo")
    private Integer stockMinimo = 5;

    @Column(name = "stock_maximo")
    private Integer stockMaximo = 100;

    @Column(name = "esta_activo")
    private Boolean estaActivo = true;

    @Column(name = "creado_en")
    private ZonedDateTime creadoEn = ZonedDateTime.now();

    public Producto() {}

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
    public PrincipioActivo getPrincipioActivo() { return principioActivo; }
    public void setPrincipioActivo(PrincipioActivo principioActivo) { this.principioActivo = principioActivo; }
    public Laboratorio getLaboratorio() { return laboratorio; }
    public void setLaboratorio(Laboratorio laboratorio) { this.laboratorio = laboratorio; }
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
    public BigDecimal getPrecioCosto() { return precioCosto; }
    public void setPrecioCosto(BigDecimal precioCosto) { this.precioCosto = precioCosto; }
    public BigDecimal getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(BigDecimal precioVenta) { this.precioVenta = precioVenta; }
    public BigDecimal getPrecioVentaFraccion() { return precioVentaFraccion; }
    public void setPrecioVentaFraccion(BigDecimal precioVentaFraccion) { this.precioVentaFraccion = precioVentaFraccion; }
    public String getTipoAfectacionIgv() { return tipoAfectacionIgv; }
    public void setTipoAfectacionIgv(String tipoAfectacionIgv) { this.tipoAfectacionIgv = tipoAfectacionIgv; }
    public Integer getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(Integer stockMinimo) { this.stockMinimo = stockMinimo; }
    public Integer getStockMaximo() { return stockMaximo; }
    public void setStockMaximo(Integer stockMaximo) { this.stockMaximo = stockMaximo; }
    public Boolean getEstaActivo() { return estaActivo; }
    public void setEstaActivo(Boolean estaActivo) { this.estaActivo = estaActivo; }
    public ZonedDateTime getCreadoEn() { return creadoEn; }
    public void setCreadoEn(ZonedDateTime creadoEn) { this.creadoEn = creadoEn; }
}

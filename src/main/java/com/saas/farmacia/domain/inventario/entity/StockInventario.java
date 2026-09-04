package com.saas.farmacia.domain.inventario.entity;

import com.saas.farmacia.domain.catalogo.entity.Producto;
import jakarta.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "stock_inventario")
public class StockInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "almacen_id", nullable = false)
    private UUID almacenId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lote_id", nullable = false)
    private LoteProducto lote;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad = 0;

    @Column(name = "cantidad_fraccion")
    private Integer cantidadFraccion = 0;

    @Column(name = "actualizado_en")
    private ZonedDateTime actualizadoEn = ZonedDateTime.now();

    public StockInventario() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getAlmacenId() { return almacenId; }
    public void setAlmacenId(UUID almacenId) { this.almacenId = almacenId; }
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
    public LoteProducto getLote() { return lote; }
    public void setLote(LoteProducto lote) { this.lote = lote; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public Integer getCantidadFraccion() { return cantidadFraccion; }
    public void setCantidadFraccion(Integer cantidadFraccion) { this.cantidadFraccion = cantidadFraccion; }
    public ZonedDateTime getActualizadoEn() { return actualizadoEn; }
    public void setActualizadoEn(ZonedDateTime actualizadoEn) { this.actualizadoEn = actualizadoEn; }
}

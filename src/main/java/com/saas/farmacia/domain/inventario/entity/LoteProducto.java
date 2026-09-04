package com.saas.farmacia.domain.inventario.entity;

import com.saas.farmacia.domain.catalogo.entity.Producto;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "lotes_producto")
public class LoteProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(name = "numero_lote", nullable = false, length = 100)
    private String numeroLote;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @Column(name = "fecha_fabricacion")
    private LocalDate fechaFabricacion;

    @Column(name = "registro_sanitario", length = 100)
    private String registroSanitario;

    @Column(name = "estado", length = 20)
    private String estado = "ACTIVO";

    @Column(name = "creado_en")
    private ZonedDateTime creadoEn = ZonedDateTime.now();

    public LoteProducto() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
    public String getNumeroLote() { return numeroLote; }
    public void setNumeroLote(String numeroLote) { this.numeroLote = numeroLote; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
    public LocalDate getFechaFabricacion() { return fechaFabricacion; }
    public void setFechaFabricacion(LocalDate fechaFabricacion) { this.fechaFabricacion = fechaFabricacion; }
    public String getRegistroSanitario() { return registroSanitario; }
    public void setRegistroSanitario(String registroSanitario) { this.registroSanitario = registroSanitario; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public ZonedDateTime getCreadoEn() { return creadoEn; }
    public void setCreadoEn(ZonedDateTime creadoEn) { this.creadoEn = creadoEn; }
}

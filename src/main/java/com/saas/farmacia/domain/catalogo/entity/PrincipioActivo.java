package com.saas.farmacia.domain.catalogo.entity;

import jakarta.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "principios_activos")
public class PrincipioActivo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "creado_en")
    private ZonedDateTime creadoEn = ZonedDateTime.now();

    public PrincipioActivo() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public ZonedDateTime getCreadoEn() { return creadoEn; }
    public void setCreadoEn(ZonedDateTime creadoEn) { this.creadoEn = creadoEn; }
}

package com.saas.farmacia.domain.catalogo.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "laboratorios")
public class Laboratorio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nombre", nullable = false, unique = true, length = 200)
    private String nombre;

    @Column(name = "pais", length = 100)
    private String pais;

    @Column(name = "esta_activo")
    private Boolean estaActivo = true;

    public Laboratorio() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
    public Boolean getEstaActivo() { return estaActivo; }
    public void setEstaActivo(Boolean estaActivo) { this.estaActivo = estaActivo; }
}

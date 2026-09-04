package com.saas.farmacia.domain.catalogo.repository;

import com.saas.farmacia.domain.catalogo.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, UUID> {

    Optional<Producto> findByCodigoBarras(String codigoBarras);
    Optional<Producto> findByCodigoInterno(String codigoInterno);

    @Query("SELECT p FROM Producto p WHERE p.estaActivo = true AND " +
           "(LOWER(p.nombreComercial) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           " LOWER(p.nombreGenerico) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           " LOWER(p.codigoBarras) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           " LOWER(p.codigoInterno) LIKE LOWER(CONCAT('%', :query, '%')))")
    List<Producto> buscarProductos(@Param("query") String query);
}

package com.saas.farmacia.domain.inventario.repository;

import com.saas.farmacia.domain.inventario.entity.StockInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StockInventarioRepository extends JpaRepository<StockInventario, UUID> {

    List<StockInventario> findByProductoId(UUID productoId);
    List<StockInventario> findByAlmacenId(UUID almacenId);

    @Query("SELECT s FROM StockInventario s JOIN s.lote l " +
           "WHERE s.almacenId = :almacenId AND s.producto.id = :productoId AND l.estado = 'ACTIVO' AND l.fechaVencimiento > CURRENT_DATE AND s.cantidad > 0 " +
           "ORDER BY l.fechaVencimiento ASC")
    List<StockInventario> findLotesFefoDisponibles(@Param("almacenId") UUID almacenId, @Param("productoId") UUID productoId);
}

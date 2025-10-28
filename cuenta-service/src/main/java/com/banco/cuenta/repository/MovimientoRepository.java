package com.banco.cuenta.repository;

import com.banco.cuenta.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
   @Query("SELECT m FROM Movimiento m WHERE m.cuenta.clienteNombre = :cliente AND m.fecha BETWEEN :inicio AND :fin")
   List<Movimiento> findByClienteAndFechaBetween(
         @Param("cliente") String cliente,
         @Param("inicio") LocalDateTime inicio,
         @Param("fin") LocalDateTime fin);

}

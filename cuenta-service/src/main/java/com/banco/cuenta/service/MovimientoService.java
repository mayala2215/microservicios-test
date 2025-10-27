package com.banco.cuenta.service;

import com.banco.cuenta.model.Movimiento;
import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoService {
    Movimiento registrarMovimiento(String numeroCuenta, String tipoMovimiento, Double valor);
    List<Movimiento> generarReporte(String clienteNombre, LocalDateTime desde, LocalDateTime hasta);
}

package com.banco.cuenta.service;

import com.banco.cuenta.model.Movimiento;
import com.banco.cuenta.model.dto.MovimientoResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoService {
    MovimientoResponseDTO registrarMovimiento(String numeroCuenta, String tipoMovimiento, Double valor);
    List<Movimiento> generarReporte(String clienteNombre, LocalDateTime desde, LocalDateTime hasta);
}

package com.banco.cuenta.service;

import java.time.LocalDate;
import java.util.List;

import com.banco.cuenta.model.dto.ReporteMovimientoDTO;

public interface ReporteService {
    List<ReporteMovimientoDTO> generarReporte(String numeroCuenta, LocalDate fechaInicio, LocalDate fechaFin);
}

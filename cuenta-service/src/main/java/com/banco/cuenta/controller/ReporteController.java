package com.banco.cuenta.controller;

import com.banco.cuenta.model.dto.ReporteMovimientoDTO;
import com.banco.cuenta.service.ReporteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    public ResponseEntity<List<ReporteMovimientoDTO>> generarReporteByNombreCuenta(
            @RequestParam String nombreCuenta,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        List<ReporteMovimientoDTO> reporte = reporteService.generarReporte(nombreCuenta, fechaInicio, fechaFin);
        return ResponseEntity.ok(reporte);
    }
}

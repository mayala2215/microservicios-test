package com.banco.cuenta.controller;

import com.banco.cuenta.model.Movimiento;
import com.banco.cuenta.service.MovimientoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {
    @Autowired
    private MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<Movimiento> registrar(@RequestParam String numeroCuenta,
            @RequestParam String tipoMovimiento,
            @RequestParam Double valor) {
        Movimiento mov = movimientoService.registrarMovimiento(numeroCuenta, tipoMovimiento, valor);
        return ResponseEntity.ok(mov);
    }

    @GetMapping("/reporte")
    public ResponseEntity<List<Movimiento>> reporte(
            @RequestParam String cliente,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta) {
        return ResponseEntity.ok(movimientoService.generarReporte(cliente, desde, hasta));
    }
}

package com.banco.cuenta.controller;

import com.banco.cuenta.model.Cuenta;
import com.banco.cuenta.repository.CuentaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    @Autowired
    private CuentaRepository cuentaRepository;

    @GetMapping
    public List<Cuenta> listarCuentas() {
        return cuentaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta) {
        return ResponseEntity.ok(cuentaRepository.save(cuenta));
    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<Cuenta> obtenerCuentaByCuenta(@PathVariable String numeroCuenta) {
        return ResponseEntity.ok(cuentaRepository.findByNumeroCuenta(numeroCuenta));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Cuenta> obtenerCuentaPorNombre(@PathVariable String nombre) {
        Cuenta cuenta = cuentaRepository.findByClienteNombre(nombre);
        if (cuenta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cuenta);
    }

}

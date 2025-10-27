package com.banco.cuenta.service.imp;

import com.banco.cuenta.model.Cuenta;
import com.banco.cuenta.model.Movimiento;
import com.banco.cuenta.repository.CuentaRepository;
import com.banco.cuenta.repository.MovimientoRepository;
import com.banco.cuenta.service.MovimientoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimientoServiceImpl implements MovimientoService {
    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Override
    public Movimiento registrarMovimiento(String numeroCuenta, String tipoMovimiento, Double valor) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta);
        if (cuenta == null) {
            throw new RuntimeException("Cuenta no encontrada");
        }

        Double saldoActual = cuenta.getSaldoInicial();

        if (tipoMovimiento.equalsIgnoreCase("Retiro") && saldoActual < valor) {
            throw new RuntimeException("Saldo insuficiente");
        }

        Double nuevoSaldo = tipoMovimiento.equalsIgnoreCase("Depósito")
                ? saldoActual + valor
                : saldoActual - valor;

        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setTipoMovimiento(tipoMovimiento);
        movimiento.setValor(valor);
        movimiento.setSaldo(saldoActual);
        movimiento.setSaldoDisponible(nuevoSaldo);

        cuenta.setSaldoInicial(nuevoSaldo);
        // cuentaRepository.save(cuenta);

        return movimientoRepository.save(movimiento);
    }

    @Override
    public List<Movimiento> generarReporte(String clienteNombre, LocalDateTime desde, LocalDateTime hasta) {
        return movimientoRepository.findByCuenta_NumeroCuentaAndFechaBetween(clienteNombre, desde, hasta);
    }
}

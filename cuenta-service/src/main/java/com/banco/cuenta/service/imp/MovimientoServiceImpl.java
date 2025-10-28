package com.banco.cuenta.service.imp;

import com.banco.cuenta.exception.CustomException;
import com.banco.cuenta.model.Cuenta;
import com.banco.cuenta.model.Movimiento;
import com.banco.cuenta.model.dto.MovimientoResponseDTO;
import com.banco.cuenta.repository.CuentaRepository;
import com.banco.cuenta.repository.MovimientoRepository;
import com.banco.cuenta.service.MovimientoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class MovimientoServiceImpl implements MovimientoService {
    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Override
    public MovimientoResponseDTO registrarMovimiento(String numeroCuenta, String tipoMovimiento, Double valor) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta);
        if (cuenta == null) {
            throw new CustomException("Cuenta no encontrada", HttpStatus.NOT_FOUND);
        }

        Double saldoActual = cuenta.getSaldoInicial();

        List<String> retiros = Arrays.asList("Retiro", "Retiro cajero", "Retiro automático", "Ret");
        if (retiros.stream().anyMatch(r -> tipoMovimiento.equalsIgnoreCase(r)) && saldoActual < valor) {
            throw new CustomException("Saldo no disponible", HttpStatus.BAD_REQUEST);
        }

        List<String> depositos = Arrays.asList("Deposito", "Depósito", "Ingreso");
        Double nuevoSaldo = depositos.contains(tipoMovimiento)
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
        cuentaRepository.save(cuenta);
        movimientoRepository.save(movimiento);

        return new MovimientoResponseDTO(
                movimiento.getFecha().toLocalDate().toString(),
                cuenta.getClienteNombre(),
                cuenta.getNumeroCuenta(),
                cuenta.getTipoCuenta(),
                saldoActual,
                cuenta.getEstado(),
                valor,
                nuevoSaldo);
    }

    @Override
    public List<Movimiento> generarReporte(String clienteNombre, LocalDateTime desde, LocalDateTime hasta) {
        return movimientoRepository.findByClienteAndFechaBetween(clienteNombre, desde, hasta);
    }

}

package com.banco.cuenta.service.imp;

import com.banco.cuenta.model.Cuenta;
import com.banco.cuenta.repository.CuentaRepository;
import com.banco.cuenta.service.CuentaService;
import com.banco.cuenta.exception.CustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public Cuenta crearCuenta(Cuenta cuenta) {
        if (cuentaRepository.findByNumeroCuenta(cuenta.getNumeroCuenta()) != null) {
            throw new CustomException("Ya existe una cuenta con número " + cuenta.getNumeroCuenta(),
                    HttpStatus.BAD_REQUEST);
        }
        return cuentaRepository.save(cuenta);
    }

    @Override
    public Cuenta obtenerCuenta(String numeroCuenta) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta);
        if (cuenta == null) {
            throw new CustomException("Cuenta no encontrada con número: " + numeroCuenta, HttpStatus.NOT_FOUND);
        }
        return cuenta;
    }

    @Override
    public List<Cuenta> listarCuentas() {
        return cuentaRepository.findAll();
    }

    @Override
    public Cuenta actualizarCuenta(String numeroCuenta, Cuenta cuentaActualizada) {
        Cuenta existente = cuentaRepository.findByNumeroCuenta(numeroCuenta);
        if (existente == null) {
            throw new CustomException("No se puede actualizar. Cuenta no encontrada con número: " + numeroCuenta,
                    HttpStatus.NOT_FOUND);
        }

        existente.setTipoCuenta(cuentaActualizada.getTipoCuenta());
        existente.setSaldoInicial(cuentaActualizada.getSaldoInicial());
        existente.setEstado(cuentaActualizada.getEstado());
        existente.setClienteNombre(cuentaActualizada.getClienteNombre());

        return cuentaRepository.save(existente);
    }

    @Override
    public void eliminarCuenta(String numeroCuenta) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta);
        if (cuenta == null) {
            throw new CustomException("No se puede eliminar. Cuenta no encontrada con número: " + numeroCuenta,
                    HttpStatus.NOT_FOUND);
        }
        cuentaRepository.delete(cuenta);
    }

    @Override
    public Cuenta findByClienteNombre(String nombre) {
        Cuenta cuenta = cuentaRepository.findByClienteNombre(nombre);
        if (cuenta == null) {
            throw new CustomException("No se encontró cuenta para el cliente: " + nombre, HttpStatus.NOT_FOUND);
        }
        return cuenta;
    }

    @Override
    public Cuenta findById(Long idCliente) {
        Cuenta cuenta = cuentaRepository.findByIdCliente(idCliente);
        if (cuenta == null) {
            throw new CustomException("No se encontró cuenta para el cliente con id: " + idCliente,
                    HttpStatus.NOT_FOUND);
        }
        return cuenta;
    }
}

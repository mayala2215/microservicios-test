package com.banco.cuenta.service;

import java.util.List;

import com.banco.cuenta.model.Cuenta;

public interface CuentaService {
    Cuenta crearCuenta(Cuenta cuenta);

    Cuenta obtenerCuenta(String numeroCuenta);

    List<Cuenta> listarCuentas();

    Cuenta actualizarCuenta(String numeroCuenta, Cuenta cuenta);

    void eliminarCuenta(String numeroCuenta);

    List<Cuenta> findByClienteNombre(String nombre);

    Cuenta findById(Long idCliente);

}

package com.banco.cuenta.repository;

import com.banco.cuenta.model.Cuenta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, String> {
    Cuenta findByNumeroCuenta(String numeroCuenta);

    Cuenta findByIdCliente(Long idCliente);

    List<Cuenta> findByClienteNombre(String clienteNombre);
}

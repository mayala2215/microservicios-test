package com.banco.cuenta.service;

import com.banco.cuenta.exception.CustomException;
import com.banco.cuenta.model.Cuenta;
import com.banco.cuenta.model.Movimiento;
import com.banco.cuenta.model.dto.MovimientoResponseDTO;
import com.banco.cuenta.repository.CuentaRepository;
import com.banco.cuenta.repository.MovimientoRepository;
import com.banco.cuenta.service.imp.MovimientoServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MovimientoServiceTest {

    @Mock
    private CuentaRepository cuentaRepository;

    @Mock
    private MovimientoRepository movimientoRepository;

    @InjectMocks
    private MovimientoServiceImpl movimientoService;

    private Cuenta cuenta;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cuenta = new Cuenta();
        cuenta.setNumeroCuenta("12345");
        cuenta.setSaldoInicial(500.0);
        cuenta.setEstado(true);
        cuenta.setTipoCuenta("Ahorros");
        cuenta.setClienteNombre("Moises Ayala");
    }

    @Test
    void testRegistrarDeposito() {
        when(cuentaRepository.findByNumeroCuenta("12345")).thenReturn(cuenta);
        when(movimientoRepository.save(any(Movimiento.class))).thenAnswer(i -> i.getArguments()[0]);

        MovimientoResponseDTO movimiento = movimientoService.registrarMovimiento("12345", "Deposito", 100.0);

        assertEquals(600.0, movimiento.getSaldoDisponible());
        verify(movimientoRepository, times(1)).save(any(Movimiento.class));
    }

    @Test
    void testSaldoInsuficienteDebeLanzarExcepcion() {
        when(cuentaRepository.findByNumeroCuenta("12345")).thenReturn(cuenta);

        CustomException exception = assertThrows(CustomException.class,
                () -> movimientoService.registrarMovimiento("12345", "Retiro", 800.0));

        assertEquals("Saldo no disponible", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }
}

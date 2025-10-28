package com.banco.cuenta.service;

import com.banco.cuenta.model.Cuenta;
import com.banco.cuenta.model.Movimiento;
import com.banco.cuenta.model.dto.ReporteMovimientoDTO;
import com.banco.cuenta.repository.MovimientoRepository;
import com.banco.cuenta.service.imp.ReporteServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReporteServiceTest {

    @Mock
    private MovimientoRepository movimientoRepository;

    @InjectMocks
    private ReporteServiceImpl reporteService;

    private Cuenta cuenta;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        cuenta = new Cuenta();
        cuenta.setNumeroCuenta("111");
        cuenta.setClienteNombre("Marianela Montalvo");
        cuenta.setTipoCuenta("Corriente");
        cuenta.setEstado(true);
    }

    @Test
    void testGenerarReporte() {
        Movimiento mov = new Movimiento();
        mov.setCuenta(cuenta);
        mov.setFecha(LocalDateTime.now());
        mov.setTipoMovimiento("Deposito");
        mov.setSaldo(100.0);
        mov.setSaldoDisponible(200.0);
        mov.setValor(100.0);

        when(movimientoRepository.findByClienteAndFechaBetween(anyString(), any(), any()))
                .thenReturn(List.of(mov));

        List<ReporteMovimientoDTO> reporte = reporteService.generarReporte(
                "Marianela Montalvo", LocalDate.now().minusDays(1), LocalDate.now());

        assertEquals(1, reporte.size());
        assertEquals("Marianela Montalvo", reporte.get(0).getCliente());
        verify(movimientoRepository, times(1))
                .findByClienteAndFechaBetween(anyString(), any(), any());
    }
}

package com.banco.cuenta.service.imp;

import com.banco.cuenta.model.Movimiento;
import com.banco.cuenta.model.dto.ReporteMovimientoDTO;
import com.banco.cuenta.repository.MovimientoRepository;
import com.banco.cuenta.service.ReporteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Override
    public List<com.banco.cuenta.model.dto.ReporteMovimientoDTO> generarReporte(String cliente, LocalDate fechaInicio,
            LocalDate fechaFin) {

        LocalDateTime inicio = fechaInicio.atStartOfDay();
        LocalDateTime fin = fechaFin.plusDays(1).atStartOfDay();

        List<Movimiento> movimientos = movimientoRepository.findByClienteAndFechaBetween(
                cliente, inicio, fin);

        return movimientos.stream().map(mov -> {
            ReporteMovimientoDTO dto = new ReporteMovimientoDTO();
            dto.setFecha(mov.getFecha().toLocalDate());
            dto.setCliente(mov.getCuenta().getClienteNombre());
            dto.setNumeroCuenta(mov.getCuenta().getNumeroCuenta());
            dto.setTipo(mov.getTipoMovimiento());
            dto.setSaldoInicial(mov.getSaldo());
            dto.setEstado(mov.getCuenta().getEstado());
            dto.setMovimiento(mov.getValor());
            dto.setSaldoDisponible(mov.getSaldoDisponible());
            return dto;
        }).collect(Collectors.toList());
    }
}
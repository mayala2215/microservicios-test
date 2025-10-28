package com.banco.cuenta.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@Table(name = "movimientos")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;
    private String tipoMovimiento; // "Depósito" o "Retiro"
    private Double valor;
    private Double saldoDisponible;

    @ManyToOne
    @JoinColumn(name = "numero_cuenta")
    @JsonIgnore
    private Cuenta cuenta;

    @Column(name = "saldo")
    private Double saldo;
}

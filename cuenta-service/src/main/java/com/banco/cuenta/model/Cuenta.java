package com.banco.cuenta.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Getter
@Setter
@Table(name = "cuentas")
public class Cuenta {

    @Id
    @Column(name = "numero_cuenta", nullable = false, length = 50, unique = true)
    private String numeroCuenta;
    private Long idCliente;
    @Column(name = "tipo_cuenta", length = 20, nullable = false)
    private String tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;
    private String clienteNombre;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Movimiento> movimientos;
}

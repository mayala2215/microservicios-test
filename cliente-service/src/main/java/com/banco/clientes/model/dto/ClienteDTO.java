package com.banco.clientes.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClienteDTO implements Serializable {
    private String evento;
    private Long id;
    private String nombre;
    private Boolean estado;
}

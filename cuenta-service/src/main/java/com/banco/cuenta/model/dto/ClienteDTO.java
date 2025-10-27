package com.banco.cuenta.model.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO implements Serializable {
    private String evento;
    private Long id;
    private String nombre;
}
package com.example.crud.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGetTarjeta {
	private String pan;
	private String titular;
	private String cedula;
	private String tipo;
	private int telefono;
	private int numvalidacion;
	private String estado;
}

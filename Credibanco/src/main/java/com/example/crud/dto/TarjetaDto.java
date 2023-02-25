package com.example.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarjetaDto {

	private Long pan;
	private String titular;
	private String cedula;
	private String tipo;
	private int telefono;
}

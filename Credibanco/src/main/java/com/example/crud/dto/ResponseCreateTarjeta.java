package com.example.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCreateTarjeta {

	public String codigo;
	public String mensaje;
	public int num_validacion;
	public String pan;
}

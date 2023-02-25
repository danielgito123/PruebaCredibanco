package com.example.crud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseCreateTransaccion {

	public String codigo;
	public String mensaje;
	public String estado;
	public int numReferencia;
}

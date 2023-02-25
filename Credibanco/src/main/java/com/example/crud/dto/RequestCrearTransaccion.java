package com.example.crud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCrearTransaccion {

	public Long pan;
	public int numReferencia;
	public Double totalCompra;
	public String dir_compra;
}

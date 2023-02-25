package com.example.crud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnularTransaccion {
	public Long pan;
	public int numReferencia;
	public Double total_compra;
}

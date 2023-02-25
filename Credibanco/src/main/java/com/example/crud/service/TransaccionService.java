package com.example.crud.service;

import java.util.List;

import com.example.crud.dto.AnularTransaccion;
import com.example.crud.model.Trasaccion;

public interface TransaccionService {
	
	Trasaccion createTransaccion(Trasaccion trasaccion);
	
	Trasaccion anularTransaccion(AnularTransaccion anularTransaccion);
	
	List<Trasaccion> getAllTransacciones();

}

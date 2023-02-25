package com.example.crud.service;

import java.util.List;

import com.example.crud.dto.RequestEnrolarTarjeta;
import com.example.crud.dto.ResponseGetTarjeta;
import com.example.crud.model.Tarjeta;

public interface TarjetaService {

	Tarjeta createTarjeta(Tarjeta tarjeta);
	
	Tarjeta getTarjetaByPan(Long pan);
	
	List<Tarjeta> getAllTarjetas();
	
	Tarjeta enrolarTarjeta(RequestEnrolarTarjeta requestEnrolarTarjeta);
	
	void deleteTarjeta(Long pan);
}

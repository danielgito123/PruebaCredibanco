package com.example.crud.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.crud.dto.RequestEnrolarTarjeta;
import com.example.crud.dto.ResponseCreateTarjeta;
import com.example.crud.dto.ResponseGetTarjeta;
import com.example.crud.model.Tarjeta;
import com.example.crud.repository.TarjetaRepository;
import com.example.crud.service.TarjetaService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TarjetaServiceImpl implements TarjetaService {

	private TarjetaRepository tarjetaRepository;
	
	public Tarjeta createTarjeta(Tarjeta tarjeta) {
		return tarjetaRepository.save(tarjeta);
	} 
	
	

	public Tarjeta getTarjetaByPan(Long pan) {
		Tarjeta optionalTarjeta = tarjetaRepository.findByPan(pan);
		return optionalTarjeta;
	}
	
	public List<Tarjeta> getAllTarjetas() {
		return tarjetaRepository.findAll();
	}

	public Tarjeta enrolarTarjeta(RequestEnrolarTarjeta requestEnrolarTarjeta) {
		Tarjeta validatePanNum = tarjetaRepository.findByPanAndNumvalidacion(requestEnrolarTarjeta.getPan(), requestEnrolarTarjeta.getNumvalidacion());
		
		validatePanNum.setEstado("Enrolada");
		
		Tarjeta updateTarjeta = tarjetaRepository.save(validatePanNum);
		return updateTarjeta;
	}

	public void deleteTarjeta(Long id) {
		tarjetaRepository.deleteById(id);		
	}
	
}

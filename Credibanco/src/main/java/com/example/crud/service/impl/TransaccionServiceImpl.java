package com.example.crud.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.crud.dto.AnularTransaccion;
import com.example.crud.model.Trasaccion;
import com.example.crud.repository.TarjetaRepository;
import com.example.crud.repository.TransaccionRepository;
import com.example.crud.service.TransaccionService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransaccionServiceImpl implements TransaccionService{

	private TransaccionRepository transaccionRepository;
	
	public Trasaccion createTransaccion(Trasaccion trasaccion) {
		return transaccionRepository.save(trasaccion);
	}
	
	public Trasaccion anularTransaccion(AnularTransaccion anularTransaccion) {
		
		Trasaccion trasaccion = transaccionRepository.findByPanAndNumreferencia(anularTransaccion.getPan(), anularTransaccion.getNumReferencia());
		
		trasaccion.setEstado("Anulada");
		
		Trasaccion updateTransaccion = transaccionRepository.save(trasaccion);
		
		return updateTransaccion;
	}

	public List<Trasaccion> getAllTransacciones() {
		return transaccionRepository.findAll();
	}
}

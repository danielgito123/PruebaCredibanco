package com.example.crud.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.dto.*;
import com.example.crud.model.Tarjeta;
import com.example.crud.model.Trasaccion;
import com.example.crud.repository.TarjetaRepository;
import com.example.crud.repository.TransaccionRepository;
import com.example.crud.service.TarjetaService;
import com.example.crud.service.TransaccionService;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping("api/transaccion")
public class TransaccionController {

	private TransaccionService transaccionService;
	private TransaccionRepository transaccionRepository;
	private TarjetaRepository tarjetaRepository;
	
	@GetMapping
	public ResponseEntity<Trasaccion> getAllTransaciones(){
		List<Trasaccion> transacciones = transaccionService.getAllTransacciones();
		return new ResponseEntity(transacciones, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ResponseCreateTransaccion> enrolarTarjeta(@RequestBody RequestCrearTransaccion crearTransaccion){
		
		LocalDateTime datetime = LocalDateTime.now();
		ResponseCreateTransaccion responseCreateTransaccion = new ResponseCreateTransaccion();
		Trasaccion trasaccion = new Trasaccion();
		
		Tarjeta tarjeta = tarjetaRepository.findByPan(crearTransaccion.getPan());
		if(tarjeta == null) {
			responseCreateTransaccion.setCodigo("01");
			responseCreateTransaccion.setMensaje("Tarjeta no existe");
			
			return new ResponseEntity(responseCreateTransaccion, HttpStatus.BAD_REQUEST);
		}
		if(tarjeta.getEstado().equals("Creada")) {
			responseCreateTransaccion.setCodigo("02");
			responseCreateTransaccion.setMensaje("Tarjeta no enrolada");
			
			return new ResponseEntity(responseCreateTransaccion, HttpStatus.BAD_REQUEST);
		}
		
		trasaccion.setPan(crearTransaccion.getPan());
		trasaccion.setNumreferencia(crearTransaccion.getNumReferencia());
		trasaccion.setTotal_compra(crearTransaccion.getTotalCompra());
		trasaccion.setDir_compra(crearTransaccion.getDir_compra());
		trasaccion.setEstado("Aprobada");
		trasaccion.setFecha(datetime);
		
		try {
			Trasaccion saveTransaccion = transaccionService.createTransaccion(trasaccion);
			
			responseCreateTransaccion.setCodigo("00");
			responseCreateTransaccion.setMensaje("Compra exitosa");
			responseCreateTransaccion.setEstado("Aprobada");
			responseCreateTransaccion.setNumReferencia(saveTransaccion.getNumreferencia());
			
			return new ResponseEntity(responseCreateTransaccion, HttpStatus.CREATED);
		} catch (Exception e) {
			responseCreateTransaccion.setCodigo("03");
			responseCreateTransaccion.setMensaje("Error al crear transaccion");
			
			return new ResponseEntity(responseCreateTransaccion, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/anular-transaccion")
	public ResponseEntity<ResponseAnularTransaccion> anularTransaccion(@RequestBody AnularTransaccion anularTransaccion){
		
		ResponseAnularTransaccion responseAnularTransaccion = new ResponseAnularTransaccion();
		Trasaccion trasaccion = transaccionRepository.findByPanAndNumreferencia(anularTransaccion.getPan(), anularTransaccion.getNumReferencia());
		
		LocalDateTime datetime = LocalDateTime.now();
		
		if(trasaccion == null) {
			responseAnularTransaccion.setCodigo("01");
			responseAnularTransaccion.setMensaje("Numero de referencia inválido");
			return new ResponseEntity(responseAnularTransaccion, HttpStatus.BAD_REQUEST);
		}
		
		int horamin1 = (trasaccion.getFecha().getHour() * 60) + trasaccion.getFecha().getMinute();
		int horamin2 = (datetime.getHour() * 60) + datetime.getMinute();
		if((horamin2 - horamin1) > 15) {
			responseAnularTransaccion.setCodigo("02");
			responseAnularTransaccion.setMensaje("No se puede anular transacción");
			return new ResponseEntity(responseAnularTransaccion, HttpStatus.BAD_REQUEST);
		}
		Trasaccion saveTrasaccion = transaccionService.anularTransaccion(anularTransaccion);
		
		responseAnularTransaccion.setCodigo("00");
		responseAnularTransaccion.setMensaje("Compra anulada");
		responseAnularTransaccion.setNum_validacion(saveTrasaccion.getNumreferencia());
		return new ResponseEntity(responseAnularTransaccion, HttpStatus.CREATED);
	}
}

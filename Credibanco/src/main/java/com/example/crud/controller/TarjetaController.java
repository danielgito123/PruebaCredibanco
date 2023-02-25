package com.example.crud.controller;

import java.util.List;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.crud.dto.RequestEnrolarTarjeta;
import com.example.crud.dto.ResponseCreateTarjeta;
import com.example.crud.dto.ResponseDeleteTarjeta;
import com.example.crud.dto.ResponseEnrolarTarjeta;
import com.example.crud.dto.ResponseGetTarjeta;
import com.example.crud.dto.TarjetaDto;
import com.example.crud.model.Tarjeta;
import com.example.crud.repository.TarjetaRepository;
import com.example.crud.service.TarjetaService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping("api/tarjeta")
public class TarjetaController {

	private TarjetaService tarjetaService;
	private TarjetaRepository tarjetaRepository;
	
	@GetMapping
	public ResponseEntity<List<Tarjeta>> getAllTarjetas(){
		
		List<Tarjeta> tarjetas = tarjetaService.getAllTarjetas();
		
		return new ResponseEntity(tarjetas, HttpStatus.OK);
	}
	
	@PostMapping 
	public ResponseEntity<ResponseCreateTarjeta> createTarjeta(@RequestBody TarjetaDto tarjetaDto){
		
		ResponseCreateTarjeta responseCreateTarjeta = new ResponseCreateTarjeta();
		Random rdn = new Random();
		Tarjeta tarjeta = new Tarjeta();
		
		
		tarjeta.setPan(tarjetaDto.getPan());
		tarjeta.setTitular(tarjetaDto.getTitular());
		tarjeta.setCedula(tarjetaDto.getCedula());
		tarjeta.setTipo(tarjetaDto.getTipo());;
		tarjeta.setTelefono(tarjetaDto.getTelefono());
		tarjeta.setNumvalidacion(rdn.nextInt(1, 100));
		tarjeta.setEstado("Creada");		
		
		try {
			Tarjeta saveTarjeta = tarjetaService.createTarjeta(tarjeta);
			String pan = tarjeta.getPan().toString();
			int sizePan = pan.length();
			
			String panResponse = pan.substring(0, 6) + "****" + pan.substring(sizePan - 4, sizePan);
			
			responseCreateTarjeta.setCodigo("00");
			responseCreateTarjeta.setMensaje("Éxito");
			responseCreateTarjeta.setNum_validacion(saveTarjeta.getNumvalidacion());
			responseCreateTarjeta.setPan(panResponse);
			
			return new ResponseEntity(responseCreateTarjeta, HttpStatus.CREATED);
			
			
		} catch (Exception e) {
			responseCreateTarjeta.setCodigo("01");
			responseCreateTarjeta.setMensaje("Fallido");
			
			return new ResponseEntity(responseCreateTarjeta, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/enrolar")
	public ResponseEntity<ResponseEnrolarTarjeta> enrolarTarjeta(@RequestBody RequestEnrolarTarjeta requestEnrolarTarjeta){
		ResponseEnrolarTarjeta responseEnrolarTarjeta = new ResponseEnrolarTarjeta();
		
		Tarjeta validatePan = tarjetaRepository.findByPan(requestEnrolarTarjeta.getPan());
		if(validatePan == null){
			responseEnrolarTarjeta.setCodigo("01");
			responseEnrolarTarjeta.setMensaje("Tarjeta no existe");
			return new ResponseEntity(responseEnrolarTarjeta, HttpStatus.NOT_FOUND);
		}
		
		Tarjeta validatePanNum = tarjetaRepository.findByPanAndNumvalidacion(requestEnrolarTarjeta.getPan(), requestEnrolarTarjeta.getNumvalidacion());
		if(validatePanNum == null){
			responseEnrolarTarjeta.setCodigo("02");
			responseEnrolarTarjeta.setMensaje("Número de validación inválido");
			return new ResponseEntity(responseEnrolarTarjeta, HttpStatus.NOT_FOUND);
		}
		
		Tarjeta updateTarjeta = tarjetaService.enrolarTarjeta(requestEnrolarTarjeta);
		
		String pan = updateTarjeta.getPan().toString();
		int sizePan = pan.length();
		
		String panResponse = pan.substring(0, 6) + "****" + pan.substring(sizePan - 4, sizePan);
		
		responseEnrolarTarjeta.setCodigo("00");
		responseEnrolarTarjeta.setMensaje("Éxito");
		responseEnrolarTarjeta.setPan(panResponse);
		
		return new ResponseEntity(responseEnrolarTarjeta, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("{pan}")
	public ResponseEntity<ResponseGetTarjeta> getTarjetaByPan(@PathVariable("pan") Long pan){
		Tarjeta tarjeta = tarjetaService.getTarjetaByPan(pan);
		
		ResponseGetTarjeta responseGetTarjeta = new ResponseGetTarjeta();
		
		responseGetTarjeta.setPan(tarjeta.getPan().toString());
		responseGetTarjeta.setTitular(tarjeta.getTitular());
		responseGetTarjeta.setCedula(tarjeta.getCedula());
		responseGetTarjeta.setTipo(tarjeta.getTipo());
		responseGetTarjeta.setTelefono(tarjeta.getTelefono());
		responseGetTarjeta.setNumvalidacion(tarjeta.getNumvalidacion());
		responseGetTarjeta.setEstado(tarjeta.getEstado());
		
		return new ResponseEntity(responseGetTarjeta, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("{pan}")
	public ResponseEntity<ResponseDeleteTarjeta> deleteTarjeta(@PathVariable("pan") Long pan){
		ResponseDeleteTarjeta responseDeleteTarjeta = new ResponseDeleteTarjeta();
		
		Tarjeta idTarjeta = tarjetaRepository.findByPan(pan);
		if(idTarjeta == null) {
			responseDeleteTarjeta.setCodigo("01");
			responseDeleteTarjeta.setMensaje("No se ha eliminado la tarjeta");
			
			return new ResponseEntity(responseDeleteTarjeta, HttpStatus.ACCEPTED);
		}
		
		tarjetaService.deleteTarjeta(idTarjeta.getId());	
		
		responseDeleteTarjeta.setCodigo("00");
		responseDeleteTarjeta.setMensaje("Se ha eliminado la tarjeta");
		
		return new ResponseEntity(responseDeleteTarjeta, HttpStatus.ACCEPTED);
	}
}

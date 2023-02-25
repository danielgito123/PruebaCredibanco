package com.example.crud.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.crud.dto.ResponseGetTarjeta;
import com.example.crud.model.Tarjeta;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Long>{

	Tarjeta findByPan(Long pan);
	
	Tarjeta findByPanAndNumvalidacion(Long pan, int num);
	
	void deleteByPan(Long pan);
	
}

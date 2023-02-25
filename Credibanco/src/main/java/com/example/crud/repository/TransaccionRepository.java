package com.example.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.model.Trasaccion;

public interface TransaccionRepository extends JpaRepository<Trasaccion, Long>{

	Trasaccion findByPanAndNumreferencia(Long pan, int numReferencia);
}

package com.example.crud.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tarjetas")
public class Tarjeta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private Long pan;
	@Column(nullable = false)
	private String titular;
	@Column(nullable = false)
	private String cedula;
	@Column(nullable = false)
	private String tipo;
	@Column(nullable = false)
	private int telefono;
	@Column(nullable = false)
	private int numvalidacion;
	@Column(nullable = false)
	private String estado;
	
	@OneToMany(mappedBy = "tarjeta")
	List<Trasaccion> transacciones;
	
}

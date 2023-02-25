package com.example.crud.model;

import java.time.LocalDateTime;

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
@Table(name = "transaccion", uniqueConstraints = {
		   @UniqueConstraint(name = "UniquePanAndNumreferencia", columnNames = {"pan", "numreferencia"})})
public class Trasaccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Long pan;
	@Column(nullable = false)
	private int numreferencia;
	@Column(nullable = false)
	private Double total_compra;
	@Column(nullable = false)
	private String dir_compra;
	@Column(nullable = false)
	private String estado;
	@Column(nullable = false)
	private LocalDateTime fecha;
	
	@ManyToOne
	private Tarjeta tarjeta;
	
}

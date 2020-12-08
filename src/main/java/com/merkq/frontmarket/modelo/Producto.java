package com.merkq.frontmarket.modelo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Producto {

	
	private int id;
	private String nombre;
	private String descripcion;
	private int proveedor;
	
}

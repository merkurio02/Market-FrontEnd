package com.merkq.frontmarket.modelo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Proveedor {

	
	private int id;
	private String nombre;
	private String contacto;
	private String cargo;
	private String direccion;
	private int telefono;

}

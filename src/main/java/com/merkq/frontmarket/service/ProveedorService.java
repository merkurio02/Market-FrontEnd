package com.merkq.frontmarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.merkq.frontmarket.modelo.Proveedor;



public interface ProveedorService {
	
	
	public List<Proveedor> findAll();
	public List<Proveedor> findByNombreContaining(String nombre);
	public Proveedor findById(int id);
	public void save(Proveedor proveedor);
	public void update(Proveedor proveedor);
	public void delete(Proveedor proveedor);
	public void deleteById(int id);

	
	
}

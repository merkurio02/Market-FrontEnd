package com.merkq.frontmarket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.merkq.frontmarket.modelo.Proveedor;
import com.merkq.frontmarket.service.ProveedorService;
@Service
public class ProveedorServiceImpl implements ProveedorService{

	@Autowired
	RestTemplate template;
	
	String uri="http://localhost:9000//api/v1/proveedores";
	
	
	@Override
	public List<Proveedor> findAll() {
		// TODO Auto-generated method stub
		ResponseEntity<List<Proveedor>> respuesta = template.exchange(uri,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Proveedor>>(){});
		return respuesta.getBody();
	}

	@Override
	public List<Proveedor> findByNombreContaining(String nombre) {
		
		// TODO Auto-generated method stub
		ResponseEntity<List<Proveedor>> respuesta = template.exchange(uri+"/buscar/"+nombre,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Proveedor>>(){});
		return respuesta.getBody();
	}

	@Override
	public Proveedor findById(int id) {
		// TODO Auto-generated method stub
		return template.getForObject(uri+"/"+id,Proveedor.class);
	}

	@Override
	public void save(Proveedor proveedor) {

	template.postForEntity(uri, proveedor, String.class);
		
	}

	@Override
	public void update(Proveedor proveedor) {
		
		template.exchange(uri,HttpMethod.PUT, new HttpEntity<Proveedor>(proveedor),Void.class);		
	}

	

	@Override
	public void delete(Proveedor proveedor) {
		template.delete(uri, proveedor);
		
	}

	@Override
	public void deleteById(int id) {
		template.delete(uri+"/"+id);
		
	}

}

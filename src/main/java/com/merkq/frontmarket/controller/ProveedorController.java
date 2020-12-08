package com.merkq.frontmarket.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.merkq.frontmarket.modelo.Proveedor;
import com.merkq.frontmarket.service.ProveedorService;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

	@Autowired
	ProveedorService service ;
	String mensaje = null;
	Proveedor objectProv = new Proveedor();
	
	
	@GetMapping
	public ModelAndView listar(Model model) {
		List<Proveedor> proveedores =service.findAll();
		if(mensaje==null) {
			mensaje="Se encontraron "+proveedores.size()+" registros.";
		}
		model.addAttribute("mensaje",mensaje);
		mensaje=null;
		model.addAttribute("proveedores",proveedores);
		model.addAttribute("proveedor", objectProv.getNombre()==null?new Proveedor():objectProv);
		return new ModelAndView("inicio");
	}
	
	@PostMapping("/save")
	public ModelAndView agregar(@ModelAttribute Proveedor prov, Model model){
		service.save(prov);
		List<Proveedor> proveedores =service.findAll();
		if(proveedores.stream().map(x->x.getNombre()).collect(Collectors.toList()).contains(prov.getNombre()))
			{
			mensaje="Proveedor guardado correctamente.";
			objectProv= new Proveedor();
		}
		else
			{
			mensaje="Error al guardar proveedor";
			objectProv=prov;
			}
		return new ModelAndView("redirect:/proveedores");
	}
	
	@PostMapping("/update")
	public ModelAndView actualizar(@ModelAttribute Proveedor prov, Model model) {
		System.out.println(prov);
		service.update(prov);
		List<Proveedor> proveedores = service.findAll();
		System.out.println(proveedores);
		
		int index=-1;
		
		for (Proveedor proveedor : proveedores) {
			if(proveedor.getId()==prov.getId()) {
				index=proveedores.indexOf(proveedor);
			}
		}
		if(index != -1 && proveedores.get(index).getId()==prov.getId() 
			&& proveedores.get(index).getNombre().equals(prov.getNombre())
			&& proveedores.get(index).getCargo().equals(prov.getCargo()) 
			&& proveedores.get(index).getContacto().equals(prov.getContacto())
			&& proveedores.get(index).getDireccion().equals(prov.getDireccion()))
			{
				mensaje="Proveedor modificado correctamente.";
				objectProv= new Proveedor();
			}
			else
				{
				mensaje="Error al modificar proveedor";
				objectProv=prov;
				
				} 
			return new ModelAndView("redirect:/proveedores");
	}
	
	@GetMapping("/{id}")
	public ModelAndView buscarPorId(@PathVariable int id, Model model) {
		Proveedor proveedor = service.findById(id);
		List<Proveedor> proveedores = new ArrayList<Proveedor>();
		
		if(proveedor.getNombre()==null) {
			mensaje="No se ha encontrado el proveedor buscado";
			return new ModelAndView("redirect:/proveedor");
		}else {
			mensaje ="Proveedor encontrado";
			proveedores.add(proveedor);
			model.addAttribute("proveedores", proveedores);
			model.addAttribute("mensaje", mensaje );
			model.addAttribute("proveedor",new Proveedor());
			return new ModelAndView("inicio");
		}
	}
	@GetMapping("/search")
	public ModelAndView buscar(@PathParam(value = "nombre") String nombre,Model model) {
		List<Proveedor> proveedores = service.findByNombreContaining(nombre);
		mensaje ="Se han encontrado "+proveedores.size()+" registros.";
		
		model.addAttribute("proveedores", proveedores);
		model.addAttribute("mensaje", mensaje );
		model.addAttribute("proveedor",new Proveedor());
		return new ModelAndView("inicio");
	}
	
	@PostMapping("/delete")
	public ModelAndView eliminar(@RequestParam("id") int id) {
		
		service.deleteById(id);
		
		if(service.findById(id).getNombre()==null)
			mensaje="Proveedor eliminado correctamente";
		else
			mensaje="Error al eliminar el proveedor";
		return new ModelAndView("redirect:/proveedores");
	}
}

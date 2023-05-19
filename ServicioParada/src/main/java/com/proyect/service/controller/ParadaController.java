package com.proyect.service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.service.entity.Parada;
import com.proyect.service.service.ParadaService;

@RestController
@RequestMapping("/parada")
public class ParadaController {

	@Autowired
	private ParadaService service;
	
	@GetMapping("/")
	public String saludar() {
		return "Miaow";
	}
	
	@GetMapping("/all")
	public List<Parada> findAll(){
		return service.getAll();
	}
	
	@GetMapping("/find/{nombre}")
	public Optional<Parada> findById(@PathVariable("nombre") String ubicacion){
		Optional<Parada> parada = service.getUsuarioByNombre(ubicacion);
		if(parada != null) {
			return parada; 
		}else {
			return Optional.empty();
		}
	}
	
	@PostMapping("/save")
	public String save(@RequestBody Parada parada) {
		if(service.save(parada)) {
			return "guardado";
		}else {
			return "exciste";
		}
	}
	
	@DeleteMapping("/delete/{nombre}")
	public String delete(@PathVariable("nombre") String ubicacion) {
		if(service.delete(ubicacion)) {
			return "eliminado";
		}else {
			return "no existe";
		}
	}
	
	@PostMapping("/update")
	public String update(@RequestBody Parada parada) {
		
		if(service.update(parada)) {
			return "actualizado";
		}else {
			return "no exciste";
		}
	}
}

package com.proyect.service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.service.entity.Usuario;
import com.proyect.service.models.Parada;
import com.proyect.service.models.PuntoRecarga;
import com.proyect.service.models.Ruta;
import com.proyect.service.service.UsuarioService;

@RestController
@RequestMapping("/user")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@GetMapping("/")
	public String saludar() {
		return "Miaow";
	}
	
	@GetMapping("/all")
	public List<Usuario> findAll(){
		return service.getAll();
	}
	
	@GetMapping("/find/{nombre}")
	public Optional<Usuario> findById(@PathVariable("nombre") String nombre){
		Optional<Usuario> user = service.getUsuarioByNombre(nombre);
		if(user != null) {
			return user; 
		}else {
			return Optional.empty();
		}
	}
	
	@PostMapping("/save")
	public String save(@RequestBody Usuario user) {
		if(service.save(user)) {
			return "guardado";
		}else {
			return "exciste";
		}
	}
	
	@DeleteMapping("/delete/{nombre}")
	public String delete(@PathVariable("nombre") String nombre) {
		if(service.delete(nombre)) {
			return "eliminado";
		}else {
			return "no existe";
		}
	}
	
	@PostMapping("/update")
	public String update(@RequestBody Usuario user) {
		
		if(service.update(user)) {
			return "actualizado";
		}else {
			return "no exciste";
		}
	}
	
	@GetMapping("/pr")
	public ResponseEntity<List<PuntoRecarga>> getPuntosRecarga(){
		List<PuntoRecarga> prs = service.getPuntosRecarga();
		return ResponseEntity.ok(prs);
	}
	
	@GetMapping("/parada")
	public ResponseEntity<List<Parada>> getParadas(){
		List<Parada> parada = service.getParadas();
		return ResponseEntity.ok(parada);
	}
	
	@GetMapping("/ruta")
	public ResponseEntity<List<Ruta>> getRuta(){
		List<Ruta> ruta = service.getRutas();
		return ResponseEntity.ok(ruta);
	}
}

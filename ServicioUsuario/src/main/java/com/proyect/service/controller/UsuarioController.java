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
	
	//LISTA USUARIOS
	@GetMapping("/all")
	public List<Usuario> findAll(){
		return service.getAll();
	}
	
	//BUSCAR USUARIO
	@GetMapping("/find/{nombre}")
	public Optional<Usuario> findById(@PathVariable("nombre") String nombre){
		Optional<Usuario> user = service.getUsuarioByNombre(nombre);
		if(user != null) {
			return user; 
		}else {
			return Optional.empty();
		}
	}
	
	//NUEVO USUARIO
	@PostMapping("/save")
	public String save(@RequestBody Usuario user) {
		if(service.save(user)) {
			return "guardado";
		}else {
			return "exciste";
		}
	}
	
	//ELIMINAR USUARIO
	@DeleteMapping("/delete/{nombre}")
	public String delete(@PathVariable("nombre") String nombre) {
		if(service.delete(nombre)) {
			return "eliminado";
		}else {
			return "no existe";
		}
	}
	
	//ACTUALIZAR USUARIO
	@PostMapping("/update")
	public String update(@RequestBody Usuario user) {
		
		if(service.update(user)) {
			return "actualizado";
		}else {
			return "no exciste";
		}
	}
	
	//LOGIN
	@GetMapping("/login")
	public Optional<Usuario> login(@RequestBody Usuario user) {
		Optional<Usuario> luser = service.login(user.getCorreo(), user.getContraseña());
		
		if(luser != null) {
			return luser;
		}else {
			return null;
		}
	}
	
	//NUEVA PARADA
	@PostMapping("/saveparada")
	public String saveParada(@RequestBody Parada parada) {
		String nuevo = service.saveParada(parada);
		return nuevo;
	}
	
	//ACTUALIZAR PARADA
	@PostMapping("/updateparada")
	public String updateParada(@RequestBody Parada parada) {
		String nuevo = service.updateParada(parada);
		return nuevo;
	}
	
	//LISTA DE PARADAS
	@GetMapping("/paradas")
	public List<Parada> findAllParadas(){
		List<Parada> paradas = service.findAllParada();
		return paradas;
	}
	
	//BUSCAR PARADA
	@GetMapping("/paradas/{ubicacion}")
	public Optional<Parada> findParada(@PathVariable("ubicacion") String ubicacion){
		Optional<Parada> parada = service.findParada(ubicacion);
		return parada;
	}
	
	//ELIMINAR PARADA
	@DeleteMapping("/deleteparada/{ubicacion}")
	public String deleteParada(@PathVariable("ubicacion") String ubicacion){
		String eliminar = service.deleteParada(ubicacion);
		return eliminar;
	}
	
	//NUEVO PUNTO
	@PostMapping("/savepunto")
	public String savePunto(@RequestBody PuntoRecarga pr) {
		String nuevo = service.savePunto(pr);
		return nuevo;
	}
	
	//ACTUALIZAR PUNTO
	@PostMapping("/updatepunto")
	public String updatePunto(@RequestBody PuntoRecarga pr) {
		String nuevo = service.updatePunto(pr);
		return nuevo;
	}
	
	//LISTA DE PUNTO
	@GetMapping("/puntos")
	public List<PuntoRecarga> findAllPunto(){
		List<PuntoRecarga> pr = service.findAllPunto();
		return pr;
	}
	
	//BUSCAR PUNTO
	@GetMapping("/punto/{ubicacion}")
	public Optional<PuntoRecarga> findPunto(@PathVariable("ubicacion") String ubicacion){
		Optional<PuntoRecarga> punto = service.findPunto(ubicacion);
		return punto;
	}
	
	//ELIMINAR PUNTO
	@DeleteMapping("/deletepunto/{ubicacion}")
	public String deletePunto(@PathVariable("ubicacion") String ubicacion){
		String eliminar = service.deletePunto(ubicacion);
		return eliminar;
	}
	
	//NUEVO RUTA
	@PostMapping("/saveruta")
	public String saveRuta(@RequestBody Ruta pr) {
		String nuevo = service.saveRuta(pr);
		return nuevo;
	}
	
	//ACTUALIZAR RUTA
	@PostMapping("/updateruta")
	public String updateRuta(@RequestBody Ruta pr) {
		String nuevo = service.updateRuta(pr);
		return nuevo;
	}
	
	//LISTA DE RUTAS
	@GetMapping("/rutas")
	public List<Ruta> findAllRuta(){
		List<Ruta> ruta = service.findAllRuta();
		return ruta;
	}
	
	//BUSCAR RUTA
	@GetMapping("/ruta/{ubicacion}")
	public Optional<Ruta> findRuta(@PathVariable("ubicacion") String ubicacion){
		Optional<Ruta> ruta = service.findRuta(ubicacion);
		return ruta;
	}
	
	//ELIMINAR RUTA
	@DeleteMapping("/deleteruta/{ubicacion}")
	public String deleteRuta(@PathVariable("ubicacion") String ubicacion){
		String eliminar = service.deleteRuta(ubicacion);
		return eliminar;
	}
}

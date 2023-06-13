package com.proyect.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.proyect.service.entity.Usuario;
import com.proyect.service.feignclient.ParadaFeignClient;
import com.proyect.service.feignclient.PuntoRecargaFeignClient;
import com.proyect.service.feignclient.RutaFeignClient;
import com.proyect.service.models.Parada;
import com.proyect.service.models.PuntoRecarga;
import com.proyect.service.models.Ruta;
import com.proyect.service.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private RestTemplate rest;
	
	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private ParadaFeignClient feipa;
	
	@Autowired
	private PuntoRecargaFeignClient feipr;
	
	@Autowired
	private RutaFeignClient feiru;
	
	/*public List<PuntoRecarga> getPuntosRecarga(){
		List<PuntoRecarga> prs = rest.getForObject("http://localhost:9992/punto/all", List.class);
		return prs;
	}
	
	public List<Parada> getParadas(){
		List<Parada> paradas = rest.getForObject("http://localhost:9993/parada/all", List.class);
		return paradas;
	}
	
	public List<Ruta> getRutas(){
		List<Ruta> rutas = rest.getForObject("http://localhost:9994/ruta/all", List.class);
		return rutas;
	}*/
	
	//PARADA---------------------------------------------------------
	//
	//NUEVA PARADA
	public String saveParada(Parada parada) {
		String nuevo = feipa.save(parada);
		return nuevo;
	}
	
	//ACTUALIZAR PARADA
	public String updateParada(Parada parada) {
		String nuevo = feipa.update(parada);
		return nuevo;
	}
	
	//LISTA DE PARADAS
	public List<Parada> findAllParada(){
		List<Parada> paradas = feipa.findAll();
		return paradas;
	}
	
	//BUSCAR PARADA
	public Optional<Parada> findParada(String ubicacion){
		Optional<Parada> parada = feipa.findParada(ubicacion);
		return parada;
	}
	
	//ELIMINAR PARADA
	public String deleteParada(String nombre){
		String eliminar = feipa.delete(nombre);
		return eliminar;
	}
	
	//PUNTO DE RECARGA
	//----------------------------------------------------------------
	//NUEVO PUNTO
	public String savePunto(PuntoRecarga pr) {
		String nuevo = feipr.save(pr);
		return nuevo;
	}
	
	//ACTUALIZAR PUNTO
	public String updatePunto(PuntoRecarga pr) {
		String nuevo = feipr.update(pr);
		return nuevo;
	}
	
	//LISTA PUNTOS DE RECARGA
	public List<PuntoRecarga> findAllPunto(){
		List<PuntoRecarga> pr = feipr.findAll();
		return pr;
	}
	
	//BUSCAR PUNTO
	public Optional<PuntoRecarga> findPunto(String ubicacion){
		Optional<PuntoRecarga> punto = feipr.findPunto(ubicacion);
		return punto;
	}
	
	//ELIMINAR PUNTO
	public String deletePunto(String nombre){
		String eliminar = feipr.delete(nombre);
		return eliminar;
	}
	
	//RUTA
	//--------------------------------------------------------------------
	//NUEVO RUTA
	public String saveRuta(Ruta ruta) {
		String nuevo = feiru.save(ruta);
		return nuevo;
	}
	
	//ACTUALIZAR RUTA
	public String updateRuta(Ruta ruta) {
		String nuevo = feiru.update(ruta);
		return nuevo;
	}
	
	//LISTA RUTAS
	public List<Ruta> findAllRuta(){
		List<Ruta> pr = feiru.findAll();
		return pr;
	}
	
	//BUSCAR RUTA
	public Optional<Ruta> findRuta(String ubicacion){
		Optional<Ruta> punto = feiru.findRuta(ubicacion);
		return punto;
	}
	
	//ELIMINAR RUTA
	public String deleteRuta(String nombre){
		String eliminar = feiru.delete(nombre);
		return eliminar;
	}
	
	//USUARIO
	//--------------------------------------------------------------------
	//LISTA USUARIOS
	public List<Usuario> getAll(){
		return repo.findAll();
	}
	
	//BUSCAR USUARIO
	public Optional<Usuario> getUsuarioByNombre(String correo){
		Optional<Usuario> user = java.util.Optional.empty();
		
		for(Usuario item :repo.findAll()) {
			if(item.getCorreo().equals(correo)) {
				user = repo.findById(item.getId());
			}
		}
		
		return user;	
	}
	
	//NUEVO USUARIO
	public boolean save(Usuario user) {
		Optional<Usuario> vuser = getUsuarioByNombre(user.getCorreo());
		
		if(vuser.equals(Optional.empty())){
			repo.save(user);
			return true;  
		}else {
			return false;
		}
	}
	
	//ELIMINAR USUARIO
	public boolean delete(String nombre) {
		
		Optional<Usuario> user = getUsuarioByNombre(nombre);
		
		if(!user.equals(Optional.empty())){
			repo.deleteById(user.get().getId());
			return true;
		}else {
			return false;
		}
	}
	
	//ACTUALIZAR USUARIO
	public boolean update(Usuario user) {

		Optional<Usuario> vuser = getUsuarioByNombre(user.getCorreo());
		
		if(!vuser.equals(Optional.empty())){
			repo.save(user);
			return true;
		}else {
			return false;
		}
	}
	
	//LOGIN
	public Optional<Usuario> login(String correo, String contraseña) {
		Optional<Usuario> user = java.util.Optional.empty();
		
		for(Usuario item :repo.findAll()) {
			if(item.getCorreo().equals(correo) && item.getContraseña().equals(contraseña)) {
				user = repo.findById(item.getId());
			}
		}
		
		return user;
	}
	
}

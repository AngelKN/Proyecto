package com.proyect.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.proyect.service.entity.Usuario;
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
	
	public List<PuntoRecarga> getPuntosRecarga(){
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
	}
	
	public List<Usuario> getAll(){
		return repo.findAll();
	}
	
	public Optional<Usuario> getUsuarioByNombre(String nombre){
		Optional<Usuario> user = java.util.Optional.empty();
		
		for(Usuario item :repo.findAll()) {
			if(item.getNombre().equals(nombre)) {
				user = repo.findById(item.getId());
			}
		}
		
		return user;	
	}
	
	public boolean save(Usuario user) {
		Optional<Usuario> vuser = getUsuarioByNombre(user.getNombre());
		
		if(vuser.equals(Optional.empty())){
			repo.save(user);
			return true;  
		}else {
			return false;
		}
	}
	
	public boolean delete(String nombre) {
		
		Optional<Usuario> user = getUsuarioByNombre(nombre);
		
		if(!user.equals(Optional.empty())){
			repo.deleteById(user.get().getId());
			return true;
		}else {
			return false;
		}
	}
	
	public boolean update(Usuario user) {

		Optional<Usuario> vuser = getUsuarioByNombre(user.getNombre());
		
		if(!vuser.equals(Optional.empty())){
			repo.save(user);
			return true;
		}else {
			return false;
		}
	}
	
}

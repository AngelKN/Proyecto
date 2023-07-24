package com.proyect.service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.service.entity.Cupo;
import com.proyect.service.repository.CupoRepository;

@Service
public class CupoService {

	@Autowired
	private CupoRepository repo;
	
	public List<Cupo> getAll(){
		return repo.findAll();
	}
	
	public Optional<Cupo> getCupoByUbicacion(String precio){
		Optional<Cupo> cupo = java.util.Optional.empty();
		
		for(Cupo item :repo.findAll()) {
			if(item.getPrecio().equals(precio)) {
				cupo = repo.findById(item.getId());
			}
		}
		
		return cupo;	
	}
	
	public Optional<Cupo> getCupoById(String id){
		Optional<Cupo> cupo = repo.findById(id);
				
		return cupo;	
	}
	
	public boolean save(Cupo parada) {
		//Optional<Cupo> vparada = getUsuarioByUbicacion(parada.getUbicacion());
		
			repo.save(parada);
			return true; 
	}
	
	public boolean delete(String id) {
		
		Optional<Cupo> cupo = getCupoById(id);
		
		if(!cupo.equals(Optional.empty())){
			repo.deleteById(cupo.get().getId());
			return true;
		}else {
			return false;
		}
	}
	
	public boolean update(Cupo cupo) {

		Optional<Cupo> vparada = getCupoById(cupo.getId());
		
		if(!vparada.equals(Optional.empty())){
			repo.save(cupo);
			return true;
		}else {
			return false;
		}
	}
	
	//CUPOS POR ID_USER
	public List<Cupo> paradasRuta(String id_user){
		List<Cupo> cupo = new ArrayList<Cupo>();
		Cupo par = new Cupo();
		
		for(Cupo item :repo.findAll()) {
			if(item.getId_user().equals(id_user)) {
				par.setId(item.getId());
				par.setPrecio(item.getPrecio());
				par.setHora_llegada(item.getHora_llegada());
				par.setHora_salida(item.getHora_salida());
				par.setDescripcion(item.getDescripcion());
				cupo.add(par);
			}
		}
		
		return cupo;
	}
}
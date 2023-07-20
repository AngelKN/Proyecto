package com.proyect.service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.service.entity.Parada;
import com.proyect.service.repository.ParadaRepository;

@Service
public class ParadaService {

	@Autowired
	private ParadaRepository repo;
	
	public List<Parada> getAll(){
		return repo.findAll();
	}
	
	public Optional<Parada> getUsuarioByUbicacion(String ubicacion){
		Optional<Parada> parada = java.util.Optional.empty();
		
		for(Parada item :repo.findAll()) {
			if(item.getUbicacion().equals(ubicacion)) {
				parada = repo.findById(item.getId());
			}
		}
		
		return parada;	
	}
	
	public Optional<Parada> getUsuarioById(String id){
		Optional<Parada> parada = repo.findById(id);
		
		/*for(Ruta item :repo.findAll()) {
			if(item.getNombre().equals(nombre)) {
				ruta = repo.findById(item.getId());
			}
		}*/
		
		return parada;	
	}
	
	public boolean save(Parada parada) {
		Optional<Parada> vparada = getUsuarioByUbicacion(parada.getUbicacion());
		
		if(vparada.equals(Optional.empty())){
			repo.save(parada);
			return true;  
		}else {
			return false;
		}
	}
	
	public boolean delete(String id) {
		
		Optional<Parada> parada = getUsuarioById(id);
		
		if(!parada.equals(Optional.empty())){
			repo.deleteById(parada.get().getId());
			return true;
		}else {
			return false;
		}
	}
	
	public boolean update(Parada parada) {

		Optional<Parada> vparada = getUsuarioById(parada.getId());
		
		if(!vparada.equals(Optional.empty())){
			repo.save(parada);
			return true;
		}else {
			return false;
		}
	}
	
	//PARADAS POR ID_RUTA
	public List<Parada> paradasRuta(String id_ruta){
		List<Parada> parada = new ArrayList<Parada>();
		Parada par = new Parada();
		
		for(Parada item :repo.findAll()) {
			if(item.getId_ruta().equals(id_ruta)) {
				par.setId(item.getId());
				par.setUbicacion(item.getUbicacion());
				par.setMapa(item.getMapa());
				par.setId_ruta(item.getId_ruta());
				parada.add(par);
			}
		}
		
		return parada;
	}
}

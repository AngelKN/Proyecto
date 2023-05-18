package com.proyect.service.service;

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
	
	public Optional<Parada> getUsuarioByNombre(String nombre){
		Optional<Parada> parada = java.util.Optional.empty();
		
		for(Parada item :repo.findAll()) {
			if(item.getUbicacion().equals(nombre)) {
				parada = repo.findById(item.getId());
			}
		}
		
		return parada;	
	}
	
	public boolean save(Parada parada) {
		Optional<Parada> vparada = getUsuarioByNombre(parada.getUbicacion());
		
		if(vparada.equals(Optional.empty())){
			repo.save(parada);
			return true;  
		}else {
			return false;
		}
	}
	
	public boolean delete(String nombre) {
		
		Optional<Parada> parada = getUsuarioByNombre(nombre);
		
		if(!parada.equals(Optional.empty())){
			repo.deleteById(parada.get().getId());
			return true;
		}else {
			return false;
		}
	}
	
	public boolean update(Parada parada) {

		Optional<Parada> vparada = getUsuarioByNombre(parada.getUbicacion());
		
		if(!vparada.equals(Optional.empty())){
			repo.save(parada);
			return true;
		}else {
			return false;
		}
	}
}

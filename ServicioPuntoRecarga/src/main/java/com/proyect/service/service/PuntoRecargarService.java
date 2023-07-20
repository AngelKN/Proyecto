package com.proyect.service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.service.entity.PuntoRecarga;
import com.proyect.service.repository.PuntoRecargarRepository;

@Service
public class PuntoRecargarService {

	@Autowired
	private PuntoRecargarRepository repo;
	
	public List<PuntoRecarga> getAll(){
		return repo.findAll();
	}
	
	public Optional<PuntoRecarga> getUsuarioByNombre(String nombre){
		Optional<PuntoRecarga> pr = java.util.Optional.empty();
		
		for(PuntoRecarga item :repo.findAll()) {
			if(item.getNombre().equals(nombre)) {
				pr = repo.findById(item.getId());
			}
		}
		
		return pr;	
	}
	
	public Optional<PuntoRecarga> getUsuarioById(String id){
		Optional<PuntoRecarga> punto = repo.findById(id);
		
		/*for(Ruta item :repo.findAll()) {
			if(item.getNombre().equals(nombre)) {
				ruta = repo.findById(item.getId());
			}
		}*/
		
		return punto;	
	}
	
	public boolean save(PuntoRecarga pr) {
		Optional<PuntoRecarga> vpr = getUsuarioByNombre(pr.getNombre());
		
		if(vpr.equals(Optional.empty())){
			repo.save(pr);
			return true;  
		}else {
			return false;
		}
	}
	
	public boolean delete(String nombre) {
		
		Optional<PuntoRecarga> pr = getUsuarioById(nombre);
		
		if(!pr.equals(Optional.empty())){
			repo.deleteById(pr.get().getId());
			return true;
		}else {
			return false;
		}
	}
	
	public boolean update(PuntoRecarga pr) {

		Optional<PuntoRecarga> vpr = getUsuarioById(pr.getId());
		
		if(!vpr.equals(Optional.empty())){
			repo.save(pr);
			return true;
		}else {
			return false;
		}
	}
}

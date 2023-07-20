package com.proyect.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.service.feignclient.ParadaFeignClient;
import com.proyect.service.models.Parada;

@Service
public class ParadaService {
	
	@Autowired
	private ParadaFeignClient fei;

	//PARADA---------------------------------------------------------
	//
	//NUEVA PARADA
	public String saveParada(Parada parada) {
		String nuevo = fei.save(parada);
		return nuevo;
	}
	
	//ACTUALIZAR PARADA
	public String updateParada(Parada parada) {
		String nuevo = fei.update(parada);
		return nuevo;
	}
	
	//LISTA DE PARADAS
	public List<Parada> findAllParada(){
		List<Parada> paradas = fei.findAll();
		return paradas;
	}
	
	//BUSCAR PARADA
	public Optional<Parada> findParada(String id){
		Optional<Parada> parada = fei.findParada(id);
		return parada;
	}
	
	//ELIMINAR PARADA
	public String deleteParada(String nombre){
		String eliminar = fei.delete(nombre);
		return eliminar;
	}
	

}

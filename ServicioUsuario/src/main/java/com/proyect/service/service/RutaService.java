package com.proyect.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.service.feignclient.RutaFeignClient;
import com.proyect.service.models.Ruta;

@Service
public class RutaService {


	@Autowired
	private RutaFeignClient fei;
	
	//RUTA
	//--------------------------------------------------------------------
	//NUEVO RUTA
	public String saveRuta(Ruta ruta) {
		String nuevo = fei.save(ruta);
		return nuevo;
	}
	
	//ACTUALIZAR RUTA
	public String updateRuta(Ruta ruta) {
		String nuevo = fei.update(ruta);
		return nuevo;
	}
	
	//LISTA RUTAS
	public List<Ruta> findAllRuta(){
		List<Ruta> pr = fei.findAll();
		return pr;
	}
	
	//BUSCAR RUTA
	public Optional<Ruta> findRuta(String id){
		Optional<Ruta> punto = fei.findRuta(id);
		return punto;
	}
	
	//ELIMINAR RUTA
	public String deleteRuta(String id){
		String eliminar = fei.delete(id);
		return eliminar;
	}
}

package com.proyect.service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.service.entity.Usuario;
import com.proyect.service.feignclient.ParadaFeignClient;
import com.proyect.service.feignclient.RutaFeignClient;
import com.proyect.service.feignclient.CupoFeignClient;
import com.proyect.service.models.Cupo;
import com.proyect.service.models.Parada;
import com.proyect.service.models.Ruta;
import com.proyect.service.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private ParadaFeignClient feipa;
	
	@Autowired
	private CupoFeignClient feic;
	
	@Autowired
	private RutaFeignClient feir;
	
	//USUARIO
	//--------------------------------------------------------------------
	//LISTA USUARIOS
	public List<Usuario> getAll(){
		return repo.findAll();
	}
	
	//BUSCAR USUARIO
	public Optional<Usuario> getUsuarioByNombre(String id){
		Optional<Usuario> user = java.util.Optional.empty();
		
		for(Usuario item :repo.findAll()) {
			if(item.getCorreo().equals(id)) {
				user = repo.findById(item.getId());
			}
		}
		
		return user;	
	}
	
	public Optional<Usuario> getUsuarioById(String id){
		Optional<Usuario> ruta = repo.findById(id);
		
		/*for(Ruta item :repo.findAll()) {
			if(item.getNombre().equals(nombre)) {
				ruta = repo.findById(item.getId());
			}
		}*/
		
		return ruta;	
	}
	
	//NUEVO USUARIO
	public boolean save(Usuario user) {
		Optional<Usuario> vuser = getUsuarioById(user.getId());
		
		if(vuser.equals(Optional.empty())){
			repo.save(user);
			return true;  
		}else {
			return false;
		}
	}
	
	//ELIMINAR USUARIO
	public boolean delete(String id) {
		
		Optional<Usuario> user = getUsuarioByNombre(id);
		
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
	
	//PARADAS POR RUTA
	public List<Parada> paradasRuta(String id){
		List<Parada> paradas = feipa.paradasRuta(id);
		return paradas;
	}
	
	//CUPOS POR USUARIO
	public List<Cupo> cupoUser(String id){
		List<Cupo> cupo = feic.cupoUser(id);
		return cupo;
	}
	
}

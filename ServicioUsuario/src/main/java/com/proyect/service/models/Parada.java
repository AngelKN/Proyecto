package com.proyect.service.models;

public class Parada {

	private String id;
	private String ubicacion;
	private String mapa;
	private String id_ruta;
	
	public Parada() {
		super();
	}
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getMapa() {
		return mapa;
	}
	public void setMapa(String mapa) {
		this.mapa = mapa;
	}


	public String getId_ruta() {
		return id_ruta;
	}


	public void setId_ruta(String id_ruta) {
		this.id_ruta = id_ruta;
	}
	
}

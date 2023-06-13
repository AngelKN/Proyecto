package com.proyect.service.models;

import java.util.List;

public class Ruta {

	private String id;
	private String nombre;
	private String tipo;
	private List<String> paradas;
	private List<String> puntorecarga;
	
	public Ruta() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<String> getParadas() {
		return paradas;
	}

	public void setParadas(List<String> paradas) {
		this.paradas = paradas;
	}

	public List<String> getPuntorecarga() {
		return puntorecarga;
	}

	public void setPuntorecarga(List<String> puntorecarga) {
		this.puntorecarga = puntorecarga;
	}
}

package model;

import dao.ProveedorDAO;
import dto.ProveedorDTO;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : @Almac�n
//  @ File Name : Proveedor.java
//  @ Date : @Sept2018
//  @ Author : @Grupo 1
//		Boujon Di Maio, Antonella.
//		Gomila, Tomas Guido.		
//		Graue, Florencia.
//		Retamar, Nicolas.
//
//




public class Proveedor {
	private Integer idProveedor;
	private String nombre;
	private String url;
	private String apiKey;
	private boolean estadoActivo;
	private Integer idComoSuCliente;
	
	public Proveedor () {}
	
	public Proveedor (ProveedorDTO dto) {
		idProveedor = dto.getIdProveedor();
		nombre = dto.getNombre();
		url = dto.getUrl();
		apiKey = dto.getApiKey();
		estadoActivo = dto.getEstadoActivo();
		idComoSuCliente = dto.getIdComoSuCliente();
	}
	
	
	public ProveedorDTO toDTO() {
		ProveedorDTO dto = new ProveedorDTO();
		dto.setIdProveedor(this.idProveedor);
		dto.setNombre(this.nombre);
		dto.setUrl(this.url);
		dto.setApiKey(this.apiKey);
		dto.setEstadoActivo(this.estadoActivo);
		dto.setIdComoSuCliente(this.idComoSuCliente);
		return dto;
	}
	
	public Proveedor save() {
		
		Proveedor prove = ProveedorDAO.getInstancia().save(this);
		
		return prove;
		
	}
	
	public boolean soyElProveedor(String url) {
		if(url.equals(this.url))
			return true;
		return false;
	}
	
	public void darDeBaja() {
		estadoActivo = false;
		save();
	}

	public Integer getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public boolean getEstadoActivo() {
		return estadoActivo;
	}

	public void setEstadoActivo(boolean estadoActivo) {
		this.estadoActivo = estadoActivo;
	}

	public Integer getIdComoSuCliente() {
		return idComoSuCliente;
	}

	public void setIdComoSuCliente(Integer idComoSuCliente) {
		this.idComoSuCliente = idComoSuCliente;
	}
	
	
	
	
	
	
}
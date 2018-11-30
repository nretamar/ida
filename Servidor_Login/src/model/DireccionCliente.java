package model;

import dto.DireccionClienteDTO;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : @Almac�n
//  @ File Name : DireccionCliente.java
//  @ Date : @Sept2018
//  @ Author : @Grupo 1
//		Boujon Di Maio, Antonella.
//		Gomila, Tomas Guido.		
//		Graue, Florencia.
//		Retamar, Nicolas.
//
//




public class DireccionCliente {
	private Integer idDireccionCliente;
	private String calle;
	private String numero;
	private String piso;
	private String unidad;
	private String entreCalles;
	private String provincia;
	private String localidad;
	private String codigoPostal;
	
	public DireccionCliente() {}
	
	public DireccionCliente (DireccionClienteDTO dto) {
		this.idDireccionCliente = dto.getIdDireccionCliente();
		this.calle = dto.getCalle();
		this.numero = dto.getNumero();
		this.piso = dto.getPiso();
		this.unidad = dto.getUnidad();
		this.entreCalles = dto.getEntreCalles();
		this.provincia = dto.getProvincia();
		this.localidad = dto.getLocalidad();
		this.codigoPostal = dto.getCodigoPostal();
	}
	
	
	public DireccionClienteDTO toDTO() {
		DireccionClienteDTO dto = new DireccionClienteDTO();
		dto.setIdDireccionCliente(this.idDireccionCliente);
		dto.setCalle(this.calle);
		dto.setNumero(this.numero);
		dto.setPiso(this.piso);
		dto.setUnidad(this.unidad);
		dto.setEntreCalles(this.entreCalles);
		dto.setProvincia(this.provincia);
		dto.setLocalidad(this.localidad);
		dto.setCodigoPostal(this.codigoPostal);
		return dto;
	}

	public Integer getIdDireccionCliente() {
		return idDireccionCliente;
	}

	public void setIdDireccionCliente(Integer idDireccionCliente) {
		this.idDireccionCliente = idDireccionCliente;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public String getEntreCalles() {
		return entreCalles;
	}

	public void setEntreCalles(String entreCalles) {
		this.entreCalles = entreCalles;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	
}
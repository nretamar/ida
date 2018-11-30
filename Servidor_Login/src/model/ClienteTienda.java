package model;

import dto.ClienteTiendaDTO;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : @Almac�n
//  @ File Name : ClienteTienda.java
//  @ Date : @Sept2018
//  @ Author : @Grupo 1
//		Boujon Di Maio, Antonella.
//		Gomila, Tomas Guido.		
//		Graue, Florencia.
//		Retamar, Nicolas.
//
//




public class ClienteTienda {
	private Integer idClienteTienda;
	private String cuil_cuit_dni;
	private String nombreYApellido_RazonSocial;
	private String email;
	
	public ClienteTienda() {
		super();
	}
	
	public ClienteTienda(ClienteTiendaDTO dto) {
		this();
		
		this.idClienteTienda = dto.getIdClienteTienda();
		this.cuil_cuit_dni = dto.getCuil_cuit_dni();
		this.nombreYApellido_RazonSocial = dto.getNombreYApellido_RazonSocial();
		this.email = dto.getEmail();		
		
	}
	
	public ClienteTiendaDTO toDTO() {
		ClienteTiendaDTO dto = new ClienteTiendaDTO();
		dto.setIdClienteTienda(this.idClienteTienda);
		dto.setCuil_cuit_dni(this.cuil_cuit_dni);
		dto.setNombreYApellido_RazonSocial(this.nombreYApellido_RazonSocial);
		dto.setEmail(this.email);
		return dto;
	}
	
		
	public Integer getIdClienteTienda() {
		return idClienteTienda;
	}

	public void setIdClienteTienda(Integer idClienteTienda) {
		this.idClienteTienda = idClienteTienda;
	}

	public String getCuil_cuit_dni() {
		return cuil_cuit_dni;
	}

	public void setCuil_cuit_dni(String cuil_cuit_dni) {
		this.cuil_cuit_dni = cuil_cuit_dni;
	}

	public String getNombreYApellido_RazonSocial() {
		return nombreYApellido_RazonSocial;
	}

	public void setNombreYApellido_RazonSocial(String nombreYApellido_RazonSocial) {
		this.nombreYApellido_RazonSocial = nombreYApellido_RazonSocial;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
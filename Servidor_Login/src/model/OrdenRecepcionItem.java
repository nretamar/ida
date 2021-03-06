package model;

import java.util.Date;

import dto.OrdenRecepcionItemDTO;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : @Almac�n
//  @ File Name : Producto.java
//  @ Date : @Sept2018
//  @ Author : @Grupo 1
//		Boujon Di Maio, Antonella.
//		Gomila, Tomas Guido.		
//		Graue, Florencia.
//		Retamar, Nicolas.
//
//




public class OrdenRecepcionItem {
	private Integer idOrdenRecepcionItem;
	private Date fecha;
	private int cantidad;
	
	public OrdenRecepcionItem () {}
	
	public OrdenRecepcionItem (OrdenRecepcionItemDTO dto) {
		this.idOrdenRecepcionItem = dto.getIdOrdenRecepcionItem();
		this.fecha = dto.getFecha();
		this.cantidad = dto.getCantidad();
	} 
	
	public OrdenRecepcionItemDTO toDTO() {
		OrdenRecepcionItemDTO dto = new OrdenRecepcionItemDTO();
		dto.setIdOrdenRecepcionItem(this.getIdOrdenRecepcionItem());
		dto.setFecha(this.getFecha());
		dto.setCantidad(this.getCantidad());
		return dto;
	}


	public Integer getIdOrdenRecepcionItem() {
		return idOrdenRecepcionItem;
	}


	public void setIdOrdenRecepcionItem(Integer idOrdenRecepcionItem) {
		this.idOrdenRecepcionItem = idOrdenRecepcionItem;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
}

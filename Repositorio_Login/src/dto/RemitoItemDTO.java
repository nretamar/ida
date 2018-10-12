package dto;

import java.io.Serializable;


public class RemitoItemDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3493817465039559950L;
	private Integer idRemitoItem;
	private ProductoDTO producto;
	private int cantidad;
	
	
	public RemitoItemDTO() {}
	
	
	public Integer getIdRemitoItem() {
		return idRemitoItem;
	}
	public void setIdRemitoItem(Integer idRemitoItem) {
		this.idRemitoItem = idRemitoItem;
	}
	public ProductoDTO getProducto() {
		return producto;
	}
	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}

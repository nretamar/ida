package entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import model.Producto;

@Entity
@Table(name ="remitoItem")
public class RemitoItemEntity {
	
	@Id
	private Integer idItemRecibo;
	
	private Producto producto;
	private int cantidad;
	public RemitoItemEntity(Integer idItemRecibo, Producto producto, int cantidad) {
		super();
		this.idItemRecibo = idItemRecibo;
		this.producto = producto;
		this.cantidad = cantidad;
	}
	public RemitoItemEntity() {
		super();
	}
	public Integer getIdItemRecibo() {
		return idItemRecibo;
	}
	public void setIdItemRecibo(Integer idItemRecibo) {
		this.idItemRecibo = idItemRecibo;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}

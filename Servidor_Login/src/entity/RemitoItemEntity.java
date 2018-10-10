package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import model.Producto;

@Entity
@Table(name ="remitoItems")
public class RemitoItemEntity {
	
	@Id
	private Integer idItemRecibo;
	
	@JoinColumn(name="id_Producto")
	private Producto producto;	
	
	private int cantidad;
	
	public RemitoItemEntity() {}
	
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

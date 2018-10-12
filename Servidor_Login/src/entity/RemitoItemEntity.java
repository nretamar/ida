package entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;


@Entity
@Table(name ="remitoItems")
public class RemitoItemEntity {
	
	@Id
	private Integer idRemitoItem;
	
	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name="id_Producto")
	@OrderBy("descripcion ASC")
	private ProductoEntity producto;	
	
	private int cantidad;
	
	public RemitoItemEntity() {}
	
	public Integer getIdRemitoItem() {
		return idRemitoItem;
	}
	public void setIdRemitoItem(Integer idRemitoItem) {
		this.idRemitoItem = idRemitoItem;
	}
	public ProductoEntity getProducto() {
		return producto;
	}
	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}

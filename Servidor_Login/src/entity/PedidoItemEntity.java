package entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "pedidoItems")
public class PedidoItemEntity {
	
	@Id
	private Integer idPedidoItem;
	
	@ManyToOne( cascade = CascadeType.ALL)
	@OrderBy("descripcion ASC")
	private ProductoEntity producto;
	private int cantidad;
	private int total;
	
	
	public Integer getIdPedidoItem() {
		return idPedidoItem;
	}

	public void setIdPedidoItem(Integer idPedidoItem) {
		this.idPedidoItem = idPedidoItem;
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

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
}

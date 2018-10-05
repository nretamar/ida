package dto;

import java.io.Serializable;


public class PedidoItemDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3025204539780634784L;
	
	private Integer idPedidoItem;
	
	private ProductoDTO producto;
	private int cantidad;
	
	//Constructor
	//Sin constructores >:V, no es necesario


	public Integer getIdPedidoItem() {
		return idPedidoItem;
	}

	public void setIdPedidoItem(Integer idPedidoItem) {
		this.idPedidoItem = idPedidoItem;
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

package integracionDto;

import java.io.Serializable;


public class IntegracionItemPedidoTiendaDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4887972208710641334L;
	
	private Integer idPedidoItem;
	private IntegracionProductoTiendaDTO producto;
	private int cantidad;
	
	
	
	
	//Constructor
	public IntegracionItemPedidoTiendaDTO() {
		super();
	}
	
	
	
	//Getters y setters
	public Integer getIdPedidoItem() {
		return idPedidoItem;
	}


	public void setIdPedidoItem(Integer idPedidoItem) {
		this.idPedidoItem = idPedidoItem;
	}


	public IntegracionProductoTiendaDTO getProducto() {
		return producto;
	}


	public void setProducto(IntegracionProductoTiendaDTO producto) {
		this.producto = producto;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}

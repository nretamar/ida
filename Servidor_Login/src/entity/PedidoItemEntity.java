package entity;

import javax.persistence.Id;

import model.Producto;

public class PedidoItemEntity {
	
	@Id
	private Integer idPedidoItem;
	private Producto producto;
	private int cantidad;
}

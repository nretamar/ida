package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdenDeCompraDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2470892162417206890L;
	private Integer idOrdenDeCompra;
	private ProductoDTO producto;
	private Date fechaEmitida;
	private boolean ordenActiva;
	private int cantidadOrdenada;
	private List<OrdenRecepcionItemDTO> recepcionesDelProducto;
	
	
	public OrdenDeCompraDTO() {
		recepcionesDelProducto = new ArrayList<OrdenRecepcionItemDTO>();
	}
	public Integer getIdOrdenDeCompra() {
		return idOrdenDeCompra;
	}
	public void setIdOrdenDeCompra(Integer idOrdenDeCompra) {
		this.idOrdenDeCompra = idOrdenDeCompra;
	}
	public ProductoDTO getProducto() {
		return producto;
	}
	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}
	public Date getFechaEmitida() {
		return fechaEmitida;
	}
	public void setFechaEmitida(Date fechaEmitida) {
		this.fechaEmitida = fechaEmitida;
	}
	public boolean getOrdenActiva() {
		return ordenActiva;
	}
	public void setOrdenActiva(boolean ordenActiva) {
		this.ordenActiva = ordenActiva;
	}
	public int getCantidadOrdenada() {
		return cantidadOrdenada;
	}
	public void setCantidadOrdenada(int cantidadOrdenada) {
		this.cantidadOrdenada = cantidadOrdenada;
	}
	public List<OrdenRecepcionItemDTO> getRecepcionesDelProducto() {
		return recepcionesDelProducto;
	}
	public void setRecepcionesDelProducto(List<OrdenRecepcionItemDTO> recepcionesDelProducto) {
		this.recepcionesDelProducto = recepcionesDelProducto;
	}
	
	
}

package dto;

import java.io.Serializable;
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
	private RemitoDTO remito;
	private List<OrdenRecepcionItemDTO> recepcionesDelProducto;
	
	public OrdenDeCompraDTO(Integer idOrdenDeCompra, ProductoDTO producto, Date fechaEmitida, boolean ordenActiva,
			int cantidadOrdenada, RemitoDTO remito, List<OrdenRecepcionItemDTO> recepcionesDelProducto) {
		super();
		this.idOrdenDeCompra = idOrdenDeCompra;
		this.producto = producto;
		this.fechaEmitida = fechaEmitida;
		this.ordenActiva = ordenActiva;
		this.cantidadOrdenada = cantidadOrdenada;
		this.remito = remito;
		this.recepcionesDelProducto = recepcionesDelProducto;
	}
	public OrdenDeCompraDTO() {
		super();
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
	public boolean isOrdenActiva() {
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
	public RemitoDTO getRemito() {
		return remito;
	}
	public void setRemito(RemitoDTO remito) {
		this.remito = remito;
	}
	public List<OrdenRecepcionItemDTO> getRecepcionesDelProducto() {
		return recepcionesDelProducto;
	}
	public void setRecepcionesDelProducto(List<OrdenRecepcionItemDTO> recepcionesDelProducto) {
		this.recepcionesDelProducto = recepcionesDelProducto;
	}
	
	
}

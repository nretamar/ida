package entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import model.Date;
import model.List;
import model.OrdenRecepcionItem;
import model.Producto;
import model.Remito;

@Entity
@Table(name ="ordenDeCompra")
public class OrdenDeCompraEntity {
	
	@Id
	private Integer idOrdenDeCompra;
	
	private Producto producto;
	private Date fechaEmitida;
	private boolean ordenActiva;
	private int cantidadOrdenada;
	private Remito remito;
	private List<OrdenRecepcionItem> recepcionesDelProducto;
	
	public OrdenDeCompraEntity(Integer idOrdenDeCompra, Producto producto, entity.Date fechaEmitida,
			boolean ordenActiva, int cantidadOrdenada, Remito remito,
			entity.List<OrdenRecepcionItem> recepcionesDelProducto) {
		super();
		this.idOrdenDeCompra = idOrdenDeCompra;
		this.producto = producto;
		this.fechaEmitida = fechaEmitida;
		this.ordenActiva = ordenActiva;
		this.cantidadOrdenada = cantidadOrdenada;
		this.remito = remito;
		this.recepcionesDelProducto = recepcionesDelProducto;
	}
	public OrdenDeCompraEntity() {
		super();
	}
	public Integer getIdOrdenDeCompra() {
		return idOrdenDeCompra;
	}
	public void setIdOrdenDeCompra(Integer idOrdenDeCompra) {
		this.idOrdenDeCompra = idOrdenDeCompra;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
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
	public Remito getRemito() {
		return remito;
	}
	public void setRemito(Remito remito) {
		this.remito = remito;
	}
	public List<OrdenRecepcionItem> getRecepcionesDelProducto() {
		return recepcionesDelProducto;
	}
	public void setRecepcionesDelProducto(List<OrdenRecepcionItem> recepcionesDelProducto) {
		this.recepcionesDelProducto = recepcionesDelProducto;
	}
	
	
}

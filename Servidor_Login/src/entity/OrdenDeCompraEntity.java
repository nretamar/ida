package entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import model.OrdenRecepcionItem;
import model.Producto;
import model.Remito;

@Entity
@Table(name ="ordenesDeCompra")
public class OrdenDeCompraEntity {
	
	@Id
	private Integer idOrdenDeCompra;
	
	@JoinColumn(name="id_Producto")
	private Producto producto;
	private Date fechaEmitida;
	private boolean ordenActiva;
	private int cantidadOrdenada;
	
	@JoinColumn(name="id_Remito")
	private Remito remito;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<OrdenRecepcionItem> recepcionesDelProducto;
	

	public OrdenDeCompraEntity() {
		this.recepcionesDelProducto = new ArrayList<OrdenRecepcionItem>();
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

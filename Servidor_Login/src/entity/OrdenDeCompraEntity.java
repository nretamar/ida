package entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


@Entity
@Table(name ="ordenesDeCompra")
public class OrdenDeCompraEntity {
	
	@Id
	private Integer idOrdenDeCompra;
	
	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name="id_Producto")
	@OrderBy("descripcion ASC")
	private ProductoEntity producto;
	private Date fechaEmitida;
	private boolean ordenActiva;
	private int cantidadOrdenada;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<OrdenRecepcionItemEntity> recepcionesDelProducto;
	

	public OrdenDeCompraEntity() {
		this.recepcionesDelProducto = new ArrayList<OrdenRecepcionItemEntity>();
	}
	public Integer getIdOrdenDeCompra() {
		return idOrdenDeCompra;
	}
	public void setIdOrdenDeCompra(Integer idOrdenDeCompra) {
		this.idOrdenDeCompra = idOrdenDeCompra;
	}
	public ProductoEntity getProducto() {
		return producto;
	}
	public void setProducto(ProductoEntity producto) {
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
	public List<OrdenRecepcionItemEntity> getRecepcionesDelProducto() {
		return recepcionesDelProducto;
	}
	public void setRecepcionesDelProducto(List<OrdenRecepcionItemEntity> recepcionesDelProducto) {
		this.recepcionesDelProducto = recepcionesDelProducto;
	}
	
	
}

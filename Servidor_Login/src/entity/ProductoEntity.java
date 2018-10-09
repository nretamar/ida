package entity;

import java.math.BigDecimal;

import javax.persistence.*;
//import javax.swing.ImageIcon;

@Entity
@Table(name="productos")
public class ProductoEntity {
	
	@Id
	@Column(name="id_Producto")
	private Integer idProducto;
	
	private String codigoBarras;
	private String descripcion;
	private BigDecimal precioVenta;
	private int cantFijaCompra;
	private int cantMinimaStock;
	private int stockActual;
	private boolean estadoActivo;
	
	public ProductoEntity(){}

	public ProductoEntity(Integer idProducto, String codigoBarras, String descripcion, BigDecimal precioVenta,
			int cantFijaCompra, int cantMinimaStock, int stockActual, boolean estadoActivo/*, ImageIcon foto*/) {
		super();
		this.idProducto = idProducto;
		this.codigoBarras = codigoBarras;
		this.descripcion = descripcion;
		this.precioVenta = precioVenta;
		this.cantFijaCompra = cantFijaCompra;
		this.cantMinimaStock = cantMinimaStock;
		this.stockActual = stockActual;
		this.estadoActivo = estadoActivo;
		//this.foto = foto;
	}

	public ProductoEntity(String codigoBarras, String descripcion, BigDecimal precioVenta, int cantFijaCompra,
			int cantMinimaStock, int stockActual, boolean estadoActivo/*, ImageIcon foto*/) {
		super();
		this.codigoBarras = codigoBarras;
		this.descripcion = descripcion;
		this.precioVenta = precioVenta;
		this.cantFijaCompra = cantFijaCompra;
		this.cantMinimaStock = cantMinimaStock;
		this.stockActual = stockActual;
		this.estadoActivo = estadoActivo;
		//this.foto = foto;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public int getCantFijaCompra() {
		return cantFijaCompra;
	}

	public void setCantFijaCompra(int cantFijaCompra) {
		this.cantFijaCompra = cantFijaCompra;
	}

	public int getCantMinimaStock() {
		return cantMinimaStock;
	}

	public void setCantMinimaStock(int cantMinimaStock) {
		this.cantMinimaStock = cantMinimaStock;
	}

	public int getStockActual() {
		return stockActual;
	}

	public void setStockActual(int stockActual) {
		this.stockActual = stockActual;
	}

	public boolean getEstadoActivo() {
		return estadoActivo;
	}

	public void setEstadoActivo(boolean estadoActivo) {
		this.estadoActivo = estadoActivo;
	}

	/*public ImageIcon getFoto() {
		return foto;
	}

	public void setFoto(ImageIcon foto) {
		this.foto = foto;
	}*/
	
	
	
}

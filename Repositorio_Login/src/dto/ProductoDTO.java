package dto;

import java.io.Serializable;
import java.math.BigDecimal;

//import javax.swing.ImageIcon;

public class ProductoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1078524224595687594L;
	private Integer idProducto;
	
	private String codigoBarras;
	private String descripcion;
	private BigDecimal precioVenta;
	private int cantFijaCompra;
	private int cantMinimaStock;
	private int stockActual;
	private boolean estadoActivo;
	private String fotoUrl;
	private boolean fragil;
	private ProveedorDTO proveedor;
	private Integer idProductoDelProveedor;
	
	//Constructor
	public ProductoDTO(Integer idProducto, String codigoBarras, String descripcion, BigDecimal precioVenta,
			int cantFijaCompra, int cantMinimaStock, int stockActual, boolean estadoActivo, String fotoUrl, 
			boolean fragil, ProveedorDTO proveedor, Integer idProductoDelProveedor) {
		super();
		this.idProducto = idProducto;
		this.codigoBarras = codigoBarras;
		this.descripcion = descripcion;
		this.precioVenta = precioVenta;
		this.cantFijaCompra = cantFijaCompra;
		this.cantMinimaStock = cantMinimaStock;
		this.stockActual = stockActual;
		this.estadoActivo = estadoActivo;
		this.fotoUrl = fotoUrl;
		this.fragil = fragil;
		this.proveedor = proveedor;
		this.idProductoDelProveedor = idProductoDelProveedor;
		//this.foto = foto;
	}	

	public ProductoDTO() {
		super();
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

	public String getFotoUrl() {
		return fotoUrl;
	}

	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}

	public boolean getFragil() {
		return fragil;
	}

	public void setFragil(boolean fragil) {
		this.fragil = fragil;
	}

	public ProveedorDTO getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorDTO proveedor) {
		this.proveedor = proveedor;
	}

	public Integer getIdProductoDelProveedor() {
		return idProductoDelProveedor;
	}

	public void setIdProductoDelProveedor(Integer idProductoDelProveedor) {
		this.idProductoDelProveedor = idProductoDelProveedor;
	}
	
	
	
}

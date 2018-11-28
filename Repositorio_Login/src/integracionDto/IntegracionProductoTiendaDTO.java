package integracionDto;

import java.io.Serializable;
import java.math.BigDecimal;


public class IntegracionProductoTiendaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7428397716552810680L;
	
	private Integer idProducto;
	private String codigoBarras;
	private String descripcion;
	private BigDecimal precioVenta;
	private int stockActual;
	private String fotoUrl;
	private boolean fragil;
	
	
	
	
	//Constructor
	public IntegracionProductoTiendaDTO() {
		super();
	}
	
	//Getters y setters
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
	public int getStockActual() {
		return stockActual;
	}
	public void setStockActual(int stockActual) {
		this.stockActual = stockActual;
	}
	public String getFotoUrl() {
		return fotoUrl;
	}
	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}
	public boolean isFragil() {
		return fragil;
	}
	public void setFragil(boolean fragil) {
		this.fragil = fragil;
	}
	
	
}

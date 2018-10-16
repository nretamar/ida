package dto;

import java.io.Serializable;

public class RecibidoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4692503014231222079L;
	
	private Integer idProducto;
	private String codigoBarras;
	private String descripcion;
	private int cantidad;
	
	public RecibidoDTO () {}
	
	
	
	
	
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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
}

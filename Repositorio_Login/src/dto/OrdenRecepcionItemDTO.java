package dto;

import java.io.Serializable;
import java.util.Date;

public class OrdenRecepcionItemDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8853111679134476959L;
	private Integer idOrdenRecepcionItem;
	private Date fecha;
	private int cantidad;
	
	
	public OrdenRecepcionItemDTO() {}
	
	
	public Integer getIdOrdenRecepcionItem() {
		return idOrdenRecepcionItem;
	}
	public void setIdOrdenRecepcionItem(Integer idOrdenRecepcionItem) {
		this.idOrdenRecepcionItem = idOrdenRecepcionItem;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}

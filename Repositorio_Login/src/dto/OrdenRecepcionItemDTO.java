package dto;

import java.io.Serializable;
import java.util.Date;

public class OrdenRecepcionItemDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8853111679134476959L;
	private int idOrdenRecepcionItem;
	private Date fecha;
	private int cantidad;
	
	
	public OrdenRecepcionItemDTO() {}
	
	
	public int getIdOrdenRecepcionItem() {
		return idOrdenRecepcionItem;
	}
	public void setIdOrdenRecepcionItem(int idOrdenRecepcionItem) {
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

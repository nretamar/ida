package entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import model.Date;

@Entity
@Table(name="ordenRecepcionItem")
public class OrdenRecepcionItemEntity {
	
	@Id
	private int idOrdenRecepcionItem;
	
	private Date fecha;
	private int cantidad;
	public OrdenRecepcionItemEntity(int idOrdenRecepcionItem, entity.Date fecha, int cantidad) {
		super();
		this.idOrdenRecepcionItem = idOrdenRecepcionItem;
		this.fecha = fecha;
		this.cantidad = cantidad;
	}
	public OrdenRecepcionItemEntity() {
		super();
	}
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
package entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="ordenRecepcionItems")
public class OrdenRecepcionItemEntity {
	
	@Id
	private int idOrdenRecepcionItem;
	
	private Date fecha;
	private int cantidad;

	public OrdenRecepcionItemEntity() {}
	
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

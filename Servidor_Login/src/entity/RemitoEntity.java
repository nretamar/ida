package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

import model.RemitoItem;

@Entity
@Table(name ="remitos")
public class RemitoEntity {
	
	@Id 
	private Integer idRemito;
	
	private Date fecha;
	private List<RemitoItem> productosRecibidos;
	
	public RemitoEntity() {}
	
	public RemitoEntity(Integer idRemito, Date fecha, List<RemitoItem> productosRecibidos) {
		super();
		this.idRemito = idRemito;
		this.fecha = fecha;
		this.productosRecibidos = productosRecibidos;
	}

	public Integer getIdRemito() {
		return idRemito;
	}

	public void setIdRemito(Integer idRemito) {
		this.idRemito = idRemito;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<RemitoItem> getProductosRecibidos() {
		return productosRecibidos;
	}

	public void setProductosRecibidos(List<RemitoItem> productosRecibidos) {
		this.productosRecibidos = productosRecibidos;
	}
	
	
	
	
}

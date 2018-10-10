package entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.RemitoItem;

@Entity
@Table(name ="remitos")
public class RemitoEntity {
	
	@Id 
	@Column(name="id_Remito")
	private Integer idRemito;
	
	private Date fecha;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<RemitoItem> productosRecibidos;
	
	public RemitoEntity() {
		this.productosRecibidos = new ArrayList<RemitoItem>();
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

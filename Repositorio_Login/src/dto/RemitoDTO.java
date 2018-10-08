package dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class RemitoDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5225970438898278830L;
	private Integer idRemito;
	private Date fecha;
	private List<RemitoItemDTO> productosRecibidos;
	
	public RemitoDTO(Integer idRemito, Date fecha, List<RemitoItemDTO> productosRecibidos) {
		super();
		this.idRemito = idRemito;
		this.fecha = fecha;
		this.productosRecibidos = productosRecibidos;
	}
	
	public RemitoDTO() {
		super();
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
	public List<RemitoItemDTO> getProductosRecibidos() {
		return productosRecibidos;
	}
	public void setProductosRecibidos(List<RemitoItemDTO> productosRecibidos) {
		this.productosRecibidos = productosRecibidos;
	}
	
	
}

package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PedidoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2071632209672397718L;
	
	private Integer idPedido;
	private Date fecha;
	private String estadoPedido;
	private boolean tPersonaYfLogistica;
	private String direccionEnvioCoordinado;
	private List<PedidoItemDTO> items;
	
	public PedidoDTO() {
		this.items = new ArrayList<PedidoItemDTO>();
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEstadoPedido() {
		return estadoPedido;
	}

	public void setEstadoPedido(String estadoPedido) {
		this.estadoPedido = estadoPedido;
	}

	public boolean getTPersonaYfLogistica() {
		return tPersonaYfLogistica;
	}

	public void settPersonaYfLogistica(boolean tPersonaYfLogistica) {
		this.tPersonaYfLogistica = tPersonaYfLogistica;
	}

	public String getDireccionEnvioCoordinado() {
		return direccionEnvioCoordinado;
	}

	public void setDireccionEnvioCoordinado(String direccionEnvioCoordinado) {
		this.direccionEnvioCoordinado = direccionEnvioCoordinado;
	}

	public List<PedidoItemDTO> getItems() {
		return items;
	}

	public void setItems(List<PedidoItemDTO> items) {
		this.items = items;
	}
	
	
	
}

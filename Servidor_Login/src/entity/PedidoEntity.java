package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import model.EstadoPedido;
import model.PedidoItem;

@Entity
@Table(name="pedidos")
public class PedidoEntity {
	
	@Id
	private Integer idPedido;
	
	private Date fecha;
	
	@Enumerated(EnumType.STRING)
	private EstadoPedido estadoPedido;
	private String direccionEnvioCoordinado;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<PedidoItem> items;
	
	
	public PedidoEntity() {
		 this.items = new ArrayList<>();
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


	public EstadoPedido getEstadoPedido() {
		return estadoPedido;
	}


	public void setEstadoPedido(EstadoPedido estadoPedido) {
		this.estadoPedido = estadoPedido;
	}


	public String getDireccionEnvioCoordinado() {
		return direccionEnvioCoordinado;
	}


	public void setDireccionEnvioCoordinado(String direccionEnvioCoordinado) {
		this.direccionEnvioCoordinado = direccionEnvioCoordinado;
	}


	public List<PedidoItem> getItems() {
		return items;
	}


	public void setItems(List<PedidoItem> items) {
		this.items = items;
	}
	
	
	
	
}

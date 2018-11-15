package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import model.EstadoPedido;

@Entity
@Table(name="pedidos")
public class PedidoEntity {
	
	@Id
	private Integer idPedido;
	
	private Date fecha;
	
	@Enumerated(EnumType.STRING)
	private EstadoPedido estadoPedido;
	
	private boolean tPersonaYfLogistica;
	
	@OneToOne(fetch=FetchType.LAZY)
	private ClienteTiendaEntity cliente;
	
	@OneToOne(fetch=FetchType.LAZY)
	private DireccionClienteEntity direccion;
	private boolean fragil;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<PedidoItemEntity> items;
	
	
	public PedidoEntity() {
		 this.items = new ArrayList<PedidoItemEntity>();
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


	public List<PedidoItemEntity> getItems() {
		return items;
	}


	public void setItems(List<PedidoItemEntity> items) {
		this.items = items;
	}


	public boolean getTPersonaYfLogistica() {
		return tPersonaYfLogistica;
	}


	public void settPersonaYfLogistica(boolean tPersonaYfLogistica) {
		this.tPersonaYfLogistica = tPersonaYfLogistica;
	}


	public ClienteTiendaEntity getCliente() {
		return cliente;
	}


	public void setCliente(ClienteTiendaEntity cliente) {
		this.cliente = cliente;
	}


	public DireccionClienteEntity getDireccion() {
		return direccion;
	}


	public void setDireccion(DireccionClienteEntity direccion) {
		this.direccion = direccion;
	}


	public boolean getFragil() {
		return fragil;
	}


	public void setFragil(boolean fragil) {
		this.fragil = fragil;
	}
	
	
	
	
}

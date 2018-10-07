package model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import dao.PedidoDAO;
import dto.PedidoDTO;
import dto.PedidoItemDTO;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : @Almac�n
//  @ File Name : Producto.java
//  @ Date : @Sept2018
//  @ Author : @Grupo 1
//		Boujon Di Maio, Antonella.
//		Gomila, Tomas Guido.		
//		Graue, Florencia.
//		Retamar, Nicolas.
//
//



/*
 * se asume que si Direccion est� vacio, se despacha en persona, pero ante la duda se crea el boolean
 * de tPersonaYfLogistica para aclarar su estado.
 */
public class Pedido {
	private Integer idPedido;
	private Date fecha;
	private EstadoPedido estadoPedido;
	private boolean tPersonaYfLogistica;
	private String direccionEnvioCoordinado;
	private List<PedidoItem> items;
	
		
	public Pedido() {
		super();
	}
	
	public Pedido (PedidoDTO p){
		this.direccionEnvioCoordinado= p.getDireccionEnvioCoordinado();
		this.estadoPedido = EstadoPedido.valueOf(p.getEstadoPedido());
		this.fecha= p.getFecha();
		this.idPedido= p.getIdPedido();
		
		for (PedidoItemDTO item : p.getItems()) {
			this.agregarItem(new PedidoItem(item));
		}
		
		this.tPersonaYfLogistica= p.istPersonaYfLogistica();
	}

	public PedidoDTO toDTO() {
		PedidoDTO dto = new PedidoDTO();
		dto.setIdPedido(this.getIdPedido());
		dto.setFecha(this.getFecha());
		dto.setEstadoPedido(this.getEstadoPedido().toString());
		dto.settPersonaYfLogistica(this.getTPersonaYfLogistica());
		dto.setDireccionEnvioCoordinado(this.getDireccionEnvioCoordinado());
		for (PedidoItem item : this.items) {
			dto.getItems().add(item.toDTO());
		}
		
		return dto;
	}
	
	public Pedido save() {
		return PedidoDAO.getInstancia().save(this);
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

	public List<PedidoItem> getItems() {
		return items;
	}

	public void setItems(List<PedidoItem> items) {
		this.items = items;
	}

	public void faltaStock() {
		this.setEstadoPedido(EstadoPedido.FALTA_STOCK);
		save();
	}
	public void pendienteEnPersona() {
		if (EstadoPedido.FALTA_STOCK.equals(estadoPedido)) {
			this.setEstadoPedido(EstadoPedido.PENDIENTE_EN_PERSONA);
			save();
		}
	}
	public void pendienteEnLogistica() {
		if (EstadoPedido.FALTA_STOCK.equals(estadoPedido)) {
			this.setEstadoPedido(EstadoPedido.PENDIENTE_EN_LOGISTICA);
			save();
		}
	}
	
	public void despacharEnPersona() {
		if (EstadoPedido.PENDIENTE_EN_PERSONA.equals(estadoPedido)) {
			this.setEstadoPedido(EstadoPedido.DESPACHADO_EN_PERSONA);
			save();
		}
	}
	
	public void despacharEnLogistica() {
		if (EstadoPedido.PENDIENTE_EN_LOGISTICA.equals(estadoPedido)) {
			this.setEstadoPedido(EstadoPedido.DESPACHADO_EN_LOGISTICA);
			save();
		}
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for(PedidoItem item : items) {
			total = total.add(new BigDecimal(item.getCantidad()).multiply(item.getProducto().getPrecioVenta()));
		}
		return total;
	}
	
	public void agregarItem(PedidoItem item) {
		boolean existe = false;
		for(PedidoItem i : this.items) {
			if(i.getProducto().getIdProducto().equals((item.getProducto().getIdProducto()))){
				i.setCantidad(i.getCantidad() + item.getCantidad());
				existe = true;
				break;
			}
		}
		if(!existe)
			this.items.add(item);
	}
	
	public boolean poseoElProducto(String codigoBarras) {
		boolean existe = false;
		for(PedidoItem i : this.items) {
			if(i.getProducto().getCodigoBarras().equals(codigoBarras)){
				existe = true;
				break;
			}
		}
		return existe;
	}
	
	public boolean cancelarPedido() {
		//Consulto si mis productos siguen en el almacen, para que no me caguen
		if (EstadoPedido.FALTA_STOCK.equals(estadoPedido) 
				|| (EstadoPedido.PENDIENTE_EN_PERSONA.equals(estadoPedido)
				|| EstadoPedido.PENDIENTE_EN_LOGISTICA.equals(estadoPedido))) {
			
			//Se asume que no se movi� stock para despacho, asique todo
			//sigue en el almacen sin tocar.
			//Por falta de stock si se realiz� una orden de compra, ...
			//no cancelamos la orden de compra, por ahora.
			
			//Tarea: Devuelver mis productos reservados de este pedido a almacen
			//�Quien es el responsable de esto? ExpedicionControlador, yo solo soy un modelito
			//de negocio, mi deber es avisarle con true que devuelva todo a ProductoControlador.
			
			this.setEstadoPedido(EstadoPedido.CANCELADO);
			save();
			return true;
		}
		//Ya que fue despachado, no se cancela el pedido y devuelvo false a la accion requerida.
		return false;
	}
}

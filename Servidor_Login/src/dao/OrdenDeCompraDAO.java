package dao;

import dto.OrdenDeCompraDTO;
import entity.OrdenDeCompraEntity;
import entity.PedidoEntity;
import model.OrdenDeCompra;
import model.Pedido;

public class OrdenDeCompraDAO {
	
	private static OrdenDeCompraDAO instancia;

	private OrdenDeCompraDAO() {
	}

	public static OrdenDeCompraDAO getInstancia() {
		if (instancia == null)
			instancia = new OrdenDeCompraDAO();
		return instancia;
	}
	
	//TODO
	
	
	public OrdenDeCompra toNegocio(OrdenDeCompraEntity orden) {
		OrdenDeCompra orden = new OrdenDeCompra();
		orden.se
		
		
		Pedido pedi = new Pedido();
		pedi.setIdPedido(pedido.getIdPedido());
		pedi.setFecha(pedido.getFecha());
		pedi.setEstadoPedido(pedido.getEstadoPedido());
		pedi.settPersonaYfLogistica(pedido.getTPersonaYfLogistica());
		pedi.setDireccionEnvioCoordinado(pedido.getDireccionEnvioCoordinado());
		
		if(pedido.getItems()!=null)
		{
			pedi.setItems(getItems(pedido.getItems()));
		}
		else
		{
			//System.out.println("ES null");
		}
		return pedi;

	}
	
	
}

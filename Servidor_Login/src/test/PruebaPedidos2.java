package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controlador.ExpedicionControlador;
import controlador.ProductoControlador;
import dto.ClienteTiendaDTO;
import dto.DireccionClienteDTO;
import dto.PedidoDTO;
import dto.PedidoItemDTO;
import dto.ProductoDTO;

public class PruebaPedidos2 {

	public static void main(String[] args) {
		
		PedidoDTO p = crearPedido();
		p.setItems(crearItems());
		System.out.println("INICIO");
		PruebaPedidos.imprimirPedido(p);
		System.out.println("FIN");
		
		Integer nroPedido = ExpedicionControlador.getInstancia().altaPedido(crearPedido());
		
		PedidoDTO posta = ExpedicionControlador.getInstancia().buscarPedido(nroPedido);
		
		PruebaPedidos.imprimirPedido(posta);
		
	}
	
	public static PedidoDTO crearPedido (){
		PedidoDTO pedido = new PedidoDTO();
		pedido.setIdPedido(null);
		pedido.setFecha(new Date());
		pedido.setEstadoPedido("FALTA_STOCK");
		pedido.settPersonaYfLogistica(true);
		
		ClienteTiendaDTO cliente = new ClienteTiendaDTO();
		cliente.setIdClienteTienda(1);
		cliente.setNombreYApellido_RazonSocial("Fulano de tal");
		cliente.setCuil_cuit_dni("12.456.789");
		cliente.setEmail("a@a.com");
		pedido.setCliente(cliente);
		
		DireccionClienteDTO direccion = new DireccionClienteDTO();
		direccion.setNumero("717");
		direccion.setCalle("Lima");
		direccion.setEntreCalles("Independencia y Chile");
		direccion.setLocalidad("Montserrat");
		direccion.setPiso("8");
		direccion.setProvincia("Bs As");
		direccion.setUnidad("802");
		direccion.setCodigoPostal("1073");
		
		
		pedido.setDireccion(direccion);
		
		List<PedidoItemDTO> lista = crearItems();
		
		pedido.setItems(lista);
		
		return pedido;
	}
	
public static List<PedidoItemDTO> crearItems(){
		
		List<PedidoItemDTO> lista = new ArrayList<PedidoItemDTO>();	
		
		PedidoItemDTO item1 = new PedidoItemDTO();
		ProductoDTO p1 = ProductoControlador.getInstancia().buscarProductoById(1);
		item1.setIdPedidoItem(null);
		item1.setProducto(p1);
		item1.setCantidad(2);		
		lista.add(item1);
		
		PedidoItemDTO item2 = new PedidoItemDTO();
		ProductoDTO p2 = ProductoControlador.getInstancia().buscarProductoById(2);
		item2.setIdPedidoItem(null);
		item2.setProducto(p2);
		item2.setCantidad(7);		
		lista.add(item2);
		//lista.add(item1);		//Para desordenar y probar
		
		return lista;
		
	}

}

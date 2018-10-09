package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controlador.ExpedicionControlador;
import controlador.ProductoControlador;
import dao.PedidoDAO;
import dto.PedidoDTO;
import dto.PedidoItemDTO;
import dto.ProductoDTO;

public class PruebaPedidos {

	public static void main(String[] args) {
		
		PruebaProductos.crearTodosLosProductos();
		
		ExpedicionControlador.getInstancia().altaPedido(crearPedido());
		
		PedidoDTO pedido = ExpedicionControlador.getInstancia().buscarPedido(1);
		imprimirPedido(pedido);
		
		
	}
	
	public static PedidoDTO crearPedido (){
		PedidoDTO pedido = new PedidoDTO();
		pedido.setIdPedido(1);
		pedido.setFecha(new Date());
		pedido.setEstadoPedido("FALTA_STOCK");
		pedido.settPersonaYfLogistica(true);
		pedido.setDireccionEnvioCoordinado("Prueba 123");
		
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
		
		return lista;
		
	}
	
	public static void imprimirPedido(PedidoDTO pedido) {
		System.out.println();
		System.out.println("Pedido buscado en Base de Datos:");
		System.out.println("pedido direccion: " + pedido.getDireccionEnvioCoordinado());
		
		for(PedidoItemDTO item : pedido.getItems()) {
			System.out.println();
			System.out.println("item id: " + item.getIdPedidoItem());
			System.out.println("item producto: " + item.getProducto().getDescripcion());
			System.out.println("item cantidad: " + item.getCantidad());
		}
		System.out.println();
		
		System.out.println("Listo");
	}
}

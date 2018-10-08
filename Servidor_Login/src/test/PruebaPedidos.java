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
		
		System.out.println("Prueba: Jamon buscado = " + ProductoControlador.getInstancia().buscarProductoById(1).getDescripcion());
		
		ExpedicionControlador.getInstancia().altaPedido(crearPedido());
		
		System.out.println("Prueba: Jamon buscado = " + ProductoControlador.getInstancia().buscarProductoById(1).getDescripcion());
		
	}
	
	public static PedidoDTO crearPedido (){
		PedidoDTO pedido = new PedidoDTO();
		pedido.setIdPedido(1);
		pedido.setFecha(new Date());
		pedido.setEstadoPedido("FALTA_STOCK");
		pedido.settPersonaYfLogistica(true);
		pedido.setDireccionEnvioCoordinado("Prueba 123");
		
		ProductoDTO p1 = ProductoControlador.getInstancia().buscarProductoById(1);
		
		List<PedidoItemDTO> lista = new ArrayList();
		
		PedidoItemDTO item1 = new PedidoItemDTO();
		item1.setIdPedidoItem(1);
		item1.setProducto(p1);
		item1.setCantidad(2);
		
		lista.add(item1);
		pedido.setItems(lista);
		
		return pedido;
	}
}

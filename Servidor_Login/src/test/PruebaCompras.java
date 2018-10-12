package test;

import java.util.ArrayList;
import java.util.List;

import controlador.ComprasControlador;
import controlador.ExpedicionControlador;
import controlador.ProductoControlador;
import dto.OrdenDeCompraDTO;
import dto.PedidoDTO;
import dto.RemitoItemDTO;

public class PruebaCompras {

	public static void main(String[] args) {
		
		
		
		
		crearPedidosYCompras();
		
		
		
		
		List<OrdenDeCompraDTO> lista = ComprasControlador.getInstancia().findAllOrdenesDeCompraActivas();
		for(OrdenDeCompraDTO item : lista)
		{
			imprimirOrdenesDeCompra(item);
		}
		
		int orden = ComprasControlador.getInstancia().buscarOrdenesActivasByProductoCantidad("123-AA-Jamon");
		System.out.println("Cantidad ordenada para Jamon id1: " + orden);
		
		
		
		
		recepcionarCompras();//Le mando 400 de 480, falta 80
		
		
		
		
		System.out.println();
		System.out.println();
		lista = ComprasControlador.getInstancia().findAllOrdenesDeCompraActivas();
		for(OrdenDeCompraDTO item : lista)
		{
			imprimirOrdenesDeCompra(item);
		}
		
		
		//recepcionarCompras2();//Le mando los 80 restantes
		recepcionarCompras4();
		
		System.out.println();
		System.out.println();
		lista = ComprasControlador.getInstancia().findAllOrdenesDeCompraActivas();
		for(OrdenDeCompraDTO item : lista)
		{
			imprimirOrdenesDeCompra(item);
		}
		
		/*
		recepcionarCompras3();//Le mando los 80 restantes
		System.out.println();
		System.out.println();
		lista = ComprasControlador.getInstancia().findAllOrdenesDeCompraActivas();
		for(OrdenDeCompraDTO item : lista)
		{
			imprimirOrdenesDeCompra(item);
		}*/
		
		
		
		ExpedicionControlador.getInstancia().actualizarTodoFaltaDeStockAPendiente();
		
	}
	
	public static void crearPedidosYCompras(){
		PruebaProductos.crearProducto1(102);
		PruebaProductos.crearProducto2(107);
		
		PruebaPedidos.crearPedidos();
		
		PedidoDTO pedido1 = ExpedicionControlador.getInstancia().buscarPedido(1);
		PruebaPedidos.imprimirPedido(pedido1);
		
		PedidoDTO pedido2 = ExpedicionControlador.getInstancia().buscarPedido(2);
		PruebaPedidos.imprimirPedido(pedido2);
		
		ProductoControlador.getInstancia().verificarMinimoStockAndCrearOrdenes();
		
		
		
		
	}
	
	public static void recepcionarCompras(){
		List<RemitoItemDTO> items = new ArrayList<RemitoItemDTO>();
		RemitoItemDTO remito1 = new RemitoItemDTO();
		remito1.setProducto(ProductoControlador.getInstancia().buscarProductoByCodigoBarras("123-AA-Jamon"));
		remito1.setCantidad(400);
		remito1.setIdRemitoItem(null);
		items.add(remito1);
		
		ComprasControlador.getInstancia().recepcionarCompra(items);
	}
	
	public static void recepcionarCompras2(){
		List<RemitoItemDTO> items = new ArrayList<RemitoItemDTO>();
		RemitoItemDTO remito1 = new RemitoItemDTO();
		remito1.setProducto(ProductoControlador.getInstancia().buscarProductoByCodigoBarras("123-AA-Jamon"));
		remito1.setCantidad(80);
		remito1.setIdRemitoItem(null);
		items.add(remito1);
		
		ComprasControlador.getInstancia().recepcionarCompra(items);
	}
	
	public static void recepcionarCompras3(){
		List<RemitoItemDTO> items = new ArrayList<RemitoItemDTO>();
		RemitoItemDTO remito1 = new RemitoItemDTO();
		remito1.setProducto(ProductoControlador.getInstancia().buscarProductoByCodigoBarras("123-BB-Queso"));
		remito1.setCantidad(400);
		remito1.setIdRemitoItem(null);
		items.add(remito1);
		
		ComprasControlador.getInstancia().recepcionarCompra(items);
	}
	
	//Combinacion de recepcion 2 y 3
	public static void recepcionarCompras4(){
		List<RemitoItemDTO> items = new ArrayList<RemitoItemDTO>();
		RemitoItemDTO remito1 = new RemitoItemDTO();
		remito1.setProducto(ProductoControlador.getInstancia().buscarProductoByCodigoBarras("123-AA-Jamon"));
		remito1.setCantidad(80);
		remito1.setIdRemitoItem(null);
		items.add(remito1);
		
		RemitoItemDTO remito2 = new RemitoItemDTO();
		remito2.setProducto(ProductoControlador.getInstancia().buscarProductoByCodigoBarras("123-BB-Queso"));
		remito2.setCantidad(400);
		remito2.setIdRemitoItem(null);
		items.add(remito2);
		
		ComprasControlador.getInstancia().recepcionarCompra(items);
	}
	
	public static void imprimirOrdenesDeCompra(OrdenDeCompraDTO dto){
		System.out.println("==== Orden de compra =====");
		System.out.println("Producto: "+dto.getProducto().getDescripcion() + "    Cantidad: " + dto.getCantidadOrdenada());
		
		int cantRecibida = ComprasControlador.getInstancia().buscarOrdenesActivasByProductoCantidad(dto.getProducto().getCodigoBarras());
		
		System.out.println("CantidadOrdenada: " + dto.getCantidadOrdenada() + "     CantidadRestante: " + cantRecibida);
		System.out.println("==== Fin Orden de compra =====");
	}
	
	

}

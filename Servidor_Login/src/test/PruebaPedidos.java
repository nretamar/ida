package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controlador.ExpedicionControlador;
import controlador.ProductoControlador;
import controlador.ProveedorControlador;
import dto.ClienteTiendaDTO;
import dto.DireccionClienteDTO;
import dto.PedidoDTO;
import dto.PedidoItemDTO;
import dto.ProductoDTO;

public class PruebaPedidos {

	public static void main(String[] args) {
		
		/*
		 * Documentacion para eviar errores:
		 * Al proveedor cargarlo con NULL cuando se lo crea.
		 * No olvidar de dar de alta a un proveedor antes de dar de alta a un producto.
		 */
		
		
		//Id proveedor 1
		PruebaProductos.crearProveedor1();
		PruebaProductos.crearProveedor2();
		System.out.println("Proveedor: " + ProveedorControlador.getInstancia().buscarProveedorById(1).getUrl());
		PruebaProductos.crearProducto1(102);
		PruebaProductos.crearProducto2(107);
		
		crearPedidos();
		
		PedidoDTO pedido1 = ExpedicionControlador.getInstancia().buscarPedido(1);
		imprimirPedido(pedido1);
		
		PedidoDTO pedido2 = ExpedicionControlador.getInstancia().buscarPedido(2);
		imprimirPedido(pedido2);
		
		System.out.println("Antes de sumar Stock");
		PruebaProductos.imprimirProducto(ProductoControlador.getInstancia().buscarProductoById(1));
		PruebaProductos.imprimirProducto(ProductoControlador.getInstancia().buscarProductoById(2));
		System.out.println("Sumo stock :P");		
		ProductoControlador.getInstancia().sumarStockProducto(1, 1000);
		ProductoControlador.getInstancia().sumarStockProducto(2, 2000);
		PruebaProductos.imprimirProducto(ProductoControlador.getInstancia().buscarProductoById(1));
		PruebaProductos.imprimirProducto(ProductoControlador.getInstancia().buscarProductoById(2));
		
		System.out.println("Al tener stock, paso a pendiente a pedido2");
		ExpedicionControlador.getInstancia().actualizarTodoFaltaDeStockAPendiente();
		pedido2 = ExpedicionControlador.getInstancia().buscarPedido(2);
		imprimirPedido(pedido2);
		
		/*System.out.println("Despacho pedido2 y lo muestro");
		ExpedicionControlador.getInstancia().despachar(pedido2.getIdPedido());
		pedido2 = ExpedicionControlador.getInstancia().buscarPedido(2);
		imprimirPedido(pedido2);
		
		System.out.println("Despacho pedido1 y lo muestro");
		ExpedicionControlador.getInstancia().despachar(pedido1.getIdPedido());
		pedido1 = ExpedicionControlador.getInstancia().buscarPedido(1);
		imprimirPedido(pedido1);*/
		
	}
	
	public static void crearPedidos(){
		ExpedicionControlador.getInstancia().altaPedido(crearPedido());
		ExpedicionControlador.getInstancia().altaPedido(crearPedido2());
		
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
	
	public static PedidoDTO crearPedido2 (){
		PedidoDTO pedido = new PedidoDTO();
		pedido.setIdPedido(null);
		pedido.setFecha(new Date());
		pedido.setEstadoPedido("Jacuna batata"); //Pruebo errores
		pedido.settPersonaYfLogistica(false);

		ClienteTiendaDTO cliente = new ClienteTiendaDTO();
		cliente.setIdClienteTienda(null);
		cliente.setNombreYApellido_RazonSocial("Fulano de tal");
		cliente.setCuil_cuit_dni("12.456.789");
		cliente.setEmail("a@a.com");
		pedido.setCliente(cliente);
		
		DireccionClienteDTO direccion = new DireccionClienteDTO();
		/*direccion.setNumero("717");
		direccion.setCalle("Lima");
		direccion.setEntreCalles("Independencia y Chile");
		direccion.setLocalidad("Montserrat");
		direccion.setPiso("8");
		direccion.setProvincia("Bs As");
		direccion.setUnidad("802");
		direccion.setCodigoPostal("1073");*/
		
		
		pedido.setDireccion(direccion);
		
		List<PedidoItemDTO> lista = crearItems2();
		
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
	
	public static List<PedidoItemDTO> crearItems2(){
		
		List<PedidoItemDTO> lista = new ArrayList<PedidoItemDTO>();	
		
		PedidoItemDTO item1 = new PedidoItemDTO();
		ProductoDTO p1 = ProductoControlador.getInstancia().buscarProductoById(1);
		item1.setIdPedidoItem(null);
		item1.setProducto(p1);
		item1.setCantidad(500);		
		lista.add(item1);
		
		PedidoItemDTO item2 = new PedidoItemDTO();
		ProductoDTO p2 = ProductoControlador.getInstancia().buscarProductoById(2);
		item2.setIdPedidoItem(null);
		item2.setProducto(p2);
		item2.setCantidad(500);		
		lista.add(item2);
		//lista.add(item1);	//Para desordenar y probar
		
		//Agrego devuelta a jamon para probar agrupación.
		PedidoItemDTO item3 = new PedidoItemDTO();
		ProductoDTO p3 = ProductoControlador.getInstancia().buscarProductoById(1);
		item3.setIdPedidoItem(null);
		item3.setProducto(p3);
		item3.setCantidad(75);		
		lista.add(item3);
		
		return lista;
		
	}
	
	public static void imprimirPedido(PedidoDTO pedido) {
		System.out.println();
		System.out.println("Pedido buscado en Base de Datos:");
		System.out.println("pedido direccion calle: " + pedido.getDireccion().getCalle());
		System.out.println("pedido estado: " + pedido.getEstadoPedido());
		
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

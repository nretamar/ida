package testConexiones;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import businessDelegate.ExpedicionDelegate;
import businessDelegate.ProductoDelegate;
import dto.PedidoDTO;
import dto.PedidoItemDTO;
import dto.ProductoDTO;
import excepciones.GenericRemoteException;

public class PruebaPedidosDelegateSwing {

	public static void main(String[] args) {
		
		
		try {
			crearProducto1(102);
			crearProducto2(107);
			
			crearPedidos();
			
			PedidoDTO pedido1;
			pedido1 = ExpedicionDelegate.getInstancia().buscarPedido(1);
			imprimirPedido(pedido1);
			
			PedidoDTO pedido2 = ExpedicionDelegate.getInstancia().buscarPedido(2);
			imprimirPedido(pedido2);
			
		} catch (GenericRemoteException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	public static ProductoDTO crearProducto1 (int stockActual){
		
		try {
			ProductoDTO producto = new ProductoDTO();
			producto.setIdProducto(null);
			producto.setCodigoBarras("789");
			producto.setDescripcion("Elsa Pallo");
			producto.setPrecioVenta(BigDecimal.valueOf(15.2));
			producto.setCantFijaCompra(10);
			producto.setCantMinimaStock(50);
			producto.setStockActual(stockActual);
			producto.setEstadoActivo(true);
			
			String fotosUrl = "https://drive.google.com/open?id=1MhjEy13AM6Qlq_qNGRpyJllj6QroUXcK";
			producto.setFotoUrl(fotosUrl);
			
			int ret;
			
			ret = ProductoDelegate.getInstancia().altaProducto(producto);
			
			return ProductoDelegate.getInstancia().buscarProductoById(ret);
			
		} catch (GenericRemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
public static ProductoDTO crearProducto2 (int stockActual){
		
		try {
			ProductoDTO producto = new ProductoDTO();
			producto.setIdProducto(null);
			producto.setCodigoBarras("101112");
			producto.setDescripcion("Elsa Queo");
			producto.setPrecioVenta(BigDecimal.valueOf(19.4));
			producto.setCantFijaCompra(25);
			producto.setCantMinimaStock(75);
			producto.setStockActual(stockActual);
			producto.setEstadoActivo(true);
			
			String fotosUrl = "https://drive.google.com/open?id=1MhjEy13AM6Qlq_qNGRpyJllj6QroUXcK";
			producto.setFotoUrl(fotosUrl);
			
			int ret;
			
			ret = ProductoDelegate.getInstancia().altaProducto(producto);
			
			return ProductoDelegate.getInstancia().buscarProductoById(ret);
			
		} catch (GenericRemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void crearPedidos(){
		try {
			ExpedicionDelegate.getInstancia().altaPedido(crearPedido());
			ExpedicionDelegate.getInstancia().altaPedido(crearPedido2());
		} catch (GenericRemoteException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static PedidoDTO crearPedido (){
		PedidoDTO pedido = new PedidoDTO();
		pedido.setIdPedido(null);
		pedido.setFecha(new Date());
		pedido.setEstadoPedido("FALTA_STOCK");
		pedido.settPersonaYfLogistica(false);
		pedido.setDireccionEnvioCoordinado("Lima 717");
		
		List<PedidoItemDTO> lista = crearItems();
		
		pedido.setItems(lista);
		
		return pedido;
	}
	
	public static PedidoDTO crearPedido2 (){
		PedidoDTO pedido = new PedidoDTO();
		pedido.setIdPedido(null);
		pedido.setFecha(new Date());
		pedido.setEstadoPedido("Jacuna batata"); //Pruebo errores
		pedido.settPersonaYfLogistica(true);
		pedido.setDireccionEnvioCoordinado(null);
		
		List<PedidoItemDTO> lista = crearItems2();
		
		pedido.setItems(lista);
		
		return pedido;
	}
	
public static List<PedidoItemDTO> crearItems(){
		
		
		try {
			List<PedidoItemDTO> lista = new ArrayList<PedidoItemDTO>();	
			
			PedidoItemDTO item1 = new PedidoItemDTO();
			ProductoDTO p1;
			p1 = ProductoDelegate.getInstancia().buscarProductoByCodigoDeBarras("789");
			
			item1.setIdPedidoItem(null);
			item1.setProducto(p1);
			item1.setCantidad(2);		
			lista.add(item1);
			
			PedidoItemDTO item2 = new PedidoItemDTO();
			ProductoDTO p2 = ProductoDelegate.getInstancia().buscarProductoByCodigoDeBarras("101112");
			item2.setIdPedidoItem(null);
			item2.setProducto(p2);
			item2.setCantidad(7);		
			lista.add(item2);
			//lista.add(item1);		//Para desordenar y probar
			
			return lista;
		} catch (GenericRemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static List<PedidoItemDTO> crearItems2(){
		
		
		try {
			List<PedidoItemDTO> lista = new ArrayList<PedidoItemDTO>();	
			
			PedidoItemDTO item1 = new PedidoItemDTO();
			ProductoDTO p1;
			p1 = ProductoDelegate.getInstancia().buscarProductoByCodigoDeBarras("789");
			
			item1.setIdPedidoItem(null);
			item1.setProducto(p1);
			item1.setCantidad(500);		
			lista.add(item1);
			
			PedidoItemDTO item2 = new PedidoItemDTO();
			ProductoDTO p2 = ProductoDelegate.getInstancia().buscarProductoByCodigoDeBarras("101112");
			item2.setIdPedidoItem(null);
			item2.setProducto(p2);
			item2.setCantidad(500);		
			lista.add(item2);
			//lista.add(item1);	//Para desordenar y probar
			
			//Agrego devuelta a jamon para probar agrupación.
			PedidoItemDTO item3 = new PedidoItemDTO();
			ProductoDTO p3 = ProductoDelegate.getInstancia().buscarProductoByCodigoDeBarras("101112");
			item3.setIdPedidoItem(null);
			item3.setProducto(p3);
			item3.setCantidad(75);		
			lista.add(item3);
			
			return lista;
		} catch (GenericRemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static void imprimirPedido(PedidoDTO pedido) {
		System.out.println();
		System.out.println("Pedido buscado en Base de Datos:");
		System.out.println("pedido direccion: " + pedido.getDireccionEnvioCoordinado());
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

package testConexiones;

import java.math.BigDecimal;

import businessDelegate.ProductoDelegate;
import dto.ProductoDTO;
import excepciones.GenericRemoteException;

public class PruebaProductosDelegateSwing {

	public static void main(String[] args) {
		
		//crearTodosLosProductos();
		try {
			//crearTodosLosProductos();
			Integer id1 = crearProducto1(100).getIdProducto();
			ProductoDTO p1 = ProductoDelegate.getInstancia().buscarProductoById(id1);
			System.out.println("Busco Jamon = " + p1.getDescripcion() + "   stockActual: " + p1.getStockActual());
			
			p1.setStockActual(50);
			ProductoDelegate.getInstancia().modificarProducto(p1);
			p1 = ProductoDelegate.getInstancia().buscarProductoById(id1);
			System.out.println("Busco Jamon = " + p1.getDescripcion() + "   stockActual: " + p1.getStockActual());
			
			ProductoDelegate.getInstancia().descontarStockProducto(id1, 5);
			p1 = ProductoDelegate.getInstancia().buscarProductoById(id1);
			System.out.println("Busco Jamon = " + p1.getDescripcion() + "   stockActual: " + p1.getStockActual());
			
			ProductoDelegate.getInstancia().verificarMinimoStockAndCrearOrdenes();
			
			@SuppressWarnings("unused")
			ProductoDTO p2 = crearProducto2(150);
			
			for(ProductoDTO item : ProductoDelegate.getInstancia().findAllProductos()) {
				imprimirProducto(item);
			}
			
		} catch (GenericRemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void crearTodosLosProductos (){
		
		System.out.println("Creo Jamon id = " + crearProducto1(100).getIdProducto());
		
		System.out.println("Creo Queso id = " + crearProducto2(100).getIdProducto());
		
		try {
			System.out.println("Busco Jamon = " + ProductoDelegate.getInstancia().buscarProductoById(1).getDescripcion());
		} catch (GenericRemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public static ProductoDTO crearProducto1 (int stockActual) {
		ProductoDTO producto = new ProductoDTO();
		producto.setIdProducto(null);
		producto.setCodigoBarras("123-AA-Jamon");
		producto.setDescripcion("Jamon ahumado");
		producto.setPrecioVenta(BigDecimal.valueOf(15.2));
		producto.setCantFijaCompra(10);
		producto.setCantMinimaStock(50);
		producto.setStockActual(stockActual);
		producto.setEstadoActivo(true);
		
		String fotosUrl = "https://drive.google.com/open?id=1MhjEy13AM6Qlq_qNGRpyJllj6QroUXcK";
		producto.setFotoUrl(fotosUrl);
		
		try {
			int ret = ProductoDelegate.getInstancia().altaProducto(producto);
			return ProductoDelegate.getInstancia().buscarProductoById(ret);
		} catch (GenericRemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static ProductoDTO crearProducto2 (int stockActual){
		ProductoDTO producto = new ProductoDTO();
		producto.setIdProducto(null);
		producto.setCodigoBarras("123-BB-Queso");
		producto.setDescripcion("Queso cremoso");
		producto.setPrecioVenta(BigDecimal.valueOf(19.4));
		producto.setCantFijaCompra(25);
		producto.setCantMinimaStock(75);
		producto.setStockActual(stockActual);
		producto.setEstadoActivo(true);
		
		String fotosUrl = "https://drive.google.com/open?id=1MhjEy13AM6Qlq_qNGRpyJllj6QroUXcK";
		producto.setFotoUrl(fotosUrl);
		
		int ret;
		try {
			ret = ProductoDelegate.getInstancia().altaProducto(producto);
			return ProductoDelegate.getInstancia().buscarProductoById(ret);
		} catch (GenericRemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
		
	}
	
	public static void imprimirProducto (ProductoDTO p){
		System.out.println("Imprimo Producto");
		System.out.println("Producto id: "+p.getIdProducto() + "   Descripcion: "+p.getDescripcion());
		System.out.println("StockActual: " + p.getStockActual());
		System.out.println();
	}

}

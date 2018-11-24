package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import controlador.ProductoControlador;
import controlador.ProveedorControlador;
import dto.ProductoDTO;
import dto.ProveedorDTO;

public class PruebaProductos {

	public static void main(String[] args) {
		
		Integer idProveedor = crearProveedor1();
		Integer idProveedor2 = crearProveedor2();
		System.out.println("id proveedor:" + idProveedor);
		Integer id1 = crearProducto1(100).getIdProducto();
		
		crearTodosLosProductos();
		System.out.println(" hfsdd  Pase por aca");
		
		
		ProductoDTO p1 = ProductoControlador.getInstancia().buscarProductoById(id1);
		System.out.println("Busco Jamon = " + p1.getDescripcion() + "   stockActual: " + p1.getStockActual());
		
		p1.setStockActual(50);
		ProductoControlador.getInstancia().modificarProducto(p1);
		p1 = ProductoControlador.getInstancia().buscarProductoById(id1);
		System.out.println("Busco Jamon = " + p1.getDescripcion() + "   stockActual: " + p1.getStockActual());
		
		ProductoControlador.getInstancia().descontarStockProducto(id1, 5);
		p1 = ProductoControlador.getInstancia().buscarProductoById(id1);
		System.out.println("Busco Jamon = " + p1.getDescripcion() + "   stockActual: " + p1.getStockActual());
		
		ProductoControlador.getInstancia().verificarMinimoStockAndCrearOrdenes();
	}
	
	public static void crearTodosLosProductos (){
		
		System.out.println("Creo Jamon id = " + crearProducto1(100).getIdProducto());
		
		System.out.println("Creo Queso id = " + crearProducto2(100).getIdProducto());
		
		System.out.println("Busco Jamon = " + ProductoControlador.getInstancia().buscarProductoById(1).getDescripcion());
		
		
	}
	
	
	public static Integer crearProveedor1 () {
		ProveedorDTO p = new ProveedorDTO();
		p.setIdProveedor(null);
		p.setNombre("Diarco");
		p.setUrl("www.Diarco.com");
		p.setApiKey("contra");
		p.setEstadoActivo(true);
		Integer idProveedor = ProveedorControlador.getInstancia().altaProveedor(p);
		return idProveedor;
	}
	
	public static Integer crearProveedor2 () {
		ProveedorDTO p = new ProveedorDTO();
		p.setIdProveedor(null);
		p.setNombre("Vital");
		p.setUrl("www.Vital.com");
		p.setApiKey("contra");
		p.setEstadoActivo(true);
		Integer idProveedor = ProveedorControlador.getInstancia().altaProveedor(p);
		return idProveedor;
	}
	
	
	public static ProductoDTO crearProducto1 (int stockActual){
		ProductoDTO producto = new ProductoDTO();
		producto.setIdProducto(null);
		producto.setCodigoBarras("123-AA-Mochila");
		producto.setDescripcion("Valija Azul Samsonite");
		producto.setPrecioVenta(BigDecimal.valueOf(15.2));
		producto.setCantFijaCompra(10);
		producto.setCantMinimaStock(50);
		producto.setStockActual(stockActual);
		producto.setEstadoActivo(true);
		
		ProveedorDTO p = ProveedorControlador.getInstancia().buscarProveedorById(1);
		producto.setProveedor(p);
		System.out.println("Prove de producto prueba: " + producto.getProveedor().getNombre());
		
		String fotosUrl = "https://shop.samsonite.com/dw/image/v2/AAUE_PRD/on/demandware.static/-/Sites-product-catalog/default/collections/_samsonite/valor/500x500/1028972824be01.jpg?sw=500";
		producto.setFotoUrl(fotosUrl);
		
		//producto.setFoto(null);
		int ret = ProductoControlador.getInstancia().altaProducto(producto);
		
		return ProductoControlador.getInstancia().buscarProductoById(ret);
		
	}
	
	public static ProductoDTO crearProducto2 (int stockActual){
		ProductoDTO producto = new ProductoDTO();
		producto.setIdProducto(null);
		producto.setCodigoBarras("123-BB-Asus");
		producto.setDescripcion("Asus ROG");
		producto.setPrecioVenta(BigDecimal.valueOf(19.4));
		producto.setCantFijaCompra(25);
		producto.setCantMinimaStock(75);
		producto.setStockActual(stockActual);
		producto.setEstadoActivo(true);
		
		ProveedorDTO p = ProveedorControlador.getInstancia().buscarProveedorById(2);
		producto.setProveedor(p);
		
		String fotosUrl = "https://static.digit.in/product/b6420d8538d72a55d9ff4501fdd4d17671b6f7ba.jpeg";
		producto.setFotoUrl(fotosUrl);
		
		//producto.setFoto(null);
		int ret = ProductoControlador.getInstancia().altaProducto(producto);
		
		return ProductoControlador.getInstancia().buscarProductoById(ret);
		
	}
	
	public static void imprimirProducto (ProductoDTO p){
		System.out.println("Imprimo Producto");
		System.out.println("Producto id: "+p.getIdProducto() + "   Descripcion: "+p.getDescripcion());
		System.out.println("StockActual: " + p.getStockActual());
		System.out.println();
	}

}

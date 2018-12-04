package test;

import java.math.BigDecimal;

import controlador.ProductoControlador;
import controlador.ProveedorControlador;
import dto.ProductoDTO;
import dto.ProveedorDTO;

public class PruebaProductos {

	public static void main(String[] args) {
		
		Integer idProveedor = crearProveedor1();
		/*Integer idProveedor2 = */crearProveedor2();
		crearProveedor3();
		System.out.println("id proveedor:" + idProveedor);
		Integer id1 = crearProducto1(100).getIdProducto();
		
		crearTodosLosProductos();
		System.out.println(" hfsdd  Pase por aca");
		
		
		ProductoDTO p1 = ProductoControlador.getInstancia().buscarProductoById(2);
		System.out.println("Busco Jamon = " + p1.getDescripcion() + "   stockActual: " + p1.getStockActual());
		
		p1.setStockActual(50);
		ProductoControlador.getInstancia().modificarProducto(p1);
		p1 = ProductoControlador.getInstancia().buscarProductoById(2);
		System.out.println("Busco Jamon = " + p1.getDescripcion() + "   stockActual: " + p1.getStockActual());
		
		ProductoControlador.getInstancia().descontarStockProducto(2, 1);
		p1 = ProductoControlador.getInstancia().buscarProductoById(2);
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
		p.setIdComoSuCliente(1);
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
		p.setIdComoSuCliente(1);
		Integer idProveedor = ProveedorControlador.getInstancia().altaProveedor(p);
		return idProveedor;
	}
	
	public static Integer crearProveedor3 () {
		ProveedorDTO p = new ProveedorDTO();
		p.setIdProveedor(null);
		p.setNombre("GonzaDistributions");
		p.setUrl("http://iap-2018.herokuapp.com");
		p.setApiKey("a9fd27d1-5918-4dfa-8239-18ff706055a2");
		p.setEstadoActivo(true);
		p.setIdComoSuCliente(1);
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
		producto.setFragil(false);
		producto.setIdProductoDelProveedor(61);
		
		ProveedorDTO p = ProveedorControlador.getInstancia().buscarProveedorById(3);
		producto.setProveedor(p);
		System.out.println("Prove de producto prueba: " + producto.getProveedor().getNombre());
		
		String fotosUrl = "https://www.monoblock.tv/8313-thickbox_default/mochila-olga-bold.jpg";
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
		producto.setFragil(true);
		producto.setIdProductoDelProveedor(71);
		
		ProveedorDTO p = ProveedorControlador.getInstancia().buscarProveedorById(3);
		producto.setProveedor(p);
		
		String fotosUrl = "https://images-na.ssl-images-amazon.com/images/I/61ifLThAMOL._SY355_.jpg";
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

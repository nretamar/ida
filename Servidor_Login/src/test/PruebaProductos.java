package test;

import java.math.BigDecimal;

import controlador.ProductoControlador;
import dto.ProductoDTO;

public class PruebaProductos {

	public static void main(String[] args) {
		
		//crearTodosLosProductos();
		Integer id1 = crearProducto1(100).getIdProducto();
		ProductoDTO p1 = ProductoControlador.getInstancia().buscarProductoById(id1);
		System.out.println("Busco Jamon = " + p1.getDescripcion() + "   stockActual: " + p1.getStockActual());
		
		p1.setStockActual(50);
		ProductoControlador.getInstancia().modificarProducto(p1);
		p1 = ProductoControlador.getInstancia().buscarProductoById(id1);
		System.out.println("Busco Jamon = " + p1.getDescripcion() + "   stockActual: " + p1.getStockActual());
		
		ProductoControlador.getInstancia().descontarStockProducto(id1, 5);
		p1 = ProductoControlador.getInstancia().buscarProductoById(id1);
		System.out.println("Busco Jamon = " + p1.getDescripcion() + "   stockActual: " + p1.getStockActual());
	}
	
	public static void crearTodosLosProductos (){
		
		System.out.println("Creo Jamon id = " + crearProducto1(100).getIdProducto());
		
		System.out.println("Creo Queso id = " + crearProducto2(100).getIdProducto());
		
		System.out.println("Busco Jamon = " + ProductoControlador.getInstancia().buscarProductoById(1).getDescripcion());
		
		
	}
	
	
	
	public static ProductoDTO crearProducto1 (int stockActual){
		ProductoDTO producto = new ProductoDTO();
		producto.setIdProducto(null);
		producto.setCodigoBarras("123-AA-Jamon");
		producto.setDescripcion("Jamon ahumado");
		producto.setPrecioVenta(BigDecimal.valueOf(15.2));
		producto.setCantFijaCompra(10);
		producto.setCantMinimaStock(50);
		producto.setStockActual(stockActual);
		producto.setEstadoActivo(true);
		producto.setFoto(null);
		int ret = ProductoControlador.getInstancia().altaProducto(producto);
		
		return ProductoControlador.getInstancia().buscarProductoById(ret);
		
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
		producto.setFoto(null);
		int ret = ProductoControlador.getInstancia().altaProducto(producto);
		
		return ProductoControlador.getInstancia().buscarProductoById(ret);
		
	}

}

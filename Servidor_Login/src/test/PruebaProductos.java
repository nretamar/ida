package test;

import java.math.BigDecimal;

import controlador.ProductoControlador;
import dto.ProductoDTO;

public class PruebaProductos {

	public static void main(String[] args) {
		
		crearTodosLosProductos();
		
		
	}
	
	public static void crearTodosLosProductos (){
		
		System.out.println("Creo Jamon id = " + ProductoControlador.getInstancia().altaProducto(crearProducto()));
		
		System.out.println("Creo Queso id = " + ProductoControlador.getInstancia().altaProducto(crearProducto2()));
		
		System.out.println("Busco Jamon = " + ProductoControlador.getInstancia().buscarProductoById(1).getDescripcion());
	}
	
	
	
	public static ProductoDTO crearProducto (){
		ProductoDTO producto = new ProductoDTO();
		producto.setIdProducto(null);
		producto.setCodigoBarras("123-AA-Jamon");
		producto.setDescripcion("Jamon ahumado");
		producto.setPrecioVenta(BigDecimal.valueOf(15.2));
		producto.setCantFijaCompra(10);
		producto.setCantMinimaStock(50);
		producto.setStockActual(40);
		producto.setEstadoActivo(true);
		producto.setFoto(null);
		
		return producto;
		
	}
	
	public static ProductoDTO crearProducto2 (){
		ProductoDTO producto = new ProductoDTO();
		producto.setIdProducto(null);
		producto.setCodigoBarras("123-BB-Queso");
		producto.setDescripcion("Queso cremoso");
		producto.setPrecioVenta(BigDecimal.valueOf(19.4));
		producto.setCantFijaCompra(25);
		producto.setCantMinimaStock(75);
		producto.setStockActual(125);
		producto.setEstadoActivo(true);
		producto.setFoto(null);
		
		return producto;
		
	}

}

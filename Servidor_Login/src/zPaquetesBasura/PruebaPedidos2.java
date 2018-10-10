package zPaquetesBasura;

import java.math.BigDecimal;

import controlador.ExpedicionControlador;
import controlador.ProductoControlador;
import dto.PedidoDTO;
import dto.ProductoDTO;

public class PruebaPedidos2 {

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Jamon id = " + ProductoControlador.getInstancia().altaProducto(crearProducto()));
		
		System.out.println("Queso id = " + ProductoControlador.getInstancia().altaProducto(crearProducto2()));
		
		ExpedicionControlador.getInstancia().altaPedido(crearPedido(crearProducto()));
	}*/
	
	
	
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

	public static PedidoDTO crearPedido (ProductoDTO prod){
		PedidoDTO ped= new PedidoDTO();
		ped.setDireccionEnvioCoordinado(null);
		ped.setEstadoPedido(null);
		ped.setFecha(null);
		ped.setIdPedido(18);
		ped.setItems(null);
		ped.settPersonaYfLogistica(false);
		
		return ped;
	}
}

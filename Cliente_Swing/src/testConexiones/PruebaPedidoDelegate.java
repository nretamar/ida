package testConexiones;

import java.util.ArrayList;
import java.util.List;

import businessDelegate.ExpedicionDelegate;
import businessDelegate.ProductoDelegate;
import dto.ClienteTiendaDTO;
import dto.DireccionClienteDTO;
import dto.PedidoDTO;
import dto.PedidoItemDTO;
import excepciones.GenericRemoteException;

public class PruebaPedidoDelegate {
	
	public static void main(String[] args) {
		
		PedidoDTO pedido = new PedidoDTO();
		
		//Cargo un ejemplo test de cliente
		ClienteTiendaDTO testc = new ClienteTiendaDTO();
		testc.setIdClienteTienda(null);  //inicializalo con null, igual no se va a tomar en cuenta si hay un nro
		testc.setCuil_cuit_dni("12.345.678");
		testc.setNombreYApellido_RazonSocial("Pepe Argento");
		testc.setEmail("pepeargento@gmail.com");
		pedido.setCliente(testc);
				
		DireccionClienteDTO testd = new DireccionClienteDTO();
		testd.setIdDireccionCliente(null);
		testd.setCalle("Lima");
		testd.setNumero("717");
		testd.setPiso("8");
		testd.setUnidad("A");
		testd.setEntreCalles("Av Independencia y Calle Chile");
		testd.setProvincia("Bs As");
		testd.setLocalidad("Montserrat");
		testd.setCodigoPostal("1073");
		pedido.setDireccion(testd);
		
		pedido.setEstadoPedido(null);
		pedido.setFecha(null);
		pedido.setFragil(false);
		
		List<PedidoItemDTO> items = new ArrayList<PedidoItemDTO>();
		
		PedidoItemDTO item1 = new PedidoItemDTO();
		item1.setCantidad(5);
		item1.setIdPedidoItem(null);
		try {
			item1.setProducto(ProductoDelegate.getInstancia().buscarProductoById(1));
		} catch (GenericRemoteException e) {
			e.printStackTrace();
		}
		items.add(item1);
		
		PedidoItemDTO item2 = new PedidoItemDTO();
		item2.setCantidad(5);
		item2.setIdPedidoItem(null);
		try {
			item1.setProducto(ProductoDelegate.getInstancia().buscarProductoById(1));
		} catch (GenericRemoteException e) {
			e.printStackTrace();
		}
		items.add(item2);
		
		try {
			Integer idPedido = ExpedicionDelegate.getInstancia().altaPedido(pedido);
			System.out.println("Genere pedido con id: " + idPedido);
		} catch (GenericRemoteException e) {
			e.printStackTrace();
		}
		
		
	}
}

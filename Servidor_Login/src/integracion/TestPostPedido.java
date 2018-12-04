package integracion;

import dto.ClienteTiendaDTO;
import dto.DireccionClienteDTO;
import exceptions.PedidoException;
import integracionDto.IntegracionPedidoTiendaDTO;

public class TestPostPedido {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		IntegracionPedidoTiendaDTO test = new IntegracionPedidoTiendaDTO();
		
		test.setNombreAlmacen(null);
		test.setFecha(null);
		test.setEstadoPedido(null);
		test.setRequiereLogistica(true);	//Aclarar si requiere logistica
		
		
		//Cargo un ejemplo test de cliente
		ClienteTiendaDTO testc = new ClienteTiendaDTO();
		testc.setIdClienteTienda(null);  //inicializalo con null, igual no se va a tomar en cuenta si hay un nro
		testc.setCuil_cuit_dni("12.345.678");
		testc.setNombreYApellido_RazonSocial("Pepe Argento");
		testc.setEmail("pepeargento@gmail.com");
		test.setCliente(testc);
		
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
		test.setDireccion(testd);
		
		test.setFragil(false);  //No es necesario cargar esto, ya que el almacen
		//va a deducir que, con que 1 producto sea fragil, todo el pedido sera fragil.
		
		
		
		try {
			new PostPedidoDesdeTienda(test);	//Inyectar el dto
		} catch (PedidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

}

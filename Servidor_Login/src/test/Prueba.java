package test;

/*import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;*/

import controlador.ClienteControlador;
//import dao.ClienteDAO;
import dto.ClientesDTO;
import exceptions.ClienteException;

public class Prueba {

	public static void main(String[] args) throws ClienteException {
		// TODO Auto-generated method stub
		
		crearCliente();		
		obtenerUltimoCliente();		
		System.out.println("Cliente 1 = " + ClienteControlador.getInstancia().buscarCliente(1).getNombre());
		
		crearCliente2();
		obtenerUltimoCliente();
		System.out.println("Cliente 2 = " + ClienteControlador.getInstancia().buscarCliente(2).getNombre());
		
		//Existe el 3?
		System.out.println("Cliente 3 = " + ClienteControlador.getInstancia().buscarCliente(3).getNombre());
		
	}
	
	public static void crearCliente() {
		try {
			ClientesDTO c = new ClientesDTO();
			c.setCodigocliente(null);
			c.setNombre("Lucas");
			c.setApellido("Espegazzini");
			c.setCuit_cuil("20555555550");
			c.setResponsableinscripto(true);
			c.setDireccion("De la Serna 1956 - catastrofe");
			c.setActivo(true);
			ClienteControlador.getInstancia().grabarCliente(c,"i");
		} catch (Exception e) { // TODO: handle exception e.printStackTrace();
		}
	}
	
	public static void crearCliente2() {
		try {
			ClientesDTO c = new ClientesDTO();
			c.setCodigocliente(null);
			c.setNombre("Prueba RAIZZ");
			c.setApellido("Escalante");
			c.setCuit_cuil("20666666660");
			c.setResponsableinscripto(true);
			c.setDireccion("Lima 700 - Independencia");
			c.setActivo(true);
			ClienteControlador.getInstancia().grabarCliente(c,"i");
		} catch (Exception e) { // TODO: handle exception e.printStackTrace();
		}
	}

	
	public static void obtenerUltimoCliente() {
		int codigoCliente;

		try {
			codigoCliente = ClienteControlador.getInstancia().siguienteCodigoCliente();
			System.out.println(codigoCliente);
		} catch (ClienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
}

package test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import controlador.Controlador;
import dao.ClienteDAO;
import dto.ClientesDTO;
import exceptions.ClienteException;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//crearCliente();
		
		obtenerUltimoCliente();
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
			Controlador.getInstancia().grabarCliente(c,"c");
		} catch (Exception e) { // TODO: handle exception e.printStackTrace();
		}
	}

	
	public static void obtenerUltimoCliente() {
		int codigoCliente;

		try {
			codigoCliente = Controlador.getInstancia().siguienteCodigoCliente();
			System.out.println(codigoCliente);
		} catch (ClienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
}

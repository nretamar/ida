package test;

import controlador.ProveedorControlador;
import dao.ProveedorDAO;
import dto.ProveedorDTO;
import exceptions.ProveedorException;
import model.Proveedor;

public class PruebaProveedor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//ProveedorControlador.getInstancia().bajaProveedor(13);
		crear1();
		
		
		
		
		
		/*Proveedor p;
		try {
			p = ProveedorDAO.getInstancia().buscar(1);
			System.out.println("idCliente de proveedor " + p.getNombre() + ": " + p.getIdComoSuCliente());
		} catch (ProveedorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	
	public static Integer crear1 () {
		ProveedorDTO p = new ProveedorDTO();
		p.setIdProveedor(null);
		p.setNombre("Roquero");
		p.setUrl("NoHay");
		p.setApiKey("NoHay");
		p.setEstadoActivo(true);
		p.setIdComoSuCliente(1);
		Integer idProveedor = ProveedorControlador.getInstancia().altaProveedor(p);
		return idProveedor;
	}

}

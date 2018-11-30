package test;

import dao.ProveedorDAO;
import exceptions.ProveedorException;
import model.Proveedor;

public class PruebaProveedor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Proveedor p;
		try {
			p = ProveedorDAO.getInstancia().buscar(1);
			System.out.println("idCliente de proveedor " + p.getNombre() + ": " + p.getIdComoSuCliente());
		} catch (ProveedorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

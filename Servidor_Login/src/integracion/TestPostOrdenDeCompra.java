package integracion;

import dao.OrdenDeCompraDAO;
import dao.ProductoDAO;
import dto.ProductoDTO;
import exceptions.OrdenDeCompraException;
import exceptions.ProductoException;
import model.OrdenDeCompra;

public class TestPostOrdenDeCompra {

	public static void main(String[] args) {
		
		OrdenDeCompra orden = new OrdenDeCompra();
		
		//Cargo datos
		
		try {
			orden = OrdenDeCompraDAO.getInstancia().buscar(1);
			ProductoDTO p;
			try {
				p = ProductoDAO.getInstancia().buscar(1).toDTO();
				System.out.println("idCliente producto: " + p.getProveedor().getIdComoSuCliente());
				System.out.println("Descripcion producto: " + p.getProveedor().getNombre()+"  " + p.getDescripcion());
			} catch (ProductoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("idCliente: " + orden.getProducto().getProveedor().getIdComoSuCliente());
			System.out.println("Encontre orden para test, cantidadOrdenada: " + orden.getCantidadOrdenada());
			System.out.println("Producto ordenado: " + orden.getProducto().getDescripcion());
			new PostOrdenDeCompraDistribuidora(orden);
		} catch (OrdenDeCompraException e) {
			e.printStackTrace();
		}
	}

}

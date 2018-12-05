package integracion;

import dao.PedidoDAO;
import exceptions.PedidoException;
import model.Pedido;

public class TestPostLogistica {

	public static void main(String[] args) {
		
		Pedido pedido = new Pedido();
		
		//Cargo datos
		
		try {
			pedido = PedidoDAO.getInstancia().buscar(1);
			System.out.println("Encontre pedido para test, nombre cliente: " + pedido.getCliente().getNombreYApellido_RazonSocial());
			new PostLogistica(pedido);
		} catch (PedidoException e) {
			e.printStackTrace();
		}
	}

}

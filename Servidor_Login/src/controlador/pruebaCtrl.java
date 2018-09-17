package controlador;

import exceptions.ClienteException;

public class pruebaCtrl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println(Controlador.getInstancia().siguienteCodigoCliente());
		} catch (ClienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

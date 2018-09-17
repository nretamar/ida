package objetoRemoto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import controlador.Controlador;
import dto.ClientesDTO;
import exceptions.ClienteException;
import interfaz.interfaceRemote;

public class ObjetoRemoto extends UnicastRemoteObject implements interfaceRemote {

	private static final long serialVersionUID = -7564997541330495153L;


	public ObjetoRemoto() throws RemoteException {
		super();
	}
	
	public int sumarNumeros(int a, int b) {
		try {
			return Controlador.getInstancia().sumarNumeros(a, b);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -1;
	}


	/*********************************************************************************************************
	 * Clientes
	 *********************************************************************************************************/
	
	public int grabarCliente(ClientesDTO c,String ABM) throws RemoteException {
		System.out.println("entro al OR");

		try {
			return Controlador.getInstancia().grabarCliente(c,ABM);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	public ClientesDTO buscarCliente(Integer idcliente){
		try {
			return Controlador.getInstancia().buscarCliente(idcliente);
		} catch (ClienteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public int siguienteCodigoCliente (){
		try {
			return Controlador.getInstancia().siguienteCodigoCliente();
		} catch (ClienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public List<ClientesDTO> listarClientes(){
		try {
			return Controlador.getInstancia().listarClientes();
		} catch (ClienteException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public ClientesDTO buscarClienteporUsuario(Integer idUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
}

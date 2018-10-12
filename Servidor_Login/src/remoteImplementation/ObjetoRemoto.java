package remoteImplementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import controlador.ClienteControlador;
import dto.ClientesDTO;
import exceptions.ClienteException;
import remoteInterfaz.ClienteRemote;

public class ObjetoRemoto extends UnicastRemoteObject implements ClienteRemote {

	private static final long serialVersionUID = -7564997541330495153L;


	public ObjetoRemoto() throws RemoteException {
		super();
	}
	
	public int sumarNumeros(int a, int b) {
		try {
			return ClienteControlador.getInstancia().sumarNumeros(a, b);
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
			return ClienteControlador.getInstancia().grabarCliente(c,ABM);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	public ClientesDTO buscarCliente(Integer idcliente){
		try {
			return ClienteControlador.getInstancia().buscarCliente(idcliente);
		} catch (ClienteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public int siguienteCodigoCliente (){
		try {
			return ClienteControlador.getInstancia().siguienteCodigoCliente();
		} catch (ClienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public List<ClientesDTO> listarClientes(){
		try {
			return ClienteControlador.getInstancia().listarClientes();
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

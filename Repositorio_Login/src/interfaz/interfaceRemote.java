package interfaz;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import dto.ClientesDTO;

public interface interfaceRemote extends Remote, Serializable{

	/*********************************************************************************************************
	 * Clientes
	 *********************************************************************************************************/
	public int grabarCliente(ClientesDTO c,String ABM) throws RemoteException;
	public ClientesDTO buscarCliente(Integer idcliente) throws RemoteException;
	public List<ClientesDTO> listarClientes() throws RemoteException;
	public int siguienteCodigoCliente() throws RemoteException;	
	public ClientesDTO buscarClienteporUsuario(Integer idUsuario) throws RemoteException;
	public int sumarNumeros(int a, int b) throws RemoteException;
	
}

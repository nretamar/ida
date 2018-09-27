package businessDelegate;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import com.sun.xml.internal.ws.api.server.InstanceResolverAnnotation;

import dto.ClientesDTO;
import excepciones.Exceptions;
import remoteInterfaz.ClienteRemote;

public class BusinessDelegate {	
	
	private ClienteRemote ir;
	
	private static BusinessDelegate instancia;
	
	public static BusinessDelegate getInstancia() throws  Exception {
		if(instancia == null) {
			instancia = new BusinessDelegate();
		}
		return instancia;
	}
	
	public BusinessDelegate () throws Exceptions{
		try {
			ir = (ClienteRemote) Naming.lookup("//localhost/TP2018");
		} catch (MalformedURLException e) {
			throw new Exceptions("La direccion especificada no es correcta");
		} catch (RemoteException e) {
			throw new Exceptions("Error en las comunicaciones");
		} catch (NotBoundException e) {
			throw new Exceptions("El servidor no esta disponible");		
		}
	}		
	
	public int sumarNumeros(int a, int b) throws RemoteException {
		return ir.sumarNumeros(a, b);
	}

	/*********************************************************************************************************
	 * Clientes
	 *********************************************************************************************************/
	
	public int grabarCliente(ClientesDTO c,String ABM) throws RemoteException {
		System.out.println("entro al BD");
		return ir.grabarCliente(c,ABM);
	}
	
	public ClientesDTO buscarCliente(Integer idcliente) throws RemoteException
	{
		return ir.buscarCliente(idcliente);
	}
	
	public ClientesDTO buscarClienteporUsuario(Integer idUsuario) throws RemoteException
	{
		return ir.buscarClienteporUsuario(idUsuario);
	}
	
	
	public List<ClientesDTO> listarClientes() throws RemoteException {
		return ir.listarClientes();
	}

	public int siguienteCodigoCliente() throws RemoteException{
		return ir.siguienteCodigoCliente();
	}
	
}

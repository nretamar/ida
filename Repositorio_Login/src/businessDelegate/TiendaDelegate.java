package businessDelegate;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import dto.ArticuloDTO;
import excepciones.GenericRemoteException;
import remoteInterfaz.TiendaRemote;

public class TiendaDelegate {
	
	private static TiendaDelegate instancia;
	
	 private TiendaRemote TiendaRemote;
	 
	 private TiendaDelegate() throws GenericRemoteException {
		 try {
			 	TiendaRemote = (TiendaRemote) Naming.lookup("TiendaRemote");
			 	
		 } catch (MalformedURLException e) {
			 throw new GenericRemoteException(e);
		 } catch (RemoteException e) {
			 throw new GenericRemoteException(e);
		 } catch (NotBoundException e) {
			 throw new GenericRemoteException(e);
		 }
	}
	 
	public static TiendaDelegate getInstancia() throws GenericRemoteException {
		if (instancia == null) {
			instancia = new TiendaDelegate();
		}
		return instancia;
	}
	
	
	public List<ArticuloDTO> findAllProductos() throws GenericRemoteException{
		try {
			return TiendaRemote.findAllProductos();
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}


	 
	
}

package remoteInterfaz;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import dto.ArticuloDTO;

public interface TiendaRemote extends Remote, Serializable {
	
	
	List<ArticuloDTO> findAllProductos() throws RemoteException;

}

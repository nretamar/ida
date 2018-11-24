package remoteImplementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import controlador.ProductoControlador;
import dto.ArticuloDTO;
import remoteInterfaz.TiendaRemote;

public class TiendaRemoteImpl extends UnicastRemoteObject implements TiendaRemote  {

	private static final long serialVersionUID = -3088503228983291564L;

	public TiendaRemoteImpl() throws RemoteException {
		super();
	}

	@Override
	public List<ArticuloDTO> findAllProductos() throws RemoteException {
		return ProductoControlador.getInstancia().findAllProductosTienda();
	}
	
}

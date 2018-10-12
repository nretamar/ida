package remoteImplementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import controlador.ComprasControlador;
import dto.OrdenDeCompraDTO;
import dto.RemitoDTO;
import dto.RemitoItemDTO;
import remoteInterfaz.ComprasRemote;

public class ComprasRemoteImpl extends UnicastRemoteObject implements ComprasRemote {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5454933445410260310L;

	public ComprasRemoteImpl() throws RemoteException {
		super();
	}

	@Override
	public List<OrdenDeCompraDTO> findAllOrdenesDeCompra() throws RemoteException {
		return ComprasControlador.getInstancia().findAllOrdenesDeCompra();
	}

	@Override
	public List<OrdenDeCompraDTO> findAllOrdenesDeCompraActivas() throws RemoteException {
		return ComprasControlador.getInstancia().findAllOrdenesDeCompraActivas();
	}

	@Override
	public Integer altaOrdenDeCompra(OrdenDeCompraDTO orden) throws RemoteException {
		return ComprasControlador.getInstancia().altaOrdenDeCompra(orden);
	}

	@Override
	public Integer altaRemito(RemitoDTO remito) throws RemoteException {
		return ComprasControlador.getInstancia().altaRemito(remito);
	}

	@Override
	public RemitoDTO recepcionarCompra(List<RemitoItemDTO> items) throws RemoteException {
		return ComprasControlador.getInstancia().recepcionarCompra(items);
	}

	@Override
	public List<OrdenDeCompraDTO> buscarOrdenesActivasByProducto(String codigoBarras) throws RemoteException {
		return ComprasControlador.getInstancia().buscarOrdenesActivasByProducto(codigoBarras);
	}

	@Override
	public int buscarOrdenesActivasByProductoCantidad(String codigoBarras) throws RemoteException {
		return ComprasControlador.getInstancia().buscarOrdenesActivasByProductoCantidad(codigoBarras);
	}
	
	

}

package businessDelegate;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import dto.OrdenDeCompraDTO;
import dto.RemitoDTO;
import dto.RemitoItemDTO;
import excepciones.GenericRemoteException;
import remoteInterfaz.ComprasRemote;

public class ComprasDelegate {
	
	private static ComprasDelegate instancia;
	
	 private ComprasRemote comprasRemote;
	 
	 private ComprasDelegate() throws GenericRemoteException {
		 try {
			 comprasRemote = (ComprasRemote) Naming.lookup("ComprasRemote");
		 } catch (MalformedURLException e) {
			 throw new GenericRemoteException(e);
		 } catch (RemoteException e) {
			 throw new GenericRemoteException(e);
		 } catch (NotBoundException e) {
			 throw new GenericRemoteException(e);
		 }
	}
	 
	public static ComprasDelegate getInstancia() throws GenericRemoteException {
		if (instancia == null) {
			instancia = new ComprasDelegate();
		}
		return instancia;
	}
	
	
	
	
	public List<OrdenDeCompraDTO> findAllOrdenesDeCompra() throws GenericRemoteException{
		try {
			return comprasRemote.findAllOrdenesDeCompra();
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	public List<OrdenDeCompraDTO> findAllOrdenesDeCompraActivas() throws GenericRemoteException{
		try {
			return comprasRemote.findAllOrdenesDeCompraActivas();
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	public Integer altaOrdenDeCompra(OrdenDeCompraDTO orden) throws GenericRemoteException{
		try {
			return comprasRemote.altaOrdenDeCompra(orden);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	public Integer altaRemito(RemitoDTO remito) throws GenericRemoteException{
		try {
			return comprasRemote.altaRemito(remito);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	public RemitoDTO recepcionarCompra(List<RemitoItemDTO> items) throws GenericRemoteException{
		try {
			return comprasRemote.recepcionarCompra(items);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	public List<OrdenDeCompraDTO> buscarOrdenesActivasByProducto(String codigoBarras) throws GenericRemoteException{
		try {
			return comprasRemote.buscarOrdenesActivasByProducto(codigoBarras);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	public int buscarOrdenesActivasByProductoCantidad(String codigoBarras) throws GenericRemoteException{
		try {
			return comprasRemote.buscarOrdenesActivasByProductoCantidad(codigoBarras);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	
	
}

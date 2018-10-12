package remoteInterfaz;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import dto.OrdenDeCompraDTO;
import dto.RemitoDTO;
import dto.RemitoItemDTO;

public interface ComprasRemote extends Remote, Serializable {
	
	
	List<OrdenDeCompraDTO> findAllOrdenesDeCompra() throws RemoteException;
	
	List<OrdenDeCompraDTO> findAllOrdenesDeCompraActivas() throws RemoteException;
	
	Integer altaOrdenDeCompra(OrdenDeCompraDTO orden) throws RemoteException;
	
	Integer altaRemito(RemitoDTO remito) throws RemoteException;
	
	RemitoDTO recepcionarCompra(List<RemitoItemDTO> items) throws RemoteException;
	
	List<OrdenDeCompraDTO> buscarOrdenesActivasByProducto(String codigoBarras) throws RemoteException;
	
	int buscarOrdenesActivasByProductoCantidad(String codigoBarras) throws RemoteException;
	
}

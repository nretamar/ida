package remoteInterfaz;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import dto.PedidoDTO;

public interface ExpedicionRemote extends Remote, Serializable {
	
	Integer altaPedido(PedidoDTO pedido) throws RemoteException;

	void despacharPedido(Integer pedidoId) throws RemoteException;

	void cancelarPedido(Integer pedidoId) throws RemoteException;

	PedidoDTO buscarPedido(Integer pedidoId) throws RemoteException;

	List<PedidoDTO> buscarPedidosPendientesDespacho() throws RemoteException;
	
}

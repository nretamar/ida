package remoteInterfaz;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import dto.PedidoDTO;

public interface ExpedicionRemote extends Remote, Serializable {
	
	List<PedidoDTO> findAllPedidos() throws RemoteException;
	
	Integer altaPedido(PedidoDTO pedido) throws RemoteException;

	void actualizarTodoFaltaDeStockAPendiente() throws RemoteException;
	
	PedidoDTO pendienteSiTengoStock(Integer idPedido) throws RemoteException;
	
	boolean tengoStock(Integer idPedido) throws RemoteException;
	
	boolean bajaPedido(Integer idPedido) throws RemoteException;
	
	void modificarPedido(PedidoDTO pedido) throws RemoteException;
	
	PedidoDTO buscarPedido(Integer idPedido) throws RemoteException;
	
	List<PedidoDTO> buscarPedidosPendientesDespacho() throws RemoteException;
	
	void cancelar(Integer idPedido) throws RemoteException;
	
	void despachar(Integer idPedido) throws RemoteException;
	
	PedidoDTO buscarPedidoByDireccionEnvioCoordinado(String direccion) throws RemoteException;

	int buscarFaltaStockByProducto(String codigoBarras) throws RemoteException;
	
}

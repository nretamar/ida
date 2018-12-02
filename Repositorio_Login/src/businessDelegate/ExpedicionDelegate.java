package businessDelegate;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import dto.PedidoDTO;
import excepciones.GenericRemoteException;
import remoteInterfaz.ExpedicionRemote;

public class ExpedicionDelegate {
	
	private static ExpedicionDelegate instancia;
	
	 private ExpedicionRemote expedicionRemote;
	 
	 private ExpedicionDelegate() throws GenericRemoteException {
		 try {
			 expedicionRemote = (ExpedicionRemote) Naming.lookup("ExpedicionRemote");
		 } catch (MalformedURLException e) {
			 throw new GenericRemoteException(e);
		 } catch (RemoteException e) {
			 throw new GenericRemoteException(e);
		 } catch (NotBoundException e) {
			 throw new GenericRemoteException(e);
		 }
	}
	 
	public static ExpedicionDelegate getInstancia() throws GenericRemoteException {
		if (instancia == null) {
			instancia = new ExpedicionDelegate();
		}
		return instancia;
	}
	
	
	public List<PedidoDTO> findAllPedidos() throws GenericRemoteException{
		try {
			return expedicionRemote.findAllPedidos();
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	public Integer altaPedido(PedidoDTO pedido) throws GenericRemoteException{
		try {
			return expedicionRemote.altaPedido(pedido);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}

	public void actualizarTodoFaltaDeStockAPendiente() throws GenericRemoteException{
		try {
			expedicionRemote.actualizarTodoFaltaDeStockAPendiente();
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	public PedidoDTO pendienteSiTengoStock(Integer idPedido) throws GenericRemoteException{
		try {
			return expedicionRemote.pendienteSiTengoStock(idPedido);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	public boolean tengoStock(Integer idPedido) throws GenericRemoteException{
		try {
			return expedicionRemote.tengoStock(idPedido);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	public boolean bajaPedido(Integer idPedido) throws GenericRemoteException{
		try {
			return expedicionRemote.bajaPedido(idPedido);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	public void modificarPedido(PedidoDTO pedido) throws GenericRemoteException{
		try {
			expedicionRemote.modificarPedido(pedido);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	public PedidoDTO buscarPedido(Integer idPedido) throws GenericRemoteException{
		try {
			return expedicionRemote.buscarPedido(idPedido);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	public List<PedidoDTO> buscarPedidosPendientesDespacho() throws GenericRemoteException{
		try {
			return expedicionRemote.buscarPedidosPendientesDespacho();
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	public void cancelar(Integer idPedido) throws GenericRemoteException{
		try {
			expedicionRemote.cancelar(idPedido);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	public void despachar(Integer idPedido) throws GenericRemoteException{
		try {
			expedicionRemote.despachar(idPedido);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	public void entregadoEnDomicilioDelCliente(Integer idPedido) throws GenericRemoteException{
		try {
			expedicionRemote.entregadoEnDomicilioDelCliente(idPedido);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	public PedidoDTO buscarPedidoByDireccionEnvioCoordinado(String direccion) throws GenericRemoteException{
		try {
			return expedicionRemote.buscarPedidoByDireccionEnvioCoordinado(direccion);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}

	public int buscarFaltaStockByProducto(String codigoBarras) throws GenericRemoteException{
		try {
			return expedicionRemote.buscarFaltaStockByProducto(codigoBarras);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	
	
	
	
	
}

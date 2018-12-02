package remoteImplementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import controlador.ExpedicionControlador;
import dto.PedidoDTO;
import remoteInterfaz.ExpedicionRemote;

public class ExpedicionRemoteImpl extends UnicastRemoteObject implements ExpedicionRemote {

	private static final long serialVersionUID = -933475448905501128L;

	public ExpedicionRemoteImpl() throws RemoteException {
		super();
	}

	@Override
	public List<PedidoDTO> findAllPedidos() throws RemoteException {
		return ExpedicionControlador.getInstancia().findAllPedidos();
	}

	@Override
	public Integer altaPedido(PedidoDTO pedido) throws RemoteException {
		return ExpedicionControlador.getInstancia().altaPedido(pedido);
	}

	@Override
	public void actualizarTodoFaltaDeStockAPendiente() throws RemoteException {
		ExpedicionControlador.getInstancia().actualizarTodoFaltaDeStockAPendiente();
	}

	@Override
	public PedidoDTO pendienteSiTengoStock(Integer idPedido) throws RemoteException {
		return ExpedicionControlador.getInstancia().pendienteSiTengoStock(idPedido);
	}

	@Override
	public boolean tengoStock(Integer idPedido) throws RemoteException {
		return ExpedicionControlador.getInstancia().tengoStock(idPedido);
	}

	@Override
	public boolean bajaPedido(Integer idPedido) throws RemoteException {
		return ExpedicionControlador.getInstancia().bajaPedido(idPedido);
	}

	@Override
	public void modificarPedido(PedidoDTO pedido) throws RemoteException {
		ExpedicionControlador.getInstancia().modificarPedido(pedido);
	}

	@Override
	public PedidoDTO buscarPedido(Integer idPedido) throws RemoteException {
		return ExpedicionControlador.getInstancia().buscarPedido(idPedido);
	}

	@Override
	public List<PedidoDTO> buscarPedidosPendientesDespacho() throws RemoteException {
		return ExpedicionControlador.getInstancia().buscarPedidosPendientesDespacho();
	}

	@Override
	public void cancelar(Integer idPedido) throws RemoteException {
		ExpedicionControlador.getInstancia().cancelar(idPedido);
	}

	@Override
	public void despachar(Integer idPedido) throws RemoteException {
		ExpedicionControlador.getInstancia().despachar(idPedido);
	}
	
	@Override
	public void entregadoEnDomicilioDelCliente(Integer idPedido) throws RemoteException {
		ExpedicionControlador.getInstancia().entregadoEnDomicilioDelCliente(idPedido);
	}

	@Override
	public PedidoDTO buscarPedidoByDireccionEnvioCoordinado(String direccion) throws RemoteException {
		return ExpedicionControlador.getInstancia().buscarPedidoByDireccionEnvioCoordinado(direccion);
	}

	@Override
	public int buscarFaltaStockByProducto(String codigoBarras) throws RemoteException {
		return ExpedicionControlador.getInstancia().buscarFaltaStockByProducto(codigoBarras);
	}

}

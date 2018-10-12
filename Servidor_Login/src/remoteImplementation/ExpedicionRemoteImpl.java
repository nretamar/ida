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
	public Integer altaPedido(PedidoDTO pedido) throws RemoteException {
		return ExpedicionControlador.getInstancia().altaPedido(pedido);
	}

	@Override
	public void despacharPedido(Integer idPedido) throws RemoteException {
		ExpedicionControlador.getInstancia().despachar(idPedido);
		
	}

	@Override
	public void cancelarPedido(Integer idPedido) throws RemoteException {
		ExpedicionControlador.getInstancia().cancelar(idPedido);
	}

	@Override
	public PedidoDTO buscarPedido(Integer idPedido) throws RemoteException {
		return ExpedicionControlador.getInstancia().buscarPedido(idPedido);
	}

	@Override
	public List<PedidoDTO> buscarPedidosPendientesDespacho() throws RemoteException {
		return ExpedicionControlador.getInstancia().buscarPedidosPendientesDespacho();
	}

}

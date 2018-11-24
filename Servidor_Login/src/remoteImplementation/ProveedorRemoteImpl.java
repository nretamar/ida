package remoteImplementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import controlador.ProveedorControlador;
import dto.ProveedorDTO;
import remoteInterfaz.ProveedorRemote;

public class ProveedorRemoteImpl  extends UnicastRemoteObject implements ProveedorRemote  {

	private static final long serialVersionUID = 6212140579376707276L;

	public ProveedorRemoteImpl() throws RemoteException {
		super();
	}

	@Override
	public List<ProveedorDTO> findAllProveedores() throws RemoteException {
		return ProveedorControlador.getInstancia().findAllProveedores();
	}

	@Override
	public Integer altaProveedor(ProveedorDTO proveedor) throws RemoteException {
		return ProveedorControlador.getInstancia().altaProveedor(proveedor);
	}

	@Override
	public void bajaProveedor(Integer idProveedor) throws RemoteException {
		ProveedorControlador.getInstancia().bajaProveedor(idProveedor);
	}

	@Override
	public void modificarProveedor(ProveedorDTO proveedor) throws RemoteException {
		ProveedorControlador.getInstancia().modificarProveedor(proveedor);
	}

	@Override
	public ProveedorDTO buscarProveedorById(Integer idProveedor) throws RemoteException {
		return ProveedorControlador.getInstancia().buscarProveedorById(idProveedor);
	}

	@Override
	public ProveedorDTO buscarProveedorByUrl(String url) throws RemoteException {
		return ProveedorControlador.getInstancia().buscarProveedorByUrl(url);
	}

	@Override
	public ProveedorDTO buscarProveedorByNombre(String nombre) throws RemoteException {
		return ProveedorControlador.getInstancia().buscarProveedorByNombre(nombre);
	}
	
	

}

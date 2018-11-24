package businessDelegate;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import dto.ProveedorDTO;
import excepciones.GenericRemoteException;
import remoteInterfaz.ProveedorRemote;

public class ProveedorDelegate {
	
	
	private static ProveedorDelegate instancia;
	
	 private ProveedorRemote proveedorRemote;
	 
	 private ProveedorDelegate() throws GenericRemoteException {
		 try {
			 	proveedorRemote = (ProveedorRemote) Naming.lookup("ProveedorRemote");
		 } catch (MalformedURLException e) {
			 throw new GenericRemoteException(e);
		 } catch (RemoteException e) {
			 throw new GenericRemoteException(e);
		 } catch (NotBoundException e) {
			 throw new GenericRemoteException(e);
		 }
	}
	 
	public static ProveedorDelegate getInstancia() throws GenericRemoteException {
		if (instancia == null) {
			instancia = new ProveedorDelegate();
		}
		return instancia;
	}
	
	public List<ProveedorDTO> findAllProveedores() throws GenericRemoteException{
		try {
			return proveedorRemote.findAllProveedores();
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	public Integer altaProveedor(ProveedorDTO proveedor) throws GenericRemoteException{
		try {
			return proveedorRemote.altaProveedor(proveedor);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	public void bajaProveedor(Integer idProveedor) throws GenericRemoteException{
		try {
			proveedorRemote.bajaProveedor(idProveedor);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	public void modificarProveedor(ProveedorDTO proveedor) throws GenericRemoteException{
		try {
			proveedorRemote.modificarProveedor(proveedor);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	public ProveedorDTO buscarProveedorById(Integer idProveedor) throws GenericRemoteException{
		try {
			return proveedorRemote.buscarProveedorById(idProveedor);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	public ProveedorDTO buscarProveedorByUrl(String url) throws GenericRemoteException{
		try {
			return proveedorRemote.buscarProveedorByUrl(url);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	
	public ProveedorDTO buscarProveedorByNombre(String nombre) throws GenericRemoteException{
		try {
			return proveedorRemote.buscarProveedorByNombre(nombre);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
}

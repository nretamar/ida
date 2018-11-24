package remoteInterfaz;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import dto.ProveedorDTO;

public interface ProveedorRemote extends Remote, Serializable {
	
	
	List<ProveedorDTO> findAllProveedores() throws RemoteException;
	
	Integer altaProveedor(ProveedorDTO proveedor) throws RemoteException;
	
	void bajaProveedor(Integer idProveedor) throws RemoteException;
	
	void modificarProveedor(ProveedorDTO proveedor) throws RemoteException;
	
	ProveedorDTO buscarProveedorById(Integer idProveedor) throws RemoteException;
	
	ProveedorDTO buscarProveedorByUrl(String url) throws RemoteException;
	
	ProveedorDTO buscarProveedorByNombre(String nombre) throws RemoteException;
	
	
}

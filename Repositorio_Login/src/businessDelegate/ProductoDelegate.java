package businessDelegate;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import dto.ProductoDTO;
import excepciones.GenericRemoteException;
import remoteInterfaz.ProductoRemote;

public class ProductoDelegate {
	
	private static ProductoDelegate instancia;
	
	 private ProductoRemote productoRemote;
	 
	 private ProductoDelegate() throws GenericRemoteException {
		 try {
			 	productoRemote = (ProductoRemote) Naming.lookup("ProductoRemote");
		 } catch (MalformedURLException e) {
			 throw new GenericRemoteException(e);
		 } catch (RemoteException e) {
			 throw new GenericRemoteException(e);
		 } catch (NotBoundException e) {
			 throw new GenericRemoteException(e);
		 }
	}
	 
	public static ProductoDelegate getInstancia() throws GenericRemoteException {
		if (instancia == null) {
			instancia = new ProductoDelegate();
		}
		return instancia;
	}
	
	
	public List<ProductoDTO> findAllProductos() throws GenericRemoteException{
		try {
			return productoRemote.findAllProductos();
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}

	public Integer altaProducto(ProductoDTO productoDTO) throws GenericRemoteException{
		try {
			return productoRemote.altaProducto(productoDTO);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
    }

	public void bajaProducto(Integer idProducto) throws GenericRemoteException{
		try {
			productoRemote.bajaProducto(idProducto);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}

	public void modificarProducto(ProductoDTO productoDTO) throws GenericRemoteException{
		try {
			productoRemote.modificarProducto(productoDTO);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}

	public ProductoDTO buscarProductoById(Integer idProducto) throws GenericRemoteException{
		try {
			return productoRemote.buscarProductoById(idProducto);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
    
	public ProductoDTO buscarProductoByCodigoDeBarras(String codigoBarras) throws GenericRemoteException{
		try {
			return productoRemote.buscarProductoByCodigoDeBarras(codigoBarras);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	
	
	
	public void verificarMinimoStockAndCrearOrdenes() throws GenericRemoteException{
		try {
			productoRemote.verificarMinimoStockAndCrearOrdenes();
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
    
	public void sumarStockProducto(Integer idProducto, int cantidad) throws GenericRemoteException{
		try {
			productoRemote.sumarStockProducto(idProducto, cantidad);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
    
	public void descontarStockProducto(Integer idProducto, Integer cantidad) throws GenericRemoteException{
		try {
			productoRemote.descontarStockProducto(idProducto, cantidad);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
    
	public boolean tengoStock (Integer idProducto, Integer cantidadPedida) throws GenericRemoteException{
		try {
			return productoRemote.tengoStock(idProducto, cantidadPedida);
		} catch (RemoteException e) {
			throw new GenericRemoteException(e);
		}
	}
	 
	 
	
}

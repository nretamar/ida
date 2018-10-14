package remoteInterfaz;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import dto.ProductoDTO;

public interface ProductoRemote extends Remote, Serializable {
	
	
	List<ProductoDTO> findAllProductos() throws RemoteException;

    Integer altaProducto(ProductoDTO productoDTO) throws RemoteException;

    void bajaProducto(Integer idProducto) throws RemoteException;

    void modificarProducto(ProductoDTO productoDTO) throws RemoteException;

    ProductoDTO buscarProductoById(Integer idProducto) throws RemoteException;
    
    ProductoDTO buscarProductoByCodigoDeBarras(String codigoBarras) throws RemoteException;
    
    void verificarMinimoStockAndCrearOrdenes() throws RemoteException;
    
    void sumarStockProducto(Integer idProducto, int cantidad) throws RemoteException;
    
    void descontarStockProducto(Integer idProducto, Integer cantidad) throws RemoteException;
    
    boolean tengoStock (Integer idProducto, Integer cantidadPedida) throws RemoteException;
}

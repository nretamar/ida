package remoteImplementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import controlador.ProductoControlador;
import dto.ProductoDTO;
import remoteInterfaz.ProductoRemote;

public class ProductoRemoteImpl extends UnicastRemoteObject implements ProductoRemote  {

	private static final long serialVersionUID = -3088503228983291564L;

	public ProductoRemoteImpl() throws RemoteException {
		super();
	}

	@Override
	public List<ProductoDTO> findAllProductos() throws RemoteException {
		return ProductoControlador.getInstancia().findAllProductos();
	}

	@Override
	public Integer altaProducto(ProductoDTO productoDTO) throws RemoteException {
		return ProductoControlador.getInstancia().altaProducto(productoDTO);
	}

	@Override
	public void bajaProducto(Integer idProducto) throws RemoteException {
		ProductoControlador.getInstancia().bajaProducto(idProducto);
		
	}

	@Override
	public void modificarProducto(ProductoDTO productoDTO) throws RemoteException {
		ProductoControlador.getInstancia().modificarProducto(productoDTO);
		
	}

	@Override
	public ProductoDTO buscarProductoById(Integer idProducto) throws RemoteException {
		return ProductoControlador.getInstancia().buscarProductoById(idProducto);
	}

	@Override
	public ProductoDTO buscarProductoByCodigoDeBarras(String codigoBarras) throws RemoteException {
		return ProductoControlador.getInstancia().buscarProductoByCodigoBarras(codigoBarras);
	}

	@Override
	public void verificarMinimoStockAndCrearOrdenes() throws RemoteException {
		ProductoControlador.getInstancia().verificarMinimoStockAndCrearOrdenes();
		
	}

	@Override
	public void sumarStockProducto(Integer idProducto, int cantidad) throws RemoteException {
		ProductoControlador.getInstancia().sumarStockProducto(idProducto, cantidad);
	}

	@Override
	public void descontarStockProducto(Integer idProducto, Integer cantidad) throws RemoteException {
		ProductoControlador.getInstancia().descontarStockProducto(idProducto, cantidad);
	}

	@Override
	public boolean tengoStock(Integer idProducto, Integer cantidadPedida) throws RemoteException {
		return ProductoControlador.getInstancia().tengoStock(idProducto, cantidadPedida);
	}
	
	
	
}

package dao;

import entity.ProductoEntity;
import model.Producto;
import model.Proveedor;

public class ProveedorDAO {

	private static ProveedorDAO instancia;
	
	private ProveedorDAO() {
	}
	
	public static ProveedorDAO getInstancia() {
		if (instancia == null)
			instancia = new ProveedorDAO();
		return instancia;
	}
	
	public Proveedor toNegocio(ProveedorEntity proveedor) {
		
		Proveedor prove = new Proveedor();
		prove.setIdProveedor(proveedor.get);
		
		private Integer idProveedor;
		private String nombre;
		private String url;
		private String apiKey;
		private boolean estadoActivo;
		
		
		Producto produ = new Producto();
		produ.setIdProducto(producto.getIdProducto());
		produ.setCodigoBarras(producto.getCodigoBarras());
		produ.setDescripcion(producto.getDescripcion());
		produ.setPrecioVenta(producto.getPrecioVenta());
		produ.setCantFijaCompra(producto.getCantFijaCompra());
		produ.setCantMinimaStock(producto.getCantMinimaStock());
		produ.setStockActual(producto.getStockActual());
		produ.setEstadoActivo(producto.getEstadoActivo());
		
		//produ.setFoto(buscarFoto(producto.getIdProducto()));
		//produ.setFoto(producto.getFoto());
		return produ;

	}
}

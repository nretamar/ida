package controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.ProductoDAO;
import dto.ArticuloDTO;
import dto.OrdenDeCompraDTO;
import dto.ProductoDTO;
import exceptions.ProductoException;
import model.Producto;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : @Almac�n
//  @ File Name : Producto.java
//  @ Date : @Sept2018
//  @ Author : @Grupo 1
//		Boujon Di Maio, Antonella.
//		Gomila, Tomas Guido.		
//		Graue, Florencia.
//		Retamar, Nicolas.
//
//




public class ProductoControlador {
	
	public static ProductoControlador instancia;
	
	private ProductoControlador() {	
	}
	
	public static ProductoControlador getInstancia() {
		if (instancia == null) {
			instancia = new ProductoControlador();
		}
		return instancia;
	}
	
	/*
	 * @return Solo devuelve productos activos
	 */
	public List<ProductoDTO> findAllProductos() {
		List<ProductoDTO> productos = new ArrayList<ProductoDTO>();
		
		try {
			for (Producto producto : ProductoDAO.getInstancia().getAll())
			{
				if(producto.getEstadoActivo() == true)
				{
					productos.add(producto.toDTO());
				}
			}
		} catch (ProductoException e) {
			e.printStackTrace();
		}

		return productos;
	}
	
	public List<ProductoDTO> findAllProductosActivos() {
		List<ProductoDTO> productos = new ArrayList<ProductoDTO>();
		
		try {
			for (Producto producto : ProductoDAO.getInstancia().getAll()) {
				if(producto.getEstadoActivo() == true)
					productos.add(producto.toDTO());
			}
		} catch (ProductoException e) {
			e.printStackTrace();
		}

		return productos;
	}
	
	public Integer altaProducto(ProductoDTO producto) {
		ProductoDTO p = buscarProductoByCodigoBarras(producto.getCodigoBarras());
		
		//Si existe un producto con mismo codigo barra, te lo doy de baja y te creo uno nuevo con tus nuevos datos.
		if(p != null)
		{
			p.setEstadoActivo(false);
			modificarProducto(p);
		}
		return new Producto(producto).save().toDTO().getIdProducto();
	}
	
	public void bajaProducto(Integer idProducto) {
		try {
			ProductoDAO.getInstancia().buscar(idProducto).darDeBaja();
		} catch (ProductoException e) {
			e.printStackTrace();
		}
	}
	
	public void modificarProducto(ProductoDTO producto) {
		new Producto(producto).save();
	}
	
	public ProductoDTO buscarProductoById(Integer idProducto) {
		try {
			return ProductoDAO.getInstancia().buscar(idProducto).toDTO();
		} catch (ProductoException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * Devuelve un producto activo
	 */
	public ProductoDTO buscarProductoByCodigoBarras(String codigoBarras) {
		
		for (ProductoDTO producto : findAllProductos()) {
			if(producto.getCodigoBarras().equals(codigoBarras))
				return producto;
		}
		return null;
		
	}
	
	public void verificarMinimoStockAndCrearOrdenes() {
		List<Producto> lista = findAllProductosActivosModel();
		//Recorro todos los productos, si encuentro falta de stock, le consulto
		//a OrdenDeCompra si existe Orden sobre ese producto Activa, si no existe,
		//debera crear una nueva orden de compra.
		//System.out.println("=================== VerificarMinimoStockAndCrearOrdenes =====================");
		for(Producto item: lista)		//Recorro lista
		{
			//Obtengo cantidad ordenada a distribuidor de este producto item
			int cantidadOrdenada = ComprasControlador.getInstancia()
					.buscarOrdenesActivasByProductoCantidad(item.getCodigoBarras());
			//Cantidad pedida con falta de stock
			int cantidadAPedir = ExpedicionControlador.getInstancia()
					.buscarFaltaStockByProducto(item.getCodigoBarras());
			/*int deboPedir = item.getCantidadAPedir
					(item.getStockActual() + cantidadOrdenada - cantidadAPedir);*/
			int deboPedir = item.getCantidadAPedir(cantidadAPedir-cantidadOrdenada-item.getStockActual()+item.getCantMinimaStock());
			
			/*System.out.println("Verifico:");
			System.out.println("Producto: "+item.getDescripcion());
			System.out.println("stockActual: " + item.getStockActual());
			System.out.println("stock minimo: " + item.getCantMinimaStock());
			System.out.println("cantidadOrdenada: "+cantidadOrdenada + "    cantidadPedida: "+cantidadAPedir + "     deboPedir: "+deboPedir);
			System.out.println("cantidad a pedir "+ item.getCantidadAPedir(cantidadAPedir-cantidadOrdenada-item.getStockActual()));
			System.out.println("cantidadMinimaAPedir: " + item.getCantFijaCompra());
			System.out.println("deboPedir: " + deboPedir);
			System.out.println();
			
			System.out.println("========= Barra separadora ========");*/
			//Realizo la orden de compra
			if(deboPedir>0)
			{
				//System.out.println("========= Barra separadora ========");
				//System.out.println();
				OrdenDeCompraDTO dto = new OrdenDeCompraDTO();
				dto.setIdOrdenDeCompra(null);
				dto.setProducto(item.toDTO());
				dto.setFechaEmitida(new Date());
				dto.setOrdenActiva(true);
				dto.setCantidadOrdenada(deboPedir);
				dto.setRecepcionesDelProducto(null);
				
				ComprasControlador.getInstancia().altaOrdenDeCompra(dto);
			}	
		}
		
	}
	
	private List<Producto> findAllProductosActivosModel() {
		List<Producto> productos = new ArrayList<Producto>();
		
		try {
			for (Producto producto : ProductoDAO.getInstancia().getAll()) {
				if(producto.getEstadoActivo() == true)
					productos.add(producto);
			}
		} catch (ProductoException e) {
			e.printStackTrace();
		}

		return productos;
	}
	
	/*
	 * Aumento de stock al recibir mercader�as
	 * 			�
	 * Significa que al cliente no le gust� el producto, y lo devolvi� a la tienda
	 */
	public void sumarStockProducto(Integer idProducto, int cantidad) {
		ProductoDTO producto = buscarProductoById(idProducto);
		if(producto != null) {
			int stockActual = producto.getStockActual() + cantidad;
			if(stockActual < 0)
				stockActual = 0;
			producto.setStockActual(stockActual);
			modificarProducto(producto);
			
		}
	}
	
	/*
	 * Jam�s habra stock negativo, si cantidad es menor a 0, cantidad ser� igual a 0.
	 */
	public void descontarStockProducto(Integer idProducto, Integer cantidad) {
		ProductoDTO producto = buscarProductoById(idProducto);
		if(producto != null) {
			int stockActual = producto.getStockActual() - cantidad;
			if(stockActual < 0)
				stockActual = 0;
			producto.setStockActual(stockActual);
			modificarProducto(producto);
			
		}
	}
	
	public boolean tengoStock (Integer idProducto, Integer cantidadPedida) {
		Producto p;
		try {
			p = ProductoDAO.getInstancia().buscar(idProducto);
			return (cantidadPedida <= p.getStockActual());
		} catch (ProductoException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void darDeBajaPorIdProveedor(Integer id) {
		List<ProductoDTO> lista = findAllProductosActivos();
		for(ProductoDTO p : lista) {
			if(id != null && p.getProveedor().getIdProveedor() == id) {
				this.bajaProducto(p.getIdProducto());
			}
		}
	}

	public List<ArticuloDTO> findAllProductosTienda() {
		List<ArticuloDTO> productos = new ArrayList<ArticuloDTO>();
		
		try {
			for (Producto producto : ProductoDAO.getInstancia().getAll())
			{
				if(producto.getEstadoActivo() == true)
				{
					productos.add(producto.toArticuloDTO());
				}
			}
		} catch (ProductoException e) {
			e.printStackTrace();
		}

		return productos;
	}
	
}

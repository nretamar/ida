package model;

import java.math.BigDecimal;



import dao.ProductoDAO;
import dao.ProveedorDAO;
import dto.ProductoDTO;
import exceptions.ProveedorException;

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



public class Producto {
	private Integer idProducto;
	private String codigoBarras;
	private String descripcion;
	private BigDecimal precioVenta;
	private int cantFijaCompra;
	private int cantMinimaStock;
	private int stockActual;
	private boolean estadoActivo;
	private String fotoUrl;
	private boolean fragil;
	private Proveedor proveedor;
	//private ImageIcon foto;
	
		
	public Producto() {
		super();
	}
	
	public Producto(ProductoDTO dto) {
		this();
		
		this.codigoBarras = dto.getCodigoBarras();
		this.descripcion = dto.getDescripcion();
		this.precioVenta = dto.getPrecioVenta();
		this.cantFijaCompra = dto.getCantFijaCompra();
		this.cantMinimaStock = dto.getCantMinimaStock();
		this.stockActual = dto.getStockActual();	
		this.fotoUrl = dto.getFotoUrl();
		this.estadoActivo = dto.getEstadoActivo();
		this.fragil = dto.getFragil();
		if(dto.getProveedor() == null)
			System.out.println("Proveedor null");
		if(dto.getProveedor().getIdProveedor() == null)
			System.out.println("Proveedor id = null");
		try {
			
			this.proveedor = ProveedorDAO.getInstancia().buscar(dto.getProveedor().getIdProveedor());
		} catch (ProveedorException e) {
			e.printStackTrace();
		}
		
		this.idProducto = dto.getIdProducto();
		
		if(idProducto == null)
			estadoActivo = dto.getEstadoActivo();
	}
	

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public int getCantFijaCompra() {
		return cantFijaCompra;
	}

	public void setCantFijaCompra(int cantFijaCompra) {
		this.cantFijaCompra = cantFijaCompra;
	}

	public int getCantMinimaStock() {
		return cantMinimaStock;
	}

	public void setCantMinimaStock(int cantMinimaStock) {
		this.cantMinimaStock = cantMinimaStock;
	}

	public int getStockActual() {
		return stockActual;
	}

	public void setStockActual(int stockActual) {
		this.stockActual = stockActual;
	}

	public boolean getEstadoActivo() {
		return estadoActivo;
	}

	public void setEstadoActivo(boolean estadoActivo) {
		this.estadoActivo = estadoActivo;
	}
	
	public String getFotoUrl() {
		return fotoUrl;
	}

	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}
	
	public boolean getFragil() {
		return fragil;
	}

	public void setFragil(boolean fragil) {
		this.fragil = fragil;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public ProductoDTO toDTO() {
		ProductoDTO producto = new ProductoDTO();
		producto.setIdProducto(idProducto);
		producto.setCodigoBarras(codigoBarras);
		producto.setDescripcion(descripcion);
		producto.setPrecioVenta(precioVenta);
		producto.setCantFijaCompra(cantFijaCompra);
		producto.setCantMinimaStock(cantMinimaStock);
		producto.setStockActual(stockActual);
		producto.setEstadoActivo(estadoActivo);
		producto.setFotoUrl(fotoUrl);
		producto.setFragil(fragil);
		
		producto.setProveedor(proveedor.toDTO());
		
		return producto;
		
	}
	
	public Producto save() {
		
		Producto produ = ProductoDAO.getInstancia().save(this);
		
		return produ;
		
	}
	
	public boolean hayStock(int cantidad) {
		
		if(cantidad <= stockActual)
			return true;
		else
			return false;
		
	}
	
	public boolean despacharStock(int cantidad) {
		if(hayStock(cantidad)) {
			stockActual = stockActual - cantidad;
			save();
			return true;
		}
		else {
			return false;
		}
		
		
	}
	
	public void recepcionarStock(int cantidad) {
		stockActual = stockActual + cantidad;
		save();
	}
	
	public void darDeBaja() {
		estadoActivo = false;
		save();
	}
	
	/*public int getCantidadByPedido(int cantidad) {
		int aux = cantFijaCompra;
		while (aux < cantidad)
			aux += cantFijaCompra;
		return aux;
	}*/
	
	/*
	 * Introduzca su stock actual (negativo o positivo)
	 */
	public int getCantidadAPedir(int cantidad) {
		//System.out.println("Recibo cantidad: "+cantidad);
		int aux = 0;
		while (aux < cantidad)
			aux += cantFijaCompra;
		return aux;
	}
	
	public boolean soyElProducto(String codigoBarras) {
		if(codigoBarras.equals(this.codigoBarras))
			return true;
		return false;
	}
	
	
}

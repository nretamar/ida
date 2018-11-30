package conversiones;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import businessDelegate.ProductoDelegate;
import dto.OrdenDeCompraDTO;
import dto.PedidoDTO;
import dto.PedidoItemDTO;
import dto.ProductoDTO;
import excepciones.GenericRemoteException;
import integracionDto.IntegracionItemOrdenDeCompraDistribuidoraDTO;
import integracionDto.IntegracionItemPedidoTiendaDTO;
import integracionDto.IntegracionOrdenDeCompraDistribuidorDTO;
import integracionDto.IntegracionPedidoTiendaDTO;
import integracionDto.IntegracionProductoTiendaDTO;

public class IntegracionConversionTienda{

	private static IntegracionConversionTienda instancia;

	private IntegracionConversionTienda() {
	}
	
	public static IntegracionConversionTienda getInstancia() {
		if (instancia == null)
			instancia = new IntegracionConversionTienda();
		return instancia;
	}
	
	
	/*
	 * Conversion
	 */
	public IntegracionPedidoTiendaDTO pedidoAlmacenToTienda(PedidoDTO almacen) {
		IntegracionPedidoTiendaDTO tienda = new IntegracionPedidoTiendaDTO();
		
		tienda.setIdPedido(almacen.getIdPedido());
		tienda.setNombreAlmacen(getNombreAlmacen());
		tienda.setFecha(almacen.getFecha());
		tienda.setEstadoPedido(almacen.getEstadoPedido());
		tienda.setRequiereLogistica(almacen.getTPersonaYfLogistica());
		tienda.setCliente(almacen.getCliente());
		tienda.setDireccion(almacen.getDireccion());
		tienda.setFragil(almacen.getFragil());
		
		List<IntegracionItemPedidoTiendaDTO> items = new ArrayList<IntegracionItemPedidoTiendaDTO>();;
		for(PedidoItemDTO item : almacen.getItems())
		{
			IntegracionItemPedidoTiendaDTO i = new IntegracionItemPedidoTiendaDTO();
			i.setIdPedidoItem(item.getIdPedidoItem());
			
			//pasar producto
			
			
			/*IntegracionProductoTiendaDTO p = new IntegracionProductoTiendaDTO();
			p.setIdProducto(item.getProducto().getIdProducto());
			p.setCodigoBarras(item.getProducto().getCodigoBarras());
			p.setDescripcion(item.getProducto().getDescripcion());
			p.setPrecioVenta(item.getProducto().getPrecioVenta());
			p.setStockActual(item.getProducto().getStockActual());
			p.setFotoUrl(item.getProducto().getFotoUrl());
			p.setFragil(item.getProducto().getFragil());*/
			//Fin pasar producto
			i.setProducto(productoToTienda(item.getProducto()));
			
			i.setCantidad(item.getCantidad());
			items.add(i);
		}
		tienda.setItems(items);
		
		
		
		return tienda;
	}
	
	
	public IntegracionProductoTiendaDTO productoToTienda(ProductoDTO almacen) {
		IntegracionProductoTiendaDTO p = new IntegracionProductoTiendaDTO();
		p.setIdProducto(almacen.getIdProducto());
		p.setCodigoBarras(almacen.getCodigoBarras());
		p.setDescripcion(almacen.getDescripcion());
		p.setPrecioVenta(almacen.getPrecioVenta());
		p.setStockActual(almacen.getStockActual());
		p.setFotoUrl(almacen.getFotoUrl());
		p.setFragil(almacen.getFragil());
		//Fin pasar producto
		
		return p;
		
	}
	
	
	
	public PedidoDTO pedidoTiendaToAlmacen(IntegracionPedidoTiendaDTO tienda) {
		PedidoDTO almacen = new PedidoDTO();
		
		almacen.setIdPedido(tienda.getIdPedido());
		almacen.setFecha(tienda.getFecha());
		almacen.setEstadoPedido(tienda.getEstadoPedido());
		almacen.settPersonaYfLogistica(tienda.isRequiereLogistica());
		almacen.setCliente(tienda.getCliente());
		almacen.setDireccion(tienda.getDireccion());
		almacen.setFragil(tienda.isFragil());
		almacen.settPersonaYfLogistica(tienda.isRequiereLogistica());
		
		
		List<PedidoItemDTO> items = new ArrayList<PedidoItemDTO>();;
		for(IntegracionItemPedidoTiendaDTO item : tienda.getItems())
		{
			
			
			PedidoItemDTO i = new PedidoItemDTO();
			i.setIdPedidoItem(item.getIdPedidoItem());
			
			//pasar producto
			ProductoDTO p;
			try {
				p = ProductoDelegate.getInstancia().buscarProductoByCodigoDeBarras(item.getProducto().getCodigoBarras());
				i.setProducto(p);
				//Fin pasar producto
			} catch (GenericRemoteException e) {
				e.printStackTrace();
			}
			
			
			i.setCantidad(item.getCantidad());
			items.add(i);
		}
		almacen.setItems(items);
		
		
		
		return almacen;
	}
	
	public String getNombreAlmacen() {
		Properties prop = new Properties();
		InputStream input = null;
		try {

			input = new FileInputStream("nombreAlmacen.properties");

			// load a properties file
			prop.load(input);
		}
		catch(Exception e){
			
		}
			
		String nombreAlmacen = prop.getProperty("nombreAlmacen");
		return nombreAlmacen;
	}
	
	
	
	
	/*
	 * Orden de Compra
	 */
	public IntegracionOrdenDeCompraDistribuidorDTO ordenAlmacenToTienda(OrdenDeCompraDTO almacen) {
		IntegracionOrdenDeCompraDistribuidorDTO tienda = new IntegracionOrdenDeCompraDistribuidorDTO();
		
		//Inicializo en null, no me corresponde a mi la ID
		tienda.setClient(almacen.getProducto().getProveedor().getIdComoSuCliente());
		IntegracionItemOrdenDeCompraDistribuidoraDTO p = new IntegracionItemOrdenDeCompraDistribuidoraDTO();
		p.setProductId(almacen.getProducto().getIdProductoDelProveedor());
		p.setAmount(almacen.getCantidadOrdenada());
		tienda.getItems().add(p);
		
		return tienda;
	}
	
	public OrdenDeCompraDTO ordenTiendaToAlmacen(IntegracionOrdenDeCompraDistribuidorDTO tienda) {
		OrdenDeCompraDTO almacen = new OrdenDeCompraDTO();
		
		almacen.setIdOrdenDeCompra(null);
		almacen.setProducto(new ProductoDTO());
		almacen.getProducto().setIdProducto(tienda.getItems().get(0).getProductId());
		/*try {
			almacen.setProducto(ProductoDelegate.getInstancia().buscarProductoById(tienda.getItems().get(0).getProductId()));
		} catch (GenericRemoteException e) {
			e.printStackTrace();
		}*/
		almacen.setFechaEmitida(new Date());
		almacen.setOrdenActiva(true);
		almacen.setCantidadOrdenada(tienda.getItems().get(0).getAmount());
		
		
		return almacen;
	}
	
	
	
	
}

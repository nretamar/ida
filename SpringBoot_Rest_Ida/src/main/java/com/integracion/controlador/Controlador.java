package com.integracion.controlador;



import java.util.ArrayList;
import java.util.List;

//import org.junit.runner.RunWith;
//import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;

import businessDelegate.ExpedicionDelegate;
import businessDelegate.ProductoDelegate;
import conversiones.IntegracionConversionTienda;
import dto.OrdenDeCompraDTO;
import dto.PedidoDTO;
import dto.ProductoDTO;
import dto.TestDTO;
import dto.TestItemDTO;
import excepciones.GenericRemoteException;
import integracionDto.IntegracionOrdenDeCompraDistribuidorDTO;
import integracionDto.IntegracionPedidoTiendaDTO;
import integracionDto.IntegracionProductoTiendaDTO;

@Controller
public class Controlador {
	
	@RequestMapping(value="/producto/{codigoBarras}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<IntegracionProductoTiendaDTO> buscarProductoById(@PathVariable String codigoBarras) {
		
		System.out.println("in GET  Producto: " + codigoBarras);
		ProductoDTO producto = null;
		try {
			producto = ProductoDelegate.getInstancia().buscarProductoByCodigoDeBarras(codigoBarras);
		} catch (GenericRemoteException e) {
			e.printStackTrace();
		}
		
		//Si no poseo el producto buscado
		if(producto == null)
			return new ResponseEntity<IntegracionProductoTiendaDTO>(new IntegracionProductoTiendaDTO(),HttpStatus.BAD_REQUEST);
		IntegracionProductoTiendaDTO ptienda = IntegracionConversionTienda.getInstancia().productoToTienda(producto);
		
		return new ResponseEntity<IntegracionProductoTiendaDTO>(ptienda,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/pedido/{codigoPedido}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<IntegracionPedidoTiendaDTO> getPedido(@PathVariable int codigoPedido) {
		
		PedidoDTO pedido = null; 
		
		System.out.println("GET  Pedido: " + codigoPedido);
		try {
			pedido = ExpedicionDelegate.getInstancia().buscarPedido(codigoPedido);
		} catch (GenericRemoteException e) {
			e.printStackTrace();
		}
		
		//Si no poseo pedido con ese codigo que me pidió
		if(pedido == null)
			return new ResponseEntity<IntegracionPedidoTiendaDTO>(new IntegracionPedidoTiendaDTO(),HttpStatus.BAD_REQUEST);
		IntegracionPedidoTiendaDTO ptienda = IntegracionConversionTienda.getInstancia().pedidoAlmacenToTienda(pedido);
		
		return new ResponseEntity<IntegracionPedidoTiendaDTO>(ptienda,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/productos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<IntegracionProductoTiendaDTO>> getAllProductos() {
		
		List<ProductoDTO> lista = null;
		System.out.println("in GET  Productos");
		try {
			lista = ProductoDelegate.getInstancia().findAllProductos();
		} catch (GenericRemoteException e) {
			e.printStackTrace();
		}
		
		//Si no poseo productos
		if(lista == null)
			return new ResponseEntity<List<IntegracionProductoTiendaDTO>>(new ArrayList<IntegracionProductoTiendaDTO>(),HttpStatus.BAD_REQUEST);
		
		List<IntegracionProductoTiendaDTO> integra = new ArrayList<IntegracionProductoTiendaDTO>();
		for(ProductoDTO item : lista) {
			integra.add(IntegracionConversionTienda.getInstancia().productoToTienda(item));
		}
		
		
		
		return new ResponseEntity<List<IntegracionProductoTiendaDTO>>(integra,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/pedidos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<IntegracionPedidoTiendaDTO>> getAllPedidos() {
		
		List<PedidoDTO> lista = null;
		System.out.println("in GET  Pedidos");
		try {
			lista = ExpedicionDelegate.getInstancia().findAllPedidos();
		} catch (GenericRemoteException e) {
			e.printStackTrace();
		}
		
		//Si no poseo pedidos
		if(lista == null)
			return new ResponseEntity<List<IntegracionPedidoTiendaDTO>>(new ArrayList<IntegracionPedidoTiendaDTO>(),HttpStatus.BAD_REQUEST);
				
		
		List<IntegracionPedidoTiendaDTO> integra = new ArrayList<IntegracionPedidoTiendaDTO>();
		for(PedidoDTO item : lista) {
			integra.add(IntegracionConversionTienda.getInstancia().pedidoAlmacenToTienda(item));
		}		
		
		return new ResponseEntity<List<IntegracionPedidoTiendaDTO>>(integra,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/pedido", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Integer> add(@RequestBody IntegracionPedidoTiendaDTO ptienda){
		
		System.out.println("in POST Pedido");
		ptienda.setEstadoPedido(null);
		PedidoDTO pedido = IntegracionConversionTienda.getInstancia().pedidoTiendaToAlmacen(ptienda);
		
		/*System.out.println("id: " + pedido.getIdPedido());
		System.out.println("nombre: " + pedido.getCliente().getNombreYApellido_RazonSocial());
		System.out.println("direccion: " + pedido.getDireccion().getCalle());
		for(PedidoItemDTO item : pedido.getItems()) {
			System.out.println("");
			System.out.println("    codigoBarrasProducto: " + item.getProducto().getCodigoBarras());
			System.out.println("    descripcion producto: " + item.getProducto().getDescripcion());
			System.out.println("    cantidad: " + item.getCantidad());
		}*/
		
		Integer nroPedido;
		try {
			nroPedido = ExpedicionDelegate.getInstancia().altaPedido(pedido);
			return new ResponseEntity<Integer>(nroPedido,HttpStatus.CREATED);
		} catch (GenericRemoteException e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(-1,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
		
	
	@RequestMapping(value="/pedidoEntregado/{codigoPedido}", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Integer> pedidoEntregado(@PathVariable int codigoPedido) {
		
		PedidoDTO pedido = null; 
		
		System.out.println("in POST  Pedido entregado: " + codigoPedido);
		try {
			ExpedicionDelegate.getInstancia().entregadoEnDomicilioDelCliente(codigoPedido);
			pedido = ExpedicionDelegate.getInstancia().buscarPedido(codigoPedido);
		} catch (GenericRemoteException e) {
			e.printStackTrace();
		}
		
		//Si no poseo pedido con ese codigo que me pidió
		if(pedido == null)
			return new ResponseEntity<Integer>(-1,HttpStatus.BAD_REQUEST);
		
		if(!pedido.getEstadoPedido().equals("ENTREGADO_EN_DOMICILIO_DEL_CLIENTE"))
			return new ResponseEntity<Integer>(-1,HttpStatus.NOT_MODIFIED);
		
		return new ResponseEntity<Integer>(pedido.getIdPedido(),HttpStatus.OK);
		
	}
	
	
	
	
	/*
	 * TEST A BORRAR
	 */
	
	
	
	@RequestMapping(value="/test", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Integer> add(@RequestBody TestDTO test){
		
		System.out.println("Entre a post test");
		
		System.out.println("id: " + test.getId());
		System.out.println("nombre: " + test.getNombre());
		System.out.println("apellido: " + test.getApellido());
		for(TestItemDTO item : test.getMaterias()) {
			System.out.println("");
			System.out.println("    idMateria: " + item.getIdMateria());
			System.out.println("    Descripción: " + item.getDescripcion());
		}
		
		return new ResponseEntity<Integer>(717,HttpStatus.ACCEPTED);
		
		
	}
	
	@RequestMapping(value="/testt", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Integer> addPedido(@RequestBody PedidoDTO pedido){
		
		System.out.println("Entre a post test");
		
		System.out.println("idPedido: " + pedido.getIdPedido());
		System.out.println("nombre cliente: " + pedido.getCliente().getNombreYApellido_RazonSocial());
		System.out.println("calle: " + pedido.getDireccion().getCalle());
		
		return new ResponseEntity<Integer>(22,HttpStatus.ACCEPTED);
		
		
	}
	
	@RequestMapping(value="/ordenTest", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Integer> add(@RequestBody IntegracionOrdenDeCompraDistribuidorDTO otienda){
		
		System.out.println("Entre a post orden");
		
		System.out.println("idClienteParaProveedor: " + otienda.getClient());
		
		OrdenDeCompraDTO orden = IntegracionConversionTienda.getInstancia().ordenTiendaToAlmacen(otienda);
		
		System.out.println("idProducto: " + orden.getProducto().getDescripcion());
		System.out.println("cantidadOrdenada: " + orden.getCantidadOrdenada());
		
		return new ResponseEntity<Integer>(717,HttpStatus.ACCEPTED);
		
		
	}
	
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	@ResponseBody
	public void test() {
		
		System.out.println("This is a test");
		
	}
	
	
}

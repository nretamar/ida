package com.integracion.conversiones;

import java.util.ArrayList;
import java.util.List;

import businessDelegate.ProductoDelegate;
import dto.PedidoDTO;
import dto.PedidoItemDTO;
import dto.ProductoDTO;
import excepciones.GenericRemoteException;
import integracionDto.IntegracionItemPedidoTiendaDTO;
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
	
	
	public IntegracionPedidoTiendaDTO pedidoAlmacenToTienda(PedidoDTO almacen) {
		IntegracionPedidoTiendaDTO tienda = new IntegracionPedidoTiendaDTO();
		
		tienda.setIdPedido(almacen.getIdPedido());
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
			IntegracionProductoTiendaDTO p = new IntegracionProductoTiendaDTO();
			p.setIdProducto(item.getProducto().getIdProducto());
			p.setCodigoBarras(item.getProducto().getCodigoBarras());
			p.setDescripcion(item.getProducto().getDescripcion());
			p.setPrecioVenta(item.getProducto().getPrecioVenta());
			p.setStockActual(item.getProducto().getStockActual());
			p.setFotoUrl(item.getProducto().getFotoUrl());
			p.setFragil(item.getProducto().getFragil());
			//Fin pasar producto
			i.setProducto(p);
			
			i.setCantidad(item.getCantidad());
			items.add(i);
		}
		tienda.setItems(items);
		
		
		
		return tienda;
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
				i.setProducto(p);
			} catch (GenericRemoteException e) {
				e.printStackTrace();
			}
			
			
			i.setCantidad(item.getCantidad());
			items.add(i);
		}
		almacen.setItems(items);
		
		
		
		return almacen;
	}
	
	
	
	
	
	
}

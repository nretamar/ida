package dao;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.PedidoEntity;
import entity.PedidoItemEntity;
import exceptions.ProductoException;
import model.Pedido;
import model.PedidoItem;
import model.Producto;
import util.HibernateUtil;

public class PedidoDAO {
	
	private static PedidoDAO instancia;

	private PedidoDAO() {
	}

	public static PedidoDAO getInstancia() {
		if (instancia == null)
			instancia = new PedidoDAO();
		return instancia;
	}

	public Pedido toNegocio(PedidoEntity pedido) {
		
		Pedido pedi = new Pedido();
		pedi.setIdPedido(pedido.getIdPedido());
		pedi.setFecha(pedido.getFecha());
		pedi.setEstadoPedido(pedido.getEstadoPedido());
		pedi.settPersonaYfLogistica(pedido.getTPersonaYfLogistica());
		pedi.setDireccionEnvioCoordinado(pedido.getDireccionEnvioCoordinado());
		pedi.setItems(getItems(pedido.getItems()));
		
		return pedi;

	}
	
	/*
	 * Quizas esto no funcione
	 */
	private List<PedidoItem> getItems(List<PedidoItemEntity> items) {
		return items.stream().map(i -> {
			PedidoItem pedido = new PedidoItem();
			pedido.setIdPedidoItem(i.getIdPedidoItem());
			pedido.setProducto(ProductoDAO.getInstancia().toNegocio(i.getProducto()));
			pedido.setCantidad(i.getCantidad());
			return pedido;
		}).collect(Collectors.toList());
	}

	public PedidoEntity toEntity(Pedido pedido) {
		
		PedidoEntity pedi = new PedidoEntity();
		
		pedi.setIdPedido(pedido.getIdPedido());
		pedi.setFecha(pedido.getFecha());
		pedi.setEstadoPedido(pedido.getEstadoPedido());
		pedi.settPersonaYfLogistica(pedido.getTPersonaYfLogistica());
		pedi.setDireccionEnvioCoordinado(pedido.getDireccionEnvioCoordinado());
		pedi.setItems(getItemsEntity(pedido.getItems()));

		return pedi;
	}
	
	private List<PedidoItemEntity> getItemsEntity(List<PedidoItem> items) {
		return items.stream().map(i -> {			
			PedidoItemEntity entity = new PedidoItemEntity();
			entity.setIdPedidoItem(i.getIdPedidoItem());
			entity.setCantidad(i.getCantidad());
			return entity;
		}).collect(Collectors.toList());
	}
	
	public Pedido save(Pedido p) {
		Pedido ret = null;
		if (p.getIdPedido() != null) {
			ret = grabarConId(p);
		} else {
						
			try {
				int codPedi;
				codPedi = ultimoCodigoPedido()+1;
				p.setIdPedido(codPedi);
				ret = grabarConId(p);
			} catch (PedidoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

		return ret;
	}
	
	public int ultimoCodigoProducto() throws ProductoException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Integer ce = (Integer) session.createQuery("select isnull(max(ce.idProducto), 0) from ProductoEntity ce ")
				.uniqueResult();
		if (ce != null) {
			return ce;
		} else
			throw new ProductoException("No se pudo obtener un nuevo id de Producto válido");
	}
	
	
	
}

package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.PedidoEntity;
import entity.PedidoItemEntity;
import exceptions.PedidoException;
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
	
	public Pedido grabarConId(Pedido p){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		PedidoEntity e = toEntity(p);
		session.saveOrUpdate(e);
		session.getTransaction().commit();
		session.close();

		Pedido pp = null;
		try {
			pp = buscar(p.getIdPedido());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (pp != null) {
			return pp;
		} else {
			return null;
		}
	}
	
	public List<Pedido> getAll() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<Pedido> resultado = new ArrayList<Pedido>();
		
		Query query = session.createQuery("from PedidoEntity");

		List<PedidoEntity> entidades = (List<PedidoEntity>) query.list();

		if (entidades != null) {
			for (PedidoEntity item : entidades) {
				resultado.add(toNegocio(item));
			}
		} //else
			//throw new ProductoException("Fallo en el listado de Productos");
		return resultado;
	}

	public int ultimoCodigoPedido() /*throws ProductoException*/ {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Integer ce = (Integer) session.createQuery("select isnull(max(ce.idPedido), 0) from PedidoEntity ce ")
				.uniqueResult();
		if (ce != null)
			return ce;
		//else
			//throw new ProductoException("No se pudo obtener un nuevo id de Producto válido");
		return ce;
	}
	
	public Pedido buscar(Integer idPedido) /* throws pedidoException */{
		Session session = null;
		Pedido ped = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			ped = session.load(Pedido.class, idPedido);
			Hibernate.initialize(ped);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return ped;
	}
}

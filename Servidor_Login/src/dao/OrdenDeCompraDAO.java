package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.OrdenDeCompraEntity;
import entity.OrdenRecepcionItemEntity;
import exceptions.OrdenDeCompraException;
import model.OrdenDeCompra;
import model.OrdenRecepcionItem;
import util.HibernateUtil;

public class OrdenDeCompraDAO {
	
	private static OrdenDeCompraDAO instancia;

	private OrdenDeCompraDAO() {
	}

	public static OrdenDeCompraDAO getInstancia() {
		if (instancia == null)
			instancia = new OrdenDeCompraDAO();
		return instancia;
	}
	
	
	
	public OrdenDeCompra toNegocio(OrdenDeCompraEntity orden) {
		OrdenDeCompra orde = new OrdenDeCompra();
		orde.setIdOrdenDeCompra(orden.getIdOrdenDeCompra());
		orde.setProducto(ProductoDAO.getInstancia().toNegocio(orden.getProducto()));
		orde.setFechaEmitida(orden.getFechaEmitida());
		orde.setOrdenActiva(orden.getOrdenActiva());
		orde.setCantidadOrdenada(orden.getCantidadOrdenada());
		

		if(orden.getRecepcionesDelProducto()!=null)
		{
			orde.setRecepcionesDelProducto(getItemsToNegocio(orden.getRecepcionesDelProducto()));
		}
		else
		{
			//System.out.println("ES null");
		}
		return orde;

	}
	
	private List<OrdenRecepcionItem> getItemsToNegocio(List<OrdenRecepcionItemEntity> items) {
		/*for(OrdenRecepcionItemEntity item : items)
		{
			System.out.println("ItemFor cant: "+item.getFecha() + "  Fecha: "+item.getFecha());
		}*/
		
		return items.stream().map(i -> {
			OrdenRecepcionItem orden = new OrdenRecepcionItem();
			orden.setIdOrdenRecepcionItem(i.getIdOrdenRecepcionItem());
			orden.setFecha(i.getFecha());
			orden.setCantidad(i.getCantidad());
			return orden;
		}).collect(Collectors.toList());
	}
	
	public OrdenDeCompraEntity toEntity(OrdenDeCompra orden) {
		
		OrdenDeCompraEntity orde = new OrdenDeCompraEntity();
		
		orde.setIdOrdenDeCompra(orden.getIdOrdenDeCompra());
		orde.setProducto(ProductoDAO.getInstancia().toEntity(orden.getProducto()));
		orde.setFechaEmitida(orden.getFechaEmitida());
		orde.setOrdenActiva(orden.getOrdenActiva());
		orde.setCantidadOrdenada(orden.getCantidadOrdenada());
		
		
		/*for(OrdenRecepcionItem item : orden.getRecepcionesDelProducto())
		{
			System.out.println("ItemFor cant: "+item.getFecha() + "  Fecha: "+item.getFecha());
		}*/
		
		if(orden.getRecepcionesDelProducto() != null)
		{
			orde.setRecepcionesDelProducto(getItemsToEntity(orden.getRecepcionesDelProducto()));
		}
		else
		{
			//System.out.println("ES null");
		}

		return orde;
	}
	
	private List<OrdenRecepcionItemEntity> getItemsToEntity(List<OrdenRecepcionItem> items) {
		return items.stream().map(i -> {
			OrdenRecepcionItemEntity entity = new OrdenRecepcionItemEntity();
			entity.setIdOrdenRecepcionItem(i.getIdOrdenRecepcionItem());
			entity.setFecha(i.getFecha());
			entity.setCantidad(i.getCantidad());
			return entity;
		}).collect(Collectors.toList());
	}
	
	public OrdenDeCompra save(OrdenDeCompra o) {
		
		OrdenDeCompra ret = null;
		
		//Le doy id a los items si no tienen
		try {
			List<OrdenRecepcionItem> lista = o.getRecepcionesDelProducto();
			Integer ultimoItem = ultimoCodigoOrdenRecepcionItem()+1;
			for(OrdenRecepcionItem item : lista)
			{
				if(item.getIdOrdenRecepcionItem() == null)
				{
					item.setIdOrdenRecepcionItem(ultimoItem);
					ultimoItem = ultimoItem +1;
				}
			}
			//Listo el pollo...
			o.setRecepcionesDelProducto(lista);
			//Cocinado la gallina :)
			
		} catch (OrdenDeCompraException e1) {
			e1.printStackTrace();
		}
		
		if (o.getIdOrdenDeCompra() != null) {
			ret = grabarConId(o);
		} else {
						
			try {
				//Se asume que aca es pedido nuevo
				int codOrd;
				codOrd = ultimoCodigoOrdenDeCompra()+1;
				o.setIdOrdenDeCompra(codOrd);
				ret = grabarConId(o);
				
			} catch (OrdenDeCompraException e) {
				e.printStackTrace();
			}
			
			
		}

		return ret;
	}
	
	public int ultimoCodigoOrdenDeCompra() throws OrdenDeCompraException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Integer ce = (Integer) session.createQuery("select isnull(max(ce.idOrdenDeCompra), 0) from OrdenDeCompraEntity ce ")
				.uniqueResult();
		if (ce != null)
			return ce;
		else
			throw new OrdenDeCompraException("No se pudo obtener un nuevo id de OrdenDeCompra válido");
	}
	
	public int ultimoCodigoOrdenRecepcionItem() throws OrdenDeCompraException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Integer ce = (Integer) session.createQuery("select isnull(max(ce.idOrdenRecepcionItem), 0) from OrdenRecepcionItemEntity ce ")
				.uniqueResult();
		if (ce != null)
			return ce;
		else
			throw new OrdenDeCompraException("No se pudo obtener un nuevo id de Item OrdenDeCompra válido");
	}
	
	public OrdenDeCompra grabarConId(OrdenDeCompra o){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		OrdenDeCompraEntity e = toEntity(o);
		session.saveOrUpdate(e);
		session.getTransaction().commit();
		session.close();

		OrdenDeCompra pp = null;
		try {
			pp = buscar(o.getIdOrdenDeCompra());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (pp != null) {
			return pp;
		} else {
			return null;
		}
	}
	
	public List<OrdenDeCompra> getAll() throws OrdenDeCompraException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<OrdenDeCompra> resultado = new ArrayList<OrdenDeCompra>();
		
		Query query = session.createQuery("from OrdenDeCompraEntity");

		@SuppressWarnings("unchecked")
		List<OrdenDeCompraEntity> entidades = (List<OrdenDeCompraEntity>) query.list();

		if (entidades != null) {
			for (OrdenDeCompraEntity item : entidades) {
				resultado.add(toNegocio(item));
			}
			return resultado;
		} else
			throw new OrdenDeCompraException("Fallo en el listado de Ordenes");
	}
	
	public List<OrdenDeCompra> getAllActivos() throws OrdenDeCompraException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<OrdenDeCompra> resultado = new ArrayList<OrdenDeCompra>();
		
		Query query = session.createQuery("from OrdenDeCompraEntity");

		@SuppressWarnings("unchecked")
		List<OrdenDeCompraEntity> entidades = (List<OrdenDeCompraEntity>) query.list();

		if (entidades != null) {
			for (OrdenDeCompraEntity item : entidades) {
				if(item.getOrdenActiva() == true)
				{
					resultado.add(toNegocio(item));
				}					
			}
			return resultado;
		} else
			throw new OrdenDeCompraException("Fallo en el listado de Ordenes");
	}
	
	public OrdenDeCompra buscar(Integer idOrdenDeCompra) throws OrdenDeCompraException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		OrdenDeCompraEntity pe = (OrdenDeCompraEntity) session.createQuery("from OrdenDeCompraEntity where idOrdenDeCompra = ?")
				.setParameter(0, idOrdenDeCompra).uniqueResult();
		if (pe != null) {
			return toNegocio(pe);
		} else
			throw new OrdenDeCompraException("La OrdenDeCompra solicitada no existe");
	}
	
}

package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.RemitoEntity;
import entity.RemitoItemEntity;
import exceptions.RemitoException;
import model.Remito;
import model.RemitoItem;
import util.HibernateUtil;

public class RemitoDAO {
	
	private static RemitoDAO instancia;
	
	private RemitoDAO() {
	}

	public static RemitoDAO getInstancia() {
		if (instancia == null)
			instancia = new RemitoDAO();
		return instancia;
	}
	
	public Remito toNegocio(RemitoEntity remito) {
		
		Remito remi = new Remito();
		remi.setIdRemito(remito.getIdRemito());
		remi.setFecha(remito.getFecha());
		
		if(remito.getProductosRecibidos()!=null)
		{
			remi.setProductosRecibidos(getItemsToNegocio(remito.getProductosRecibidos()));
		}
		else
		{
			//System.out.println("ES null");
		}
		return remi;

	}
	
	private List<RemitoItem> getItemsToNegocio(List<RemitoItemEntity> items) {
		/*for(RemitoItemEntity item : items)
		{
			System.out.println("ItemFor: " + item.getProducto().getDescripcion());
		}*/
		
		return items.stream().map(i -> {
			RemitoItem remito = new RemitoItem();
			remito.setIdRemitoItem(i.getIdRemitoItem());
			remito.setProducto(ProductoDAO.getInstancia().toNegocio(i.getProducto()));
			remito.setCantidad(i.getCantidad());
			return remito;
		}).collect(Collectors.toList());
	}
	
public RemitoEntity toEntity(Remito remito) {
		
		RemitoEntity remi = new RemitoEntity();
		
		remi.setIdRemito(remito.getIdRemito());
		remi.setFecha(remito.getFecha());
		
		if(remito.getProductosRecibidos()!=null)
		{
			remi.setProductosRecibidos(getItemsToEntity(remito.getProductosRecibidos()));
		}
		else
		{
			//System.out.println("ES null");
		}
		return remi;
	}
	
	private List<RemitoItemEntity> getItemsToEntity(List<RemitoItem> items) {
		return items.stream().map(i -> {
			RemitoItemEntity remito = new RemitoItemEntity();
			remito.setIdRemitoItem(i.getIdRemitoItem());
			remito.setProducto(ProductoDAO.getInstancia().toEntity(i.getProducto()));
			remito.setCantidad(i.getCantidad());
			return remito;
		}).collect(Collectors.toList());
	}
	
	public Remito save(Remito r) {
		
		Remito ret = null;
		
		//Le doy id a los items si no tienen
		try {
			List<RemitoItem> lista = r.getProductosRecibidos();
			Integer ultimoItem = ultimoCodigoItemRemito()+1;
			for(RemitoItem item : lista)
			{
				if(item.getIdRemitoItem() == null)
				{
					item.setIdRemitoItem(ultimoItem);
					ultimoItem = ultimoItem +1;
				}
			}
			//Listo el pollo...
			r.setProductosRecibidos(lista);
			//Cocinado la gallina :)
			
		} catch (RemitoException e1) {
			e1.printStackTrace();
		}
		
		if (r.getIdRemito() != null) {
			ret = grabarConId(r);
		} else {
						
			try {
				//Se asume que aca es remito nuevo
				int codRemi;
				codRemi = ultimoCodigoRemito()+1;
				r.setIdRemito(codRemi);
				ret = grabarConId(r);
				
			} catch (RemitoException e) {
				e.printStackTrace();
			}
			
			
		}

		return ret;
	}
	
	public int ultimoCodigoRemito() throws RemitoException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Integer ce = (Integer) session.createQuery("select isnull(max(ce.idRemito), 0) from RemitoEntity ce ")
				.uniqueResult();
		if (ce != null)
			return ce;
		else
			throw new RemitoException("No se pudo obtener un nuevo id de Remito válido");
	}
	
	public int ultimoCodigoItemRemito() throws RemitoException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Integer ce = (Integer) session.createQuery("select isnull(max(ce.idRemitoItem), 0) from RemitoItemEntity ce ")
				.uniqueResult();
		if (ce != null)
			return ce;
		else
			throw new RemitoException("No se pudo obtener un nuevo id de Item Remito válido");
	}
	
	public Remito grabarConId(Remito r){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		RemitoEntity e = toEntity(r);
		session.saveOrUpdate(e);
		session.getTransaction().commit();
		session.close();

		Remito pp = null;
		try {
			pp = buscar(r.getIdRemito());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (pp != null) {
			return pp;
		} else {
			return null;
		}
	}
	
	public List<Remito> getAll() throws RemitoException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<Remito> resultado = new ArrayList<Remito>();
		
		Query query = session.createQuery("from RemitoEntity");

		@SuppressWarnings("unchecked")
		List<RemitoEntity> entidades = (List<RemitoEntity>) query.list();

		if (entidades != null) {
			for (RemitoEntity item : entidades) {
				resultado.add(toNegocio(item));
			}
			return resultado;
		} else
			throw new RemitoException("Fallo en el listado de Remitos");
	}
	
	public Remito buscar(Integer idRemito) throws RemitoException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		RemitoEntity pe = (RemitoEntity) session.createQuery("from RemitoEntity where idRemito = ?")
				.setParameter(0, idRemito).uniqueResult();
		if (pe != null) {
			return toNegocio(pe);
		} else
			throw new RemitoException("El remito solicitado no existe");
	}
	
}

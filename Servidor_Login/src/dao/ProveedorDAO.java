package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.ProveedorEntity;
import exceptions.ProveedorException;
import model.Proveedor;
import util.HibernateUtil;

public class ProveedorDAO {

	private static ProveedorDAO instancia;
	
	private ProveedorDAO() {
	}
	
	public static ProveedorDAO getInstancia() {
		if (instancia == null)
			instancia = new ProveedorDAO();
		return instancia;
	}
	
	
	public Proveedor toNegocio(ProveedorEntity entity) {
		Proveedor model = new Proveedor();
		model.setIdProveedor(entity.getIdProveedor());
		model.setNombre(entity.getNombre());
		model.setUrl(entity.getUrl());
		model.setApiKey(entity.getApiKey());
		model.setEstadoActivo(entity.getEstadoActivo());
		return model;

	}
	
	
	public ProveedorEntity toEntity(Proveedor model) {
		ProveedorEntity entity = new ProveedorEntity();
		entity.setIdProveedor(model.getIdProveedor());
		entity.setNombre(model.getNombre());
		entity.setUrl(model.getUrl());
		entity.setApiKey(model.getApiKey());
		entity.setEstadoActivo(model.getEstadoActivo());
		return entity;

	}
	
	public Proveedor save(Proveedor p){
		System.out.println("Entre a Save");
		Proveedor ret = null;
		if (p.getIdProveedor() != null) {
			System.out.println("2222 Pase por aca");
			ret = grabarConId(p);
		} else {
						
			try {
				System.out.println("Pase por aca");
				int codProve;
				codProve = ultimoCodigoProveedor()+1;
				p.setIdProveedor(codProve);
				ret = grabarConId(p);
			} catch (ProveedorException e) {
				System.out.println("3333  Pase por aca");
				e.printStackTrace();
			}
			
			
		}

		return ret;

	}
	
	public int ultimoCodigoProveedor() throws ProveedorException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Integer ce = (Integer) session.createQuery("select isnull(max(ce.idProveedor), 0) from ProveedorEntity ce ")
				.uniqueResult();
		if (ce != null) {
			return ce;
		} else
			throw new ProveedorException("No se pudo obtener un nuevo id de Proveedor válido");
	}
	
	public Proveedor grabarConId(Proveedor p){
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		ProveedorEntity e = toEntity(p);
		session.saveOrUpdate(e);
		session.getTransaction().commit();
		session.close();
		
		Proveedor pp = null;
		try {
			pp = buscar(p.getIdProveedor());
		} catch (ProveedorException e1) {
			e1.printStackTrace();
		}

		if (pp != null) {
			return pp;
		} else {
			return null;
		}
	}
	
	public Proveedor buscar(Integer idProveedor) throws ProveedorException {
		
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ProveedorEntity pe = (ProveedorEntity) session.createQuery("from ProveedorEntity where idProveedor = ?")
				.setParameter(0, idProveedor).uniqueResult();
		if (pe != null) {
			return toNegocio(pe);
		} else
			throw new ProveedorException("El proveedor solicitado no existe");
			
		
	}
	
	public List<Proveedor> getAll() throws ProveedorException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<Proveedor> resultado = new ArrayList<Proveedor>();
		
		Query query = session.createQuery("from ProveedorEntity");

		@SuppressWarnings("unchecked")
		List<ProveedorEntity> entidades = (List<ProveedorEntity>) query.list();

		if (entidades != null) {
			for (ProveedorEntity item : entidades) {
				resultado.add(toNegocio(item));
			}

			return resultado;
		} else
			throw new ProveedorException("Fallo en el listado de Proveedores");
	}
	
	
}

package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.ProductoEntity;
import exceptions.ProductoException;
import model.Producto;
import util.HibernateUtil;

public class ProductoDAO {
	
	
	private static ProductoDAO instancia;

	private ProductoDAO() {
	}

	public static ProductoDAO getInstancia() {
		if (instancia == null)
			instancia = new ProductoDAO();
		return instancia;
	}

	public Producto toNegocio(ProductoEntity producto) {

		Producto produ = new Producto();
		produ.setIdProducto(producto.getIdProducto());
		produ.setCodigoBarras(producto.getCodigoBarras());
		produ.setDescripcion(producto.getDescripcion());
		produ.setPrecioVenta(producto.getPrecioVenta());
		produ.setCantFijaCompra(producto.getCantFijaCompra());
		produ.setCantMinimaStock(producto.getCantMinimaStock());
		produ.setStockActual(producto.getStockActual());
		produ.setEstadoActivo(producto.getEstadoActivo());
		//produ.setFoto(producto.getFoto());
		
		return produ;

	}

	public ProductoEntity toEntity(Producto producto) {
		
		ProductoEntity produ = new ProductoEntity();
		produ.setIdProducto(producto.getIdProducto());
		produ.setCodigoBarras(producto.getCodigoBarras());
		produ.setDescripcion(producto.getDescripcion());
		produ.setPrecioVenta(producto.getPrecioVenta());
		produ.setCantFijaCompra(producto.getCantFijaCompra());
		produ.setCantMinimaStock(producto.getCantMinimaStock());
		produ.setStockActual(producto.getStockActual());
		produ.setEstadoActivo(producto.getEstadoActivo());

		return produ;
	}
	
	public Producto save(Producto p){
		
		Producto ret = null;
		if (p.getIdProducto() != null) {
			ret = grabarConId(p);
		} else {
						
			try {
				int codProdu;
				codProdu = ultimoCodigoProducto()+1;
				p.setIdProducto(codProdu);
				ret = grabarConId(p);
			} catch (ProductoException e) {
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
	
	public Producto grabarConId(Producto p){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		ProductoEntity e = toEntity(p);
		session.saveOrUpdate(e);
		session.getTransaction().commit();
		session.close();

		Producto pp = null;
		try {
			pp = buscar(p.getIdProducto());
		} catch (ProductoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (pp != null) {
			return pp;
		} else {
			return null;
		}
	}
	
	public Producto buscar(Integer idProducto) throws ProductoException {
		
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ProductoEntity pe = (ProductoEntity) session.createQuery("from ProductoEntity where idProducto = ?")
				.setParameter(0, idProducto).uniqueResult();
		if (pe != null) {
			return toNegocio(pe);
		} else
			throw new ProductoException("El producto solicitado no existe");
			
		
	}
	
	public List<Producto> getAll() throws ProductoException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<Producto> resultado = new ArrayList<Producto>();
		
		Query query = session.createQuery("from ProductoEntity");

		@SuppressWarnings("unchecked")
		List<ProductoEntity> entidades = (List<ProductoEntity>) query.list();

		if (entidades != null) {
			for (ProductoEntity item : entidades) {
				resultado.add(toNegocio(item));
			}

			return resultado;
		} else
			throw new ProductoException("Fallo en el listado de Productos");
	}
	
	
}

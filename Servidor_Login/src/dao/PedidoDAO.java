package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.ClienteTiendaEntity;
import entity.DireccionClienteEntity;
import entity.PedidoEntity;
import entity.PedidoItemEntity;
import exceptions.PedidoException;
import model.ClienteTienda;
import model.DireccionCliente;
import model.Pedido;
import model.PedidoItem;
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
		
		pedi.setCliente( clienteToModel( pedido.getCliente() ));
		pedi.setDireccion(direccionToModel( pedido.getDireccion() ));
		pedi.setFragil(pedido.getFragil());
		
		if(pedido.getItems()!=null)
		{
			pedi.setItems(getItems(pedido.getItems()));
		}
		else
		{
			//System.out.println("ES null");
		}
		return pedi;

	}
	
	private List<PedidoItem> getItems(List<PedidoItemEntity> items) {
		/*for(PedidoItemEntity item : items)
		{
			System.out.println("ItemFor: " + item.getProducto().getDescripcion());
		}*/
		
		return items.stream().map(i -> {
			PedidoItem pedido = new PedidoItem();
			pedido.setIdPedidoItem(i.getIdPedidoItem());
			pedido.setProducto(ProductoDAO.getInstancia().toNegocio(i.getProducto()));
			pedido.setCantidad(i.getCantidad());
			return pedido;
		}).collect(Collectors.toList());
	}
	
	public ClienteTienda clienteToModel (ClienteTiendaEntity entity) {
		ClienteTienda model = new ClienteTienda();
		model.setIdClienteTienda(entity.getIdClienteTienda());
		model.setCuil_cuit_dni( entity.getCuil_cuit_dni() );
		model.setNombreYApellido_RazonSocial(entity.getNombreYApellido_RazonSocial());
		model.setEmail(entity.getEmail());
		return model;
	}
	
	public DireccionCliente direccionToModel (DireccionClienteEntity entity) {
		DireccionCliente model = new DireccionCliente();
		model.setIdDireccionCliente(entity.getIdDireccionCliente());
		model.setCalle(entity.getCalle());
		model.setNumero(entity.getNumero());
		model.setPiso(entity.getPiso());
		model.setUnidad(entity.getUnidad());
		model.setEntreCalles(entity.getEntreCalles());
		model.setProvincia(entity.getProvincia());
		model.setLocalidad(entity.getLocalidad());
		model.setCodigoPostal(entity.getCodigoPostal());
		return model;
	}
	

	public PedidoEntity toEntity(Pedido pedido) {
		
		PedidoEntity pedi = new PedidoEntity();
		
		pedi.setIdPedido(pedido.getIdPedido());
		pedi.setFecha(pedido.getFecha());
		pedi.setEstadoPedido(pedido.getEstadoPedido());
		pedi.settPersonaYfLogistica(pedido.getTPersonaYfLogistica());
		
		pedi.setCliente(getClienteToEntity(pedido.getCliente()));
		pedi.setDireccion(getDireccionToEntity(pedido.getDireccion()));
		pedi.setFragil(pedido.getFragil());
		
		/*for(PedidoItem item : pedido.getItems())
		{
			System.out.println("item: " + item.getProducto().getDescripcion());
		}*/
		
		if(pedido.getItems() != null)
		{
			pedi.setItems(getItemsToEntity(pedido.getItems()));
		}
		else
		{
			//System.out.println("ES null");
		}

		return pedi;
	}
	
	private List<PedidoItemEntity> getItemsToEntity(List<PedidoItem> items) {
		return items.stream().map(i -> {	
			PedidoItemEntity entity = new PedidoItemEntity();
			entity.setIdPedidoItem(i.getIdPedidoItem());
			entity.setCantidad(i.getCantidad());
			entity.setProducto(ProductoDAO.getInstancia().toEntity(i.getProducto()));
			return entity;
		}).collect(Collectors.toList());
	}
	
	private ClienteTiendaEntity getClienteToEntity(ClienteTienda cliente) {
		ClienteTiendaEntity entity = new ClienteTiendaEntity();
		entity.setIdClienteTienda(cliente.getIdClienteTienda());
		entity.setCuil_cuit_dni( cliente.getCuil_cuit_dni() );
		entity.setNombreYApellido_RazonSocial(cliente.getNombreYApellido_RazonSocial());
		entity.setEmail(cliente.getEmail());
		return entity;
	}
	
	private DireccionClienteEntity getDireccionToEntity ( DireccionCliente direccion) {
		DireccionClienteEntity entity = new DireccionClienteEntity();
		entity.setIdDireccionCliente(direccion.getIdDireccionCliente());
		entity.setCalle(direccion.getCalle());
		entity.setNumero(direccion.getNumero());
		entity.setPiso(direccion.getPiso());
		entity.setUnidad(direccion.getUnidad());
		entity.setEntreCalles(direccion.getEntreCalles());
		entity.setProvincia(direccion.getProvincia());
		entity.setLocalidad(direccion.getLocalidad());
		entity.setCodigoPostal(direccion.getCodigoPostal());
		return entity;
	}
	
	public Pedido save(Pedido p) {
		
		Pedido ret = null;
		
		//Le doy id a los items si no tienen
		try {
			List<PedidoItem> lista = p.getItems();
			ClienteTienda cliente = p.getCliente();
			DireccionCliente direccion = p.getDireccion();
			Integer ultimoItem = ultimoCodigoItemPedido()+1;
			Integer ultimoCliente = ultimoCodigoCliente()+1;
			Integer ultimaDireccion = ultimoCodigoDireccionCliente()+1;
			for(PedidoItem item : lista)
			{
				if(item.getIdPedidoItem() == null)
				{
					item.setIdPedidoItem(ultimoItem);
					ultimoItem = ultimoItem +1;
				}
			}
			if(cliente.getIdClienteTienda() == null) {
				cliente.setIdClienteTienda(ultimoCliente);
				ultimoCliente = ultimoCliente + 1;				
			}
			if(direccion.getIdDireccionCliente() == null) {
				direccion.setIdDireccionCliente(ultimaDireccion);
				ultimaDireccion = ultimaDireccion + 1;
			}
			
			//Listo el pollo...
			p.setItems(lista);
			p.setCliente(cliente);
			p.setDireccion(direccion);
			//Cocinado la gallina :)
			
		} catch (PedidoException e1) {
			e1.printStackTrace();
		}
		
		if (p.getIdPedido() != null) {
			ret = grabarConId(p);
		} else {
						
			try {
				//Se asume que aca es pedido nuevo
				int codPedi;
				codPedi = ultimoCodigoPedido()+1;
				p.setIdPedido(codPedi);
				ret = grabarConId(p);
				
			} catch (PedidoException e) {
				e.printStackTrace();
			}
			
			
		}

		return ret;
	}
	
	public int ultimoCodigoPedido() throws PedidoException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Integer ce = (Integer) session.createQuery("select isnull(max(ce.idPedido), 0) from PedidoEntity ce ")
				.uniqueResult();
		if (ce != null)
			return ce;
		else
			throw new PedidoException("No se pudo obtener un nuevo id de Pedido válido");
	}
	
	public int ultimoCodigoItemPedido() throws PedidoException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Integer ce = (Integer) session.createQuery("select isnull(max(ce.idPedidoItem), 0) from PedidoItemEntity ce ")
				.uniqueResult();
		if (ce != null)
			return ce;
		else
			throw new PedidoException("No se pudo obtener un nuevo id de Item Pedido válido");
	}
	
	public int ultimoCodigoCliente() throws PedidoException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Integer ce = (Integer) session.createQuery("select isnull(max(ce.idClienteTienda), 0) from ClienteTiendaEntity ce ")
				.uniqueResult();
		if (ce != null)
			return ce;
		else
			throw new PedidoException("No se pudo obtener un nuevo id de ClienteTienda válido");
	}
	
	public int ultimoCodigoDireccionCliente() throws PedidoException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Integer ce = (Integer) session.createQuery("select isnull(max(ce.idDireccionCliente), 0) from DireccionClienteEntity ce ")
				.uniqueResult();
		if (ce != null)
			return ce;
		else
			throw new PedidoException("No se pudo obtener un nuevo id de Direccion válido");
	}
	
	public Pedido grabarConId(Pedido p){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		PedidoEntity e = toEntity(p);
		//System.out.println("idPedido: " + e.getIdPedido() + "   " + e.getItems().get(1).getProducto().getProveedor().getUrl());
		session.saveOrUpdate(e);
		session.getTransaction().commit();
		session.close();

		Pedido pp = null;
		try {
			pp = buscar(p.getIdPedido());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (pp != null) {
			return pp;
		} else {
			return null;
		}
	}
	
	public List<Pedido> getAll() throws PedidoException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<Pedido> resultado = new ArrayList<Pedido>();
		
		Query query = session.createQuery("from PedidoEntity");

		@SuppressWarnings("unchecked")
		List<PedidoEntity> entidades = (List<PedidoEntity>) query.list();

		if (entidades != null) {
			for (PedidoEntity item : entidades) {
				resultado.add(toNegocio(item));
			}
			return resultado;
		} else
			throw new PedidoException("Fallo en el listado de Pedidos");
	}
	
	public Pedido buscar(Integer idPedido) throws PedidoException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		PedidoEntity pe = (PedidoEntity) session.createQuery("from PedidoEntity where idPedido = ?")
				.setParameter(0, idPedido).uniqueResult();
		if (pe != null) {
			return toNegocio(pe);
		} else
			throw new PedidoException("El pedido solicitado no existe");
	}
}

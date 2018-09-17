package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ClienteEntity;
import exceptions.ClienteException;
import util.HibernateUtil;
import negocio.Cliente;

public class ClienteDAO {

	private static ClienteDAO instancia;

	private ClienteDAO() {
	}

	public static ClienteDAO getInstance() {
		if (instancia == null)
			instancia = new ClienteDAO();
		return instancia;
	}

	public Cliente toNegocio(ClienteEntity cliente) {

		Cliente cli = new Cliente();
		cli.setCodigocliente(cliente.getCodigocliente());
		cli.setNombre(cliente.getNombre());
		cli.setApellido(cliente.getApellido());
		cli.setCuit_cuil(cliente.getCuit_cuil());
		cli.setResponsableinscripto(cliente.isResponsableinscripto());
		cli.setCondicionespago(cliente.getCondicionespago());
		cli.setAdvertencias(cliente.getAdvertencias());
		cli.setRazonsocial(cliente.getRazonsocial());
		cli.setDireccion(cliente.getDireccion());
		cli.setEmail(cliente.getEmail());
		cli.setDescuentos(cliente.getDescuentos());
		cli.setActivo(cliente.isActivo());
		
		return cli;

	}

	public ClienteEntity toEntity(Cliente cliente) {
		
		ClienteEntity cli = new ClienteEntity();
		cli.setCodigocliente(cliente.getCodigocliente());
		cli.setNombre(cliente.getNombre());
		cli.setApellido(cliente.getApellido());
		cli.setCuit_cuil(cliente.getCuit_cuil());
		cli.setResponsableinscripto(cliente.isResponsableinscripto());
		cli.setCondicionespago(cliente.getCondicionespago());
		cli.setAdvertencias(cliente.getAdvertencias());
		cli.setRazonsocial(cliente.getRazonsocial());
		cli.setDireccion(cliente.getDireccion());
		cli.setEmail(cliente.getEmail());
		cli.setDescuentos(cliente.getDescuentos());
		cli.setActivo(cliente.isActivo());

		return cli;
	}

	/*
	 * Guardar y Update
	 */
	public Integer grabar(Cliente c) throws ClienteException {

		Integer ret = null;

		if (c.getCodigocliente() != null) {
			ret = grabarConId(c);
		} else {
			int codCli = ultimoCodigoCliente();
			c.setCodigocliente(codCli);
			ret = grabarConId(c);
		}

		return ret;

	}

	public Integer grabarConId(Cliente c) throws ClienteException {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(toEntity(c));
		session.getTransaction().commit();
		session.close();

		Cliente cc = null;
		cc = buscar(c.getCodigocliente());

		if (cc != null) {
			return cc.getCodigocliente();
		} else {
			return null;
		}
	}

	/*
	 * public Integer grabarSinId(Cliente c, int codCli) throws ClienteException
	 * {
	 * 
	 * ClienteEntity cli = new ClienteEntity(c.getNombre(), c.getApellido(),
	 * c.getCuit_cuil(), c.isResponsableinscripto(), c.getDireccion(),
	 * c.isActivo()); SessionFactory sf = HibernateUtil.getSessionFactory();
	 * Session session = sf.openSession(); session.beginTransaction();
	 * session.saveOrUpdate(cli); session.getTransaction().commit();
	 * session.close();
	 * 
	 * Cliente cc = null; cc = buscarId(c.getCodigocliente());
	 * 
	 * if (cc != null) { return cc.getCodigocliente(); } else { return null; } }
	 */
	/*
	 * 
	 */

	/*
	 * Búsquedas
	 */

	public Cliente buscar(Integer idCliente) throws ClienteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ClienteEntity ce = (ClienteEntity) session.createQuery("from ClienteEntity where codigocliente = ?")
				.setParameter(0, idCliente).uniqueResult();
		if (ce != null) {
			return toNegocio(ce);
		} else
			throw new ClienteException("El cliente solicitado no existe");
	}
	
	public Cliente buscarPorUsuario(Integer idUsuario) throws ClienteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ClienteEntity ce = (ClienteEntity) session.createQuery("from ClienteEntity where idUsuario = ?")
				.setParameter(0, idUsuario).uniqueResult();
		if (ce != null) {
			return toNegocio(ce);
		} else
			throw new ClienteException("El cliente solicitado no existe");
	}

	public int ultimoCodigoCliente() throws ClienteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Integer ce = (Integer) session.createQuery("select isnull(max(ce.codigocliente), 0) from ClienteEntity ce ")
				.uniqueResult();
		if (ce != null) {
			return ce + 1;
		} else
			throw new ClienteException("No se pudo obtener un nuevo id de Cliente válido");
	}

	/*
	 * 
	 */

	/*
	 * Listados
	 */
	public List<Cliente> listar() throws ClienteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<Cliente> resultado = new ArrayList<Cliente>();
		Cliente aux = null;

		Query query = session.createQuery("from ClienteEntity");

		List<ClienteEntity> entidades = (List<ClienteEntity>) query.list();

		if (entidades != null) {
			for (ClienteEntity item : entidades) {
				resultado.add(toNegocio(item));
			}

			return resultado;
		} else
			throw new ClienteException("Fallo en el listado de Clientes");
	}
	/*
	 * 
	 */

}

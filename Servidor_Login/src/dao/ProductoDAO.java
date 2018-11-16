package dao;

//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;

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
		produ.setFragil(producto.getFragil());
		produ.setProveedor(ProveedorDAO.getInstancia().toNegocio( producto.getProveedor() ));
		
		//produ.setFoto(buscarFoto(producto.getIdProducto()));
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
		produ.setFragil(producto.getFragil());
		produ.setProveedor(ProveedorDAO.getInstancia().toEntity( producto.getProveedor() ));
		
		//produ.setFoto(producto.getFoto());
		
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
		
		/*if(p.getFoto() == null)
		{
			p.setFoto(new ImageIcon(getClass().getResource("/Fotos/_Default.jpg")));
			File file = new File("/Fotos/_Default.jpg");
	        byte[] bFile = new byte[(int) file.length()];
		}*/
			
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		ProductoEntity e = toEntity(p);
		session.saveOrUpdate(e);
		session.getTransaction().commit();
		session.close();
		
		/*if(p.getFoto() != null)
		{
			// get image from imageicon
			Image image = p.getFoto().getImage();

			// cast it to bufferedimage
			BufferedImage buffered = (BufferedImage) image;

			try {
			    // save to file
			    File outputfile = new File("/Fotos/" + p.getIdProducto() + ".jpg");
			    ImageIO.write(buffered, "jpg", outputfile);
			} catch (IOException eio) {
			    eio.printStackTrace();
			}
		}
		else
		{
			System.out.println("Hola");
			ImageIcon defaultFoto = new ImageIcon(getClass().getResource("/Fotos/_Default.jpg"));
			Image img = defaultFoto.getImage();

			BufferedImage bi = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_RGB);

			Graphics2D g2 = bi.createGraphics();
			g2.drawImage(img, 0, 0, null);
			g2.dispose();
			try {
				ImageIO.write(bi, "jpg", new File("./src/Fotos/1.jpg"));
				System.out.println("Chau");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}*/
		

		Producto pp = null;
		try {
			pp = buscar(p.getIdProducto());
		} catch (ProductoException e1) {
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
	
	/*public ImageIcon buscarFoto(Integer id) {
		ImageIcon foto = null;
		System.out.println("id: " + id);
		String testPath = this.getClass().getResource("/Users/tomas/git/ida/Servidor_Login/bin/").getPath();
		System.out.println(testPath);
		if(id!=null)
		{
			try {
				foto = new ImageIcon(ImageIO.read(getClass().getResource("/Fotos/1.jpg")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			//foto = new ImageIcon(getClass().getResource("/Fotos/" + id + ".jpg"));
		}
		return foto;
	}*/
			
}

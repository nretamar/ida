package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import entity.ClienteEntity;
import entity.PedidoEntity;
import entity.PedidoItemEntity;
import entity.ProductoEntity;

public class HibernateUtil
{
    private static final SessionFactory sessionFactory;

    static
    {
        try
        {
        	 AnnotationConfiguration config = new AnnotationConfiguration();
             config.addAnnotatedClass(ClienteEntity.class);
             config.addAnnotatedClass(ProductoEntity.class);
             config.addAnnotatedClass(PedidoEntity.class);
             config.addAnnotatedClass(PedidoItemEntity.class);
             
             sessionFactory = config.buildSessionFactory();
        }
        catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}

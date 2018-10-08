package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import entity.ClienteEntity;
import entity.OrdenDeCompraEntity;
import entity.OrdenRecepcionItemEntity;
import entity.PedidoEntity;
import entity.PedidoItemEntity;
import entity.ProductoEntity;
import entity.RemitoEntity;
import entity.RemitoItemEntity;

public class HibernateUtil
{
    private static final SessionFactory sessionFactory;

    static
    {
        try
        {
        	 AnnotationConfiguration config = new AnnotationConfiguration();
             config.addAnnotatedClass(ClienteEntity.class);
             //config.addAnnotatedClass(OrdenDeCompraEntity.class);
             //config.addAnnotatedClass(OrdenRecepcionItemEntity.class);
             config.addAnnotatedClass(PedidoEntity.class);
             config.addAnnotatedClass(PedidoItemEntity.class);
             config.addAnnotatedClass(ProductoEntity.class);
             //config.addAnnotatedClass(RemitoEntity.class);
             //config.addAnnotatedClass(RemitoItemEntity.class);
             
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

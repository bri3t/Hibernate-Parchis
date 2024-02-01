package proba;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import java.util.List;

import ambDAO.VehicleDAO;
import model.*;

public class main {
	
	static Session session;
	static SessionFactory sessionFactory;
	static ServiceRegistry serviceRegistry;
	
	public static synchronized SessionFactory getSessionFactory () {
		if (sessionFactory == null) {

			serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

			sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		}
		return sessionFactory;
	}

	public static void main(String[] args) {
		try {
			session = getSessionFactory().openSession();			
			
			Partida partida1 = new Partida();
			
			session.beginTransaction();
			session.getTransaction().commit();

			
					
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			if (null != session.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				session.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		
			
		}
		
	}

}

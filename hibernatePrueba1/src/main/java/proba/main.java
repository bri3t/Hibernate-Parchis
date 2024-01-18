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
			VehicleDAO vehicleDAO = new VehicleDAO();
			session = getSessionFactory().openSession();			
			
			Vehicle v1 = new Vehicle("123", "marca-1", "model-1", 2024);
			
			session.beginTransaction();
			
			vehicleDAO.saveOrUpdate(v1);
			session.getTransaction().commit();

//			DESCOMENTAR PARA ELIMINAR
//			vehicleDAO.delete(v1);
//			session.getTransaction().commit();
			
//			No funcionan los gets :(
			List<Vehicle> vehicles = vehicleDAO.get(v1.getMarca());
			System.out.println(vehicles.toString());				
//			
//			List<Vehicle> vehicles = vehicleDAO.get(v1.getMarca(), v1.getModel());
//			System.out.println(vehicles.toString());
			
//			List<Vehicle> vehicles = vehicleDAO.get(v1.getAnyFabricacio());
//			System.out.println(vehicles.toString());
			
					
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

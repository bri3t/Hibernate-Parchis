package ambDAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import model.Jugador;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class VehicleDAO implements IVehicleDAO {
	SessionFactory sessionFactory;
	
	public VehicleDAO() {
		sessionFactory = Utils.getSessionFactory();
	}

	@Override
	public void saveOrUpdate(Jugador vehicle) {
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			session.saveOrUpdate(vehicle);
			session.getTransaction().commit();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			if (session != null && session.getTransaction() != null) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Jugador vehicle) {
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			session.delete(vehicle);
			session.getTransaction().commit();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			if (session != null && session.getTransaction() != null) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
				
	}

	@Override
	public Jugador getVehicle(String vin) {	
		return null;
	}

	@Override
	public List<Jugador> get(String marca) {
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			List<Jugador> vehicles = session.createQuery("SELECT e FROM Vehicle e "
					+ "WHERE marca = '" + marca +"'" ).list();
			
			return vehicles;
		} catch (HibernateException e) {
			e.printStackTrace();
			if (session != null && session.getTransaction() != null) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				session.getTransaction().rollback();
			}
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public List<Jugador> get(String marca, String model) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			List<Jugador> vehicles = session.createQuery("SELECT e FROM Vehicle e "
					+ "WHERE marca = " + marca + " AND model = " + model).list();
			
			return vehicles;
		} catch (HibernateException e) {
			e.printStackTrace();
			if (session != null && session.getTransaction() != null) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				session.getTransaction().rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Jugador> get(int anyFabricacio) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			List<Jugador> vehicles = session.createQuery("SELECT e FROM Vehicle e "
					+ "WHERE any_fabricacio = " + anyFabricacio + "").list();
			
			return vehicles;
		} catch (HibernateException e) {
			e.printStackTrace();
			if (session != null && session.getTransaction() != null) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				session.getTransaction().rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

}
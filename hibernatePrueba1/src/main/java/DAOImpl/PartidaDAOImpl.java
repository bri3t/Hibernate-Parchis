package DAOImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import ambDAO.PartidaDAO;
import java.sql.Date;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import model.*;

public class PartidaDAOImpl implements PartidaDAO{

	private Session session;
	
	public PartidaDAOImpl(Session session) {
		this.session = session;
	}
	
	List<Integer>salvens = Arrays.asList(5,12,17,22,29,34,39,46,51,56,63,68);

	@Override
	public void iniciarPartida(int num) {
		// Obtener la fecha y hora actuales
        java.util.Date fechaYHoraActual = new java.util.Date();
        
        // Convertir la fecha y hora actuales a un objeto java.sql.Date
        java.sql.Date fechaSql = new java.sql.Date(fechaYHoraActual.getTime());
        Partida p = new Partida(num, fechaSql, true);
		session.saveOrUpdate(p);
		
		for (int i = 1; i <= 68; i++) {
			Casella c = new Casella();
			c.setPosicio(i);
			String tipus = "normal";
			if (salvens.contains(i)) tipus = "salvens";
			c.setTipusCasella(tipus);
			c.setPartida(p);
			session.saveOrUpdate(c);
		}
        session.beginTransaction();
		session.getTransaction().commit();

	}

	@Override
	public void verificarVictoria() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}

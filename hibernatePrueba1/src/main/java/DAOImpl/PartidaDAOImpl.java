package DAOImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import ambDAO.PartidaDAO;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import model.*;

public class PartidaDAOImpl implements PartidaDAO{

	private Session session;
	private List<Jugador> listJugadors = new ArrayList<Jugador>();
	
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
		
		Casella cInicial = new Casella();
		for (int i = 0; i <= 68; i++) {
			Casella c = new Casella();
			c.setPosicio(i);
			String tipus = "normal";
			if (salvens.contains(i)) tipus = "salvens";
			c.setTipusCasella(tipus);
			c.setPartida(p);
			if (i == 0) {
				c.setTipusCasella("casa");
				cInicial = c;				
			}
			session.saveOrUpdate(c);
		}
		
		Query query = session.createNativeQuery("SELECT * FROM jugador", Jugador.class);
		this.listJugadors = query.list();
		int indexJugador = 0;
		for (int i = 1; i <= 4 * num; i++) {
			Fitxa fitxa = new Fitxa();
			fitxa.setActive(false);
			fitxa.setCasella(cInicial);
			fitxa.setJugador(this.listJugadors.get(indexJugador));
			fitxa.setPartida(p);
			if (i % 4 == 0) {
				indexJugador ++;
			}
			session.saveOrUpdate(fitxa);
		}
        session.beginTransaction();
		session.getTransaction().commit();
		
		jugarPartida();
	}
	
	@Override
	public void jugarPartida() {
		JugadorDAOImpl jugadorDAO = new JugadorDAOImpl(session);
		int indexJugador = 0;
		int i = 0;
		while(true) {
			jugadorDAO.tirarDaus(this.listJugadors.get(indexJugador));
			indexJugador++;
			if (indexJugador == listJugadors.size()) indexJugador = 0;
			i++;
			if (i == 50) {
				System.out.println("fin");
				System.exit(0);
			}
		}
		
	}

	@Override
	public void verificarVictoria() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}

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
	private List<Integer>salvens = Arrays.asList(5,12,17,22,29,34,39,46,51,56,63,68);
	Partida p;
	
	public PartidaDAOImpl(Session session) {
		this.session = session;
	}
	
	@Override
	public void iniciarPartida(int num) {
		// Obtener la fecha y hora actuales
        java.util.Date fechaYHoraActual = new java.util.Date();
        
        // Convertir la fecha y hora actuales a un objeto java.sql.Date
        java.sql.Date fechaSql = new java.sql.Date(fechaYHoraActual.getTime());
        
        p = new Partida(num, fechaSql, true);
		session.save(p);
		
		Casella cInicial = new Casella();
		
		for (int i = 0; i <= 69; i++) {
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
			if (i == 69) {
				c.setTipusCasella("final");
			}
			session.saveOrUpdate(c);
		}
		
		Query query = session.createNativeQuery("SELECT * FROM jugador", Jugador.class);
		this.listJugadors = query.list();
		int indexJugador = 0;
		for (int i = 1; i <= 4 * num; i++) {
			Fitxa fitxa = new Fitxa();
			fitxa.setActive(false);
			fitxa.setPasos(0);
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
		JugadorDAOImpl jugadorDAO = new JugadorDAOImpl(session, p);
		int indexJugador = 0;
		int i = 0;
		while(true) {
			jugadorDAO.tirarDaus(this.listJugadors.get(indexJugador));
			if (verificarVictoria(this.listJugadors.get(indexJugador))){
				Jugador j = this.listJugadors.get(indexJugador);
				System.out.println("Partida acabada");
				System.out.println("Ha guanyat el " + j.getNom());
				p.setJugadorGuanyador(j);
				p.setEnCurs(false);
		        java.util.Date fechaYHoraActual = new java.util.Date();
		        java.sql.Date fechaSql = new java.sql.Date(fechaYHoraActual.getTime());
				p.setDataFin(fechaSql);
				Query query = session.createNativeQuery("SELECT VICTORIES FROM jugador WHERE ID_JUGADOR = :idJ")
						.setParameter("idJ", j.getId_Jugador());
				int count = ((Number) query.getSingleResult()).intValue();
				this.listJugadors.get(indexJugador).setVictories(j.getVictories()+1);
				session.saveOrUpdate(p);
				session.saveOrUpdate(j);
				session.beginTransaction();
				session.getTransaction().commit();
				break;
			}
			indexJugador++;
			if (indexJugador == listJugadors.size()) indexJugador = 0;
				
		}
		
	}

	@Override
	public boolean verificarVictoria(Jugador jugador) {
		Query query = session.createNativeQuery("SELECT COUNT(*) FROM fitxa WHERE POSICIÓ = 70 AND ID_JUGADOR = :idj AND ID_PARTIDA = :idp")
				.setParameter("idj", jugador.getId_Jugador())
				.setParameter("idp", p.getId_Partida());
		int count = ((Number) query.getSingleResult()).intValue();
		if (count == 4) return true;
		
		return false;

	}
	
	
	
}

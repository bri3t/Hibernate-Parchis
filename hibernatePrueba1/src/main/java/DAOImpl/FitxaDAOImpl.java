package DAOImpl;

import org.hibernate.Query;
import org.hibernate.Session;

import ambDAO.FitxaDAO;
import model.Casella;
import model.Fitxa;
import model.Jugador;
import proba.main;
import org.hibernate.Session;


public class FitxaDAOImpl implements FitxaDAO{
	
    private Session session;

	
	public FitxaDAOImpl(Session session) {
		this.session = session;
	}

	
	@Override
	public void moureFitxa(Fitxa fitxa, int quantitat) {
		Query query = session.createNativeQuery("SELECT * FROM casella WHERE POSICIÓ = :posicio", Casella.class).setParameter("posicio", fitxa.getCasella().getPosicio()+quantitat);
		Casella c = (Casella) query.getSingleResult();
		fitxa.setCasella(c);
		session.saveOrUpdate(fitxa);
		session.beginTransaction();
		session.getTransaction().commit();
	}

	@Override
	public void capturarFitxa(Fitxa fitxa) {
		Query query = session.createNativeQuery("SELECT * FROM casella WHERE POSICIÓ = :pos", Casella.class).setParameter("pos", 0);
		Casella c = (Casella) query.getSingleResult();
		fitxa.setCasella(c);
		session.saveOrUpdate(fitxa);
		session.beginTransaction();
		session.getTransaction().commit();
	}

	@Override
	public void entradaAlTablero(Fitxa fitxa) {
		Query query = session.createNativeQuery("SELECT * FROM jugador WHERE ID_JUGADOR = :id", Jugador.class).setParameter("id", fitxa.getJugador().getId_Jugador());
		Jugador jugador = (Jugador) query.getSingleResult();
		int posicio = -1;
		if (jugador.getColor().equalsIgnoreCase("Groc")) posicio = 5;
		if (jugador.getColor().equalsIgnoreCase("Blau")) posicio = 22;
		if (jugador.getColor().equalsIgnoreCase("Vermell")) posicio = 39;
		if (jugador.getColor().equalsIgnoreCase("Verd")) posicio = 56;
		Query query_2 = session.createNativeQuery("SELECT * FROM casella WHERE POSICIÓ = :posicio", Casella.class).setParameter("posicio", posicio);
		Casella c = (Casella) query_2.getSingleResult();
		fitxa.setCasella(c);
		fitxa.setActive(true);
		session.saveOrUpdate(fitxa);
		session.beginTransaction();
		session.getTransaction().commit();
		
	}

	@Override
	public boolean verificarCasaSegura(int posicio) {
		boolean casaSegura = false;
		Query query = session.createNativeQuery("SELECT * FROM casella WHERE POSICIÓ = :posicio", Casella.class).setParameter("posicio", posicio);
		Casella c = (Casella) query.getSingleResult();
		if (c.getTipusCasella().equalsIgnoreCase("salvens")) {
			casaSegura = true;
		}
		return casaSegura;
	}

	@Override
	public boolean finalitzarRecorregut(Fitxa fitxa, int quantitat) {
		// TODO Auto-generated method stub
		return false;
	}

}

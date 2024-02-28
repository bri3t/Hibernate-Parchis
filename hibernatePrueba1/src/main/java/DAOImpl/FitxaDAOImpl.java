package DAOImpl;

import javax.persistence.criteria.CriteriaBuilder.Case;

import org.hibernate.Query;
import org.hibernate.Session;

import ambDAO.FitxaDAO;
import model.Casella;
import model.Fitxa;
import model.Jugador;
import model.Partida;
import proba.main;
import org.hibernate.Session;


public class FitxaDAOImpl implements FitxaDAO{
	
    private Session session;
    private Partida p;
	
	public FitxaDAOImpl(Session session, Partida p) {
		this.session = session;
		this.p = p;
	}

	
	@Override
	public void moureFitxa(Fitxa fitxa, int quantitat) {
		
		fitxa.setPasos(fitxa.getPasos()+quantitat);
		
		
		int aux = fitxa.getCasella().getPosicio()+quantitat;
		
		if (aux > 68) aux -= 68;
		
		
		
		Query queraay = session.createNativeQuery("SELECT PASOS FROM fitxa WHERE ID_FITXA = :id")
				.setParameter("id", fitxa.getId_Fitxa());
		int resultat = ((Number) queraay.getSingleResult()).intValue();
		
		if (resultat >= 68) {
			finalitzarRecorregut(fitxa);
		}else {
			
			Query query = session.createNativeQuery("SELECT * FROM casella WHERE POSICIÓ = :posicio AND ID_PARTIDA = :idp", Casella.class)
					.setParameter("posicio", aux)
					.setParameter("idp", p.getId_Partida());
			Casella c = (Casella) query.getSingleResult();
			fitxa.setCasella(c);
			fitxa.setPasos(aux);
			
		}
		session.saveOrUpdate(fitxa);
		session.beginTransaction();
		session.getTransaction().commit();
		
	}

	@Override
	public void capturarFitxa(Fitxa fitxa) {
		Query query = session.createNativeQuery("SELECT * FROM casella WHERE POSICIÓ = :pos AND POSICIÓ <> 69 AND ID_PARTIDA = :idp", Casella.class)
				.setParameter("pos", 0)
				.setParameter("idp", p.getId_Partida());
		Casella c = (Casella) query.getSingleResult();
		fitxa.setCasella(c);
		fitxa.setPasos(0);
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
		Query query_2 = session.createNativeQuery("SELECT * FROM casella WHERE POSICIÓ = :posicio AND ID_PARTIDA = :idp", Casella.class)
				.setParameter("posicio", posicio)
				.setParameter("idp", p.getId_Partida());
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
		Query query = session.createNativeQuery("SELECT * FROM casella WHERE POSICIÓ = :posicio AND ID_PARTIDA = :idp", Casella.class)
				.setParameter("posicio", posicio)
				.setParameter("idp", p.getId_Partida());
		Casella c = (Casella) query.getSingleResult();
		if (c.getTipusCasella().equalsIgnoreCase("salvens")) {
			casaSegura = true;
		}
		return casaSegura;
	}

	@Override
	public void finalitzarRecorregut(Fitxa fitxa) {
		Query query = session.createNativeQuery("SELECT * FROM casella WHERE POSICIÓ = 69 AND ID_PARTIDA = :idp", Casella.class)
				.setParameter("idp", p.getId_Partida());
		Casella cFinal = (Casella) query.getSingleResult();
		fitxa.setActive(false);
		fitxa.setCasella(cFinal);
		session.saveOrUpdate(fitxa);
		session.beginTransaction();
		session.getTransaction().commit();
	}

}

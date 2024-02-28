package DAOImpl;

import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;

import ambDAO.IJugadorDAO;
import model.Jugador;
import model.Casella;
import model.Fitxa;
import ambDAO.*;

public class JugadorDAOImpl implements IJugadorDAO{

	private Session session;
	private String[] colors = {"Blau", "Groc", "Verd", "Vermell"};
	Random rn = new Random();
	
	public JugadorDAOImpl(Session session) {
		super();
		this.session = session;
	}

	@Override
	public void initzialitzarJugadors(int nombreJugadors) {
		for (int i = 0; i < nombreJugadors; i++) {
			Jugador jugador = new Jugador();
			jugador.setColor(colors[i]);
			jugador.setNom("jugador "+ colors[i]);
			jugador.setVictories(0);
			session.saveOrUpdate(jugador);
			session.beginTransaction();
			session.getTransaction().commit();
		}
		System.out.println("Jugadors creats correctament!");
	}

	@Override
	public void sumarVictoria(Jugador jugador) {
		jugador.setVictories(jugador.getVictories() + 1);
		session.saveOrUpdate(jugador);
		session.beginTransaction();
		session.getTransaction().commit();
	}

	@Override
	public void tirarDaus(Jugador jugador) {
		FitxaDAOImpl fitxaDAO = new FitxaDAOImpl(session);
		boolean doble = false;
		int contDobles = 0;
		int dau1;
		int dau2;
		
		while(true) {
			dau1 = rn.nextInt(6) + 1;
			dau2 = rn.nextInt(6) + 1;
			if (dau1 == dau2) {
				doble = true;
				contDobles++;
			}
			
			Fitxa ultimaFitxa = new Fitxa();
			if (contDobles == 3) {
				Query query = session.createNativeQuery("SELECT * FROM casella WHERE POSICIÓ = :posicio", Casella.class).setParameter("posicio", 0);
				Casella c = (Casella) query.getSingleResult();
				ultimaFitxa.setCasella(c);
				ultimaFitxa.setActive(false);
				session.saveOrUpdate(c);
				session.beginTransaction();
				session.getTransaction().commit();
			}
			
			if (dau1 == 5) {
				Query query = session.createNativeQuery("SELECT * FROM fitxa WHERE ACTIVA = FALSE AND ID_JUGADOR = :id", Fitxa.class).setParameter("id", jugador.getId_Jugador());
				List<Fitxa> resultList = query.getResultList();
				if (!resultList.isEmpty()) {
					Fitxa fitxa = resultList.get(rn.nextInt(resultList.size()));
					fitxaDAO.entradaAlTablero(fitxa);
					dau1 = 0;		
				}
			}
			
			if (dau2 == 5) {
				Query query = session.createNativeQuery("SELECT * FROM fitxa WHERE ACTIVA = FALSE AND ID_JUGADOR = :id", Fitxa.class).setParameter("id", jugador.getId_Jugador());
				List<Fitxa> resultList = query.getResultList();
				if (!resultList.isEmpty()) {
					Fitxa fitxa = resultList.get(rn.nextInt(resultList.size()));
					fitxaDAO.entradaAlTablero(fitxa);
					dau2 = 0;		
				}
			}
			
			Query query = session.createNativeQuery("SELECT * FROM fitxa WHERE ACTIVA = TRUE AND ID_JUGADOR = :id", Fitxa.class).setParameter("id", jugador.getId_Jugador());
			List<Fitxa> resultList = query.getResultList();
			if (!resultList.isEmpty()) {
				Fitxa fitxa = resultList.get(rn.nextInt(resultList.size()));
				ultimaFitxa = fitxa;
				fitxaDAO.moureFitxa(fitxa, dau1 + dau2);
				while (true) {
					Query query_2 = session.createNativeQuery("SELECT * FROM fitxa WHERE POSICIÓ = :pos AND ID_FITXA <> :id AND ID_JUGADOR <> :idJ", Fitxa.class)
							.setParameter("pos", fitxa.getCasella().getPosicio())
							.setParameter("id", fitxa.getId_Fitxa())
							.setParameter("idJ", fitxa.getJugador().getId_Jugador());
					List<Fitxa> resultList_2 = query.getResultList();
					if (resultList_2.size() == 1) {
						if (!fitxaDAO.verificarCasaSegura(fitxa.getCasella().getPosicio())) {
							fitxaDAO.capturarFitxa(resultList.get(0));
							fitxaDAO.moureFitxa(fitxa, 20);
						}	
					} else {
						break;
					}
				}
				
			}
			
			if (!doble) {
				break;
			}
			doble = false;
		}
		
	
	}
	
}

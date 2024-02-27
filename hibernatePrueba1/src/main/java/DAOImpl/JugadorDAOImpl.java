package DAOImpl;

import org.hibernate.Session;

import ambDAO.IJugadorDAO;
import model.Jugador;

public class JugadorDAOImpl implements IJugadorDAO{

	private Session session;
	private String[] colors = {"Blau", "Groc", "Verd", "Vermell"};
	
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
	}

	@Override
	public void sumarVictoria(Jugador jugador) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tirarDaus(Jugador jugador) {
		// TODO Auto-generated method stub
		
	}
	
}

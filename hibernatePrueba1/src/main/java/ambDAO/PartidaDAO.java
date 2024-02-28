package ambDAO;

import model.Jugador;

public interface PartidaDAO {

	void iniciarPartida(int num); // inicia l'estat del tauler i la posicio de les fitxes
	void jugarPartida(); // inicia la partida 
	boolean verificarVictoria(Jugador jugador); // comprova si un jugador ha portat toes les seves fitxes a l'area de finalització
	
}

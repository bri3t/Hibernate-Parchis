package ambDAO;

public interface PartidaDAO {

	void iniciarPartida(int num); // inicia l'estat del tauler i la posicio de les fitxes
	void verificarVictoria(); // comprova si un jugador ha portat toes les seves fitxes a l'area de finalització
	
}

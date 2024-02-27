package ambDAO;

import model.Jugador;

public interface IJugadorDAO {
	
	void initzialitzarJugadors (int nombreJugadors);
	void sumarVictoria (Jugador jugador);
	void tirarDaus (Jugador jugador);
		
}

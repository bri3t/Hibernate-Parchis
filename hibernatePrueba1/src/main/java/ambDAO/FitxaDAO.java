package ambDAO;

import model.Fitxa;

public interface FitxaDAO {

	
	void moureFitxa(Fitxa fitxa, int quantitat); // mou una fitxa especifica una quantitat de posicions
	void capturarFitxa(Fitxa fitxa); // captura una fitxa que ha aterrat en una casella ocupada per una altre i la envia de tornada al'àrea d'inici
	void entradaAlTablero(Fitxa fitxa); // entra una fitxa a jugar al tablero desde l'àrea d'inici
	boolean verificarCasaSegura(int posicio); // verifica si la posicio actual d'una fitxa és una casa segura
	boolean finalitzarRecorregut(Fitxa fitxa, int quantitat); // verifica si una fitxa ha completat el seu recorregut amb la quantitat exacta d'espais, moventla a la casella final
	
	
	
}

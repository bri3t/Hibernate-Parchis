package DAOImpl;

import org.hibernate.Query;
import org.hibernate.Session;

import ambDAO.FitxaDAO;
import model.Casella;
import model.Fitxa;
import proba.main;
import org.hibernate.Session;


public class FitxaDAOImpl implements FitxaDAO{
	
    private Session session;

	
	public FitxaDAOImpl(Session session) {
		this.session = session;
	}

	
	@Override
	public void moureFitxa(Fitxa fitxa, int quantitat) {
		Query query = session.createQuery("SELECT c FROM casella WHERE c.POSICIÓ = :posicio").setParameter("posicio", fitxa.getCasella().getPosicio()+quantitat);
		Casella c = (Casella) query.getSingleResult();
		fitxa.setCasella(c);
		session.saveOrUpdate(fitxa);
	}

	@Override
	public void capturarFitxa(Fitxa fitxa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void entradaAlTablero(Fitxa fitxa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean verificarCasaSegura(int posicio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean finalitzarRecorregut(Fitxa fitxa, int quantitat) {
		// TODO Auto-generated method stub
		return false;
	}

}

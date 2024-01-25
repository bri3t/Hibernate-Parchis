package ambDAO;

import model.*;
import java.util.List;

public interface IVehicleDAO {
	
	public void saveOrUpdate(Jugador vehicle);
	public void delete(Jugador vehicle);
	public Jugador getVehicle(String vin);
	public List<Jugador> get(String marca);
	public List<Jugador> get(String marca, String model);
	public List<Jugador> get(int anyFabricacio);

}

package ambDAO;

import model.*;
import java.util.List;

public interface IVehicleDAO {
	
	public void saveOrUpdate(Vehicle vehicle);
	public void delete(Vehicle vehicle);
	public Vehicle getVehicle(String vin);
	public List<Vehicle> get(String marca);
	public List<Vehicle> get(String marca, String model);
	public List<Vehicle> get(int anyFabricacio);

}

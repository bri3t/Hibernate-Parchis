package model;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "VEHICLES", indexes = {@Index(name = "marca_index", columnList = "marca")})
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name="vin", length=17, nullable=false, unique=true)
	private String vin;
	
	@Column(name="marca", length=50, nullable=false)
	private String marca;
	
	@Column(name="model", length=100, nullable=false)
	private String model;
	
	@Column(name="any_fabricacio", nullable=false)
	private int anyFabricacio;
	
	public Vehicle() {
		
	}
	
	public Vehicle(String vin, String marca, String model, int anyFabricacio) {
		super();
		this.vin = vin;
		this.marca = marca;
		this.model = model;
		this.anyFabricacio = anyFabricacio;
	}

	public long getId() {
		return id;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getAnyFabricacio() {
		return anyFabricacio;
	}

	public void setAnyFabricacio(int anyFabricacio) {
		this.anyFabricacio = anyFabricacio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(vin);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		return Objects.equals(vin, other.vin);
	}
	
	
	

}

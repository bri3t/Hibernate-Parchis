package model;

import javax.persistence.*;

@Entity
@Table(name = "Jugador")
public class Jugador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_JUGADOR")
	private int id_Jugador;
	
	@Column(name="NOM", length=30, nullable=false)
	private String nom;
	
	@Column(name="COLOR", length=7, nullable=false, unique = true)
	private String color;
	
	@Column(name="VICTORIES", nullable=false)
	private int victories;
	
	public Jugador() {
		
	}

	public Jugador(String nom, String color) {
		super();
		this.nom = nom;
		this.color = color;
		this.victories = 0;
	}

	public int getId_Jugador() {
		return id_Jugador;
	}

	public String getNom() {
		return nom;
	}

	public String getColor() {
		return color;
	}

	public int getVictories() {
		return victories;
	}

	public void setVictories(int victories) {
		this.victories = victories;
	}

	@Override
	public String toString() {
		return "Jugador [id_Jugador=" + id_Jugador + ", nom=" + nom + ", color=" + color + ", victories=" + victories
				+ "]";
	}
	
	
	
	
	
}

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

	public int getId_Jugador() {
		return id_Jugador;
	}

	public String getNom() {
		return nom;
	}

	public String getColor() {
		return color;
	}

	public void setId_Jugador(int id_Jugador) {
		this.id_Jugador = id_Jugador;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setColor(String color) {
		this.color = color;
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

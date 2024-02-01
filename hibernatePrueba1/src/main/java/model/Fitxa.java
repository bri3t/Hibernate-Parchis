package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Fitxa")
public class Fitxa {
	
	@Column(name="POSICIÓ", nullable=false)
	private int posicio;
	
	@Column(name="ACTIVA", nullable=false)
	private boolean isActive;
	
	@ManyToOne
	@JoinColumn(name="ID_JUGADOR")
	private int id_Jugador;
	
	@ManyToOne
	@JoinColumn(name="ID_PARTIDA")
	private int id_Partida;
	
	private int passos;

	public Fitxa(int id_Jugador, int id_Partida) {
		super();
		this.passos = 0;
		this.posicio = 0;
		this.isActive = false;
		this.id_Jugador = id_Jugador;
		this.id_Partida = id_Partida;
	}
	
	
}

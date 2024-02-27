package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Fitxa")
public class Fitxa {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_FITXA")
	private int id_Fitxa;
	
	@ManyToOne
	@JoinColumn(name="POSICIÓ", nullable=false)
	private Casella casella;
	
	@Column(name="ACTIVA", nullable=false)
	private boolean isActive;

	
	@ManyToOne
	@JoinColumn(name="ID_JUGADOR")
	private Jugador jugador;
	
	@ManyToOne
	@JoinColumn(name="ID_PARTIDA")
	private Partida partida;
	
	public Fitxa() {
	}

	public int getId_Fitxa() {
		return id_Fitxa;
	}

	public void setId_Fitxa(int id_Fitxa) {
		this.id_Fitxa = id_Fitxa;
	}

	public Casella getCasella() {
		return casella;
	}

	public void setCasella(Casella casella) {
		this.casella = casella;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}
	
	
	
	
}

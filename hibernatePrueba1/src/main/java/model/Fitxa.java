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
	
	
}

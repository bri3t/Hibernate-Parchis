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
@Table(name = "Casella")
public class Casella {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CASELLA")
	private int id_Casella;
	
	@Column(name = "TIPUS_CASELLA", nullable = false)
	private String tipusCasella;
	
	@Column(name = "POSICIÓ", nullable = false)
	private int posicio;
	
	@ManyToOne
	@JoinColumn(name="ID_PARTIDA")
	private int id_Partida;

	public Casella(int id_Casella, String tipusCasella, int posicio, int id_Partida) {
		super();
		this.id_Casella = id_Casella;
		this.tipusCasella = tipusCasella;
		this.posicio = posicio;
		this.id_Partida = id_Partida;
	}
	
}

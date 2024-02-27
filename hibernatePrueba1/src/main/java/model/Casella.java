package model;

import javax.persistence.*;

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
	private Partida partida;

	public Casella() {
		
	}
	
}

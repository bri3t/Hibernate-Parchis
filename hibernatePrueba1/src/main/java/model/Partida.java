package model;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "Partida")
public class Partida {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PARTIDA")
	private int id_Partida;
	
	@Column(name="INICI", nullable = false)
	private Date dataInici;
	
	@Column(name="FIN", nullable = true)
	private Date dataFin;
	
	@ManyToOne
	@JoinColumn(name="ID_JUGADOR")
	private int id_Guanyador;
	
	@Column(name="ENCURS")
	private boolean enCurs;

	public Partida() {
		super();
		this.dataInici = new java.sql.Date(System.currentTimeMillis());
		this.enCurs = true;
	}
		
}

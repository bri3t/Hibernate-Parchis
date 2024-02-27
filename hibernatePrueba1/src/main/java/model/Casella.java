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

	public int getId_Casella() {
		return id_Casella;
	}

	public void setId_Casella(int id_Casella) {
		this.id_Casella = id_Casella;
	}

	public String getTipusCasella() {
		return tipusCasella;
	}

	public void setTipusCasella(String tipusCasella) {
		this.tipusCasella = tipusCasella;
	}

	public int getPosicio() {
		return posicio;
	}

	public void setPosicio(int posicio) {
		this.posicio = posicio;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}
	
	
	
	
}

package model;

import java.sql.Date;
import java.util.Scanner;

import javax.persistence.*;

@Entity
@Table(name = "Partida")
public class Partida {

    int jugadors;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PARTIDA")
    private int id_Partida;

    @Column(name = "INICI", nullable = false)
    private Date dataInici;

    @Column(name = "FIN", nullable = true)
    private Date dataFin;

    @ManyToOne
    @JoinColumn(name = "ID_JUGADOR")
    private Jugador jugadorGuanyador;

    @Column(name = "ENCURS")
    private boolean enCurs;

    public Partida() {
    }

	public Partida(int jugadors, Date dataInici, boolean enCurs) {
		super();
		this.jugadors = jugadors;
		this.dataInici = dataInici;
		this.enCurs = enCurs;
	}

	public int getJugadors() {
		return jugadors;
	}

	public void setJugadors(int jugadors) {
		this.jugadors = jugadors;
	}

	public int getId_Partida() {
		return id_Partida;
	}

	public void setId_Partida(int id_Partida) {
		this.id_Partida = id_Partida;
	}

	public Date getDataInici() {
		return dataInici;
	}

	public void setDataInici(Date dataInici) {
		this.dataInici = dataInici;
	}

	public Date getDataFin() {
		return dataFin;
	}

	public void setDataFin(Date dataFin) {
		this.dataFin = dataFin;
	}

	public Jugador getJugadorGuanyador() {
		return jugadorGuanyador;
	}

	public void setJugadorGuanyador(Jugador jugadorGuanyador) {
		this.jugadorGuanyador = jugadorGuanyador;
	}

	public boolean isEnCurs() {
		return enCurs;
	}

	public void setEnCurs(boolean enCurs) {
		this.enCurs = enCurs;
	}

   

}

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

   

}

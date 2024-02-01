package model;

import java.sql.Date;
import java.util.Scanner;

import javax.persistence.*;

@Entity
@Table(name = "Partida")
public class Partida {
	
	Scanner sc = new Scanner(System.in);
	int jugadors;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PARTIDA")
	private int id_Partida;
	
	@Column(name="INICI", nullable = false)
	private Date dataInici;
	
	@Column(name="FIN", nullable = true)
	private Date dataFin;
	
	@ManyToOne
	@JoinColumn(name="Jugador.ID_JUGADOR")
	private int id_Guanyador;
	
	@Column(name="ENCURS")
	private boolean enCurs;

	public Partida() {
		super();
		this.jugadors = 0;
		iniciarPartida();
	}
	
	
	public int getJugadors() {
		return jugadors;
	}


	public void setJugadors(int jugadors) {
		this.jugadors = jugadors;
	}


	private void iniciarPartida(){
		boolean continuar = true;
		
		do {
			System.out.println("Indica quants jugadors jugaran: 2-4");
			if (sc.hasNextInt()) {
				jugadors = sc.nextInt();
				if (jugadors < 2 || jugadors > 4) {
					System.out.println("ERR: Numero de jugadors invalid. (2-4");
					sc.nextLine();
				}else {
					this.dataInici = new java.sql.Date(System.currentTimeMillis());
					this.enCurs = true;
				}
			}else{
				System.out.println("ERR: Numero de jugadors invalid. (2-4");
				sc.nextLine();
			}
		} while (continuar);
		
	}
		
}

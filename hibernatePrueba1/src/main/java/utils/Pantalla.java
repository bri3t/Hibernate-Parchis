package utils;

import java.util.Scanner;
import org.hibernate.Session;

import proba.main;
import DAOImpl.*;
import ambDAO.PartidaDAO;
import model.Partida;

public class Pantalla {

    private static Session session;
    private Scanner sc = new Scanner(System.in);

    private final String MENU = "***********************\n"
            + "* 1. Jugar partida    *\n"
            + "* 2. Crear BBDD       *\n"
            + "* 3. Sortir           *\n"
            + "***********************\n"
            + "> ";

    public Pantalla() {
        session = main.getSessionFactory().openSession();
    }

    private void imprimirMenu() {
        System.out.print(MENU);
    }
    
    private int demanarJugadors() {
    	
		sc.nextLine();
    	while (true) {
			System.out.println("Introdueix el nombre de jugadors (2-4)");
			if (sc.hasNextInt()) {
				int num = sc.nextInt();
				if (num >= 2 && num <= 4) {
					return num;
				}else {
					System.out.println("Numero introduit incorrecte");
					sc.nextLine();
				}
			}else {
				System.out.println("El valor ha de ser un nombre");
				sc.nextLine();
			}
		}
    	
    }

    public void comprovarOpcio() {

        boolean continuar = true;

        
        do {
            imprimirMenu();

            if (sc.hasNextInt()) {
                int opcio = sc.nextInt();

                switch (opcio) {
                    case 1:
                    	// en cas que esculli la primera opció:
                        JugadorDAOImpl jdi = new JugadorDAOImpl(session);
                        // demana la quantitat de jguadors que jugaran la partida
                        int num = demanarJugadors();
                        jdi.initzialitzarJugadors(num);
                        PartidaDAOImpl p = new PartidaDAOImpl(session);
                        // inicia la partida
                        p.iniciarPartida(num);
                    case 2:
                    	// crea la bbbdd
                        session.beginTransaction();
                        session.getTransaction().commit();
                        break;
                    case 3:
                    	// surt
                        System.out.println("adeu");
                        System.exit(0);
                }
            } else {
                System.out.println("Escriu un nombre entre el 1 i el 3");
                sc.nextLine();
            }

        } while (continuar);

    }
}

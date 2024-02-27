package utils;

import java.util.Scanner;
import org.hibernate.Session;

import proba.main;
import DAOImpl.*;

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
	
	private void imprimirMenu () {
		System.out.print(MENU);
	}
	
	public void comprovarOpcio(){
		
		boolean continuar = true;
		
		do {
			imprimirMenu();
			
			if (sc.hasNextInt()) {
				int opcio = sc.nextInt();
				
				switch (opcio) {
				case 1:
					continuar = false;
					JugadorDAOImpl jdi = new JugadorDAOImpl(session);
					jdi.initzialitzarJugadors(2);
					break;
				case 2:
					session.beginTransaction();
					session.getTransaction().commit();
					
					break;
				default:
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

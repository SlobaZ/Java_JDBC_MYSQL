package ui;

import dao.ConnectionManager;
import utils.PomocnaKlasa;

public class ApplicationUI {

	public static void main(String[] args)  {
		try {
			ConnectionManager.open();
		} catch (Exception ex) {
			System.out.println("Neuspesna konekcija na bazu!");
			ex.printStackTrace();
			return;
		}

		menu();

		try {
			ConnectionManager.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void menu() {
		int odluka = -1;
		while (odluka != 0) {
			ApplicationUI.ispisiMenu();
			
			System.out.print("opcija:");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			
			switch (odluka) {
			case 0:
				System.out.println("Izlaz iz programa");
				break;
			case 1:
				ProizvodUI.menu();
				break;
			case 2:
				KorisnikUI.menu();
				break;
			case 3:
				KupovinaUI.menu();
				break;
			case 4:
				Sorteri_StatistikeUI.menu();
				break;
			case 5:
				
				break;
			case 6:
				
				break;
			case 7:
				
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}
	
	public static void ispisiMenu() {
		System.out.println("ONLINE SHOP - Osnovne opcije:");
		System.out.println("\t1 - Proizvodi");
		System.out.println("\t2 - Korisnici");
		System.out.println("\t3 - Kupovine");
		System.out.println("\t4 - Sorteri i Statistike (Kupovine)");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ IZ PROGRAMA");
	}


}

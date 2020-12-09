package ui;

import java.sql.Timestamp;

import dao.Sorteri_StatistikeDAO;
import model.Kupovina;
import utils.PomocnaKlasa;

public class Sorteri_StatistikeUI {

	public static void menu() {
		int odluka = -1;
		while (odluka != 0) {
			ispisiMenu();
			System.out.print("opcija:");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			switch (odluka) {
			case 0:
				ApplicationUI.menu();
				break;
			case 1:
				sortiranjeKupovinaPoNazivuProizvoda();
				break;
			case 2:
				sortiranjeKupovinaPoCeniProizvoda();
				break;
			case 3:
				statistikaSrednjaVrednost();   // SREDNJA VREDNOST
				break;
			case 4:
				statistikaNajvecaVrednost();  // NAJVECA VREDNOST  cene
				break;
			case 5:
				statistikaSrednjaVrednostUVremenskomIntervalu();  // SREDNJA VREDNOST ZA ODABRANI PERIOD
				break;
			case 6:
				
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}

	public static void ispisiMenu() {
		System.out.println("Rad sa Sorterima i Statistikama - opcije:");
		System.out.println("\t1 - Sortiranje Kupovina - po nazivu Proizvoda (rastuce)");
		System.out.println("\t2 - Sortiranje Kupovina - po ceni Proizvoda (opadajuce)");
		System.out.println("\t3 - Statistika Kupovina - za srednju vrednost cene"); 
		System.out.println("\t4 - Statistika Kupovina - za najvecu cenu");
		System.out.println("\t5 - Statistika Kupovina - za srednju vrednost cene u odredjenom vremenskom intervalu");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - POCETNI MENI");
	}
	
	
	
	
//  ISPISI SORTIRANO PO NAZIVU Proizvoda
	public static void sortiranjeKupovinaPoNazivuProizvoda() {

		try {
			System.out.println();
			System.out.println("Kupovine sortirane po nazivu Proizvoda");
			System.out.printf("%-10s %-20s %-20s %-20s  %-10s %-20s %-20s %-20s %-10s", 
					"Korisnik", 
					"",
					"",
					"",
					"Proizvod", 
					"",
					"Cena",
					"Datum i vreme",
					"Kolicina"); System.out.println();
			System.out.println("========== ==================== ==================== ==================== ========== ==================== ==================== ==================== ========== ");
			for (Kupovina kupovina: Sorteri_StatistikeDAO.getKupovinaByNazivProizvoda()) {
				System.out.printf("%-10s %-20s %-20s %-20s  %-10s %-20s %-20s %-20s %-10s ", 
						kupovina.getKorisnik().getKorisnik_id(),
						kupovina.getKorisnik().getIme(),
						kupovina.getKorisnik().getPrezime(),
						kupovina.getKorisnik().getGrad(),
						kupovina.getProizvod().getProizvod_id(), 
						kupovina.getProizvod().getNaziv(), 
						kupovina.getProizvod().getCena(),
						PomocnaKlasa.DATE_TIME_FORMAT.format(kupovina.getDatumvreme()),
						kupovina.getKolicina()); System.out.println();
			System.out.println("---------- -------------------- -------------------- -------------------- ---------- -------------------- -------------------- -------------------- ---------- ");
						
			}
			} catch (Exception e) {
				System.out.println("Greska u radu sa bazom!");
				e.printStackTrace();
			}

	}
	
	
	
//  ISPISI SORTIRANO PO CENI Proizvoda
	public static void sortiranjeKupovinaPoCeniProizvoda() {
		try {
			System.out.println();
			System.out.println("Kupovine sortirane po nazivu Proizvoda");
			System.out.printf("%-10s %-20s %-20s %-20s  %-10s %-20s %-20s %-20s %-10s", 
					"Korisnik", 
					"",
					"",
					"",
					"Proizvod", 
					"",
					"Cena",
					"Datum i vreme",
					"Kolicina"); System.out.println();
			System.out.println("========== ==================== ==================== ==================== ========== ==================== ==================== ==================== ========== ");
			for (Kupovina kupovina: Sorteri_StatistikeDAO.getKupovinaByCenaProizvoda()) {
				System.out.printf("%-10s %-20s %-20s %-20s  %-10s %-20s %-20s %-20s %-10s ", 
						kupovina.getKorisnik().getKorisnik_id(),
						kupovina.getKorisnik().getIme(),
						kupovina.getKorisnik().getPrezime(),
						kupovina.getKorisnik().getGrad(),
						kupovina.getProizvod().getProizvod_id(), 
						kupovina.getProizvod().getNaziv(), 
						kupovina.getProizvod().getCena(),
						PomocnaKlasa.DATE_TIME_FORMAT.format(kupovina.getDatumvreme()),
						kupovina.getKolicina()); System.out.println();
			System.out.println("---------- -------------------- -------------------- -------------------- ---------- -------------------- -------------------- -------------------- ---------- ");
						
			}
			} catch (Exception e) {
				System.out.println("Greska u radu sa bazom!");
				e.printStackTrace();
			}
			
	}
	
	
	
//  KUPOVINA  SA SREDNJOM VREDNOSTI CENE PROIZVODA
	public static void statistikaSrednjaVrednost() {
		try {
			System.out.println();
			System.out.println("Kupovine sa srednjom vrednosti Proizvoda");
			System.out.printf("%-10s %-20s %-20s %-20s  %-10s %-20s %-20s %-20s %-10s", 
					"Korisnik", 
					"",
					"",
					"",
					"Proizvod", 
					"",
					"Cena",
					"Datum i vreme",
					"Kolicina"); System.out.println();
			System.out.println("========== ==================== ==================== ==================== ========== ==================== ==================== ==================== ========== ");
			for (Kupovina kupovina: Sorteri_StatistikeDAO.getKupovinaByCenaLekaSrednjaVrednost()) {
				System.out.printf("%-10s %-20s %-20s %-20s  %-10s %-20s %-20s %-20s %-10s ", 
						kupovina.getKorisnik().getKorisnik_id(),
						kupovina.getKorisnik().getIme(),
						kupovina.getKorisnik().getPrezime(),
						kupovina.getKorisnik().getGrad(),
						kupovina.getProizvod().getProizvod_id(), 
						kupovina.getProizvod().getNaziv(), 
						kupovina.getProizvod().getCena(),
						PomocnaKlasa.DATE_TIME_FORMAT.format(kupovina.getDatumvreme()),
						kupovina.getKolicina()); System.out.println();
			System.out.println("---------- -------------------- -------------------- -------------------- ---------- -------------------- -------------------- -------------------- ---------- ");
						
			}
			} catch (Exception e) {
				System.out.println("Greska u radu sa bazom!");
				e.printStackTrace();
			}
	}
	
	
	
//  KUPOVINA  SA NAJVECOM CENOM PROIZVODA
	public static void statistikaNajvecaVrednost() {
		try {
			System.out.println();
			System.out.println("Kupovine sa srednjom vrednosti Proizvoda");
			System.out.printf("%-10s %-20s %-20s %-20s  %-10s %-20s %-20s %-20s %-10s", 
					"Korisnik", 
					"",
					"",
					"",
					"Proizvod", 
					"",
					"Cena",
					"Datum i vreme",
					"Kolicina"); System.out.println();
			System.out.println("========== ==================== ==================== ==================== ========== ==================== ==================== ==================== ========== ");
			for (Kupovina kupovina: Sorteri_StatistikeDAO.getKupovinaByCenaProizvodaNajveca()) {
				System.out.printf("%-10s %-20s %-20s %-20s  %-10s %-20s %-20s %-20s %-10s ", 
						kupovina.getKorisnik().getKorisnik_id(),
						kupovina.getKorisnik().getIme(),
						kupovina.getKorisnik().getPrezime(),
						kupovina.getKorisnik().getGrad(),
						kupovina.getProizvod().getProizvod_id(), 
						kupovina.getProizvod().getNaziv(), 
						kupovina.getProizvod().getCena(),
						PomocnaKlasa.DATE_TIME_FORMAT.format(kupovina.getDatumvreme()),
						kupovina.getKolicina()); System.out.println();
			System.out.println("---------- -------------------- -------------------- -------------------- ---------- -------------------- -------------------- -------------------- ---------- ");
						
			}
			} catch (Exception e) {
				System.out.println("Greska u radu sa bazom!");
				e.printStackTrace();
			}
			
	}
	
	
	
//  KUPOVINA  SA SREDNJOM VREDNOSTI CENE PROIZVODA ZA ZADATI PERIOD
	public static void statistikaSrednjaVrednostUVremenskomIntervalu() {
		
		System.out.println("Unesite pocetni datum i vreme Kupovine (" + PomocnaKlasa.DATE_TIME_FORMAT.toPattern() + "): ");
		Timestamp pocetak = new Timestamp(PomocnaKlasa.ocitajDatumVreme().getTime());
		
		System.out.println("Unesite krajnnji datum i vreme Kupovine (" + PomocnaKlasa.DATE_TIME_FORMAT.toPattern() + "): ");
		Timestamp kraj = new Timestamp(PomocnaKlasa.ocitajDatumVreme().getTime());
				
		try {
			System.out.println();
			System.out.println("Kupovina sa srednjom vrednosti Proizvoda u zadatom intervalu");
			System.out.printf("%-10s %-20s %-20s %-20s  %-10s %-20s %-20s %-20s %-10s", 
					"Korisnik", 
					"",
					"",
					"",
					"Proizvod", 
					"",
					"Cena",
					"Datum i vreme",
					"Kolicina"); System.out.println();
			System.out.println("========== ==================== ==================== ==================== ========== ==================== ==================== ==================== ========== ");
			for (Kupovina kupovina: Sorteri_StatistikeDAO.getKupovinaByCenaProizvodaSrednjaVrednostZaPeriod(pocetak,kraj)) {
				System.out.printf("%-10s %-20s %-20s %-20s  %-10s %-20s %-20s %-20s %-10s ", 
						kupovina.getKorisnik().getKorisnik_id(),
						kupovina.getKorisnik().getIme(),
						kupovina.getKorisnik().getPrezime(),
						kupovina.getKorisnik().getGrad(),
						kupovina.getProizvod().getProizvod_id(), 
						kupovina.getProizvod().getNaziv(), 
						kupovina.getProizvod().getCena(),
						PomocnaKlasa.DATE_TIME_FORMAT.format(kupovina.getDatumvreme()),
						kupovina.getKolicina()); System.out.println();
			System.out.println("---------- -------------------- -------------------- -------------------- ---------- -------------------- -------------------- -------------------- ---------- ");
						
			}
			} catch (Exception e) {
				System.out.println("Greska u radu sa bazom!");
				e.printStackTrace();
			}
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

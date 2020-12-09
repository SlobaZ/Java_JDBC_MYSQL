package ui;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dao.KupovinaDAO;
import model.Korisnik;
import model.Kupovina;
import model.Proizvod;
import utils.PomocnaKlasa;

public class KupovinaUI {

	private static void ispisiMenu() {
		System.out.println("Rad sa kupovinama - opcije:");
		System.out.println("\t1 - Sve Kupovine");
		System.out.println("\t2 - Sve Kupovine - TABELARNO");
		System.out.println("\t3 - Dodaj novu Kupovinu");
		System.out.println("\t4 - Proizvodi koje je kupio korisnik");
		System.out.println("\t5 - Korisnici koji su kupili proizvod");
		System.out.println("\t6 - Dodavanje korisnika na proizvod");
		System.out.println("\t7 - Uklanjanje korisnika sa proizvoda");
		System.out.println("\t8 - Selektovati sve proizvode koje su kupili korisnici iz odabrane drzave");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - GLAVNI MENI");
	}

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
				ispisiSveKupovine();
				break;
			case 2:
				ispisiSveKupovineTabelarno();
				break;
			case 3:
				dodajKupovinu();
				break;
			case 4:
				ispisiProizvodeZaKorisnika();
				break;
			case 5:
				ispisiKorisnikeZaProizvod();
				break;
			case 6:
				dodajKorisnikaNaProizvod();
				break;
			case 7:
				ukloniKorisnikaSaProizvoda();
				break;
			case 8:
				selektovaniProizvodiZaDrzavu();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}
	
	
	
	// ISPISI SVE SVE  KUPOVINE  
		public static void ispisiSveKupovine() {

		try {
			List<Kupovina> sveKupovine = KupovinaDAO.getAll();
			for (Kupovina kupovina: sveKupovine) {
				System.out.println(kupovina);
				}
			} catch (Exception ex) {
			System.out.println("Greska u radu sa bazom!");
			ex.printStackTrace();
			}
					
		}
		
		
		
		
		
	//  SVE  KUPOVINE  TABELARNO
		public static void ispisiSveKupovineTabelarno() {

				try {
				System.out.println();
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
				for (Kupovina kupovina: KupovinaDAO.getAll()) {
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
				}
				System.out.println("---------- -------------------- -------------------- -------------------- ---------- -------------------- -------------------- -------------------- ---------- ");
				} catch (Exception e) {
					System.out.println("Greska u radu sa bazom!");
					e.printStackTrace();
				}
				
			
		}		
		
	
		
		
		
	//  UNESI NOVU  KUPOVINU
		public static void dodajKupovinu() {
			Korisnik korisnik = KorisnikUI.pronadjiKorisnika();
			if (korisnik==null) {
				return;
			}
			Proizvod proizvod = ProizvodUI.pronadjiProizvodNaziv();
			if (proizvod==null) {
				return;
			}
			
			System.out.println();
			System.out.println("Unesite kolicinu proizvoda: ");
			int kolicina = PomocnaKlasa.ocitajCeoBroj();
			System.out.println("Unesite datum i vreme Kupovine (" + PomocnaKlasa.DATE_TIME_FORMAT.toPattern() + "): ");
			Timestamp datumvreme = new Timestamp(PomocnaKlasa.ocitajDatumVreme().getTime());
			
			
			if (korisnik != null && proizvod != null ) {
				try {
				Kupovina kupovina = new Kupovina(korisnik,proizvod, kolicina, datumvreme);
				KupovinaDAO.add( kupovina);
			} catch (Exception e) {
				System.out.println("Greska u radu sa bazom!");
				e.printStackTrace();
			}
			}
		}	
		
		
		

	
	//  SVI PROIZVODI ZA ODABRANOG KORISNIKA
	private static void ispisiProizvodeZaKorisnika() {
		// najpre pronadjemo korisnika za kojeg zelimo ispis proizvoda
		Korisnik korisnik = KorisnikUI.pronadjiKorisnika();
		ArrayList<Integer> kolicina = new ArrayList<Integer>();
		int index = 0;
		if (korisnik != null) {
			try {
			List<Proizvod> proizvodi = KupovinaDAO.getProizvodByKorisnikID(korisnik.getKorisnik_id());
			kolicina = KupovinaDAO.KolicinaProizvoda(korisnik.getKorisnik_id());
			System.out.println();
			System.out.printf("%-10s %-20s %-20s %-20s %-20s", 
					"Id", 
					"Naziv", 
					"Datum",
					"Cena",
					"Kolicina"); System.out.println();
			System.out.println("========== ==================== ==================== ==================== ====================");
			for (Proizvod proizvod: proizvodi) {
				System.out.printf("%-10s %-20s %-20s %-20s %-20s", 
						proizvod.getProizvod_id(), 
						proizvod.getNaziv(),
						proizvod.getDatum(),
						proizvod.getCena(),
						kolicina.get(index++)); System.out.println();
						System.out.println("---------- -------------------- -------------------- -------------------- --------------------");
				} 
				
		} catch (Exception ex) {
			System.out.println("Greska u radu sa bazom!");

			ex.printStackTrace();
		}
		}
	}
	
	
	
	//  SVI KORISNICI PO ODABRANOM PROIZVODU
	private static void ispisiKorisnikeZaProizvod() {
		// najpre pronadjemo proizvod za koji zelimo ispis korisnika
		Proizvod proizvod = ProizvodUI.pronadjiProizvodNaziv();
		if (proizvod != null) {
			try {
			List<Korisnik> korisnici = KupovinaDAO.getKorisnikByProizvodID(proizvod.getProizvod_id());
	
			System.out.println();
			System.out.printf("%-10s %-20s %-20s %-20s %-20s ", 
					"Id", "Ime", "Prezime", "Grad", "Drzava"); System.out.println();
					System.out.println("========== ==================== ==================== ==================== ==================== ");
					for (Korisnik korisnik: korisnici) {
						System.out.printf("%-10s %-20s %-20s %-20s %-20s ", 
								korisnik.getKorisnik_id(), 
								korisnik.getIme(), 
								korisnik.getPrezime(),
								korisnik.getGrad(),
								korisnik.getDrzava()); System.out.println();
						
						System.out.println("---------- -------------------- -------------------- -------------------- -------------------- ");
					}
		} catch (Exception ex) {
			System.out.println("Greska u radu sa bazom!");

			ex.printStackTrace();
		}
		}
	}


	
	
	//  DODAJ KORISNIKA NA KUPOVINU PROIZVODA
	private static void dodajKorisnikaNaProizvod() {
		
		Korisnik korisnik = KorisnikUI.pronadjiKorisnika();
		Proizvod proizvod = ProizvodUI.pronadjiProizvodNaziv();
		System.out.print("Unesi kolicinu kupljenog proizvoda:");
		int kolicina = PomocnaKlasa.ocitajCeoBroj(); 
		System.out.println("Unesite datum i vreme kupovine (" + PomocnaKlasa.DATE_TIME_FORMAT.toPattern() + "): ");
		Timestamp datumvreme = new Timestamp(PomocnaKlasa.ocitajDatumVreme().getTime());
		if (korisnik != null && proizvod != null ) {
			try {
			KupovinaDAO.addKorisnik(korisnik.getKorisnik_id(), proizvod.getProizvod_id() , kolicina, datumvreme);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		else {
			return;
		}
	
	}
	
	
	//  UKLONI KORISNIKA SA KUPOVINE PROIZVODA
	private static void ukloniKorisnikaSaProizvoda() {
		
		Korisnik korisnik = KorisnikUI.pronadjiKorisnika();
		Proizvod proizvod = ProizvodUI.pronadjiProizvodNaziv();
		
		if (korisnik != null && proizvod != null) {
			try {
				KupovinaDAO.deleteKorisnik(korisnik.getKorisnik_id(), proizvod.getProizvod_id());
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	
	}
	
	
	
	
//  SELEKTOVANI PROIZVODI KUPLJENI U ODABRANOJ DRZAVI
	private static void selektovaniProizvodiZaDrzavu() {
		List<Proizvod> sviProizvodi = new ArrayList<Proizvod>();
		System.out.print("Unesi naziv drzave za pregled kupovina:");
		String drzavaNaziv = PomocnaKlasa.ocitajTekst();
		try {
			sviProizvodi = KupovinaDAO.getProizvodiKupljeniUDrzavi(drzavaNaziv);
			System.out.println();
			System.out.printf("%-10s %-20s %-20s %-20s %-20s", 
					"Id", 
					"Naziv", 
					"Cena",
					"Kolicina",
					"Drzava"); System.out.println();
			System.out.println("========== ==================== ==================== ==================== ====================");
			for (Proizvod proizvod: sviProizvodi) {
				System.out.printf("%-10s %-20s %-20s ", 
						proizvod.getProizvod_id(), 
						proizvod.getNaziv(), 
						proizvod.getCena()); System.out.println();
						for(Kupovina kupovina : proizvod.getSveKupovine() ) {
							System.out.printf("%-10s %-20s %-20s %-20s %-20s", 
							"", "", "", kupovina.getKolicina() , "" ); System.out.println();
						}
						for(Korisnik korisnik : proizvod.getSviKorisnici()) {
							System.out.printf("%-10s %-20s %-20s %-20s %-20s", 
									"", "", "", "", korisnik.getDrzava()); System.out.println();
						}
				
				System.out.println("---------- -------------------- -------------------- -------------------- --------------------");
			}
		}
			 catch (Exception ex) {
				System.out.println("Greska u radu sa bazom!");

				ex.printStackTrace();
			}
		}

	
	
	
	
	
	
}

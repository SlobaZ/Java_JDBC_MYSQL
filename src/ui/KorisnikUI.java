package ui;

import java.util.List;

import dao.KorisnikDAO;
import model.Korisnik;
import utils.PomocnaKlasa;

public class KorisnikUI {

	
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
				ispisiSveKorisnike();
				break;
			case 2:
				ispisiSveKorisnikeTabelarno();
				break;
			case 3:
				unosNovogKorisnika();
				break;
			case 4:
				izmenaPodatakaOKorisniku();
				break;
			case 5:
				brisanjePodatakaOKorisniku();
				break;
			case 6:
				sortiraniKorisniciPoNazivuDrzave();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}

	public static void ispisiMenu() {
		System.out.println("Rad sa Korisnicima - opcije:");
		System.out.println("\t1 - Ispis svih Korisnika");
		System.out.println("\t2 - Ispis svih Korisnika - TABELARNO");
		System.out.println("\t3 - Unos novog Korisnika");
		System.out.println("\t4 - Izmena Korisnika");
		System.out.println("\t5 - Brisanje Korisnika");
		System.out.println("\t6 - Ispis svih Korisnika sortiranih po nazivu drzave");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - GLAVNI MENI");
	}

	
	
// ISPISI SVE KORISNIKE
	public static void ispisiSveKorisnike() {
		try {
		List<Korisnik> sviKorisnici = KorisnikDAO.getAll();
		for (Korisnik korisnik: sviKorisnici) {
			System.out.println(korisnik);
				}
		} catch (Exception ex) {
			System.out.println("Greska u radu sa bazom!");
			ex.printStackTrace();
			}
		}
	
	
	
	
//  ISPISI SVE KORISNIKE  TABELARNO
	public static void ispisiSveKorisnikeTabelarno() {
		List<Korisnik> sviKorisnici;
		try {
			sviKorisnici = KorisnikDAO.getAll();
		System.out.println();
		System.out.printf("%-10s %-20s %-20s %-20s %-20s ", 
				"Id", "Ime", "Prezime", "Grad", "Drzava"); System.out.println();
		System.out.println("========== ==================== ==================== ==================== ==================== ");
		for (Korisnik korisnik: sviKorisnici) {
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
	
	
//  PRONADJI KORISNIKA PO ID
	public static Korisnik pronadjiKorisnika() {
		Korisnik retVal = null;
		System.out.print("Unesi ID korisnika:");
		int ksId = PomocnaKlasa.ocitajCeoBroj();
		retVal = pronadjiKorisnika(ksId);
		if (retVal == null)
			System.out.println("Korisnik sa ID: " + ksId
					+ " ne postoji u evidenciji");
		return retVal;
	}
	public static Korisnik pronadjiKorisnika(int ksId) {
		Korisnik retVal=null;
		try {
			retVal = KorisnikDAO.getKorisnikById(ksId);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return retVal;
	}
	
	
	
//  UNOS NOVOG KORISNIKA
	public static void unosNovogKorisnika() {
		System.out.print("Unesi ID korisnika:");
		int ksId = PomocnaKlasa.ocitajCeoBroj();
		try {
		if(pronadjiKorisnika(ksId)!=null) {
			System.out.println("Korisnik sa ID: " + ksId + " vec postoji");
			return;
		}

		System.out.print("Unesi ime:");
		String ime = PomocnaKlasa.ocitajTekst();
		System.out.print("Unesi prezime:");
		String prezime = PomocnaKlasa.ocitajTekst();
		System.out.print("Unesi grad:");
		String grad = PomocnaKlasa.ocitajTekst();
		System.out.print("Unesi drzavu:");
		String drzava = PomocnaKlasa.ocitajTekst();

		Korisnik ks = new Korisnik(0, ime, prezime, grad, drzava);
		KorisnikDAO.add(ks);
		} catch (Exception ex) {
			System.out.println("Greska u radu sa bazom!");

			ex.printStackTrace();
		}
	}

	
	
//  IZMENA KORISNIKA
	public static void izmenaPodatakaOKorisniku() {
		Korisnik ks = pronadjiKorisnika();
		if (ks != null) {
			System.out.print("Unesi novo ime :");
			String ime = PomocnaKlasa.ocitajTekst();
			ks.setIme(ime);
			System.out.print("Unesi novo prezime :");
			String prezime = PomocnaKlasa.ocitajTekst();
			ks.setPrezime(prezime);
			System.out.print("Unesi nov grad :");
			String grad = PomocnaKlasa.ocitajTekst();
			ks.setGrad(grad);
			System.out.print("Unesi novu drzavu :");
			String drzava = PomocnaKlasa.ocitajTekst();
			ks.setDrzava(drzava);

			try {
				KorisnikDAO.update(ks);
			} catch (Exception ex) {
				System.out.println("Greska u radu sa bazom!");

				ex.printStackTrace();
			}
		}
	}
	
	
//  BRISANJE KORISNIKA
	public static void brisanjePodatakaOKorisniku() {
		Korisnik korisnik = pronadjiKorisnika();
		if (korisnik != null) {
			try {
				KorisnikDAO.delete(korisnik.getKorisnik_id());
			} 
			catch (Exception ex) {
				System.out.println("Greska u radu sa bazom!");
				ex.printStackTrace();
			}
		}
	}
	
	
	
	
//  ISPISI SVE KORISNIKE
	public static void sortiraniKorisniciPoNazivuDrzave() {
		List<Korisnik> sviKorisnici;
		try {
			sviKorisnici = KorisnikDAO.getAllSortedByNaziv();
		System.out.println();
		System.out.printf("%-10s %-20s %-20s %-20s %-20s ", 
				"Id", "Ime", "Prezime", "Grad", "Drzava"); System.out.println();
		System.out.println("========== ==================== ==================== ==================== ==================== ");
		for (Korisnik korisnik: sviKorisnici) {
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

package ui;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import dao.ProizvodDAO;
import model.Proizvod;
import utils.PomocnaKlasa;

public class ProizvodUI {
	
	// ZA EXCEL
	public static ArrayList<Proizvod> sviProizvodi = new ArrayList<Proizvod>();   // ZA EXCEL

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
				ispisiSveProizvode();
				break;
			case 2:
				ispisiSveProizvodeTabelarno();
				break;
			case 3:
				unosNovogProizvoda();
				break;
			case 4:
				izmenaPodatakaOProizvodu();
				break;
			case 5:
				brisanjePodatakaOProizvodu();
				break;
			case 6:
				sortiranjeProizvodaPoNazivu();
				break;
			case 7:
				sortiranjeProizvodaPoCeni();
				break;
			case 8:
				statistikaNajvecaVrednost();  // NAJVECA VREDNOST  cene
				break;
			case 9:
				statistikaSrednjaVrednost();   // SREDNJA VREDNOST
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}

	public static void ispisiMenu() {
		System.out.println("Rad sa Proizvodima - opcije:");
		System.out.println("\t1 - Ispis svih Proizvoda");
		System.out.println("\t2 - Ispis svih Proizvoda - TABELARNO");
		System.out.println("\t3 - Unos novog Proizvoda");
		System.out.println("\t4 - Izmena Proizvoda");
		System.out.println("\t5 - Brisanje Proizvoda");
		System.out.println("\t6 - Sortiranje Proizvoda po nazivu (rastuce)");
		System.out.println("\t7 - Sortiranje Proizvoda po ceni (opadajuce)");
		System.out.println("\t8 - Statistika Proizvoda - za najvecu cenu");
		System.out.println("\t9 - Statistika Proizvoda - za srednju vrednost cene");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - GLAVNI MENI");
	}

	
	
// ISPISI SVE PROIZVODE
	public static void ispisiSveProizvode() {
		try {
			List<Proizvod> sviProizvodi = ProizvodDAO.getAll();
			for (Proizvod proizvod: sviProizvodi) {
				System.out.println(proizvod);
			  }
			} catch (Exception ex) {
			System.out.println("Greska u radu sa bazom!");
			ex.printStackTrace();
			}
	}
			
	
	
	
//  ISPISI SVE PROIZVODE  TABELARNO
	public static void ispisiSveProizvodeTabelarno() {
		List<Proizvod> sviProizvodi;
		try {
			sviProizvodi = ProizvodDAO.getAll();
		System.out.println();
		System.out.printf("%-10s %-20s %-20s %-20s ", 
				"Id", 
				"Naziv", 
				"Datum",
				"Cena"); System.out.println();
		System.out.println("========== ==================== ==================== ==================== ");
		for (Proizvod proizvod: sviProizvodi) {
			System.out.printf("%-10s %-20s %-20s %-20s", 
					proizvod.getProizvod_id(), 
					proizvod.getNaziv(), 
					PomocnaKlasa.DATE_FORMAT.format(proizvod.getDatum()),
					proizvod.getCena()); System.out.println();
			
			System.out.println("---------- -------------------- -------------------- -------------------- ");
		}
		
		} catch (Exception ex) {
			System.out.println("Greska u radu sa bazom!");

			ex.printStackTrace();
		}
		
	}
	
	
//  PRONADJI PROIZVOD PO NAZIVU
	public static Proizvod pronadjiProizvodNaziv() {
		Proizvod retVal = null;
		System.out.print("Unesi naziv proizvoda:");
		String prNaziv = PomocnaKlasa.ocitajTekst();
		retVal = pronadjiProizvodNaziv(prNaziv);
		if (retVal == null)
			System.out.println("Proizvod sa nazivom " + prNaziv
					+ " ne postoji u evidenciji");
		return retVal;
	}
	public static Proizvod pronadjiProizvodNaziv(String prNaziv) {
		Proizvod retVal=null;
		try {
			retVal = ProizvodDAO.getProizvodByNaziv(prNaziv);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return retVal;
	}
	
	
	
//  PRONADJI PROIZVOD PO ID
	public static Proizvod pronadjiProizvodID() {
		Proizvod retVal = null;
		System.out.print("Unesi ID proizvoda:");
		int prId = PomocnaKlasa.ocitajCeoBroj();
		retVal = pronadjiProizvodID(prId);
		if (retVal == null)
			System.out.println("Proizvod sa ID: " + prId
					+ " ne postoji u evidenciji");
		return retVal;
	}
	public static Proizvod pronadjiProizvodID(int prId) {
		Proizvod retVal=null;
		try {
			retVal = ProizvodDAO.getProizvodByID(prId);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return retVal;
	}
	
	
	
//  UNOS NOVOG PROIZVODA
	public static void unosNovogProizvoda() {
		System.out.print("Unesi naziv:");
		String prNaziv = PomocnaKlasa.ocitajTekst();
		prNaziv = prNaziv.toUpperCase();
		try {
		if(pronadjiProizvodNaziv(prNaziv)!=null) {
			System.out.println("Proizvod sa nazivom " + prNaziv + " vec postoji");
			return;
		}
		
		System.out.println("Unesite datum proizvodnje (" + PomocnaKlasa.DATE_FORMAT.toPattern() + "): ");
		Date datum = new Date(PomocnaKlasa.ocitajDatum().getTime());

		System.out.print("Unesi cenu:");
		double cena = PomocnaKlasa.ocitajDouble();
		
		Proizvod pr = new Proizvod(0, prNaziv, datum, cena);
		ProizvodDAO.add(pr);
		
		/*   ZA EXSCEL  !!!
		sviProizvodi = (ArrayList<Proizvod>) ProizvodDAO.getAll();
		pisiUFajl();
		*/
		
		} catch (Exception ex) {
			System.out.println("Greska u radu sa bazom!");

			ex.printStackTrace();
		}
	}

	
	
//  IZMENA PROIZVODA
	public static void izmenaPodatakaOProizvodu() {
		Proizvod pr = pronadjiProizvodNaziv();
		if (pr != null) {
			System.out.print("Unesi novi naziv :");
			String prNaziv = PomocnaKlasa.ocitajTekst();
			pr.setNaziv(prNaziv);

			System.out.println("Unesite novi datum proizvodnje (" + PomocnaKlasa.DATE_FORMAT.toPattern() + "): ");
			Date datum = new Date(PomocnaKlasa.ocitajDatum().getTime());
			pr.setDatum(datum);
			
			System.out.print("Unesi novu cenu :");
			double cena = PomocnaKlasa.ocitajDouble();
			pr.setCena(cena);

			try {
				ProizvodDAO.update(pr);
				
				/* ZA EXSCEL  !!!
				sviProizvodi = (ArrayList<Proizvod>) ProizvodDAO.getAll();
				pisiUFajl();
				*/
			} 
			catch (Exception ex) {
				System.out.println("Greska u radu sa bazom!");

				ex.printStackTrace();
			}
				
		}
	}
	
	
	
//  BRISANJE PROIZVODA
	public static void brisanjePodatakaOProizvodu() {
		Proizvod proizvod = pronadjiProizvodNaziv();
		if (proizvod != null) {
			try {
				ProizvodDAO.delete(proizvod.getProizvod_id());
				
				/* ZA EXSCEL  !!!
				sviProizvodi = (ArrayList<Proizvod>) ProizvodDAO.getAll();
				pisiUFajl();
				*/
			} 
			catch (Exception ex) {
				System.out.println("Greska u radu sa bazom!");
				ex.printStackTrace();
			}
		}
	}
	
	
	
	
//  ISPISI SORTIRANO PO NAZIVU
	public static void sortiranjeProizvodaPoNazivu() {
		try {
		List<Proizvod> sviProizvodi = ProizvodDAO.getAllSortedByNaziv();

		System.out.println();
		System.out.printf("%-10s %-20s %-20s %-20s", 
				"Id", 
				"Naziv", 
				"Datum",
				"Cena"); System.out.println();
		System.out.println("========== ==================== ==================== ==================== ");
		for (Proizvod proizvod: sviProizvodi) {
			System.out.printf("%-10s %-20s %-20s %-20s ", 
					proizvod.getProizvod_id(), 
					proizvod.getNaziv(), 
					PomocnaKlasa.DATE_FORMAT.format(proizvod.getDatum()),
					proizvod.getCena()); System.out.println();
			System.out.println("---------- -------------------- -------------------- -------------------- ");
		}
		} catch (Exception e) {
			System.out.println("Greska u radu sa bazom!");
			e.printStackTrace();
		}
	}
	
	
	
//  ISPISI SORTIRANO PO CENI
	public static void sortiranjeProizvodaPoCeni() {
		try {
		List<Proizvod> sviProizvodi = ProizvodDAO.getAllSortedByCena();

		System.out.println();
		System.out.printf("%-10s %-20s %-20s %-20s ", 
				"Id", 
				"Naziv",
				"Datum",
				"Cena"); System.out.println();
		System.out.println("========== ==================== ==================== ==================== ");
		for (Proizvod proizvod: sviProizvodi) {
			System.out.printf("%-10s %-20s %-20s %-20s ", 
					proizvod.getProizvod_id(), 
					proizvod.getNaziv(), 
					PomocnaKlasa.DATE_FORMAT.format(proizvod.getDatum()),
					proizvod.getCena()); System.out.println();
			System.out.println("---------- -------------------- -------------------- -------------------- ");
		}
		} catch (Exception e) {
			System.out.println("Greska u radu sa bazom!");
			e.printStackTrace();
		}
	}
	
	
	
	//  STATISTIKA  ( najveca cena proizvoda )
	public static void statistikaNajvecaVrednost() {
		double maxCena = Double.MIN_VALUE;
		Proizvod proizvod = null;

		try {
			List<Proizvod> proizvodiSvi = ProizvodDAO.getAll();
			for (Proizvod pr : proizvodiSvi) {
				double cena = pr.getCena();
				if (maxCena < cena) {
					maxCena = cena;
					proizvod = pr;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String izvestaj = "< " + proizvod.getNaziv() + " >  < " + maxCena + " >";
		System.out.println(izvestaj);

	  }
	
	
	
//  STATISTIKA  ( SREDNJA VREDNOST )
	public static void statistikaSrednjaVrednost() {
		double srednjaCena=0;
		try {
		srednjaCena = ProizvodDAO.getBySrednjaVrednost();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		String izvestaj = "< Srednja vrednost cene >  < " + srednjaCena + " >";
		System.out.println(izvestaj);
	}
	
	
	
	
	
	// UPIS PODATAKA U FILE
		public static void pisiUFajl() {
			try {
				String putanja = PomocnaKlasa.vratiRelativnuPutanjuDoFajla("proizvod.csv");
				File obrisiKreirajDatoteka = new File(putanja);
				if (!obrisiKreirajDatoteka.exists())
					obrisiKreirajDatoteka.createNewFile();
				PrintWriter out = new PrintWriter(new FileWriter(putanja));
				for (Proizvod st : sviProizvodi) {
					out.println(st.toFileRepresentation()); 
				}
				out.flush();
				out.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	
	
	
	
	
	
	
	
	
	
	
}

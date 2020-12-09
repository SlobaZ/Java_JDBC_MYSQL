package utils;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PomocnaKlasa {

	public static SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
	public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy.");
	public static SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
	
	static Scanner sc = new Scanner(System.in);
	
	
	// PUTANJA DO FAJLA (u folderu data) ZA EXCEL
	public static String vratiRelativnuPutanjuDoFajla(String fileName) {
		String separatorPutanje = System.getProperty("file.separator");
		String relativnaPutanja = "." + separatorPutanje + "data" + separatorPutanje + fileName;
			return relativnaPutanja;
		}
	
	
	//citanje promenljive String
	public static String ocitajTekst(){
		String tekst = "";
		while(tekst == null || tekst.equals(""))
			tekst = sc.nextLine();
		
		return tekst;
	}
		
	//citanje promenljive integer
	public static int ocitajCeoBroj(){
		while (sc.hasNextInt()==false) {
			System.out.println("GRESKA - Pogresno unsesena vrednost, pokusajte ponovo: ");
			sc.nextLine();
		}
		int ceoBroj = sc.nextInt();
		sc.nextLine(); //cisti sve sa ulaza sto nije broj ili ostatak teste posla broja
		return ceoBroj;
	}

	public static long ocitajLong(){
		while (sc.hasNextLong() == false) {
			System.out.println("GRESKA - Pogresno unsesena vrednost, pokusajte ponovo: ");
			sc.nextLine();
		}
		long value = sc.nextLong();
		sc.nextLine();

		return value;
	}
	 
	public static double ocitajDouble(){
		while (sc.hasNextDouble() == false) {
			System.out.println("GRESKA - Pogresno unsesena vrednost, pokusajte ponovo: ");
			sc.nextLine();
		}
		double value = sc.nextDouble();
		sc.nextLine();

		return value;
	}
	
	//citanje promenljive double
	public static float ocitajRealanBroj(){

		while (sc.hasNextFloat()==false) {
			System.out.println("GRESKA - Pogresno unsesena vrednost, pokusajte ponovo: ");
			sc.nextLine();
		}
		float realanBroj = sc.nextFloat();
		sc.nextLine(); //cisti sve sa ulaza sto nije broj ili ostatak teste posla broja
		return realanBroj;
	}
		
	//citanje promenljive char
	public static char ocitajKarakter(){
		
		String text;
		while ( (text=sc.next())==null || text.length()!=1) {
			System.out.println("GRESKA - Pogresno unsesena vrednost za karakter, pokusajte ponovo: ");
			sc.nextLine();
		}
		char karakter = text.charAt(0);
		return karakter;
	}
	
	//citanje promenljive char
	public static char ocitajOdlukuOPotvrdi(String tekst){
		System.out.println("Da li zelite " + tekst + " [Y/N]:");
		char odluka = ' ';
		while( !(odluka == 'Y' || odluka == 'N') ){
			odluka = ocitajKarakter();
			if (!(odluka == 'Y' || odluka == 'N')) {
				System.out.println("Opcije su Y ili N");
			}
		}
		return odluka;
	}

	public static java.util.Date ocitajDatumVreme() {
		java.util.Date dateTime = null;

		while (dateTime == null) {
			String text = sc.nextLine();
			try {
				dateTime = DATE_TIME_FORMAT.parse(text);
			} catch (Exception ex) {
				System.out.println("GRESKA - Pogresno unsesena vrednost, pokusajte ponovo: ");
			}
		}

		return dateTime;
	}

	public static java.util.Date ocitajDatum() {
		java.util.Date dateTime = null;

		while (dateTime == null) {
			String text = sc.nextLine();
			try {
				dateTime = DATE_FORMAT.parse(text);
			} catch (Exception ex) {
				System.out.println("GRESKA - Pogresno unsesena vrednost, pokusajte ponovo: ");
			}
		}

		return dateTime;
	}

	public static java.util.Date ocitajVreme() {
		java.util.Date dateTime = null;

		while (dateTime == null) {
			String text = sc.nextLine();
			try {
				dateTime = TIME_FORMAT.parse(text);
			} catch (Exception ex) {
				System.out.println("GRESKA - Pogresno unsesena vrednost, pokusajte ponovo: ");
			}
		}

		return dateTime;
	}

	static boolean isInteger(String s){
		try {
			Integer.parseInt(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	static boolean isDouble(String s){
		try {
			Double.parseDouble(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	static boolean isBoolean(String s){
		try {
			Boolean.parseBoolean(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}

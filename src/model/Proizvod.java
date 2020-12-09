package model;

import java.sql.Date;
import java.util.ArrayList;

import utils.PomocnaKlasa;

public class Proizvod {

	private int proizvod_id;
	private String naziv;
	private Date datum;
	private double cena;
	
	protected ArrayList<Korisnik> sviKorisnici = new ArrayList<Korisnik>();
	protected ArrayList<Kupovina> sveKupovine = new ArrayList<Kupovina>();
	
	public Proizvod() {
	}
	
	public Proizvod(int proizvod_id, String naziv, Date datum, double cena) {
		this.proizvod_id = proizvod_id;
		this.naziv = naziv;
		this.datum = datum;
		this.cena = cena;
	}
	
	public Proizvod(int proizvod_id, String naziv, Date datum, double cena, ArrayList<Korisnik> sviKorisnici,
			ArrayList<Kupovina> sveKupovine) {
		this.proizvod_id = proizvod_id;
		this.naziv = naziv;
		this.datum = datum;
		this.cena = cena;
		this.sviKorisnici = sviKorisnici;
		this.sveKupovine = sveKupovine;
	}

	@Override
	public String toString() {
		return "Proizvod [proizvod_id=" + proizvod_id + ", naziv=" + naziv + ", datum=" + datum + ", cena=" + cena
				+ "]";
	}
	
	public int getProizvod_id() {
		return proizvod_id;
	}
	public void setProizvod_id(int proizvod_id) {
		this.proizvod_id = proizvod_id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	
	public ArrayList<Korisnik> getSviKorisnici() {
		return sviKorisnici;
	}

	public void setSviKorisnici(ArrayList<Korisnik> sviKorisnici) {
		this.sviKorisnici = sviKorisnici;
	}

	public ArrayList<Kupovina> getSveKupovine() {
		return sveKupovine;
	}

	public void setSveKupovine(ArrayList<Kupovina> sveKupovine) {
		this.sveKupovine = sveKupovine;
	}

	
	//  TEKSTUALNA REPREZENTACIJA ZA UPIS U EXCEL   za fajl
	public String toFileRepresentation(){
		StringBuilder bild = new StringBuilder(); 
		bild.append ( proizvod_id  +","+  naziv  +","+  PomocnaKlasa.DATE_FORMAT.format(datum)  +","+  cena );
		return bild.toString();
	}
	
	

}

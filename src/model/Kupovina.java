package model;

import java.sql.Timestamp;

public class Kupovina {

	Korisnik korisnik;
	Proizvod proizvod;
	private int kolicina;
	private Timestamp datumvreme;
	
	public Kupovina() {
	}
	
	public Kupovina(Korisnik korisnik, Proizvod proizvod, int kolicina, Timestamp datumvreme) {
		this.korisnik = korisnik;
		this.proizvod = proizvod;
		this.kolicina = kolicina;
		this.datumvreme = datumvreme;
	}
	
	@Override
	public String toString() {
		return "Kupovina [korisnik=" + korisnik + ", proizvod=" + proizvod + ", kolicina=" + kolicina + ", datumvreme="
				+ datumvreme + "]";
	}
	
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	public Proizvod getProizvod() {
		return proizvod;
	}
	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	public Timestamp getDatumvreme() {
		return datumvreme;
	}
	public void setDatumvreme(Timestamp datumvreme) {
		this.datumvreme = datumvreme;
	}
	
	
	
	
	
	
	

	
	
}

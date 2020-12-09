package model;

import java.util.ArrayList;

public class Korisnik {
		
	private int korisnik_id;
	private String ime;
	private String prezime;
	private String grad;
	private String drzava;
	
	protected ArrayList<Proizvod> sviProizvodi = new ArrayList<Proizvod>();
	protected ArrayList<Kupovina> sveKupovine = new ArrayList<Kupovina>();
	
	public Korisnik() {
	}
	
	public Korisnik(int korisnik_id, String ime, String prezime, String grad, String drzava) {
		this.korisnik_id = korisnik_id;
		this.ime = ime;
		this.prezime = prezime;
		this.grad = grad;
		this.drzava = drzava;
	}

	public Korisnik(int korisnik_id, String ime, String prezime, String grad, String drzava, ArrayList<Proizvod> sviProizvodi, ArrayList<Kupovina> sveKupovine) {
		this.korisnik_id = korisnik_id;
		this.ime = ime;
		this.prezime = prezime;
		this.grad = grad;
		this.drzava = drzava;
		this.sviProizvodi = sviProizvodi;
		this.sveKupovine = sveKupovine;
	}


	@Override
	public String toString() {
		return "Korisnik [korisnik_id=" + korisnik_id + ", ime=" + ime + ", prezime=" + prezime + ", grad=" + grad
				+ ", drzava=" + drzava + "]";
	}
	
	public int getKorisnik_id() {
		return korisnik_id;
	}
	public void setKorisnik_id(int korisnik_id) {
		this.korisnik_id = korisnik_id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getGrad() {
		return grad;
	}
	public void setGrad(String grad) {
		this.grad = grad;
	}
	public String getDrzava() {
		return drzava;
	}
	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public ArrayList<Proizvod> getSviProizvodi() {
		return sviProizvodi;
	}

	public void setSviProizvodi(ArrayList<Proizvod> sviProizvodi) {
		this.sviProizvodi = sviProizvodi;
	}

	public ArrayList<Kupovina> getSveKupovine() {
		return sveKupovine;
	}

	public void setSveKupovine(ArrayList<Kupovina> sveKupovine) {
		this.sveKupovine = sveKupovine;
	}
	
	
	

}

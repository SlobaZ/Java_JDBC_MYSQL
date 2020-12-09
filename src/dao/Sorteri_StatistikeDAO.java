package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Korisnik;
import model.Kupovina;
import model.Proizvod;

public class Sorteri_StatistikeDAO {

	
//  SVE  KUPOVINE  SORTIRANE PO NAZIVU LEKA
	public static List<Kupovina> getKupovinaByNazivProizvoda() throws Exception {
		List<Kupovina> sveKupovineProizvoda = new ArrayList<>();

			PreparedStatement pstmt = null;
			ResultSet rset = null;
			try {
				
			String query = "SELECT kupovina.korisnik_id, kupovina.proizvod_id, kupovina.kolicina, kupovina.datumvreme, " +
					"proizvod.naziv, proizvod.datum, proizvod.cena, " +
					"korisnik.ime, korisnik.prezime, korisnik.grad, korisnik.drzava FROM kupovina " +
					"LEFT JOIN proizvod ON  proizvod.proizvod_id = kupovina.proizvod_id " +
					"LEFT JOIN korisnik ON korisnik.korisnik_id = kupovina.korisnik_id " + 
					"ORDER BY proizvod.naziv ASC";

				pstmt = ConnectionManager.getConnection().prepareStatement(query);
				rset = pstmt.executeQuery();

				while (rset.next()) {
					int index = 1;
					int korisnik_id = rset.getInt(index++);
					int proizvod_id = rset.getInt(index++);
					int kolicina = rset.getInt(index++);
					Timestamp datumvreme = rset.getTimestamp(index++);
					
					String naziv = rset.getString(index++);
					Date datum = rset.getDate(index++);
					double cena = rset.getDouble(index++);
					
					String ime = rset.getString(index++);
					String prezime = rset.getString(index++);
					String grad = rset.getString(index++);
					String drzava = rset.getString(index++);

					Korisnik korisnik = new Korisnik(korisnik_id,ime,prezime,grad,drzava);
					Proizvod proizvod = new Proizvod(proizvod_id,naziv,datum,cena);

					Kupovina kupovina = new Kupovina(korisnik, proizvod, kolicina, datumvreme);
					sveKupovineProizvoda.add(kupovina);
				}
			} catch (SQLException ex) {
				System.out.println("Greska u SQL upitu!");
				ex.printStackTrace();
			} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			}

			return sveKupovineProizvoda;
		}

	
	
	
//  SVE  KUPOVINE  SORTIRANE PO CENI PROIZVODA
	public static List<Kupovina> getKupovinaByCenaProizvoda() throws Exception {
		List<Kupovina> sveKupovineProizvoda = new ArrayList<>();

			PreparedStatement pstmt = null;
			ResultSet rset = null;
			try {
				
			String query = "SELECT kupovina.korisnik_id, kupovina.proizvod_id, kupovina.kolicina, kupovina.datumvreme, " +
					"proizvod.naziv, proizvod.datum, proizvod.cena, " +
					"korisnik.ime, korisnik.prezime, korisnik.grad, korisnik.drzava FROM kupovina " +
					"LEFT JOIN proizvod ON  proizvod.proizvod_id = kupovina.proizvod_id " +
					"LEFT JOIN korisnik ON korisnik.korisnik_id = kupovina.korisnik_id " +  
					"ORDER BY proizvod.cena DESC";

				pstmt = ConnectionManager.getConnection().prepareStatement(query);
				rset = pstmt.executeQuery();

				while (rset.next()) {
					int index = 1;
					int korisnik_id = rset.getInt(index++);
					int proizvod_id = rset.getInt(index++);
					int kolicina = rset.getInt(index++);
					Timestamp datumvreme = rset.getTimestamp(index++);
					
					String naziv = rset.getString(index++);
					Date datum = rset.getDate(index++);
					double cena = rset.getDouble(index++);
					
					String ime = rset.getString(index++);
					String prezime = rset.getString(index++);
					String grad = rset.getString(index++);
					String drzava = rset.getString(index++);

					Korisnik korisnik = new Korisnik(korisnik_id,ime,prezime,grad,drzava);
					Proizvod proizvod = new Proizvod(proizvod_id,naziv,datum,cena);

					Kupovina kupovina = new Kupovina(korisnik, proizvod, kolicina, datumvreme);
					sveKupovineProizvoda.add(kupovina);
				}
			} catch (SQLException ex) {
				System.out.println("Greska u SQL upitu!");
				ex.printStackTrace();
			} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			}

			return sveKupovineProizvoda;
		}

	
//  KUPOVINA  SA SREDNJOM VREDNOSTI CENE PROIZVODA
	public static List<Kupovina> getKupovinaByCenaLekaSrednjaVrednost() throws Exception {
		List<Kupovina> sveKupovineProizvoda = new ArrayList<>();

			PreparedStatement pstmt = null;
			ResultSet rset = null;
			try {	
			String query = "SELECT kupovina.korisnik_id, kupovina.proizvod_id, kupovina.kolicina, kupovina.datumvreme, " +
					"proizvod.naziv, proizvod.datum, AVG(proizvod.cena), " +
					"korisnik.ime, korisnik.prezime, korisnik.grad, korisnik.drzava FROM kupovina " +
					"LEFT JOIN proizvod ON  proizvod.proizvod_id = kupovina.proizvod_id " +
					"LEFT JOIN korisnik ON korisnik.korisnik_id = kupovina.korisnik_id ";

				pstmt = ConnectionManager.getConnection().prepareStatement(query);
				rset = pstmt.executeQuery();

				while (rset.next()) {
					int index = 1;
					int korisnik_id = rset.getInt(index++);
					int proizvod_id = rset.getInt(index++);
					int kolicina = rset.getInt(index++);
					Timestamp datumvreme = rset.getTimestamp(index++);
					
					String naziv = rset.getString(index++);
					Date datum = rset.getDate(index++);
					double cena = rset.getDouble(index++);
					
					String ime = rset.getString(index++);
					String prezime = rset.getString(index++);
					String grad = rset.getString(index++);
					String drzava = rset.getString(index++);

					Korisnik korisnik = new Korisnik(korisnik_id,ime,prezime,grad,drzava);
					Proizvod proizvod = new Proizvod(proizvod_id,naziv,datum,cena);

					Kupovina kupovina = new Kupovina(korisnik, proizvod, kolicina, datumvreme);
					sveKupovineProizvoda.add(kupovina);
				}
			} catch (SQLException ex) {
				System.out.println("Greska u SQL upitu!");
				ex.printStackTrace();
			} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			}

			return sveKupovineProizvoda;
		}

	
//  KUPOVINA  SA NAJVECOM VREDNOSTI CENE PROIZVODA
	public static List<Kupovina> getKupovinaByCenaProizvodaNajveca() throws Exception {
		List<Kupovina> sveKupovineProizvoda = new ArrayList<>();

			PreparedStatement pstmt = null;
			ResultSet rset = null;
			try {
			String query = "SELECT kupovina.korisnik_id, kupovina.proizvod_id, kupovina.kolicina, kupovina.datumvreme, " +
					"proizvod.naziv, proizvod.datum, MAX(proizvod.cena), " +
					"korisnik.ime, korisnik.prezime, korisnik.grad, korisnik.drzava FROM kupovina " +
					"LEFT JOIN proizvod ON  proizvod.proizvod_id = kupovina.proizvod_id " +
					"LEFT JOIN korisnik ON korisnik.korisnik_id = kupovina.korisnik_id ";

				pstmt = ConnectionManager.getConnection().prepareStatement(query);
				rset = pstmt.executeQuery();

				while (rset.next()) {
					int index = 1;
					int korisnik_id = rset.getInt(index++);
					int proizvod_id = rset.getInt(index++);
					int kolicina = rset.getInt(index++);
					Timestamp datumvreme = rset.getTimestamp(index++);
					
					String naziv = rset.getString(index++);
					Date datum = rset.getDate(index++);
					double cena = rset.getDouble(index++);
					
					String ime = rset.getString(index++);
					String prezime = rset.getString(index++);
					String grad = rset.getString(index++);
					String drzava = rset.getString(index++);

					Korisnik korisnik = new Korisnik(korisnik_id,ime,prezime,grad,drzava);
					Proizvod proizvod = new Proizvod(proizvod_id,naziv,datum,cena);

					Kupovina kupovina = new Kupovina(korisnik, proizvod, kolicina, datumvreme);
					sveKupovineProizvoda.add(kupovina);
				}
			} catch (SQLException ex) {
				System.out.println("Greska u SQL upitu!");
				ex.printStackTrace();
			} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			}

			return sveKupovineProizvoda;
		}

	
//  KUPOVINA  SA SREDNJOM VREDNOSTI CENE PROIZVODA ZA ODABRANI PERIOD
	public static List<Kupovina> getKupovinaByCenaProizvodaSrednjaVrednostZaPeriod(Timestamp pocetak, Timestamp kraj) throws Exception {
		List<Kupovina> sveKupovineProizvoda = new ArrayList<>();

			PreparedStatement pstmt = null;
			ResultSet rset = null;
			try {
			String query = "SELECT kupovina.korisnik_id, kupovina.proizvod_id, kupovina.kolicina, kupovina.datumvreme, " +
					"proizvod.naziv, proizvod.datum, AVG(proizvod.cena), " +
					"korisnik.ime, korisnik.prezime, korisnik.grad, korisnik.drzava FROM kupovina " +
					"LEFT JOIN proizvod ON  proizvod.proizvod_id = kupovina.proizvod_id " +
					"LEFT JOIN korisnik ON korisnik.korisnik_id = kupovina.korisnik_id " +
					"WHERE kupovina.datumvreme >=?  AND kupovina.datumvreme <=? " ;

				pstmt = ConnectionManager.getConnection().prepareStatement(query);
				pstmt.setTimestamp(1, pocetak);
				pstmt.setTimestamp(2, kraj);
				rset = pstmt.executeQuery();

				while (rset.next()) {
					int index = 1;
					int korisnik_id = rset.getInt(index++);
					int proizvod_id = rset.getInt(index++);
					int kolicina = rset.getInt(index++);
					Timestamp datumvreme = rset.getTimestamp(index++);
					
					String naziv = rset.getString(index++);
					Date datum = rset.getDate(index++);
					double cena = rset.getDouble(index++);
					
					String ime = rset.getString(index++);
					String prezime = rset.getString(index++);
					String grad = rset.getString(index++);
					String drzava = rset.getString(index++);

					Korisnik korisnik = new Korisnik(korisnik_id,ime,prezime,grad,drzava);
					Proizvod proizvod = new Proizvod(proizvod_id,naziv,datum,cena);

					Kupovina kupovina = new Kupovina(korisnik, proizvod, kolicina, datumvreme);
					sveKupovineProizvoda.add(kupovina);
				}
			} catch (SQLException ex) {
				System.out.println("Greska u SQL upitu!");
				ex.printStackTrace();
			} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			}

			return sveKupovineProizvoda;
		}

	
	
	
	
}

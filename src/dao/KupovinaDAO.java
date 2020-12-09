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


public class KupovinaDAO {
	
	
	//  SVE  KUPOVINE 
	public static List<Kupovina> getAll() throws Exception {
		List<Kupovina> sveKupovine = new ArrayList<>();

			PreparedStatement pstmt = null;
			ResultSet rset = null;
			try {
				String query = "SELECT kupovina.korisnik_id, kupovina.proizvod_id, kupovina.kolicina, kupovina.datumvreme, " +
								"proizvod.naziv, proizvod.datum, proizvod.cena, " +
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
					sveKupovine.add(kupovina);
				}
			} catch (SQLException ex) {
				System.out.println("Greska u SQL upitu!");
				ex.printStackTrace();
			} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			}

			return sveKupovine;
		}
	
	
	
	 //  UNESI NOVU  KUPOVINU
		public static boolean add(Kupovina kupovina) throws Exception {
			PreparedStatement pstmt = null;
			try {
				String query = "INSERT INTO kupovina ( korisnik_id, proizvod_id, kolicina, datumvreme ) VALUES (?, ?, ?, ?)";

				pstmt = ConnectionManager.getConnection().prepareStatement(query);
				int index = 1;
				pstmt.setInt(index++, kupovina.getKorisnik().getKorisnik_id());
				pstmt.setInt(index++, kupovina.getProizvod().getProizvod_id());
				pstmt.setInt(index++, kupovina.getKolicina());
				pstmt.setTimestamp(index++, kupovina.getDatumvreme());

				return pstmt.executeUpdate() == 1;
			} catch (SQLException ex) {
				System.out.println("Greska u SQL upitu!");
				ex.printStackTrace();
			} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			}

			return false;
		}

	
	
		


//  KUPLJENI PROIZVODI ZA ODABRANOG KORISNIKA
	public static List<Proizvod> getProizvodByKorisnikID(int korisnikID) throws Exception{
		List<Proizvod> proizvodiKojeJeKupioKorisnik = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT proizvod_id FROM kupovina WHERE korisnik_id = ?" ;

			pstmt = ConnectionManager.getConnection().prepareStatement(sql);
			pstmt.setInt(1, korisnikID);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				int proizvodID = rset.getInt(1);

				Proizvod proizvod = ProizvodDAO.getProizvodByID(proizvodID);
				proizvodiKojeJeKupioKorisnik.add(proizvod);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return proizvodiKojeJeKupioKorisnik;
	}
	
	public static ArrayList<Integer> KolicinaProizvoda(int korisnikID) throws Exception{
		ArrayList<Integer> kolicine = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT kolicina FROM kupovina WHERE korisnik_id = ?" ;

			pstmt = ConnectionManager.getConnection().prepareStatement(sql);
			pstmt.setInt(1, korisnikID);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				int kolicina = rset.getInt(1);

				kolicine.add(kolicina);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return kolicine;
	}
	
	
	
	//  KORISNICI KOJI SU KUPILI ODREDJENI PROIZVOD
	public static List<Korisnik> getKorisnikByProizvodID(int id) throws Exception {
		List<Korisnik> korisniciKojiSuKupiliProizvod = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT korisnik_id FROM kupovina WHERE proizvod_id = ? ";

			pstmt = ConnectionManager.getConnection().prepareStatement(sql);
			pstmt.setInt(1, id);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				int korisnikID = rset.getInt(1);

				Korisnik korisnik = KorisnikDAO.getKorisnikById(korisnikID);
				korisniciKojiSuKupiliProizvod.add(korisnik);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return korisniciKojiSuKupiliProizvod;
	}
	
	
	
	//  DODAJ KORISNIKA NA KUPOVINU PROIZVODA
	public static boolean addKorisnik(int korisnikID, int proizvodID , int kolicina , Timestamp datumvreme) throws Exception{
		PreparedStatement pstmt = null;
		try {
			String sql = "INSERT INTO kupovina (korisnik_id, proizvod_id, kolicina, datumvreme) VALUES (?, ?, ?, ?)";

			pstmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			pstmt.setInt(index++, korisnikID);
			pstmt.setInt(index++, proizvodID);
			pstmt.setInt(index++, kolicina);
			pstmt.setTimestamp(index++, datumvreme);
			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
	
   //  OBRISI KORISNIKA SA KUPOVINE PROIZVODA
	public static boolean deleteKorisnik(int korisnikID, int proizvodID) throws Exception {
		PreparedStatement pstmt = null;
		try {
			String sql = "DELETE FROM kupovina WHERE korisnik_id = ?  AND proizvod_id = ? " ;

			pstmt = ConnectionManager.getConnection().prepareStatement(sql);
			pstmt.setInt(1, korisnikID);
			pstmt.setInt(2, proizvodID);
			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
	
	
	
	
//  SELEKTOVANI PROIZVODI KUPLJENI U ODABRANOJ DRZAVI
	public static List<Proizvod> getProizvodiKupljeniUDrzavi(String drzavaKupovine) throws Exception {
		List<Proizvod> proizvodiKupljeniUDrzavi = new ArrayList<>();
		
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			String sql = 
					"SELECT kupovina.korisnik_id, kupovina.proizvod_id, kupovina.kolicina, kupovina.datumvreme, " +
							"proizvod.naziv, proizvod.datum, proizvod.cena, " +
							"korisnik.ime, korisnik.prezime, korisnik.grad, korisnik.drzava FROM kupovina " +
							"LEFT JOIN proizvod ON  proizvod.proizvod_id = kupovina.proizvod_id " +
							"LEFT JOIN korisnik ON korisnik.korisnik_id = kupovina.korisnik_id " + 
							"WHERE korisnik.drzava = ?";			
			
			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			stmt.setString(1, drzavaKupovine);

			rset = stmt.executeQuery();
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
				
				proizvod.getSviKorisnici().add(korisnik);
				proizvod.getSveKupovine().add(kupovina);
				proizvodiKupljeniUDrzavi.add(proizvod);

			}
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		}

		return proizvodiKupljeniUDrzavi;
	}

	
	
	
	
	
	
}

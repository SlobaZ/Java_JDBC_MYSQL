package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Korisnik;

public class KorisnikDAO {

//  SVI KORISNICI
	public static List<Korisnik> getAll() throws Exception {
		List<Korisnik> korisnici = new ArrayList<>();

		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT korisnik_id, ime, prezime,  grad, drzava  FROM korisnik";

			stmt =  ConnectionManager.getConnection().prepareStatement(sql);
			rset = stmt.executeQuery(sql);

			while (rset.next()) {
				int index = 1;
				int korisnik_id = rset.getInt(index++);
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				String grad = rset.getString(index++);
				String drzava = rset.getString(index++);
				
				Korisnik korisnik = new Korisnik(korisnik_id, ime, prezime,grad, drzava);
				korisnici.add(korisnik); 
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return korisnici;
	}
	
	
//  KORISNIK PO ID
	public static Korisnik getKorisnikById(int idK) throws Exception {
		Korisnik korisnik = null;

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT korisnik_id, ime, prezime, grad, drzava FROM korisnik WHERE korisnik_id = ?";

			pstmt = ConnectionManager.getConnection().prepareStatement(query);
			int index = 1;
			pstmt.setInt(index++, idK);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				index = 1;
				int korisnik_id = rset.getInt(index++);
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				String grad = rset.getString(index++);
				String drzava = rset.getString(index++);
				
				korisnik = new Korisnik(korisnik_id, ime, prezime,  grad, drzava);
				
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return korisnik;
	}
	
	
//  UNESI KORISNIKA
	public static boolean add(Korisnik korisnik) throws Exception {
		PreparedStatement pstmt = null;
		try {
			String sql = "INSERT INTO korisnik (ime,prezime,grad,drzava) VALUES (?,?,?,?)";

			pstmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			pstmt.setString(index++, korisnik.getIme());
			pstmt.setString(index++, korisnik.getPrezime());
			pstmt.setString(index++, korisnik.getGrad());
			pstmt.setString(index++, korisnik.getDrzava());
			
			return pstmt.executeUpdate() == 1;
			
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
	
	
//  IZMENI KORISNIKA
	public static boolean update(Korisnik korisnik) throws Exception {
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE korisnik SET ime = ?, prezime = ?, grad=?, drzava=? WHERE korisnik_id = ?";

			pstmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			pstmt.setString(index++, korisnik.getIme());
			pstmt.setString(index++, korisnik.getPrezime());
			pstmt.setString(index++, korisnik.getGrad());
			pstmt.setString(index++, korisnik.getDrzava());
			pstmt.setInt(index++, korisnik.getKorisnik_id());

			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
	
//  OBRISI KORISNIKA
	public static boolean delete(int id) throws Exception {
		PreparedStatement pstmt = null;
		try {
			String sql = "DELETE FROM korisnik WHERE korisnik_id = ?" ;

			pstmt = ConnectionManager.getConnection().prepareStatement(sql);
			pstmt.setInt(1, id);
			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
	
	
//  SVI KORISNICI SORTIRANI PO NAZIVU DRZAVE
	public static List<Korisnik> getAllSortedByNaziv() throws Exception {
		List<Korisnik> korisnici = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT * FROM korisnik ORDER BY drzava ASC";
			
			pstmt = ConnectionManager.getConnection().prepareStatement(sql);
			rset = pstmt.executeQuery(sql);

			while (rset.next()) {
				int index = 1;
				int korisnik_id = rset.getInt(index++);
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				String grad = rset.getString(index++);
				String drzava = rset.getString(index++);
				
				Korisnik korisnik = new Korisnik(korisnik_id, ime, prezime, grad, drzava);
				korisnici.add(korisnik); 
			}

			
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return korisnici;
	}
	
	
	
	
}

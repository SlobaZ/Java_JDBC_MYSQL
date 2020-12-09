package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Proizvod;

public class ProizvodDAO {
	
	
//  SVI PROIZVODI
	public static List<Proizvod> getAll() throws Exception {
		List<Proizvod> proizvodi = new ArrayList<>();

		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT proizvod_id, naziv, datum, cena  FROM proizvod";

			stmt =  ConnectionManager.getConnection().prepareStatement(sql);
			rset = stmt.executeQuery(sql);

			while (rset.next()) {
				int index = 1;
				int proizvoid_id = rset.getInt(index++);
				String naziv = rset.getString(index++);
				Date datum = rset.getDate(index++);
				double cena = rset.getDouble(index++);
				
				Proizvod proizvod = new Proizvod(proizvoid_id, naziv,datum, cena);
				proizvodi.add(proizvod); 
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return proizvodi;
	}
	
	
//  PROIZVOD PO NAZIVU
	public static Proizvod getProizvodByNaziv(String nazivP) throws Exception {
		Proizvod proizvod = null;

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT proizvod_id, naziv, datum, cena FROM proizvod WHERE naziv = ?";

			pstmt = ConnectionManager.getConnection().prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, nazivP);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				index = 1;
				int id = rset.getInt(index++);
				String naziv = rset.getString(index++);
				Date datum = rset.getDate(index++);
				double cena = rset.getDouble(index++);
				
				proizvod = new Proizvod(id, naziv, datum, cena);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return proizvod;
	}
	
	
//  PROIZVOD PO ID
	public static Proizvod getProizvodByID(int id) throws Exception{
		Proizvod proizvod = null;

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT proizvod_id, naziv, datum, cena FROM proizvod WHERE proizvod_id = ?";

			pstmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			pstmt.setInt(index++, id);

			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				String naziv = rset.getString(index++);
				Date datum = rset.getDate(index++);
				double cena = rset.getDouble(index++);
				proizvod = new Proizvod(id, naziv, datum, cena);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return proizvod;
	}

	
	
	
//  UNESI PROIZVOD
	public static boolean add(Proizvod proizvod) throws Exception {
		PreparedStatement pstmt = null;
		try {
			String sql = "INSERT INTO proizvod (naziv,datum,cena) VALUES (?, ?, ?)";

			pstmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			pstmt.setString(index++, proizvod.getNaziv());
			pstmt.setDate(index++, proizvod.getDatum());
			pstmt.setDouble(index++, proizvod.getCena());

			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
	
	
//  IZMENI PROIZVOD
	public static boolean update(Proizvod proizvod) throws Exception {
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE proizvod SET naziv = ?, datum = ?, cena = ? WHERE proizvod_id = ?";

			pstmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			pstmt.setString(index++, proizvod.getNaziv());
			pstmt.setDate(index++, proizvod.getDatum());
			pstmt.setDouble(index++, proizvod.getCena());
			pstmt.setInt(index++, proizvod.getProizvod_id());

			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
	
//  OBRISI PROIZVOD
	public static boolean delete(int id) throws Exception {
		PreparedStatement pstmt = null;
		try {
			String sql = "DELETE FROM proizvod WHERE proizvod_id = ?" ;

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
	
	
//  SVI  SORTIRANI PO NAZIVU
	public static List<Proizvod> getAllSortedByNaziv() throws Exception {
		List<Proizvod> proizvodi = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT proizvod_id, naziv, datum, cena FROM proizvod ORDER BY naziv ASC";
			
			pstmt = ConnectionManager.getConnection().prepareStatement(sql);
			rset = pstmt.executeQuery(sql);

			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String naziv = rset.getString(index++);
				Date datum = rset.getDate(index++);
				int cena = rset.getInt(index++);
				
				Proizvod proizvod = new Proizvod(id, naziv, datum, cena);
				proizvodi.add(proizvod);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return proizvodi;
	}

	
	
	
//  SVI  SORTIRANI PO CENI
	public static List<Proizvod> getAllSortedByCena() throws Exception {
		List<Proizvod> proizvodi = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT proizvod_id, naziv, datum, cena FROM proizvod ORDER BY cena DESC";
			
			pstmt = ConnectionManager.getConnection().prepareStatement(sql);
			rset = pstmt.executeQuery(sql);

			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String naziv = rset.getString(index++);
				Date datum = rset.getDate(index++);
				int cena = rset.getInt(index++);
				
				Proizvod proizvod = new Proizvod(id, naziv, datum, cena);
				proizvodi.add(proizvod);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return proizvodi;
	}
	
	
	
	
//  SREDNJA VREDNOST - STATISTIKA   ( AVG )
	public static double getBySrednjaVrednost() throws Exception {

		double srednjaVrednost=0;

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT AVG(cena) FROM proizvod";
			
			pstmt = ConnectionManager.getConnection().prepareStatement(sql);
			rset = pstmt.executeQuery(sql);

			while (rset.next()) {
				int index = 1;
				srednjaVrednost = rset.getDouble(index++);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return srednjaVrednost;
	}
	
	
	
	
	
	
	

}

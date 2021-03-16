package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Produs;
/**
 * Clasa contine principalele metode de manipulare a bazei de date, in acest caz al tabelului "produs"
 * @author Bumbu Iulia-Diana
 * @since April 09, 2020
 */
public class ProdusDAO {

	private static final String insertStatementString = "INSERT INTO produs (denumire, cantitate, pret)" + " VALUES (?,?,?)";
	private final static String deleteStatementString = "DELETE FROM produs where denumire = ?";
	private final static String findAllStatementString = "SELECT * FROM produs";
	private final static String updateCantitateStatementString = "UPDATE produs SET cantitate = ? WHERE denumire = ?";
	private final static String findProdusStatementString = "SELECT * FROM produs WHERE denumire = ?";
	
	/**
	 * Metoda utilizata pentru a afisa toate produsele existente in tabelul "produs"
	 * @return se returneaza o lista cu toate produsele gasite
	 */
	public static ArrayList<Produs> findAll() {
		Produs toReturn = null;
        ArrayList<Produs> list = new ArrayList<Produs>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findAllStatementString);
			rs = findStatement.executeQuery();
			while(rs.next()) {
            String den = rs.getString("denumire");
			int cant = rs.getInt("cantitate");
			float pret = rs.getFloat("pret");
			toReturn = new Produs(den, cant, pret);
			list.add(toReturn);
			}
		} catch (SQLException e) {
			System.out.println("ProdusDAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return list;
	}
	
	/**
	 * Functionalitatea metodei este de a insera in tabelul creat un nou produs
	 * @param prod produsul pe care dorim sa il inseram
	 * @return se returneaza -1 in caz ca inserarea a esuat si o valoare diferita in caz contrar
	 */
	public static int insert(Produs prod) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, prod.getDenumire());
			insertStatement.setInt(2, prod.getCantitate());
			insertStatement.setFloat(3, prod.getPret());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println( "ProdusDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
	
	/**
	 * Functionalitatea metodei este de a sterge din tabelul creat un anumit produs
	 * @param prod produsul pe care dorim sa il stergem
	 * @return se returneaza -1 in caz ca stergerea a esuat si o valoare diferita in caz contrar
	 */
	public static int delete(String prod) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement deleteStatement = null;
		int deletedId = -1;
		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
			deleteStatement.setString(1, prod);
			deleteStatement.executeUpdate();

			ResultSet rs = deleteStatement.getGeneratedKeys();
			if (rs.next()) {
				deletedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println( "ProdusDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
		return deletedId;
	}
	/**
	 * metoda utilizata pentru a actualiza anumite linii ale tabelului in cazul in care se gaseste un produs cu numele dat
	 * @param cant noua cantitate pe care dorim sa o avem 
	 * @param den numele produsului pe care vrem sa il actualizam
	 */
	public static void updateCantitate(int cant, String den) {
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		
		try {
			findStatement = dbConnection.prepareStatement(updateCantitateStatementString);
			findStatement.setLong(1, cant);
			findStatement.setString(2, den);
		    findStatement.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("ProdusDAO:updateCantitate" + e.getMessage());
		} finally {
			
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
	
	}
	/**
	 * Metoda este utilizata pentru a cauta un anumit produs
	 * @param nume numele produsului cautat
	 * @return se returneaza produsul cu datele specificate daca a fost gasit si NULL in caz contrar
	 */
	public static Produs findProdus(String nume) {
		Produs toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findProdusStatementString);
			findStatement.setString(1, nume);
			rs = findStatement.executeQuery();
			rs.next();
            int cant = rs.getInt("cantitate");
			float pret = rs.getFloat("pret");
			toReturn = new Produs( nume, cant, pret);
		} catch (SQLException e) {
			System.out.println("ProdusDAO:findProdus " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
		
	}
}
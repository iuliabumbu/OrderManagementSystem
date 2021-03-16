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
import model.Client;
/**
 * Clasa contine principalele metode de manipulare a bazei de date, in acest caz al tabelului "client"
 * @author Bumbu Iulia-Diana
 * @since April 09, 2020
 */
public class ClientDAO {

	private static final String insertStatementString = "INSERT INTO client (idClient, nume, adresa)" + " VALUES (?,?,?)";
	private final static String deleteStatementString = "DELETE FROM client where nume = ? and adresa = ?";
	private final static String findStatementString = "SELECT * FROM client where nume = ? and adresa = ?";
	private final static String findNameStatementString = "SELECT * FROM client where nume = ?";
	private final static String findAllStatementString = "SELECT * FROM client";

	/**
	 * Aceasta metoda este folosita pentru a gasi un anumit client
	 * @param nume numele clientului cautat 
	 * @param adresa adresa acestuia
	 * @return se returneaza clientul cu datele specificate daca a fost gasit si NULL in caz contrar
	 */
	public static Client findClient(String nume, String adresa) {
		Client toReturn = null;
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setString(1, nume);
			findStatement.setString(2, adresa);
			rs = findStatement.executeQuery();
			rs.next();

			int clientId = rs.getInt("idClient");
			toReturn = new Client(clientId, nume, adresa);
		} catch (SQLException e) {
			System.out.println("ClientDAO:find " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	/**
	 * Metoda este utilizata pentru a cauta un anumit client
	 * @param nume numele clientului cautat
	 * @return se returneaza clientul cu datele specificate daca a fost gasit si NULL in caz contrar
	 */
	public static Client findClientByName(String nume) {
		Client toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findNameStatementString);
			findStatement.setString(1, nume);
			rs = findStatement.executeQuery();
			rs.next();

			int clientId = rs.getInt("idClient");
			String adresa = rs.getString("adresa");
			toReturn = new Client(clientId, nume, adresa);
		} catch (SQLException e) {
			System.out.println("ClientDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	/**
	 * Metoda utilizata pentru a afisa toti clientii existenti in tabelul "client"
	 * @return se returneaza o lista cu toti clientii gasiti
	 */
	public static ArrayList<Client> findAll() {
		Client toReturn = null;
        ArrayList<Client> list = new ArrayList<Client>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findAllStatementString);
			rs = findStatement.executeQuery();
			while(rs.next()) {
            int clientId = rs.getInt("idClient");
			String name = rs.getString("nume");
			String address = rs.getString("adresa");
			toReturn = new Client(clientId, name, address);
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
	 * Functionalitatea metodei este de a insera in tabelul creat un nou client
	 * @param client clientul pe care dorim sa il inseram
	 * @return se returneaza -1 in caz ca inserarea a esuat si o valoare diferita in caz contrar
	 */
	public static int insert(Client client) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, client.getId());
			insertStatement.setString(2, client.getNume());
			insertStatement.setString(3, client.getAdresa());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println( "ClientDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
	
	/**
	 * Functionalitatea metodei este de a sterge din tabelul creat un anumit client
	 * @param client clientul pe care dorim sa il stergem
	 * @return se returneaza -1 in caz ca stergerea a esuat si o valoare diferita in caz contrar
	 */
	public static int delete(Client client) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement deleteStatement = null;
		int deletedId = -1;
		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
			deleteStatement.setString(1, client.getNume());
			deleteStatement.setString(2, client.getAdresa());
			deleteStatement.executeUpdate();

			ResultSet rs = deleteStatement.getGeneratedKeys();
			if (rs.next()) {
				deletedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println( "ClientDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
		return deletedId;
	}
}
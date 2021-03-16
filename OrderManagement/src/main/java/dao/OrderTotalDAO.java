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
import model.OrderTotal;
/**
 * Clasa contine principalele metode de manipulare a bazei de date, in acest caz al tabelului "ordertotal"
 * @author Bumbu Iulia-Diana
 * @since April 09, 2020
 */
public class OrderTotalDAO {

	private static final String insertStatementString = "INSERT INTO ordertotal (cod, client, adresa, produse, cantitate, pret)" + " VALUES (?,?,?,?,?,?)";
	private final static String findAllStatementString = "SELECT * FROM ordertotal";
	
	/**
	 * Metoda utilizata pentru a afisa toate comenzile existente in tabelul "ordertotal"
	 * @return se returneaza o lista cu toate comenzile gasite
	 */
	public static ArrayList<OrderTotal> findAll() {
		OrderTotal toReturn = null;
        ArrayList<OrderTotal> list = new ArrayList<OrderTotal>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findAllStatementString);
			rs = findStatement.executeQuery();
			while(rs.next()) {
            int orderId = rs.getInt("cod");
			String name = rs.getString("client");
			String address = rs.getString("adresa");
			String produse = rs.getString("produse");
			String cantitati = rs.getString("cantitate");
			float pret = rs.getFloat("pret");
			toReturn = new OrderTotal(orderId, name, address, produse, cantitati, pret);
			list.add(toReturn);
			}
		} catch (SQLException e) {
			System.out.println("OrderTotalDAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return list;
	}

	/**
	 * Functionalitatea metodei este de a insera in tabelul creat o noua comanda totala
	 * @param order comanda pe care dorim sa il inseram
	 * @return se returneaza -1 in caz ca inserarea a esuat si o valoare diferita in caz contrar
	 */
	public static int insert(OrderTotal order) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, order.getCod());
			insertStatement.setString(2, order.getClient());
			insertStatement.setString(3, order.getAdresa());
			insertStatement.setString(4, order.getProduse());
			insertStatement.setString(5, order.getCantitati());
			insertStatement.setFloat(6, order.getPret());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println( "OrderTotalDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
	
}

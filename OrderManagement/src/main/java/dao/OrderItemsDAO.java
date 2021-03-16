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
import model.OrderItems;
/**
 * Clasa contine principalele metode de manipulare a bazei de date, in acest caz al tabelului "orderitems"
 * @author Bumbu Iulia-Diana
 * @since April 09, 2020
 */
public class OrderItemsDAO {

	private static final String insertStatementString = "INSERT INTO orderitems (id, nume, denumire, cantitate)" + " VALUES (?,?,?,?)";
	private final static String findAllStatementString = "SELECT * FROM orderitems";
	
	/**
	 * Metoda utilizata pentru a afisa toate orderItem-urile existente in tabelul "orderitems"
	 * @return se returneaza o lista cu toate orderItem-urile gasite
	 */
	public static ArrayList<OrderItems> findAll() {
		OrderItems toReturn = null;
        ArrayList<OrderItems> list = new ArrayList<OrderItems>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findAllStatementString);
			rs = findStatement.executeQuery();
			while(rs.next()) {
            int orderId = rs.getInt("id");
			String name = rs.getString("nume");
			String den = rs.getString("denumire");
			int cant = rs.getInt("cantitate");
			toReturn = new OrderItems(orderId, name, den, cant);
			list.add(toReturn);
			}
		} catch (SQLException e) {
			System.out.println("OrderItemsDAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return list;
	}

	/**
	 * Functionalitatea metodei este de a insera in tabelul creat un nou orderItem
	 * @param order OrderItem-ul pe care dorim sa il inseram
	 * @return se returneaza -1 in caz ca inserarea a esuat si o valoare diferita in caz contrar
	 */
	public static int insert(OrderItems order) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, order.getIdOrder());
			insertStatement.setString(2, order.getNumeClient());
			insertStatement.setString(3, order.getDenumireProdus());
			insertStatement.setInt(4, order.getCantitate());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println( "OrderItemsDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
	
	
}
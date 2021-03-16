package bll;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import dao.OrderTotalDAO;
import model.OrderTotal;
/**
 * Clasa utilizata pentru a apela metodele corespunzatoare din pachetul DAO incapsuleaza logica aplicatiei in cazul tabelului "orderTotal"
 * @author Bumbu Iulia-Diana
 * @since April 09, 2020
 */
public class OrderTotalBLL {
	
	public int insertOrderTotal(OrderTotal t) {
		return OrderTotalDAO.insert(t);
	}
	
    
    public ArrayList<OrderTotal> findTotalOrderItems(){
    	return OrderTotalDAO.findAll();
    }
    


}

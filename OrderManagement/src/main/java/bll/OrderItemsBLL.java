package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.CantitateComandaValidator;
import bll.validators.CantitateValidator;
import bll.validators.PretValidator;
import bll.validators.Validator;
import dao.OrderItemsDAO;
import model.OrderItems;
import model.Produs;
/**
 * Clasa utilizata pentru a apela metodele corespunzatoare din pachetul DAO incapsuleaza logica aplicatiei in cazul tabelului "orderItems"
 * @author Bumbu Iulia-Diana
 * @since April 09, 2020
 */
public class OrderItemsBLL {
	private List<Validator<OrderItems>> validators;

	public OrderItemsBLL() {
		validators = new ArrayList<Validator<OrderItems>>();
		validators.add(new CantitateComandaValidator());
		
	}

	public int insertOrderItems(OrderItems client) {
		for (Validator<OrderItems> v : validators) {
			v.validate(client);
		}
		return OrderItemsDAO.insert(client);
	}
	
    
    public ArrayList<OrderItems> findAllOrderItems(){
    	return OrderItemsDAO.findAll();
    }


}

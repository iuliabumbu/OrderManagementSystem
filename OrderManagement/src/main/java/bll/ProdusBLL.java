package bll;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import bll.validators.PretValidator;
import bll.validators.CantitateValidator;
import bll.validators.Validator;
import model.Produs;
import dao.ProdusDAO;
/**
* Clasa utilizata pentru a apela metodele corespunzatoare din pachetul DAO incapsuleaza logica aplicatiei in cazul tabelului "produs"
 * @author Bumbu Iulia-Diana
 * @since April 09, 2020
 */
public class ProdusBLL {
	private List<Validator<Produs>> validators;

	public ProdusBLL() {
		validators = new ArrayList<Validator<Produs>>();
		validators.add(new CantitateValidator());
		validators.add(new PretValidator());
	}

	public int insertProduct(Produs prod) {
		for (Validator<Produs> v : validators) {
			v.validate(prod);
		}
		return ProdusDAO.insert(prod);
	}
	
	public int deleteProduct(Produs prod) {
		for (Validator<Produs> v : validators) {
			v.validate(prod);
		}
		return ProdusDAO.delete(prod.getDenumire());
	}
	
	 public ArrayList<Produs> findAllProducts(){
	    	return ProdusDAO.findAll();
	 }
	 
	 public void updateCantitate(int val, String den) {
		 ProdusDAO.updateCantitate(val, den);
	 }
	 
	 public Produs findProdus(String nume) {
			Produs rez = ProdusDAO.findProdus(nume);
			return rez;
		}
}
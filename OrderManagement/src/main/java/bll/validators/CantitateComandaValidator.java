package bll.validators;

import model.OrderItems;
import model.Produs;
/**
 * Clasa utilizata pt a valida pretul produsului
 * @author Bumbu Iulia-Diana
 * @since April 09, 2020
 */
public class CantitateComandaValidator implements Validator<OrderItems> {

		public void validate(OrderItems t) {

			if (t.getCantitate() < 0) {
				throw new IllegalArgumentException("Nu poti comanda o cantitate negativa!");
			}

	   }

	}
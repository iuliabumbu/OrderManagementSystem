package bll.validators;
import model.Produs;
/**
 * Clasa utilizata pt a valida cantitatea produsului
 * @author Bumbu Iulia-Diana
 * @since April 09, 2020
 */
public class CantitateValidator implements Validator<Produs> {

		public void validate(Produs t) {

			if (t.getCantitate() < 0) {
				throw new IllegalArgumentException("Nu puteti comanda o cantitate negativa!");
			}

		}

	}

package bll.validators;

import model.Produs;
/**
 * Clasa utilizata pt a valida pretul produsului
 * @author Bumbu Iulia-Diana
 * @since April 09, 2020
 */
public class PretValidator implements Validator<Produs> {

		public void validate(Produs t) {

			if (t.getPret() < 0) {
				throw new IllegalArgumentException("Pretul nu poate fi negativ!");
			}

		}

	}
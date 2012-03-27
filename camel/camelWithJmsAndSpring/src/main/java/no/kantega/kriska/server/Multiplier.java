package no.kantega.kriska.server;

import org.springframework.stereotype.Service;



public interface Multiplier {

	/**
	 * Multiplies the given number by a pre-defined constant.
	 * 
	 * @param originalNumber
	 *            The number to be multiplied
	 * @return The result of the multiplication
	 */
	int multiply(int originalNumber);

}

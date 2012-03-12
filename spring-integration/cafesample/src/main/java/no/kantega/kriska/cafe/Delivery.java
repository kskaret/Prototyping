package no.kantega.kriska.cafe;

import java.util.List;

public class Delivery {

	private List<Drink> drinks;

	public Delivery(List<Drink> drinks) {
		this.drinks = drinks;
	}

	@Override
	public String toString() {
		return "Delivery: " + drinks.toString() + "\n";
	}
}

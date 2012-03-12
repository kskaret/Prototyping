package no.kantega.kriska.cafe;

import java.util.List;

public class Waiter {
	public Delivery prepareDelivery(List<Drink> drinks) {
		Delivery delivery = new Delivery(drinks);
		return delivery;
	}
}

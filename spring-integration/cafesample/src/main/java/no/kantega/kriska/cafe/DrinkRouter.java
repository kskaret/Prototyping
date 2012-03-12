package no.kantega.kriska.cafe;

public class DrinkRouter {

	public String resolveOrderItemChannel(OrderItem orderItem) {
		System.out.println("routing orderItem " + orderItem);
		return (orderItem.isIced()) ? "coldDrinks" : "hotDrinks";
	}
}
package no.kantega.kriska.cafe;

import java.util.List;

public class OrderSplitter {
	public List<OrderItem> split(Order order) {
		System.out.println("splitting order " + order.getNumber());
		return order.getItems();
	}
}

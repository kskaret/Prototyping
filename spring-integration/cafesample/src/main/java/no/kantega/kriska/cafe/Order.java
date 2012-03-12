package no.kantega.kriska.cafe;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private Integer orderNumber;

	List<OrderItem> orderItems = new ArrayList<OrderItem>();

	public Order(int i) {
		orderNumber = i;
	}

	public void addItem(DrinkType latte, int antall, boolean iced) {
		orderItems.add(new OrderItem(latte, iced, this, antall));
	}

	public Integer getNumber() {
		return orderNumber;
	}

	public List<OrderItem> getItems() {
		return orderItems;
	}

}

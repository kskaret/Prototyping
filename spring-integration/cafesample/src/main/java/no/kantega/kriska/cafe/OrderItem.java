package no.kantega.kriska.cafe;

public class OrderItem {

	private DrinkType drinkType;
	private boolean iced;
	private Order order;
	private int antall;

	public OrderItem(DrinkType drinkType, boolean iced, Order order, int antall) {
		this.drinkType = drinkType;
		this.iced = iced;
		this.order = order;
		this.antall = antall;
	}

	public boolean isIced() {
		return iced;
	}

	public Order getOrder() {
		return order;
	}

	public DrinkType getDrinkType() {
		return drinkType;
	}

	public int getShots() {
		return antall;
	}

	@Override
	public String toString() {
		return drinkType + " " + antall;
	}
}

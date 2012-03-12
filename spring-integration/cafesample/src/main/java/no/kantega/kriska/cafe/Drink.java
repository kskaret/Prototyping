package no.kantega.kriska.cafe;

public class Drink {

	private Integer orderNumber;
	private DrinkType drinkType;
	private boolean iced;
	private int antall;

	public Drink(Integer orderNumber, DrinkType drinkType, boolean iced,
			int shots) {
		this.orderNumber = orderNumber;
		this.drinkType = drinkType;
		this.iced = iced;
		this.antall = shots;
	}

	@Override
	public String toString() {
		return orderNumber + " " + drinkType + " " + iced + " " + antall;
	}
}

package no.kantega.kriska.cafe;

import java.util.concurrent.atomic.AtomicInteger;

public class Barista {
	private long hotDrinkDelay = 5000;
	private long coldDrinkDelay = 1000;

	private AtomicInteger hotDrinkCounter = new AtomicInteger();
	private AtomicInteger coldDrinkCounter = new AtomicInteger();

	public void setHotDrinkDelay(long hotDrinkDelay) {
		this.hotDrinkDelay = hotDrinkDelay;
	}

	public void setColdDrinkDelay(long coldDrinkDelay) {
		this.coldDrinkDelay = coldDrinkDelay;
	}

	public Drink prepareHotDrink(OrderItem orderItem) {
		try {
			Thread.sleep(this.hotDrinkDelay);
			System.out.println(Thread.currentThread().getName()
					+ " prepared hot drink #"
					+ hotDrinkCounter.incrementAndGet() + " for order #"
					+ orderItem.getOrder().getNumber() + ": " + orderItem);
			return new Drink(orderItem.getOrder().getNumber(),
					orderItem.getDrinkType(), orderItem.isIced(),
					orderItem.getShots());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return null;
		}
	}

	public Drink prepareColdDrink(OrderItem orderItem) {
		try {
			Thread.sleep(this.coldDrinkDelay);
			System.out.println(Thread.currentThread().getName()
					+ " prepared cold drink #"
					+ coldDrinkCounter.incrementAndGet() + " for order #"
					+ orderItem.getOrder().getNumber() + ": " + orderItem);
			return new Drink(orderItem.getOrder().getNumber(),
					orderItem.getDrinkType(), orderItem.isIced(),
					orderItem.getShots());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return null;
		}
	}
}

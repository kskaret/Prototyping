package no.kantega.kriska.cafe;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BarTest {

	@Test
	public void bar() throws InterruptedException {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"cafeDemo.xml");

		Cafe cafe = context.getBean("cafe", Cafe.class);
		for (int i = 1; i <= 100; i++) {
			Order order = new Order(i);
			order.addItem(DrinkType.LATTE, 2, false);
			order.addItem(DrinkType.MOCHA, 3, true);
			System.out.println("placing order " + i);
			cafe.placeOrder(order);
		}
	}

}

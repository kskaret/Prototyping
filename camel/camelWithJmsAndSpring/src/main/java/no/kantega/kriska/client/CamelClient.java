package no.kantega.kriska.client;

import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelClient {
	public static void main(final String[] args) throws Exception {
		System.out
				.println("Notice this client requires that the CamelServer is already running!");

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"camel-client.xml");

		// get the camel template for Spring template style sending of messages
		// (= producer)
		ProducerTemplate camelTemplate = context.getBean("camelTemplate",
				ProducerTemplate.class);

		System.out.println("Invoking the multiply with 22");
		// as opposed to the CamelClientRemoting example we need to define the
		// service URI in this java code
		int response = (Integer) camelTemplate.sendBody("jms:queue:numbers",
				ExchangePattern.InOut, 22);
		System.out.println("... the result is: " + response);

		System.exit(0);
	}
}

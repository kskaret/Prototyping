package no.kantega.kriska.client;

import no.kantega.kriska.server.Multiplier;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringRemoting {
	public static void main(final String[] args) {
		System.out
				.println("Notice this client requires that the CamelServer is already running!");

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"camel-client-remoting.xml");
		// just get the proxy to the service and we as the client can use the
		// "proxy" as it was
		// a local object we are invoking. Camel will under the covers do the
		// remote communication
		// to the remote ActiveMQ server and fetch the response.
		Multiplier multiplier = context.getBean("multiplierProxy",
				Multiplier.class);

		System.out.println("Invoking the multiply with 33");
		int response = multiplier.multiply(33);
		System.out.println("... the result is: " + response);

		System.exit(0);
	}
}

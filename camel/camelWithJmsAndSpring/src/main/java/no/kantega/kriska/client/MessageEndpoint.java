package no.kantega.kriska.client;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Producer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageEndpoint {
	public static void main(final String[] args) throws Exception {
		System.out
				.println("Notice this client requires that the CamelServer is already running!");

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"camel-client.xml");
		CamelContext camel = context
				.getBean("camel-client", CamelContext.class);

		// get the endpoint from the camel context
		Endpoint endpoint = camel.getEndpoint("jms:queue:numbers");

		// create the exchange used for the communication
		// we use the in out pattern for a synchronized exchange where we expect
		// a response
		Exchange exchange = endpoint.createExchange(ExchangePattern.InOut);
		// set the input on the in body
		// must you correct type to match the expected type of an Integer object
		exchange.getIn().setBody(11);

		// to send the exchange we need an producer to do it for us
		Producer producer = endpoint.createProducer();
		// start the producer so it can operate
		producer.start();

		// let the producer process the exchange where it does all the work in
		// this oneline of code
		System.out.println("Invoking the multiply with 11");
		producer.process(exchange);

		// get the response from the out body and cast it to an integer
		int response = exchange.getOut().getBody(Integer.class);
		System.out.println("... the result is: " + response);

		// stop and exit the client
		producer.stop();
		System.exit(0);
	}
}

package no.kantega.kriska.webcommando;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CommandoServiceTest {

	@Autowired
	CommandoService commandoService;

	/**
	 * hallo-hallo-hvordan står det til-bare bra
	 */
	@Test
	public void halloHallo() {

		String answer = commandoService.question("hallo");
		Assert.assertEquals("hallo", answer);
		answer = commandoService.question("hvordan står det til");
		 Assert.assertEquals("bare bra", answer);

	}
}

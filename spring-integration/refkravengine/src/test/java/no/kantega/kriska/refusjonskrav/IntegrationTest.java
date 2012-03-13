package no.kantega.kriska.refusjonskrav;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IntegrationTest {

	private Skjemasett byggSkjemasettInput() {
		return new Skjemasett("A", 3)//
				.leggTilSkjema(1)//
				.leggTilSkjema(2)//
				.leggTilSkjema(3)//
				.leggTilSkjema(4)//
				.leggTilSkjema(5)//
				.leggTilSkjema(6);//
	}

	private Refusjonskrav byggRefusjonskravInput() {
		return new Refusjonskrav("A", 3)//
				.leggTilSkjema(1)//
				.leggTilSkjema(2)//
				.leggTilSkjema(3)//
				.leggTilSkjema(4)//
				.leggTilSkjema(5)//
				.leggTilSkjema(6);
	}

	@Test
	public void filterIntegration() throws InterruptedException {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"refkravIntegration.xml");

		SkjemasettEngine skjemasettEngine = context.getBean("sendRefusjonskrav",
				SkjemasettEngine.class);

		Skjemasett skjemasett = byggSkjemasettInput();

		skjemasettEngine.sendSkjemasett(skjemasett);

		validerSluttilstand(skjemasett);
	}

	private void validerSluttilstand(Skjemasett skjemasett) {
		for (Skjema skjema : skjemasett.getSkjemaListe()) {
			Assert.assertTrue("Skjema " + skjema.getSkjemanNr() + " er sendt",
					skjema.isSendt());
			Assert.assertTrue("Skjema " + skjema.getSkjemanNr()
					+ " er validert", skjema.isValidert());
		}
	}

	@Test
	public void skjemasettRouterIntegration() throws InterruptedException {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"routingIntegration.xml");

		RefusjonskravEngine refusjonskravEngine = context.getBean(
				"sendRefusjonskrav", RefusjonskravEngine.class);

		Refusjonskrav refusjonskrav = byggRefusjonskravInput();

		refusjonskravEngine.sendRefusjonskrav(refusjonskrav);

		for (Skjema skjema : refusjonskrav) {
			Assert.assertTrue("Skjema " + skjema.getSkjemanNr() + " er sendt",
					skjema.isSendt());
			Assert.assertTrue("Skjema " + skjema.getSkjemanNr()
					+ " er validert", skjema.isValidert());
		}
	}
}

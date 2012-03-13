package no.kantega.kriska.refusjonskrav;

import java.util.Iterator;

import junit.framework.Assert;

import org.junit.Test;

public class RefusjonskravTest {
	@Test
	public void skjemaSkalHoldesIRekkef¿lge() {
		Refusjonskrav refusjonskrav = new Refusjonskrav("hest", 5);

		refusjonskrav.leggTilSkjema(new SkjemaMock(1, true, true));
		refusjonskrav.leggTilSkjema(new SkjemaMock(2, false, false));
		refusjonskrav.leggTilSkjema(new SkjemaMock(3, false, false));
		refusjonskrav.leggTilSkjema(new SkjemaMock(4, true, false));
		refusjonskrav.leggTilSkjema(new SkjemaMock(5, true, false));

		Iterator<Skjema> it = refusjonskrav.iterator();

		Assert.assertEquals(4, it.next().getSkjemanNr().intValue());
		Assert.assertEquals(5, it.next().getSkjemanNr().intValue());
		Assert.assertEquals(2, it.next().getSkjemanNr().intValue());
		Assert.assertEquals(3, it.next().getSkjemanNr().intValue());
		Assert.assertEquals(1, it.next().getSkjemanNr().intValue());

	}

	class SkjemaMock extends Skjema {

		public SkjemaMock(int skjemanNr, boolean sendt, boolean validert) {
			super(skjemanNr, null);
			this.setSendt(sendt);
			this.setValidert(validert);
		}

	}

}

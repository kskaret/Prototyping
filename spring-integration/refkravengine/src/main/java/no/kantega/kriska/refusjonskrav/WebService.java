package no.kantega.kriska.refusjonskrav;

public class WebService {

	public Skjema sendSkjema(Skjema skjema) throws InterruptedException {
		System.out.println("Sender " + skjema);
		if (!skjema.isSendt()) {
			Thread.sleep(100);
			// if (skjema.getSkjemanNr() != 3) {
			skjema.getSkjemasett().sendSkjema(skjema);
			// }
		}
		return skjema;
	}

	public Skjema validerSkjema(Skjema skjema) throws InterruptedException {
		System.out.println("Validerer " + skjema);
		if (!skjema.isValidert()) {
			Thread.sleep(50);
			skjema.getSkjemasett().validerSkjema(skjema);
		}
		return skjema;
	}

	public Refusjonskrav sendSkjema(Refusjonskrav refusjonskrav)
			throws InterruptedException {
		Skjema skjema = refusjonskrav.getSkjemaTilBehandling();
		System.out.println("Sender " + skjema);
		if (!skjema.isSendt()) {
			Thread.sleep(100);
			// if (skjema.getSkjemanNr() != 3) {
			refusjonskrav.sendSkjema(skjema);
			// }
		}
		return refusjonskrav;
	}

	public Refusjonskrav validerSkjema(Refusjonskrav refusjonskrav)
			throws InterruptedException {
		Skjema skjema = refusjonskrav.getSkjemaTilBehandling();
		System.out.println("Validerer " + skjema);
		if (!skjema.isValidert()) {
			Thread.sleep(50);
			refusjonskrav.validerSkjema(skjema);
		}
		return refusjonskrav;
	}
}

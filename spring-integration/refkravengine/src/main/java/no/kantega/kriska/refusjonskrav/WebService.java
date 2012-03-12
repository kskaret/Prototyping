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

	public Skjemasett sendSkjema(Skjemasett skjemasett)
			throws InterruptedException {
		Skjema skjema = skjemasett.getSkjemaTilSending();
		System.out.println("Sender " + skjema);
		if (!skjema.isSendt()) {
			Thread.sleep(100);
			// if (skjema.getSkjemanNr() != 3) {
			skjemasett.sendSkjema(skjema);
			// }
		}
		return skjemasett;
	}

	public Skjemasett validerSkjema(Skjemasett skjemasett)
			throws InterruptedException {
		Skjema skjema = skjemasett.getSkjemaTilValidering();
		System.out.println("Validerer " + skjema);
		if (!skjema.isValidert()) {
			Thread.sleep(50);
			skjemasett.validerSkjema(skjema);
		}
		return skjemasett;
	}
}

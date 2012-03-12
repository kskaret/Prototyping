package no.kantega.kriska.refusjonskrav;

public class SkjemaFilter {

	public boolean skjemaKlarTilSending(Skjema skjema) {
		System.out.println("Filtrering for sending av " + skjema);
		return skjema.isKlarTilSending();
	}

	public boolean skjemaKlarTilValidering(Skjema skjema) {
		System.out.println("Filtrering for validering av " + skjema);

		return skjema.isKlarTilValidering();
	}
}

package no.kantega.kriska.refusjonskrav;

import java.util.List;

public class SkjemasettSplitter {
	public List<Skjema> split(Skjemasett skjemasett) {

		System.out.println("splitter skjemasett " + skjemasett);

		return skjemasett.getSkjemaListe();
	}

	public Skjemasett ferdigstill(List<Skjema> ferdigeSkjema) {
		return ferdigeSkjema.get(0).getSkjemasett();
	}
}

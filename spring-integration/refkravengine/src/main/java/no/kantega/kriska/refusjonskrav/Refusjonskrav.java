package no.kantega.kriska.refusjonskrav;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import no.kantega.kriska.refusjonskrav.Skjema.SkjemaFlyt;

public class Refusjonskrav implements Iterable<Skjema> {

	private SortedSet<Skjema> skjemaSet;

	private String id;

	private int antallSkjema;

	public Refusjonskrav(String id, int antallSkjema) {
		this.id = id;
		this.antallSkjema = antallSkjema;
		skjemaSet = new TreeSet<Skjema>();

	}

	public Refusjonskrav leggTilSkjema(int i) {
		skjemaSet.add(new Skjema(i, null));
		return this;
	}

	public Refusjonskrav leggTilSkjema(Skjema skjema) {
		skjemaSet.add(skjema);
		return this;
	}

	public SkjemaFlyt getNesteFlyt() {
		return skjemaSet.first().getSkjemaFlyt();

	}

	public Skjema getSkjemaTilBehandling() {
		return skjemaSet.first();
	}

	public void sendSkjema(Skjema skjema) {
		skjemaSet.remove(skjema);
		skjema.setSendt(true);
		skjemaSet.add(skjema);
	}

	public void validerSkjema(Skjema skjema) {
		skjemaSet.remove(skjema);
		skjema.setValidert(true);
		skjemaSet.add(skjema);
	}

	@Override
	public Iterator<Skjema> iterator() {
		return skjemaSet.iterator();
	}
	
	@Override
	public String toString() {
		return "Refusjonskrav " + id;
	}

}

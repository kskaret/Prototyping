package no.kantega.kriska.refusjonskrav;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Skjemasett {

	private SortedSet<Skjema> ubehandledeSkjema;

	private SortedSet<Skjema> sendteSkjema;

	private List<Skjema> ferdigValiderteSkjema;

	private String id;

	private int antallSkjema;

	public Skjemasett(String id, int antallSkjema) {
		super();
		this.id = id;
		this.antallSkjema = antallSkjema;
		ubehandledeSkjema = new TreeSet<Skjema>(new SkjemaComparator());
		sendteSkjema = new TreeSet<Skjema>(new SkjemaComparator());
		ferdigValiderteSkjema = new ArrayList<Skjema>();

	}

	class SkjemaComparator implements Comparator<Skjema> {

		@Override
		public int compare(Skjema o1, Skjema o2) {
			return o1.getSkjemanNr().compareTo(o2.getSkjemanNr());
		}

	}

	public Skjemasett leggTilSkjema(int i) {
		ubehandledeSkjema.add(new Skjema(i, this));
		return this;
	}

	public List<Skjema> getSkjemaListe() {

		return new ArrayList<Skjema>(ubehandledeSkjema);
	}

	public String getId() {
		return id;
	}

	public int getAntallSkjema() {
		return antallSkjema;
	}

	@Override
	public String toString() {
		return "Skjemasett " + id;
	}

	public boolean isKlarTilSending(Skjema skjema) {
		return skjema.getSkjemanNr().equals(getForesteUsendteSkjema());
	}

	public boolean isKlarTilValidering(Skjema skjema) {
		return skjema.isSendt()
				&& skjema.getSkjemanNr()
						.equals(getFoersteIkkeValiderteSkjema());
	}

	public Integer getForesteUsendteSkjema() {
		return ubehandledeSkjema.first().getSkjemanNr();
	}

	public Integer getFoersteIkkeValiderteSkjema() {
		return sendteSkjema.first().getSkjemanNr();
	}

	public void sendSkjema(Skjema skjema) {
		ubehandledeSkjema.remove(skjema);
		sendteSkjema.add(skjema);
		skjema.setSendt(true);
	}

	public void validerSkjema(Skjema skjema) {
		sendteSkjema.remove(skjema);
		ferdigValiderteSkjema.add(skjema);
		skjema.setValidert(true);
	}

}

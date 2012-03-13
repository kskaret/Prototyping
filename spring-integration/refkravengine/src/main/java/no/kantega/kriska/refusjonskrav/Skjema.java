package no.kantega.kriska.refusjonskrav;

import org.apache.commons.lang.builder.CompareToBuilder;

public class Skjema implements Comparable<Skjema> {

	private Integer skjemaNr;

	private boolean sendt;
	private boolean validert;
	private Skjemasett skjemasett;

	public enum SkjemaFlyt {
		TIL_VALIDERING(1, "tilValidering"), TIL_SENDING(2, "tilSending"), FERDIG(
				3, "ferdigeRefusjonskrav");

		private int prioritet;

		private String value;

		private SkjemaFlyt(int prioritet, String value) {
			this.prioritet = prioritet;
			this.value = value;
		}

		public Integer getPrioritet() {
			return prioritet;
		}

		public String value() {
			return value;
		}

	}

	public Skjema(int skjemanNr, Skjemasett skjemasett) {
		this.skjemaNr = skjemanNr;
		sendt = false;
		validert = false;
		this.skjemasett = skjemasett;
	}

	public Integer getSkjemanNr() {
		return skjemaNr;
	}

	@Override
	public String toString() {
		return "Skjema " + skjemaNr + " " + sendt + " " + validert;
	}

	public boolean isKlarTilSending() {
		return skjemasett.isKlarTilSending(this);
	}

	public boolean isKlarTilValidering() {
		return skjemasett.isKlarTilValidering(this);
	}

	public boolean isSendt() {
		return sendt;
	}

	public void setSendt(boolean sendt) {
		this.sendt = sendt;
	}

	public boolean isValidert() {
		return validert;
	}

	public void setValidert(boolean validert) {
		this.validert = validert;
	}

	public Skjemasett getSkjemasett() {
		return skjemasett;
	}

	public SkjemaFlyt getSkjemaFlyt() {
		if (sendt && validert) {
			return SkjemaFlyt.FERDIG;
		} else if (sendt && !validert) {
			return SkjemaFlyt.TIL_VALIDERING;
		} else {
			return SkjemaFlyt.TIL_SENDING;
		}
	}

	@Override
	public int compareTo(Skjema skjema2) {
		return new CompareToBuilder()//
				.append(getSkjemaFlyt().getPrioritet(),
						skjema2.getSkjemaFlyt().getPrioritet())//
				.append(skjemaNr, skjema2.getSkjemanNr())//
				.toComparison();
	}
}

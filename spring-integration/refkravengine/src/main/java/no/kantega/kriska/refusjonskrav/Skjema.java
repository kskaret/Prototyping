package no.kantega.kriska.refusjonskrav;

public class Skjema {

	private Integer skjemaNr;

	private boolean sendt;
	private boolean validert;
	private Skjemasett skjemasett;

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

}

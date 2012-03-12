package no.kantega.kriska.refusjonskrav;

public class SkjemasettRouter {

	public String styrSkjemasett(Skjemasett skjemasett) {
		return skjemasett.getNesteFlyt().value();
	}
}

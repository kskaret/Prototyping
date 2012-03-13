package no.kantega.kriska.refusjonskrav;

public class SkjemasettRouter {

	public String styrSkjemasett(Refusjonskrav refusjonskrav) {
		return refusjonskrav.getNesteFlyt().value();
	}
}

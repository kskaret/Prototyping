package no.kantega.kriska.refusjonskrav;

import java.util.Comparator;

import org.springframework.integration.message.GenericMessage;

public class SkjemaComparator implements Comparator<GenericMessage<Skjema>> {

	@Override
	public int compare(GenericMessage<Skjema> o1, GenericMessage<Skjema> o2) {
		return o1.getPayload().getSkjemanNr()
				.compareTo(o2.getPayload().getSkjemanNr());
	}

}

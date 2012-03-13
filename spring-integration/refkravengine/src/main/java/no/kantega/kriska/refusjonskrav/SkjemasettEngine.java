package no.kantega.kriska.refusjonskrav;

import org.springframework.integration.annotation.Gateway;

public interface SkjemasettEngine {


	@Gateway(requestChannel = "skjemasett")
	void sendSkjemasett(Skjemasett skjemaett);
}

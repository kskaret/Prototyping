package no.kantega.kriska.refusjonskrav;

import org.springframework.integration.annotation.Gateway;

public interface RefusjonskravEngine {

	@Gateway(requestChannel = "skjemasett")
	void sendRefusjonskrav(Skjemasett skjemasett);
}

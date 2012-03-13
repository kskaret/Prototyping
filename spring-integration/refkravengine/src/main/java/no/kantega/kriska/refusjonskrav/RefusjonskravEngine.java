package no.kantega.kriska.refusjonskrav;

import org.springframework.integration.annotation.Gateway;

public interface RefusjonskravEngine {


	@Gateway(requestChannel = "refusjonskrav")
	void sendRefusjonskrav(Refusjonskrav refusjonskrav);
}

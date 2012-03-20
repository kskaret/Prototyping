package no.kantega.kriska;

import no.kantega.kriska.webcommando.CommandLinePage;
import no.kantega.kriska.webcommando.CommandoService;

import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Required;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see no.kantega.kriska.Start#main(String[])
 */
public class WicketApplication extends WebApplication {

	private CommandoService commandoService;

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<CommandLinePage> getHomePage() {
		return CommandLinePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();

		// add your configuration here
	}

	public CommandoService getCommandoService() {
		return commandoService;
	}

	@Required
	public void setCommandoService(CommandoService commandoService) {
		this.commandoService = commandoService;
	}

}

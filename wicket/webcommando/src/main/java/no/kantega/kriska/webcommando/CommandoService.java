package no.kantega.kriska.webcommando;

import org.springframework.beans.factory.annotation.Required;

import no.kantega.kriska.conversation.ConversationRepository;
import no.kantega.kriska.conversation.ConversationRepository2;

public class CommandoService {

	private ConversationRepository conversationRepository;

	private ConversationRepository2 conversationRepository2;

	public CommandoService() {
		conversationRepository2 = new ConversationRepository2();
	}

	public String question(String input) {
		return conversationRepository2.speakSentence(input);
	}

	@Required
	public void setConversationRepository(
			ConversationRepository conversationRepository) {
		this.conversationRepository = conversationRepository;
	}

}

package no.kantega.kriska.webcommando;

import java.io.Serializable;

public class Commando implements Serializable {
	private static final long serialVersionUID = 1L;

	private String input;
	private String output;

	public Commando(String input, String output) {
		this.input = input;
		this.output = output;
	}

	public Commando() {
	}

	public String getInput() {
		return input;
	}

	public String getOutput() {
		return output;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public void setOutput(String output) {
		this.output = output;
	}

}

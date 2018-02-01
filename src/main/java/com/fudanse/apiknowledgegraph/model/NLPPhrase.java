package com.fudanse.apiknowledgegraph.model;

import java.util.ArrayList;
import java.util.List;

public class NLPPhrase {

	private List<String> phrases;

	private List<String> verbs = new ArrayList<>();

	private List<String> nouns = new ArrayList<>();

	public List<String> getPhrases() {
		return phrases;
	}

	public void setPhrase(List<String> phrases) {
		this.phrases = phrases;
	}

	public List<String> getVerbs() {
		return verbs;
	}

	public void setVerbs(List<String> verbs) {
		this.verbs = verbs;
	}

	public List<String> getNouns() {
		return nouns;
	}

	public void setNouns(List<String> nouns) {
		this.nouns = nouns;
	}

}

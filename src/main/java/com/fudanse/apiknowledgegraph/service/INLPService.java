package com.fudanse.apiknowledgegraph.service;

import java.util.List;

import com.fudanse.apiknowledgegraph.model.NLPPhrase;

public interface INLPService {

	public NLPPhrase analyzeSentence(String sentence);

	public boolean similarity(List<String> phrases1, List<String> phrases2);

	public boolean similarity(String phrase1, String phrase2);

}

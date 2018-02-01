package com.fudanse.apiknowledgegraph.service;

import java.util.List;
import java.util.Properties;

import com.fudanse.apiknowledgegraph.model.NLPPhrase;

import edu.stanford.nlp.dcoref.CorefCoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.semgraph.SemanticGraphEdge;
import edu.stanford.nlp.util.CoreMap;
import edu.sussex.nlp.jws.JWS;
import edu.sussex.nlp.jws.Lin;

public class NLPService implements INLPService {

	private static final String DIR = "/Users/xiyaoguo/Documents/WordNet";

	@Override
	public NLPPhrase analyzeSentence(String sentence) {
		if (sentence == null || sentence.isEmpty())
			return null;
		NLPPhrase nlp = new NLPPhrase();
		Annotation document = runAllAnnotators(sentence);
		parserOutput(document, nlp);
		return nlp;
	}

	@Override
	public boolean similarity(List<String> phrases1, List<String> phrases2) {
		if (phrases1 == null || phrases2 == null || phrases1.isEmpty() || phrases2.isEmpty())
			return false;
		for (String phrase1 : phrases1) {
			for (String phrase2 : phrases2) {
				if (maxScoreOfLin(phrase1, phrase2) > 0.7)
					return true;
			}
		}
		return false;
	}

	@Override
	public boolean similarity(String phrase1, String phrase2) {
		if (phrase1 == null || phrase2 == null || phrase1.isEmpty() || phrase2.isEmpty())
			return false;
		String[] strs1 = phrase1.split(" ");
		String[] strs2 = phrase2.split(" ");
		double sum = 0.0;
		for (String s1 : strs1) {
			for (String s2 : strs2) {
				double sc = maxScoreOfLin(s1, s2);
				sum += sc;
			}
		}
		return sum / (strs1.length * strs2.length) > 0.6;
	}

	private double maxScoreOfLin(String str1, String str2) {
		Lin lin = new JWS(DIR, "2.1").getLin();
		double sc = lin.max(str1, str2, "n");
		if (sc == 0) {
			sc = lin.max(str1, str2, "v");
		}
		return sc;
	}

	private Annotation runAllAnnotators(String text) {
		// creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER,
		// parsing, and coreference resolution
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		// create an empty Annotation just with the given text
		Annotation document = new Annotation(text);

		// run all Annotators on this text
		pipeline.annotate(document);
		return document;

	}

	private void parserOutput(Annotation document, NLPPhrase nlp) {
		// these are all the sentences in this document
		// a CoreMap is essentially a Map that uses class objects as keys and has values
		// with custom types
		List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

		for (CoreMap sentence : sentences) {
			// traversing the words in the current sentence
			// a CoreLabel is a CoreMap with additional token-specific methods
			SemanticGraph dependencies = sentence.get(SemanticGraphCoreAnnotations.BasicDependenciesAnnotation.class);
			for (SemanticGraphEdge edge : dependencies.edgeListSorted()) {
				// compound(Return-2, getExitTransition-1) amod(Returns-2, isCancelled-1)
				if (edge.getRelation().toString().equals("dobj")) {
					String dobjPhrase = edge.getSource().value() + " " + edge.getTarget().value();
					nlp.getPhrases().add(this.Lemmatization(dobjPhrase));
					// System.out.println(dobjPhrase);
				}
				if (edge.getRelation().toString().equals("nsubjpass")) {
					String nsubjpassPhrase = edge.getSource().value() + " " + edge.getTarget().value();
					nlp.getPhrases().add(this.Lemmatization(nsubjpassPhrase));
					// System.out.println(nsubjpassPhrase);
				}
			}
			for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
				// this is the text of the token
				token.get(CoreAnnotations.TextAnnotation.class);
				// this is the POS tag of the token
				String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
				// this is the NER label of the token
				// String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
				String lemma = token.get(CoreAnnotations.LemmaAnnotation.class);
				if ((pos.equals("VB") || pos.equals("VBD") || pos.equals("VBN") || pos.equals("VBG")
						|| pos.equals("VBP") || pos.equals("VBZ")) && !lemma.equals("be")) {
					nlp.getVerbs().add(lemma);
				}
				if (pos.equals("NN") || pos.equals("NNS") || pos.equals("NNP") || pos.equals("NNPS")) {
					nlp.getNouns().add(lemma);
				}
			}
		}

		// This is the coreference link graph
		// Each chain stores a set of mentions that link to each other,
		// along with a method for getting the most representative mention
		// Both sentence and token offsets start at 1!
		document.get(CorefCoreAnnotations.CorefChainAnnotation.class);
	}

	public String Lemmatization(String str) {
		String lemSentence = "";
		Annotation document = runAllAnnotators(str);
		List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
		for (CoreMap sentence : sentences) {
			for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
				String lemma = token.get(CoreAnnotations.LemmaAnnotation.class);
				lemSentence += lemma;
				lemSentence += " ";
			}
		}
		return lemSentence.trim();
	}

}

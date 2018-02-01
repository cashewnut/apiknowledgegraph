package com.fudanse.apiknowledgegraph.generate;

import com.fudanse.apiknowledgegraph.model.NLPPhrase;
import com.fudanse.apiknowledgegraph.service.INLPService;
import com.fudanse.apiknowledgegraph.service.IVertexService;
import com.fudanse.apiknowledgegraph.service.NLPService;
import com.fudanse.apiknowledgegraph.service.VertexService;

public class PartialModel {

	private INLPService nlpService = new NLPService();

	private IVertexService vertexService = new VertexService();

	public String getView(String sentence) {
		String view = "";
		NLPPhrase nlp = nlpService.analyzeSentence(sentence);
		
		return view;
	}

}

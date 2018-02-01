package com.fudanse.apiknowledgegraph.service;

import java.util.List;

import com.fudanse.apiknowledgegraph.model.Edge;
import com.fudanse.apiknowledgegraph.model.EntryMethod;
import com.fudanse.apiknowledgegraph.model.Listener;
import com.fudanse.apiknowledgegraph.model.Method;
import com.fudanse.apiknowledgegraph.model.Vertex;

public interface IVertexService {

	public void saveVertex(Vertex vertex);

	public void saveEdge(Vertex left, Vertex right, Edge edge);
	
	public List<Vertex> getByLabel(String label);
	
	public List<Listener> getListenerByViewName(String viewName);
	
	public List<Method> getMethodByViewName(String viewName);
	
	public List<EntryMethod> getEntryMethodByListener(List<Listener> lis);

}

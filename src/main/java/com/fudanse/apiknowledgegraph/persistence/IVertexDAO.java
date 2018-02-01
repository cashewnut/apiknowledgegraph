package com.fudanse.apiknowledgegraph.persistence;

import java.util.List;

import com.fudanse.apiknowledgegraph.model.Edge;
import com.fudanse.apiknowledgegraph.model.EntryMethod;
import com.fudanse.apiknowledgegraph.model.Listener;
import com.fudanse.apiknowledgegraph.model.Method;
import com.fudanse.apiknowledgegraph.model.Vertex;

public interface IVertexDAO {

	public void saveVertex(Vertex vertex);

	public void saveEdge(Vertex left, Vertex right, Edge edge);

	public List<Vertex> selectByLabel(String label);

	public List<Listener> selectListenerByViewName(String viewName);

	public List<Method> selectMethodByViewName(String viewName);

	public List<EntryMethod> selectEntryMethodByListener(List<Listener> lis);

}

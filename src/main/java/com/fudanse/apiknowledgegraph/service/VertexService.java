package com.fudanse.apiknowledgegraph.service;

import java.util.List;

import com.fudanse.apiknowledgegraph.model.Edge;
import com.fudanse.apiknowledgegraph.model.EntryMethod;
import com.fudanse.apiknowledgegraph.model.Listener;
import com.fudanse.apiknowledgegraph.model.Method;
import com.fudanse.apiknowledgegraph.model.Vertex;
import com.fudanse.apiknowledgegraph.persistence.IVertexDAO;
import com.fudanse.apiknowledgegraph.persistence.VertexDAO;

public class VertexService implements IVertexService {

	IVertexDAO vertexDAO = new VertexDAO();

	@Override
	public void saveVertex(Vertex vertex) {
		vertexDAO.saveVertex(vertex);
	}

	@Override
	public void saveEdge(Vertex left, Vertex right, Edge edge) {
		if (left != null && right != null && left.getId() != null && right.getId() != null) {
			vertexDAO.saveEdge(left, right, edge);
		}
	}

	@Override
	public List<Vertex> getByLabel(String label) {
		return vertexDAO.selectByLabel(label);
	}

	@Override
	public List<Listener> getListenerByViewName(String viewName) {
		return vertexDAO.selectListenerByViewName(viewName);
	}

	@Override
	public List<Method> getMethodByViewName(String viewName) {
		return vertexDAO.selectMethodByViewName(viewName);
	}

	@Override
	public List<EntryMethod> getEntryMethodByListener(List<Listener> lis) {
		return vertexDAO.selectEntryMethodByListener(lis);
	}

}

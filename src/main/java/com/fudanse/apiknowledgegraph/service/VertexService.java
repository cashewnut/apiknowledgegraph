package com.fudanse.apiknowledgegraph.service;

import com.fudanse.apiknowledgegraph.model.Edge;
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

}

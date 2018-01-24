package com.fudanse.apiknowledgegraph.service;

import com.fudanse.apiknowledgegraph.model.Edge;
import com.fudanse.apiknowledgegraph.model.Vertex;

public interface IVertexService {

	public void saveVertex(Vertex vertex);

	public void saveEdge(Vertex left, Vertex right, Edge edge);

}

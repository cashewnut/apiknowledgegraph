package com.fudanse.apiknowledgegraph.persistence;

import com.fudanse.apiknowledgegraph.model.Edge;
import com.fudanse.apiknowledgegraph.model.Vertex;

public interface IVertexDAO {

	public void saveVertex(Vertex vertex);

	public void saveEdge(Vertex left, Vertex right, Edge edge);

}

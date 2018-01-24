package com.fudanse.apiknowledgegraph.persistence;

import com.fudanse.apiknowledgegraph.model.Edge;
import com.fudanse.apiknowledgegraph.model.Vertex;

public class GenerateCypher {

	public final static Edge PARENT = new Edge("Parent", "Parent");
	public final static Edge DEPENDENCY = new Edge("Dependency", "Dependency");

	public static String saveVertex(Vertex v) {
		String insertCypher = "CREATE(n:knowledge:" + v.getLabel()
				+ "{name:$name,description:$description,simpleDescription:$simpleDescription}) RETURN id(n)";
		return insertCypher;
	}

	public static String saveEdge(Vertex left, Vertex right, Edge edge) {
		return "Start a=node(" + left.getId() + "),b=node(" + right.getId() + ") create (a)-[r:" + edge.getLabel()
				+ "{name:" + edge.getName() + "}]->(b)";
	}

}

package com.fudanse.apiknowledgegraph.persistence;

import java.util.List;
import java.util.stream.Collectors;

import com.fudanse.apiknowledgegraph.model.Edge;
import com.fudanse.apiknowledgegraph.model.Listener;
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
				+ "{name:'" + edge.getName() + "'}]->(b)";
	}

	public static String getByLabel(String label) {
		return "match(n:knowledge:" + label + ") return n";
	}

	public static String getByViewName(String viewName, String label) {
		return "match(n:knowledge:view)-[:Parent]->(d:knowledge:" + label + ") where n.name='" + viewName
				+ "' return d";
	}

	public static String getEntryMethodByListener(List<Listener> lis) {
		String str = lis.stream().map((n) -> addMark(n)).collect(Collectors.joining(","));
		return "match(n:knowledge:Listener)-[:Parent]->(m:knowledge:EntryMethod) where n.name in [" + str
				+ "] return m";
	}

	private static String addMark(Listener l) {
		return "'" + l.getName() + "'";
	}

}

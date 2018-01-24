package com.fudanse.apiknowledgegraph.generate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fudanse.apiknowledgegraph.enums.EnumVertexLabel;
import com.fudanse.apiknowledgegraph.model.Listener;
import com.fudanse.apiknowledgegraph.model.Vertex;
import com.fudanse.apiknowledgegraph.model.View;
import com.fudanse.apiknowledgegraph.persistence.GenerateCypher;
import com.fudanse.apiknowledgegraph.service.IVertexService;
import com.fudanse.apiknowledgegraph.service.VertexService;

public class GenerateGraph {

	private IVertexService service = new VertexService();

	public Map<String, Integer> saveView(List<View> views) {
		Map<String, Integer> map = new HashMap<>();
		for (View v : views) {
			Vertex vertex = v.convert();
			service.saveVertex(vertex);
			v.setId(vertex.getId());
			map.put(v.getName(), v.getId());
		}
		return map;
	}

	public Map<String, Integer> saveCommonListener(List<Listener> lis, Map<String, Integer> viewMap) {
		Map<String, Integer> map = new HashMap<>();
		for (Listener l : lis) {
			Vertex vertex = l.convert();
			service.saveVertex(vertex);
			l.setId(vertex.getId());
			map.put(l.getName(), l.getId());
			for (String viewName : viewMap.keySet()) {
				Integer viewId = viewMap.get(viewName);
				service.saveEdge(new Vertex(viewId), new Vertex(l.getId()), GenerateCypher.PARENT);
			}
		}
		return map;
	}

	public Map<String, Integer> saveListener(List<Listener> lis, Map<String, Integer> viewMap) {
		Map<String, Integer> map = new HashMap<>();
		for (Listener l : lis) {
			Vertex vertex = l.convert();
			service.saveVertex(vertex);
			l.setId(vertex.getId());
			map.put(l.getName(), l.getId());
			Integer viewId = viewMap.get(l.getBelongto());
			service.saveEdge(new Vertex(viewId), new Vertex(l.getId()), GenerateCypher.PARENT);
		}
		return map;
	}

	public Map<String, Integer> saveEntryMethod(Map<String, Integer> listenerMap) {
		Map<String, Integer> map = new HashMap<>();
		for (String lname : listenerMap.keySet()) {
			Integer liId = listenerMap.get(lname);
			String name = lname.substring(0, lname.length() - 8);
			Vertex v = new Vertex();
			v.setLabel(EnumVertexLabel.ENTRYMETHOD.getValue());
			v.setName(name);
			service.saveVertex(v);
			service.saveEdge(new Vertex(liId), new Vertex(v.getId()), GenerateCypher.PARENT);
			map.put(name, v.getId());
		}
		return map;
	}

}

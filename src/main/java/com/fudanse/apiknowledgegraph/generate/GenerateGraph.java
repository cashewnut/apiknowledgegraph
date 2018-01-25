package com.fudanse.apiknowledgegraph.generate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fudanse.apiknowledgegraph.enums.EnumVertexLabel;
import com.fudanse.apiknowledgegraph.model.Event;
import com.fudanse.apiknowledgegraph.model.Listener;
import com.fudanse.apiknowledgegraph.model.Method;
import com.fudanse.apiknowledgegraph.model.MethodEventPair;
import com.fudanse.apiknowledgegraph.model.SubAction;
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
			name = name.contains(".") ? name.split("[.]")[1] : name;
			name = "o" + name.substring(1);
			Vertex v = new Vertex();
			v.setLabel(EnumVertexLabel.ENTRYMETHOD.getValue());
			v.setName(name);
			service.saveVertex(v);
			service.saveEdge(new Vertex(liId), new Vertex(v.getId()), GenerateCypher.PARENT);
			map.put(name, v.getId());
		}
		return map;
	}

	public Map<String, Integer> saveEvent(List<Event> events) {
		Map<String, Integer> map = new HashMap<>();
		for (Event e : events) {
			Vertex v = e.convert();
			service.saveVertex(v);
			e.setId(v.getId());
			map.put(e.getName(), e.getId());
		}
		return map;
	}

	public void saveMethod(List<Method> methods, Map<String, Integer> viewMap) {
		for (Method m : methods) {
			Vertex v = m.convert();
			service.saveVertex(v);
			m.setId(v.getId());
			Integer viewId = viewMap.get(m.getBelongto());
			service.saveEdge(new Vertex(viewId), new Vertex(m.getId()), GenerateCypher.PARENT);
		}
	}

	public void saveSubAction(List<SubAction> subActions, Map<String, Integer> eventMap) {
		for (SubAction sa : subActions) {
			Vertex v = sa.convert();
			service.saveVertex(v);
			sa.setId(v.getId());
			Integer eventId = eventMap.get(sa.getBelongto());
			service.saveEdge(new Vertex(eventId), new Vertex(sa.getId()), GenerateCypher.PARENT);
		}
	}

	public void match(List<MethodEventPair> pairs, Map<String, Integer> methodMap, Map<String, Integer> eventMap) {
		for (MethodEventPair mep : pairs) {
			Integer methodId = methodMap.get(mep.getEntryMethod());
			Integer eventId = eventMap.get(mep.getEvent());
			service.saveEdge(new Vertex(methodId), new Vertex(eventId), GenerateCypher.DEPENDENCY);
		}
	}

	public void generate() {
		String basePath = "/home/fdse/xiyaoguo/javacode/knowledges/";
		List<View> views = ParseFile.generateView(basePath + "View.csv");
		Map<String, Integer> viewMap = saveView(views);
		List<Listener> lis = ParseFile.getCommonListener(basePath + "Listener1.csv");
		Map<String, Integer> listenerMap = saveCommonListener(lis, viewMap);
		lis = ParseFile.getListener(basePath + "Listener2.csv");
		listenerMap.putAll(saveListener(lis, viewMap));
		Map<String, Integer> entryMethodMap = saveEntryMethod(listenerMap);
		List<Event> events = ParseFile.getEvent(basePath + "Event.csv");
		Map<String, Integer> eventMap = saveEvent(events);
		List<MethodEventPair> pairs = ParseFile.getMethodEventPair(basePath + "MethodEventPair.csv");
		match(pairs, entryMethodMap, eventMap);
		List<SubAction> subActions = ParseFile.getSubAction(basePath + "SubAction.csv");
		saveSubAction(subActions, eventMap);
		List<Method> methods = ParseFile.getMethod(basePath + "Method.csv");
		saveMethod(methods, viewMap);

	}

	public static void main(String[] args) {
		GenerateGraph gg = new GenerateGraph();
		gg.generate();
	}

}

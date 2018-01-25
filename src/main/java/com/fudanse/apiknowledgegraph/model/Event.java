package com.fudanse.apiknowledgegraph.model;

import com.fudanse.apiknowledgegraph.enums.EnumVertexLabel;

public class Event {

	private Integer id;
	private String name;
	private String description;

	public Event() {
	}

	public Event(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Vertex convert() {
		Vertex v = new Vertex();
		v.setName(name);
		v.setDescription(description);
		v.setLabel(EnumVertexLabel.EVENT.getValue());
		return v;
	}

}

package com.fudanse.apiknowledgegraph.model;

import com.fudanse.apiknowledgegraph.enums.EnumVertexLabel;

public class View {

	private Integer id;
	private String name;
	private String description;
	private String simpleDescription;

	public View() {
	}

	public View(String name, String description, String simpleDescription) {
		this.name = name;
		this.description = description;
		this.simpleDescription = simpleDescription;
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

	public String getSimpleDescription() {
		return simpleDescription;
	}

	public void setSimpleDescription(String simpleDescription) {
		this.simpleDescription = simpleDescription;
	}
	
	public Vertex convert() {
		Vertex v = new Vertex();
		v.setDescription(description);
		v.setName(name);
		v.setSimpleDescription(simpleDescription);
		v.setLabel(EnumVertexLabel.VIEW.getValue());
		return v;
	}

}

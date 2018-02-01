package com.fudanse.apiknowledgegraph.model;

import java.io.Serializable;

public class Vertex implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String label;
	private String name;
	private String description;
	private String simpleDescription;

	public Vertex() {

	}

	public Vertex(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	@Override
	public String toString() {
		return "Vertex [id=" + id + ", label=" + label + ", name=" + name + ", description=" + description
				+ ", simpleDescription=" + simpleDescription + "]";
	}

}

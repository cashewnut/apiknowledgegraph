package com.fudanse.apiknowledgegraph.model;

import com.fudanse.apiknowledgegraph.enums.EnumVertexLabel;

public class EntryMethod {

	private Integer id;
	private String name;

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

	public Vertex convert() {
		Vertex v = new Vertex();
		v.setLabel(EnumVertexLabel.ENTRYMETHOD.getValue());
		v.setName(name);
		return v;
	}

	@Override
	public String toString() {
		return "EntryMethod [id=" + id + ", name=" + name + "]";
	}

}

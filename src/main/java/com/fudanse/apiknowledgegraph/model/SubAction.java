package com.fudanse.apiknowledgegraph.model;

import com.fudanse.apiknowledgegraph.enums.EnumVertexLabel;

public class SubAction {

	private Integer id;
	private String name;
	private String description;
	private String belongto;

	public SubAction() {
	}

	public SubAction(String name, String description, String belongto) {
		this.name = name;
		this.description = description;
		this.belongto = belongto;
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

	public String getBelongto() {
		return belongto;
	}

	public void setBelongto(String belongto) {
		this.belongto = belongto;
	}

	public Vertex convert() {
		Vertex v = new Vertex();
		v.setLabel(EnumVertexLabel.SUBACTION.getValue());
		v.setName(name);
		v.setDescription(description);
		return v;
	}

}

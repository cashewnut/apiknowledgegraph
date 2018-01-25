package com.fudanse.apiknowledgegraph.model;

import com.fudanse.apiknowledgegraph.enums.EnumVertexLabel;

public class Method {

	private Integer id;
	private String name;
	private String description;
	private String belongto;

	public Method() {
	}

	public Method(String name, String description, String belongto) {
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
		v.setLabel(EnumVertexLabel.METHOD.getValue());
		v.setName(name);
		v.setDescription(description);
		return v;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((belongto == null) ? 0 : belongto.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Method other = (Method) obj;
		if (belongto == null) {
			if (other.belongto != null)
				return false;
		} else if (!belongto.equals(other.belongto))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}

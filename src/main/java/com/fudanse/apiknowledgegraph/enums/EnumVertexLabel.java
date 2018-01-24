package com.fudanse.apiknowledgegraph.enums;

public enum EnumVertexLabel {

	VIEW("View"), 
	METHOD("Method"), 
	LISTENER("Listener"), 
	ENTRYMETHOD("EntryMethod"), 
	EVENT("Event"), 
	SUBACTION("SubAction");

	private String value;

	private EnumVertexLabel(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}

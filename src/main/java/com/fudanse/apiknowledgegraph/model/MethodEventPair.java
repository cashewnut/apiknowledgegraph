package com.fudanse.apiknowledgegraph.model;

public class MethodEventPair {

	private String entryMethod;
	private String event;

	public MethodEventPair(String entryMethod, String event) {
		super();
		this.entryMethod = entryMethod;
		this.event = event;
	}

	public String getEntryMethod() {
		return entryMethod;
	}

	public void setEntryMethod(String entryMethod) {
		this.entryMethod = entryMethod;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

}

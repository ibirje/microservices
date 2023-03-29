package com.otakuma.utils.constants;

public class ExceptionData {

	private int id;
	private String text;
	
	
	
	
	public ExceptionData(int id, String text) {
		this.id = id;
		this.text = text;
	}
	
	public int getId() {
		return id;
	}
	public String getText() {
		return text;
	}
	@Override
	public String toString() {
		return id+"";
	}
	
}

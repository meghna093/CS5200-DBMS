package edu.northeastern.cs5200.model;

public class HeadingWidget extends Widget {
	private int size;
	
	@Override
	public String toString() {
		return "HeadingWidget ["+super.toString() + "size=" + size + "]";
	}
	public HeadingWidget() {
		super();
	}
	public HeadingWidget(int id, String name, String type, int width, int height, String cssClass, String cssStyle,
			String text, int order, int size) {
		super(id, name, type, width, height, cssClass, cssStyle, text, order);
		this.size = size;
	}
	public HeadingWidget(String name, String type, String text, int order, int size) {
		super(name, type, text, order);
		this.size = size;
	}	
	public HeadingWidget(String name, int width, int height, String cssClass, String cssStyle, String text, int order,
			int pageId, int size) {
		super(name, width, height, cssClass, cssStyle, text, order, pageId);
		this.size = size;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public HeadingWidget(int id, String name, String type, int width, int height, String cssClass, String cssStyle,
			String text, int order, int pageId, int size) {
		super(id, name, type, width, height, cssClass, cssStyle, text, order, pageId);
		this.size = size;
	}		
}

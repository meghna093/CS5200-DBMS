package edu.northeastern.cs5200.model;

public class Widget {
	private int id;
	private String type;
	private String name;
	private int width;
	private String cssClass;
	private int height;
	private int pageId;
	private String cssStyle;
	private int order;
	private String text;
	
	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", type=" + type + ", width=" + width + ", height=" + height + ", text=" + text + ", order=" + order + ", pageId=" + pageId+", ";
	}
	public Widget() {
		super();
	}
	public Widget(String name, String type, String text, int order) {
		super();
		this.name = name;
		this.type = type;
		this.text = text;
		this.order = order;
	}
	public Widget(String name, int order) {
		super();
		this.name = name;
		this.order = order;
	}
	public Widget(int id, String name, String type, int order, int pageId) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.order = order;
		this.pageId = pageId;
	}
	public Widget(int id, String name, String type, int width, int height, String cssClass, String cssStyle, String text, int order) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.width = width;
		this.height = height;
		this.cssClass = cssClass;
		this.cssStyle = cssStyle;
		this.text = text;
		this.order = order;
	}
	public Widget(String name, String type, int width, int height, int order) {
		super();
		this.name = name;
		this.type = type;
		this.width = width;
		this.height = height;
		this.order = order;
	}
	public Widget(int id, String name, String type, int width, int height, String cssClass, String cssStyle, String text, int order, int pageId) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.width = width;
		this.height = height;
		this.cssClass = cssClass;
		this.cssStyle = cssStyle;
		this.text = text;
		this.order = order;
		this.pageId = pageId;
	}
	public int getId() {
		return id;
	}
	public Widget(String name, int width, int height, String cssClass, String cssStyle, String text, int order,	int pageId) {
		super();
		this.name = name;
		this.width = width;
		this.height = height;
		this.cssClass = cssClass;
		this.cssStyle = cssStyle;
		this.text = text;
		this.order = order;
		this.pageId = pageId;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public int getWidth() {
		return width;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getHeight() {
		return height;
	}
	public String getCssClass() {
		return cssClass;
	}
	public String getCssStyle() {
		return cssStyle;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getText() {
		return text;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public int getOrder() {
		return order;
	}
	public int getPageId() {
		return pageId;
	}
	public void setOrder(int order) {
		this.order = order;
	}
}
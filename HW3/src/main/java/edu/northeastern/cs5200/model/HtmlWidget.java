package edu.northeastern.cs5200.model;

public class HtmlWidget extends Widget{
	private String html;
	
	@Override
	public String toString() {
		return "HtmlWidget [" + super.toString() + "html=" + html + "]";
	}
	public HtmlWidget() {
		super();
	}	
	public HtmlWidget(String name, String type, String text, int order, String html) {
		super(name, type, text, order);
		this.html = html;
	}
	public HtmlWidget(String name, String type, String text, int order) {
		super(name, type, text, order);
	}
	public HtmlWidget(int id, String name, String type, int width, int height, String cssClass, String cssStyle,
			String text, int order, String html) {
		super(id, name, type, width, height, cssClass, cssStyle, text, order);
		this.html = html;
	}
	public HtmlWidget(int id, String name, String type, int width, int height, String cssClass, String cssStyle,
			String text, int order, int pageId, String html) {
		super(id, name, type, width, height, cssClass, cssStyle, text, order, pageId);
		this.html = html;
	}
	public HtmlWidget(String name, int width, int height, String cssClass, String cssStyle, String text, int order,
			int pageId, String html) {
		super(name, width, height, cssClass, cssStyle, text, order, pageId);
		this.html = html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getHtml() {
		return html;
	}
}

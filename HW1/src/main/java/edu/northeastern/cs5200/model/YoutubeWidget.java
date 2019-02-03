package edu.northeastern.cs5200.model;

public class YoutubeWidget extends Widget {
	private String url;
	private boolean expandable;
	private boolean shareable;
	
	@Override
	public String toString() {
		return "YoutubeWidget ["+ super.toString() + "url=" + url + ", shareable=" + shareable + ", expandable=" + expandable+"]";
	}
	public YoutubeWidget() {
		super();
	}		
	public YoutubeWidget(String name, String type, int width, int height, int order, String url) {
		super(name, type, width, height, order);
		this.url = url;
	}
	public YoutubeWidget(String name, int width, int height, String cssClass, String cssStyle, String text, int order, int pageId, String url, boolean shareable, boolean expandable) {
		super(name, width, height, cssClass, cssStyle, text, order, pageId);
		this.url = url;
		this.shareable = shareable;
		this.expandable = expandable;
	}
	public YoutubeWidget(int id, String name, String type, int width, int height, String cssClass, String cssStyle, String text, int order, String url, boolean shareable, boolean expandable) {
		super(id, name, type, width, height, cssClass, cssStyle, text, order);
		this.url = url;
		this.shareable = shareable;
		this.expandable = expandable;
	}
	public String getUrl() {
		return url;
	}
	public YoutubeWidget(int id, String name, String type, int width, int height, String cssClass, String cssStyle, String text, int order, int pageId, String url, boolean shareable, boolean expandable) {
		super(id, name, type, width, height, cssClass, cssStyle, text, order, pageId);
		this.url = url;
		this.shareable = shareable;
		this.expandable = expandable;
	}
	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setShareble(boolean shareable) {
		this.shareable = shareable;
	}
	public boolean isShareable() {
		return shareable;
	}
	public boolean isExpandable() {
		return expandable;
	}
}

package com.singtel.core.beans;

public class SubMenuItem {
	
    private String title;
	
	private String link;

	public String getTitle() {
		return title;
	}

	public String getLink() {
		if(!link.endsWith(".html")) {
			return link + ".html";
		}
		return link;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	

}

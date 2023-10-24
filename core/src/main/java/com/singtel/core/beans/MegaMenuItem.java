package com.singtel.core.beans;

import java.util.List;

/**
 * @author sandipkumar
 *
 */
public class MegaMenuItem {
	
	private String title;
	
	private String link;
	
	private List<SubMenuItem> subMenuList;

	public String getTitle() {
		return title;
	}

	public String getLink() {
		if(!link.endsWith(".html")) {
			return link + ".html";
		}
		return link;
	}

	public List<SubMenuItem> getSubMenuList() {
		return subMenuList;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setSubMenuList(List<SubMenuItem> subMenuList) {
		this.subMenuList = subMenuList;
	}
	
	

}

/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.singtel.core.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.singtel.core.beans.MegaMenuItem;
import com.singtel.core.beans.SubMenuItem;

/**
 * @author sandipkumar
 *
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MegaMenuModel {

	@Self
	private Resource currentResource;

	@SlingObject
	private ResourceResolver resourceResolver;

	private List<MegaMenuItem> megamenu;
	
	private static final String HIDE_IN_NAV = "hideInNav";
	private static final String HIDE_ALL_SUB_NAV = "hideAllSubNav";
	private static final String TRUE = "true";

	/**r
	 * post construct.
	 */
	@PostConstruct
	protected void init() {
		PageManager pageMgr = resourceResolver.adaptTo(PageManager.class);
		Page currentPage = pageMgr.getContainingPage(currentResource);
		if (Objects.nonNull(currentPage)) {
			megamenu = new ArrayList<>();
			Iterator<Page> menuPages = currentPage.listChildren();
			while (menuPages.hasNext()) {
				Page menuPage = menuPages.next();
				String hideInNavMenu = menuPage.getContentResource().getValueMap().get(HIDE_IN_NAV,
						StringUtils.EMPTY);
				String hideAllSubNavMenu = menuPage.getContentResource().getValueMap().get(HIDE_ALL_SUB_NAV,
						StringUtils.EMPTY);
				if (!TRUE.equalsIgnoreCase(hideAllSubNavMenu) && !TRUE.equalsIgnoreCase(hideInNavMenu)) { 
					// It doesn't seems logical to hind menu but not it's
					// sub menu when user select hide in Nav hence hiding sub menu also when menu item hidden.
					MegaMenuItem megaMenuItem = new MegaMenuItem();
					megaMenuItem.setTitle(menuPage.getTitle());
					megaMenuItem.setLink(menuPage.getPath());
					megaMenuItem.setSubMenuList(getSubMenuList(menuPage));
					megamenu.add(megaMenuItem);
				}
			}
		}
	}
	
	/**
	 * @param menuPage
	 * @return sub menu list
	 */
	private List<SubMenuItem> getSubMenuList(Page menuPage) {
		Iterator<Page> subMenuPages = menuPage.listChildren();
		List<SubMenuItem> subMenuList = new ArrayList<>();
		while (subMenuPages.hasNext()) {
			Page subMenuPage = subMenuPages.next();
			String hideInNavSubMenu = subMenuPage.getContentResource().getValueMap().get(HIDE_IN_NAV,
					StringUtils.EMPTY);
			String hideAllSubNavSubMenu = subMenuPage.getContentResource().getValueMap()
					.get(HIDE_ALL_SUB_NAV, StringUtils.EMPTY);
			if (!TRUE.equalsIgnoreCase(hideInNavSubMenu)
					&& !TRUE.equalsIgnoreCase(hideAllSubNavSubMenu)) {
				SubMenuItem subMenuItem = new SubMenuItem();
				subMenuItem.setTitle(subMenuPage.getTitle());
				subMenuItem.setLink(subMenuPage.getPath());
				subMenuList.add(subMenuItem);
			}
		}
		return subMenuList;
	}

	/**
	 * @return mega menu list.
	 */
	public List<MegaMenuItem> getMegaMenu() {
		return megamenu;
	}
}

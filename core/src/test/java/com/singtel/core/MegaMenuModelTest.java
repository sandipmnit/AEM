package com.singtel.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.singtel.core.models.MegaMenuModel;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class MegaMenuModelTest {
	
	public final AemContext context = new AemContext(ResourceResolverType.JCR_MOCK);
	
	@BeforeEach
	public void setUp() throws Exception {
	  context.load().json("/singtel.json", "/content/singtel/us/en/home");
	}
	
	 @Test
	 void testMegaMenu() {
		 Resource resource = context.resourceResolver().getResource("/content/singtel/us/en/home/jcr:content/root/megamenu");
		 MegaMenuModel model = resource.adaptTo(MegaMenuModel.class);
		 model.getMegaMenu();
		 assertEquals("Car", model.getMegaMenu().get(0).getTitle());
		 assertEquals("/content/singtel/us/en/home/car.html", model.getMegaMenu().get(0).getLink());
		 assertEquals("Kia Motors", model.getMegaMenu().get(0).getSubMenuList().get(0).getTitle());
		 assertEquals("/content/singtel/us/en/home/car/kia-motors.html", model.getMegaMenu().get(0).getSubMenuList().get(0).getLink());
	 }

}

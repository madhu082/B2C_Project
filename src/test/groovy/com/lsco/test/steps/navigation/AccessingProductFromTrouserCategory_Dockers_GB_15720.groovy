package com.lsco.test.steps.navigation

import com.lsco.test.GBDockersSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import junit.framework.Test
import com.lsco.test.page.navigation.ClothingCategoryPage
import com.lsco.test.page.LevisHomePage
import geb.spock.GebReportingSpec
import geb.spock.GebSpec
import spock.lang.Unroll
import geb.Browser

import org.openqa.selenium.WebDriver

import org.openqa.selenium.firefox.FirefoxDriver

class AccessingProductFromTrouserCategory_Dockers_GB_15720 extends GebSpec{
	@GBDockersSmoke
	
	def "TrousersCategory"()
	{
		when: "Clicking on Link to Clothing Category Trousers Page"
		to LevisHomePage
		at LevisHomePage
		toClothingTrousersCategoryPage()

		then: "Navigate to Clothing Category Trousers Page and click on any PDP link of Category page"
		at ClothingCategoryPage
		toAllCollectionsPage()
		PDPofAnyProduct()

	}
}

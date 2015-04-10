package com.lsco.test.steps.navigation

import com.lsco.test.GBLevisSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import junit.framework.Test
import com.lsco.test.page.navigation.WomenCategoryPage
import com.lsco.test.page.LevisHomePage
import geb.spock.GebReportingSpec
import geb.spock.GebSpec
import spock.lang.Unroll
import geb.Browser

import org.openqa.selenium.WebDriver

import org.openqa.selenium.firefox.FirefoxDriver
// Levi-GB [SPRING-16000,15686]
class WomenCategoryPageSpec extends GebSpec{
	@GBLevisSmoke
	def "Verify Navigation to Women'sCategory Page [SPRING-16000,15686]"()
	{
		when: "Clicking on Link to Women's Category  Jeans Page"
		to LevisHomePage
		at LevisHomePage
		toWomenCategoryPage()

		then: "Navigate to Women's Category Page and click on All Collections Link"
		to WomenCategoryPage
		at WomenCategoryPage
		toAllCollectionsPage()
		selectWaistFilter()
		validateProductAfterWaistFilters()

	}

}

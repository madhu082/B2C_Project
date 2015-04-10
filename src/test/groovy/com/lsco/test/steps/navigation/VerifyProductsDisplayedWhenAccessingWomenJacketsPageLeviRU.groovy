package com.lsco.test.steps.navigation
import com.lsco.test.RULevisSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import gherkin.formatter.model.Feature
import junit.framework.Test
import geb.spock.GebReportingSpec;
import geb.spock.GebSpec;
import geb.Browser
import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.navigation.WomenJacketsNVestPage

class VerifyProductsDisplayedWhenAccessingWomenJacketsPageLeviRU extends GebSpec {
	@RULevisSmoke
	def "verifyProductsDisplayedWhenAccessingWomenJacketsPageLeviRU 15711"()
	{
		when: "navigating to women Jackets Page"
			to LevisHomePage
			at LevisHomePage
			dismissPopup()
			navigatingToWomenJackets()
	
			then: "Verify that products are displayed when accessing the Woman Jackets & Vests Category page."
			to WomenJacketsNVestPage
			at WomenJacketsNVestPage
			verifyJacketProductsDisplayed()
	
	
}
}

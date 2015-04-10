package com.lsco.test.steps.navigation
import com.lsco.test.RULevisSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import gherkin.formatter.model.Feature
import junit.framework.Test
import geb.spock.GebReportingSpec;
import geb.spock.GebSpec;
import geb.Browser
import com.lsco.test.page.register.AccountRegistrationPage
import com.lsco.test.page.login.LevisLoginPage
import com.lsco.test.page.login.LevisMyAccountPage
import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.FirstProductPage

class VerifyHoveringOverSwatchesLeviRU extends GebSpec {
	@RULevisSmoke
	def "verifyHoveringOverSwatchesLeviRU 15736"()
	{
		when: "going to Levi RU Home Page and clicking on My Account"
		to LevisHomePage
		at LevisHomePage
		dismissPopup()
		//toMyAccountPage()
		searchProductWithMultipleSwatchesHomePage()
		clickOnTheSearchedItemHomePage()
		
		then: "User goes to First Product Page and checking for the colour or swatch updation"
		at FirstProductPage
		verifyHoveringOverSwatchesLatest()
		
	}

}

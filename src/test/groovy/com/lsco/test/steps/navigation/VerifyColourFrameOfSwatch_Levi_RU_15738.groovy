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

class VerifyColourFrameOfSwatch_Levi_RU_15738 extends GebSpec {
	@RULevisSmoke
	def "VerifyColourofselectedswatch"()
	{
		when: "going to Levi GB Home Page "
		to LevisHomePage
		at LevisHomePage
		SearchProductWithMultipleSwatches()
		clickOnTheSearchedItem()
				
		then: "user is at Levi GB Login Page and logging in"
		at FirstProductPage
		SelectRandomSwatch()
		VerifySwatchSelected()
		
	}
}

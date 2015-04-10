package com.lsco.test.steps.navigation
import com.lsco.test.DELevisSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import gherkin.formatter.model.Feature
import junit.framework.Test
import geb.spock.GebReportingSpec;
import geb.spock.GebSpec;
import geb.Browser
import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.navigation.WomenJacketsNVestPage

class VerifyUserRedirectionToWomenJacketsVestsPageLeviDE extends GebSpec{
	@DELevisSmoke
	def "verifyUserRedirectionToWomenJacketsVestsPageLeviDE 15699"()
	{
		when: "navigating to women Jackets Page"
		to LevisHomePage
		at LevisHomePage
		dismissPopup()
		navigatingToWomenJackets()

	   then: "verifying the user is redirected to womenJacketsVestPage"
		to WomenJacketsNVestPage
		at WomenJacketsNVestPage
}
}

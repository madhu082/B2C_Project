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

class VerifyUserRedirectionToPDPofWomanJacketLeviDE extends GebSpec {
	@DELevisSmoke
	def "VerifyUserRedirectionToPDPofWomanJacketLeviDE 15706"()
	{
		when: "navigating to women Jackets Page Levi DE"
		to LevisHomePage
		at LevisHomePage
		dismissPopup()
		navigatingToWomenJackets()

		then: "Applying filters and verifying products displayed"
		to WomenJacketsNVestPage
		at WomenJacketsNVestPage
		applyFiltersinWomanJacketsPage()
		redirectionToPDP()
}

}

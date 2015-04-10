package com.lsco.test.steps.search
import com.lsco.test.RULevisSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import gherkin.formatter.model.Feature
import junit.framework.Test
import geb.spock.GebReportingSpec;
import geb.spock.GebSpec;
import geb.Browser
import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.search.FailSearchResultPage

class VerifyFailedSearchPageDoesNotDisplayAnyProductsLeviRU extends GebSpec {
	@RULevisSmoke
	def "verifyFailedSearchPageDoesNotDisplayAnyProductsLeviRU 15994"()
	{
		when: "going to Levi RU home page and the user has placed an invalid search."
		to LevisHomePage
		at LevisHomePage
		dismissPopup()
		searchInvalidProduct()
		
		
		then: "user goes to the Failed Search Result Page and Checking items are not displayed."
		at FailSearchResultPage
		verifyItemsNotDisplayedForInvalidSrch()
		

		}

}

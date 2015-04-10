package com.lsco.test.steps.search
import com.lsco.test.DELevisSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import gherkin.formatter.model.Feature
import junit.framework.Test
import geb.spock.GebReportingSpec;
import geb.spock.GebSpec;
import geb.Browser
import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.search.SearchResultPage

class VerifyUserRedirectionToSearchResultsPageLeviDE extends GebSpec {
	@DELevisSmoke
	def "verifyUserRedirectionToSearchResultsPageLeviDE 15746"()
	{
		when: "going to Levis DE Home Page and searching for product"
		to LevisHomePage
		at LevisHomePage
		dismissPopup()
		searchProduct()
		
		
		then: "user goes to the Search Result Page and verifying the product"
		at SearchResultPage
		verifySearchItem()
		
		}

}

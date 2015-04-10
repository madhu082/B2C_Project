package com.lsco.test.steps.search
import com.lsco.test.DEDockersSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import gherkin.formatter.model.Feature
import junit.framework.Test
import geb.spock.GebReportingSpec;
import geb.spock.GebSpec;
import geb.Browser
import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.search.SearchResultPage

class VerifySearchResultPageDisplayProductsDockersDE extends GebSpec {
	@DEDockersSmoke
	def "verifySearchResultPageDisplayProductsDockersDE 15768"()
	{
		when: "going to Dockers DE Home Page and searching for product"
		to LevisHomePage
		at LevisHomePage
		dismissPopup()
		searchProduct()
		
		
		then: "user goes to Search result Page and verifying that Products are displayed"
		at SearchResultPage
		verifySearchItem()
		
		}

}

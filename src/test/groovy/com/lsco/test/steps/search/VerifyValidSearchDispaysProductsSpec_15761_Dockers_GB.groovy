package com.lsco.test.steps.search

import com.lsco.test.GBDockersSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import gherkin.formatter.model.Feature
import junit.framework.Test
import geb.spock.GebReportingSpec;
import geb.spock.GebSpec;
import geb.Browser
import com.lsco.test.page.search.SearchResultPage
import com.lsco.test.page.LevisHomePage

class VerifyValidSearchDispaysProductsSpec_15761_Dockers_GB extends GebSpec {

	@GBDockersSmoke
	def "verifyValidSearchPage"()
	{
		when: "going to Dockers GB Home Page and searching for Invalid product"
		to LevisHomePage
		at LevisHomePage
        searchProduct()
				
		then: "user goes to the search result Page and verifies the search item returns results"
		at SearchResultPage
		VerifySearchedProduct()
		VerifySearchedGrid()
	}




}

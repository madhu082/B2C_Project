package com.lsco.test.steps.search

import com.lsco.test.GBLevisSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
//import gherkin.formatter.model.Feature
import junit.framework.Test
import geb.spock.GebReportingSpec;
import geb.spock.GebSpec;
import geb.Browser
import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.search.FailSearchResultPage

class VerifyUserRedirectionToFailedSearchPageLeviGB extends GebSpec{
	@GBLevisSmoke	
	def "verifyUserRedirectionToFailedSearchPageLeviGB 15739"()
	{
		when: "going to Levis GB Home Page and searching for Invalid product"
		to LevisHomePage
		at LevisHomePage
		dismissPopup()
		searchInvalidProduct()
		
		
		then: "user goes to the failed search Page and verifying the Error Message"
		at FailSearchResultPage
		

		}




}

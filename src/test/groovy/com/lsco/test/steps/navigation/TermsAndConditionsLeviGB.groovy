package com.lsco.test.steps.navigation
import com.lsco.test.GBLevisSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import gherkin.formatter.model.Feature
import junit.framework.Test
import geb.spock.GebReportingSpec;
import geb.spock.GebSpec;
import geb.Browser
import com.lsco.test.page.LevisHomePage

class TermsAndConditionsLeviGB extends GebSpec {
	@GBLevisSmoke
	def "termsAndConditionsLeviGB 17545_1"(){
		
		when: "clicking on shipping Link from Home Page"
		to LevisHomePage
		at LevisHomePage
		dismissPopup()
		clickShippingLink()
		
		then: "verify the contents on Service and Costs Page"
		verifyContentsOnSHIPPINGsERVICEandCOSTS()
		
		}

}

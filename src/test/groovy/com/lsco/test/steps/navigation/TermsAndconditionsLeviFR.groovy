package com.lsco.test.steps.navigation
import com.lsco.test.FRLevisSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import gherkin.formatter.model.Feature
import junit.framework.Test
import geb.spock.GebReportingSpec;
import geb.spock.GebSpec;
import geb.Browser
import com.lsco.test.page.LevisHomePage

class TermsAndconditionsLeviFR extends GebSpec {
	@FRLevisSmoke
	def "termsAndconditionsLeviFR 17545_2"(){
		
		when: "clicking on shipping Link from Home Page"
		to LevisHomePage
		at LevisHomePage
		dismissPopup()
		clickShippingLink()
		
		then: "verify the contents on Service and Costs Page"
		verifyContentsOnSHIPPINGsERVICEandCOSTS()
		
		}

}

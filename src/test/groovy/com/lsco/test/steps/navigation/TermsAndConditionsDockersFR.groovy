package com.lsco.test.steps.navigation
import com.lsco.test.FRDockersSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import gherkin.formatter.model.Feature
import junit.framework.Test
import geb.spock.GebReportingSpec;
import geb.spock.GebSpec;
import geb.Browser
import com.lsco.test.page.LevisHomePage

class TermsAndConditionsDockersFR extends GebSpec {
	@FRDockersSmoke
	def "termsAndConditionsDockersFR 17548_2"(){
		
		when: "clicking on shipping Link from Home Page"
		to LevisHomePage
		at LevisHomePage
		dismissPopup()
		clickShippingLink()
		
		then: "verify the contents on Service and Costs Page"
		verifyContentsOnSHIPPINGsERVICEandCOSTS()
		

}
}
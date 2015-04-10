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

class VerifyUserLandingAtFitGuideLandingPageLEVIGB extends GebSpec {
	@GBLevisSmoke
	def "verifyUserLandingAtFitGuideLandingPageLEVIGB 16212"(){
		 when: "user goes to Levis Home Page"
		 to LevisHomePage
		 at LevisHomePage
		 dismissPopup()
		 
		 then: "user is at Levis Home Page and navigating to Mens and Womens FitGuide Pages"
		 navigatingToFitGuideMensAndWomen()
		}
	}

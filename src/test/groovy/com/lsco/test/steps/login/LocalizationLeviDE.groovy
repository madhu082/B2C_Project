package com.lsco.test.steps.login
import com.lsco.test.DELevisSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import gherkin.formatter.model.Feature
import junit.framework.Test
import geb.spock.GebReportingSpec;
import geb.spock.GebSpec;
import geb.Browser
import com.lsco.test.page.login.LevisLoginPage
import com.lsco.test.page.LevisHomePage

class LocalizationLeviDE extends GebSpec {
	@DELevisSmoke
	def "localizationLeviDE 15982"(){
		
		when: "going to Levis DE home page and clicking on My Account"
		to LevisHomePage
		at LevisHomePage
		toMyAccountPage()
		
		
		then: "user is at Levis DE Login Page"
		at LevisLoginPage
		leviLogin()
		submitLoginFormButtonWithPopupCheck()
		// verify accesible pages under Profile,Address,Email Options,Password and Orders section are in German.
		localizationLanguageVerification()
		}
}

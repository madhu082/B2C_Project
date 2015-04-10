package com.lsco.test.steps.login
import com.lsco.test.GBDockersSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import gherkin.formatter.model.Feature
import junit.framework.Test
import geb.spock.GebReportingSpec;
import geb.spock.GebSpec;
import geb.Browser
import com.lsco.test.page.login.LevisLoginPage
import com.lsco.test.page.LevisHomePage

class LocalizationDockersGB extends GebSpec {
	@GBDockersSmoke
	def "localizationDockersGB 15983"(){
		
		when: "going to DockersGB home page and clicking on My Account"
		to LevisHomePage
		at LevisHomePage
		toMyAccountPage()
		
		
		then: "user is at DockersGB Login Page"
		at LevisLoginPage
		leviLogin()
		submitLoginFormButtonWithPopupCheck()
		localizationLanguageVerification()
		
	}

}

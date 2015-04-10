package com.lsco.test.steps.login
import com.lsco.test.DEDockersSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import gherkin.formatter.model.Feature
import junit.framework.Test
import geb.spock.GebReportingSpec;
import geb.spock.GebSpec;
import geb.Browser
import com.lsco.test.page.login.LevisLoginPage
import com.lsco.test.page.LevisHomePage

class LocalizationDockersDE extends GebSpec {
	
	@DEDockersSmoke
	def "localizationDockersDE 15984"(){
		
		when: "going to DockersDE home page and clicking on My Account"
		to LevisHomePage
		at LevisHomePage
		toMyAccountPage()
		
		
		then: "user is at DockersDE Login Page"
		at LevisLoginPage
		leviLogin()
		submitLoginFormButtonWithPopupCheck()
		localizationLanguageVerification()
		
	}


}

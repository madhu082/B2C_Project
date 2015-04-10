package com.lsco.test.steps.login
import com.lsco.test.DEDockersSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import gherkin.formatter.model.Feature
import junit.framework.Test
import geb.spock.GebReportingSpec;
import geb.spock.GebSpec;
import geb.Browser
import com.lsco.test.page.register.AccountRegistrationPage
import com.lsco.test.page.login.LevisLoginPage
import com.lsco.test.page.login.LevisMyAccountPage
import com.lsco.test.page.LevisHomePage

class EmailAddrMrktngOptInNEGTVdockersDE extends GebSpec {
	@DEDockersSmoke
	def "emailAddrMrktngOptInNEGTVdockersDE 15955"(){
		
		when: "going to Dockers DE Home page and clicking on My Account"
		to LevisHomePage
		at LevisHomePage
		toMyAccountPage()
		
		then: "user is at Dockers DE Login Page and doing OPT IN negative Flow"
		at LevisLoginPage
		leviLogin()
		submitLoginFormButtonWithPopupCheck()
		selectEmailOptions()
		dockersOptInNegativeFlow()
		
		
	}

}

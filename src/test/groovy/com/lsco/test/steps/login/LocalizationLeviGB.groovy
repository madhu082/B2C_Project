package com.lsco.test.steps.login
import com.lsco.test.GBLevisSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import gherkin.formatter.model.Feature
import junit.framework.Test
import geb.spock.GebReportingSpec;
import geb.spock.GebSpec;
import geb.Browser
import com.lsco.test.page.login.LevisLoginPage
import com.lsco.test.page.LevisHomePage

class LocalizationLeviGB extends GebSpec {
   @GBLevisSmoke
	def "localizationLeviGB 15981"(){
		
		when: "going to Levis GB home page and clicking on My Account"
		to LevisHomePage
		at LevisHomePage
		toMyAccountPage()
		
		
		then: "user is at Levis GB Login Page"
		at LevisLoginPage
		leviLogin()
		submitLoginFormButtonWithPopupCheck()
		// verify accesible pages under Profile,Address,Email Options,Password and Orders section are in English
		localizationLanguageVerification()
		}
	}

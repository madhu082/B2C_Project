package com.lsco.test.steps.login

import com.lsco.test.GBLevisSmoke
import com.lsco.test.GBDockersSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import gherkin.formatter.model.Feature
import junit.framework.Test
import com.lsco.test.page.LevisHomePageWithCookies
import com.lsco.test.page.login.LevisMyAccountPage
import com.lsco.test.page.LevisHomePage
import geb.spock.GebReportingSpec;
import geb.spock.GebSpec;
import geb.Browser

import org.openqa.selenium.WebDriver

import org.openqa.selenium.firefox.FirefoxDriver

//SPRING-16651 -[GB] - Levi - GB - newsletter popup
//SPRING-16652 -[GB] - Dockers - GB - newsletter popup

class NewsLetterPopupSpecs_16651_16652 extends GebSpec{
	@GBLevisSmoke
	@GBDockersSmoke
	def "Verify Newsletter popup_SPRING-16651_16651"()
	{
		when: "Going to Levis Home Page"
		to LevisHomePageWithCookies
		at LevisHomePageWithCookies
		Thread.sleep(4000)
		//verifyCookiePopup(true)
		
		
		then: "Verify Newletter popup opens and its functionality"
		verifyCookiePopup(true)
		verifyFuncLinksofCookiePopup()
		userSubscriptionChecking()
		Thread.sleep(2000)
		
		when: "Click on other pages"
		toPage("2")
		Thread.sleep(3000)
		
		then: "No Newletter popup opens"
		verifyCookiePopup(false)
		
		}
	}

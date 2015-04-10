package com.lsco.test.steps.login

import com.lsco.test.TRDockersSmoke
import geb.*
import geb.spock.GebSpec;

import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.LevisHomePageWithCookies
import com.lsco.test.page.login.LevisMyAccountPage
import com.lsco.test.page.login.LevisLoginPage
import com.lsco.test.page.login.FacebookLoginPage


class NewsletterPop_UP_16653_Dockers_TR extends GebSpec{
	@TRDockersSmoke
	
	def NewsletterPopUp(){
	
	when: "Going to Levis Home Page"
		to LevisHomePageWithCookies
		at LevisHomePageWithCookies
		verifyCookiePopup(true)
		
		
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
	
	

package com.lsco.test.steps.login

import com.lsco.test.GBLevisSmoke
import com.lsco.test.DELevisSmoke
import com.lsco.test.GBDockersSmoke
import com.lsco.test.DEDockersSmoke
import com.lsco.test.RULevisSmoke
import geb.*
import geb.spock.GebSpec;

import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.login.LevisMyAccountPage
import com.lsco.test.page.login.LevisLoginPage
import com.lsco.test.page.login.FacebookLoginPage

//SPRING-17726 [GB,DE]ECommerce - Verify Call US information at the footer of the page

class CallUSSpec_17726_17729 extends GebSpec{
	@GBLevisSmoke
	@DELevisSmoke
	@GBDockersSmoke
	@DEDockersSmoke
		def CallUScheckAtECommerceSite_17726(){
			
			when: "User is at Home page of ECommerce site"
			to LevisHomePage
			at LevisHomePage
			callUsFooterExist(true)
			
			then: "Checking CallUs option exist in footer"
			
			callUsFooterCheck()
			Thread.sleep(1000)
			
		}
	@RULevisSmoke
		def CallUScheckAtMarketingSite_17729(){
			
			when: "User is at Home page of ECommerce site"
			to LevisHomePage
			at LevisHomePage			
			
			then: "Checking CallUs option should not exist in footer"			
			callUsFooterExist(false)
			Thread.sleep(1000)
			
		}
}
		 
	

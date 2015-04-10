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
import com.lsco.test.page.login.LevisMyAccountPage
import com.lsco.test.page.LevisHomePage

class CustomerProfileInformationNEGATIVELeviGB extends GebSpec {
	@GBLevisSmoke
	def "customerProfileInformationNEGATIVELeviGB 15864"(){
		
		when: "going to Levi GB Home page"
		to LevisHomePage
		at LevisHomePage
		toMyAccountPage()
		
		then: "user is at Levi GB Login Page and logging in"
		at LevisLoginPage
		loginForProfileNegativeFlows()
		//submitLoginFormButton()
		submitLoginFormButtonWithPopupCheck()
		
		
		when: "user goes to My Account Page"
		to LevisMyAccountPage
		at LevisMyAccountPage
		
		
		then: "checking the negative Profile Information Flow "
		
		profileCustomerInformationNEGATIVE()
		
		
	}



}

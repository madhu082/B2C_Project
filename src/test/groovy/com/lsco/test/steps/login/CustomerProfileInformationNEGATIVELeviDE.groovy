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
import com.lsco.test.page.login.LevisMyAccountPage
import com.lsco.test.page.LevisHomePage

class CustomerProfileInformationNEGATIVELeviDE extends GebSpec {
	@DELevisSmoke
	def "customerProfileInformationNEGATIVELeviDE 15867"(){
		
		when: "going to Levi DE Home page"
		to LevisHomePage
		at LevisHomePage
		toMyAccountPage()
		
		then: "user is at Levi DE Login Page and logging in"
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

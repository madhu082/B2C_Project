package com.lsco.test.steps.login
import com.lsco.test.DELevisSmoke
import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.login.LevisLoginPage
import com.lsco.test.page.register.AccountRegistrationPage
import geb.spock.GebReportingSpec
import geb.spock.GebSpec

class LeviDEProfileCustomerInformation extends GebSpec {
	@DELevisSmoke
	def "leviDEProfileCustomerInformation 15866"(){
		
		when: "going to Levis DE Home page and clicking on My Account"
		to LevisHomePage
		at LevisHomePage
		toMyAccountPage()
		
		
		then: "user is at Levis DE Login Page"
		at LevisLoginPage
		
		when: "user is at Account Registration Page"
		at AccountRegistrationPage
		fillingRegInfoRandomEmail()
		clickAgeCheckBoxOption()
		submitRegistrationForm()
		clickNotTodayLink()
		
		
		then: "verifying the update Profile functionality"
		updateProfileInfo()
		
	}

}

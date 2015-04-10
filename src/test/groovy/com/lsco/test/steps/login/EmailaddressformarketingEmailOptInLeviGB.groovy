package com.lsco.test.steps.login
import com.lsco.test.GBLevisSmoke
import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.login.LevisLoginPage
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

class EmailaddressformarketingEmailOptInLeviGB extends GebReportingSpec{
	@GBLevisSmoke
	def "emailaddressformarketingEmailOptInLeviGB 15938"()
	{
		when: "going to Levis GB Home page and clicking on My Account"
		to LevisHomePage
		at LevisHomePage
		toMyAccountPage()
		
		
		then: "user is at Levis GB Login Page and doing the Opt In Flow"
		at LevisLoginPage
		leviLogin()
		submitLoginFormButtonWithPopupCheck()
		selectEmailOptions()
		marketingEmailOptInLevisGB()
		

		}
	

}

package com.lsco.test.steps.login

import com.lsco.test.GBLevisSmoke
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
import com.lsco.test.page.EmailPage
/*
 //Jira SPRING-15940
 */
class NegativeEmailaddressformarketingEmailOptIn_15940 extends GebSpec{
	@GBLevisSmoke
	def NegativeEmailaddressformarketingEmailOptIn()
	{
		when: "We are Home page & click on MyAccount"
		to LevisHomePage
		at LevisHomePage
		toMyAccountPage()
		
		
		then: "we are on the MyAccount Page"
		at LevisLoginPage
		FillLoginCredentials()
		submitLoginForm()
		selectEmailOptions()	
		
		when: "We are into Email Tab and giving invalid email"
		at EmailPage
		okwithinputEmailId("emailInvalid")
				
		then:"We are into MyProfile-tab"
		verifyerrmsg("emailInvalidtypeErrMSG")
		
		when: "Clicking save without updating information"
		at EmailPage
		okwithinputEmailId("username_pm")
		updateinfoPageCheck()
		clicksave()
		
		then:"We are into MyProfile-tab"
		blankinfosaveerrmsg()
		
		}
	

}

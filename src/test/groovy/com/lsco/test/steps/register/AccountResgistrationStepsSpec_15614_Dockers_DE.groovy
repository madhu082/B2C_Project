package com.lsco.test.steps.register
import com.lsco.test.DEDockersSmoke
import com.lsco.test.GBDockersSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import gherkin.formatter.model.Feature
import junit.framework.Test
import com.lsco.test.page.login.LevisMyAccountPage
import com.lsco.test.page.register.AccountRegistrationPage
import com.lsco.test.page.LevisHomePage
import geb.spock.GebReportingSpec
import geb.spock.GebSpec
import geb.Browser

import com.lsco.test.PropertyProvider

import org.openqa.selenium.WebDriver

import org.openqa.selenium.firefox.FirefoxDriver


class AccountResgistrationStepsSpec_15614_Dockers_DE extends GebSpec{

	@DEDockersSmoke
	def "Registernowdetails"()
	{
		
		when: "We are Home page & click on MyAccount"
		to LevisHomePage
		at LevisHomePage
		toMyAccountPage()
		
		then: "entering Details"
		at AccountRegistrationPage
		updatedregisterANewRandomUser()
		submitRegistrationForm()
		
		when: "we are on the MyAccount Page"
		to LevisMyAccountPage
		at LevisMyAccountPage
		
		then: "Verify successful registration"
		verifyInHeaderSuccessfulRegistration()
		
	}
}

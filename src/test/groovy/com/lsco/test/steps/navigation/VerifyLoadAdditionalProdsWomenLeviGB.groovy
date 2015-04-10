package com.lsco.test.steps.navigation
import com.lsco.test.GBLevisSmoke
import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.login.LevisLoginPage
import com.lsco.test.page.login.LevisMyAccountPage
import geb.spock.GebReportingSpec
import geb.spock.GebSpec

class VerifyLoadAdditionalProdsWomenLeviGB extends GebSpec {
	@GBLevisSmoke
	def "verifyWomenLoadAdditionalProdsLeviGB 18040_2"()
	{
		when: "access to Levis GB Site"
		to LevisHomePage
		at LevisHomePage
		toMyAccountPage()
		
		
		then: "user is at Levis GB Login Page and logging in"
		at LevisLoginPage
		leviLogin()
		//submitLoginFormButton()
		submitLoginFormButtonWithPopupCheck()
		
		when: "user goes to shop all section and selecting the Sort them by Drop Down"
		to LevisMyAccountPage
		at LevisMyAccountPage
		//mouse over Women and Select Shop all women option
		womenShopAllLinkLevi()
		//selecting the option Price: low to high from Sort them By Dropdown and scrolling down on the screen to load more products
		verifyloadAdditionalProducts()
		
		then: "scrolling and filtering is done"
		
		println "scrolling and filtering is completed successfully"
		}
}

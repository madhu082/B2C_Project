package com.lsco.test.steps.navigation
import com.lsco.test.GBLevisSmoke
import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.login.LevisLoginPage
import com.lsco.test.page.login.LevisMyAccountPage
import geb.spock.GebReportingSpec
import geb.spock.GebSpec

class VerifyLoadAdditionalProductsLeviGB extends GebSpec{
	@GBLevisSmoke
	def "verifyLoadAdditionalProductsLeviGB 18040_1"()
	{
		when: "access to Levis Gb Site"
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
		//mouse over Men and Select Shop all men option
		menShopAllLinkLevi()
		//selecting the option Price: low to high from Sort them By Dropdown and scrolling down on the screen to load more products
		verifyloadAdditionalProducts()
		
		then: "scrolling and filtering is done"
		
		println "scrolling and filtering is completed successfully"
		
	}

}

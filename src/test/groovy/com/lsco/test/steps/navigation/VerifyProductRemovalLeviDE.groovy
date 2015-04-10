package com.lsco.test.steps.navigation
import com.lsco.test.DELevisSmoke
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
import com.lsco.test.page.FirstProductPage
import com.lsco.test.page.CartPage

class VerifyProductRemovalLeviDE extends GebSpec{
	@DELevisSmoke
	def "verifyProductRemovalLeviDE 15785"(){
		
		when: "going to Levis DE and selecting the product"
		to LevisHomePage
		at LevisHomePage
		dismissPopup()
		searchProductForGuestUsr()
		clickOnTheSearchedItemForGuestUsr()
		
		then: "User goes to first Product Page and adding the product to Bag"
		at FirstProductPage
		addProductToBagLatest("1")
		viewBagFunctionality()
		
		when: "User goes to Cart Page"
		to CartPage
		at CartPage
		
		then: "doing the product removal from shopping bag"
		productRemovalFlow()
		
		
		
		
	}
	
	

}

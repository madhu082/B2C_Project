package com.lsco.test.steps.navigation
import com.lsco.test.DEDockersSmoke
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

class VerifyProductRemovalDockersDE extends GebSpec {
	
	@DEDockersSmoke
	def "verifyProductRemovalDockersDE 15799"(){
		
		when: "going to Dockers DE and selecting the product"
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

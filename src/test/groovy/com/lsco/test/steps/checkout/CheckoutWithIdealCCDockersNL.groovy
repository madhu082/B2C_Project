package com.lsco.test.steps.checkout
import com.lsco.test.NLDockersSmoke
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
import com.lsco.test.page.OrderConfirmationPage

class CheckoutWithIdealCCDockersNL extends GebSpec {
    @NLDockersSmoke
	def "checkoutWithIDealCCLeviNL 16667_2"()
	{
		when: "going to Dockers NL Home Page and clicking on My Account"
		to LevisHomePage
		at LevisHomePage
		toMyAccountPage()
		
		
		then: "user is at Dockers NL Login Page and logging in"
		at LevisLoginPage
		leviLogin()
		//submitLoginFormButton()
		submitLoginFormButtonWithPopupCheck()
		
		when: "user goes to My account page and selecting the product"
		to LevisMyAccountPage
		at LevisMyAccountPage
		searchProduct()
		clickOnTheSearchedItem()
		
		then: "User goes to First Product Page and adding product to Bag"
		at FirstProductPage
		addProductToBagLatest("1")
		viewBagFunctionality()
		
		when: "user goes to Cart Page and proceed to check out using IDEAL"
		to CartPage
		at CartPage
		toCheckoutPageLatest()
		fillingShippingAddrDetails()
		submitData()
		chooseIDeal()
	
		
		then: "checking out the order with IDEAL CC details"
		checkoutIdealCCDetails()
		at OrderConfirmationPage
	
		}
}

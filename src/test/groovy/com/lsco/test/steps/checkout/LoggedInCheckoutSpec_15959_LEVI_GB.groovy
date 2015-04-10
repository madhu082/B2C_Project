package com.lsco.test.steps.checkout

import com.lsco.test.GBLevisSmoke
import geb.*
import geb.spock.GebSpec;
import org.apache.commons.lang.RandomStringUtils
import org.apache.commons.lang.math.RandomUtils
import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.login.LevisMyAccountPage
import com.lsco.test.page.login.LevisLoginPage
import com.lsco.test.page.register.AccountRegistrationPage
import com.lsco.test.page.CartPage
import com.lsco.test.page.FirstProductPage
import com.lsco.test.page.OrderConfirmationPage


class LoggedInCheckoutSpec_15959_LEVI_GB extends GebSpec{
	@GBLevisSmoke
	
	def CheckoutAsALoggedinUser(){
	
	when:"We are Home page & click on MyAccount"
		to LevisHomePage
		at LevisHomePage
		toMyAccountPage()

	then:"we are on the MyAccount Page"
	    at LevisLoginPage
		FillLoginFields()
		submitLoginForm()
				
	when: "We are in the MyAccount Page & Does perform a search"
	    at LevisMyAccountPage
		searchProduct()
		clickOnTheSearchedItem()
		
    then :"We go to First Product Page and adding product to Bag"
		at FirstProductPage
		addProductToBagLatest("1")
		viewBagFunctionality()
	
    when: "We go to Cart Page and proceed to check out"
		to CartPage
		at CartPage
		toCheckoutPageLatest()
	   	fillingShippingAdrressGuest()
		phone()
		checkAgeCheckbox()
		submitData()
		chooseMasterCard()
		
	then: "Checking out the order with Credit Card Details"
	   fillCreditCardDataLatest()
	   at OrderConfirmationPage
	   VerifyOrder()
	   
	   }
	}
	


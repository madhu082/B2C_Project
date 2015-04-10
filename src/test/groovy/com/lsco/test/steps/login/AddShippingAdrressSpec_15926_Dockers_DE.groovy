package com.lsco.test.steps.login

import com.lsco.test.GBLevisSmoke
import com.lsco.test.DEDockersSmoke
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


class AddShippingAdrressSpec_15926_Dockers_DE extends GebSpec{
	
	@DEDockersSmoke
	def ShippingAdrress(){
	
	when: "We are Home page & click on MyAccount"
		to LevisHomePage
		at LevisHomePage
		toMyAccountPage()

	then:"we are on the MyAccount Page"
	    at LevisLoginPage
		FillLoginFields()
		submitLoginForm()
				
	when:"We are into MyProfile-tab"
	     to LevisMyAccountPage
	     at LevisMyAccountPage
	     openAdrressTab()
		
	then:"Updating Shipping adrress & logout"
	     UpdateShippingAdrressSection()
		 UpdateShippingData()
		 verifyUpdatedShipping()
		 logOut()
		 
	when: "Register user re-logs-into account & verifies the recently added billing address"	
	     at LevisLoginPage
	     FillLoginFields()
		 submitLoginForm()
	
	then:"User re-enters address tab & verifies the added billing address"	
	    to LevisMyAccountPage
	    at LevisMyAccountPage
	    openAdrressTab()
	    verifyUpdatedShipping()
		
	when:"User does perform a search "
	   searchProduct()
	   clickOnTheSearchedItem()
	   
	then: "User goes to First Product Page and adding product to Bag"   
	   at FirstProductPage
	   addProductToBagLatest("1")
	   viewBagFunctionality()
	   
	when: "user goes to Cart Page and proceed to check out"
	   to CartPage
	   at CartPage
	   toCheckoutPageLatest()
	  // fillingShippingAddrDetailsUpdated()
	   phone()
	   submitData()
	   chooseMasterCard()
   	   
	then: "Checking out the order with Credit Card Details"
	   fillCreditCardDataLatest()
	    }
	}
	


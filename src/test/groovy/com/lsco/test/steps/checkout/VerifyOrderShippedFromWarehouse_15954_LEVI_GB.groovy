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



class VerifyOrderShippedFromWareHouse_15954_LEVI_GB extends GebSpec{
	@GBLevisSmoke
	
	def OrderShippedFromWareHouse(){
	
	when:"User at HomePage & performs a search "
		to LevisHomePage
		at LevisHomePage
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
	


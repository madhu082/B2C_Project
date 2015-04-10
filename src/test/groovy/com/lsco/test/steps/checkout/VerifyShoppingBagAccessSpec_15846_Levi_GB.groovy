package com.lsco.test.steps.checkout

import com.lsco.test.GBLevisSmoke
import com.lsco.test.DEDockersSmoke
import com.lsco.test.GBDockersSmoke
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


class VerifyShoppingBagAccessSpec_15846_Levi_GB extends GebSpec{
	@GBLevisSmoke
	def CheckoutAsAGuest(){
	
	when:"User at HomePage & performs a search "
		to LevisHomePage
		at LevisHomePage
		searchProduct()
		clickOnTheSearchedItem()
		
	then: "User goes to First Product Page and adding product to Bag"
		at FirstProductPage
		addProductToBagLatest("1")
		viewBagFunctionality()

    when: "user goes to Cart Page"
		to CartPage
		at CartPage
		toCheckoutPageLatest()
		
	then: "User verifies wheather the added product reflects inside the cart"
	   // at CartPage
	    VerifyProductAdded()
	   
	   }
	}
	


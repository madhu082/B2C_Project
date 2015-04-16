package com.lsco.test.steps.checkout
import com.lsco.test.GBLevisSmoke
import com.lsco.test.page.CartPage
import com.lsco.test.page.FirstProductPage
import com.lsco.test.page.LevisHomePage
import geb.spock.GebReportingSpec
import com.lsco.test.PropertyProvider

class SignOutFromCheckOutSpec_17756_LEVI_GB extends GebReportingSpec{
	@GBLevisSmoke
	def CheckoutAsARegisteredUser(){
	
	when:"Registered User at HomePage & performs a search "
		to LevisHomePage
		at LevisHomePage
		searchProduct()
		//at SearchResultPage
		clickOnTheSearchedItem()
		
	then: "User goes to First Product Page and adding product to Bag"
		at FirstProductPage
		addProductToBagLatest("1")
		viewBagFunctionality()

    when: "user goes to Cart Page-> proceed to check out->Performs a signin"
		to CartPage
		at CartPage
		toCheckoutPageLatest()
		SignIn_Checkout()
		VerifyProductAdded()
						
	then: "Registered user performs Sign-out & verifies product is removed from thebag"
	    SignOut_Checkout()
		VerifyProductRemoved()
		//at CartPage
		SignIn_Checkout()
		VerifyProductAdded()
			   
	   }
	}
	


package com.lsco.test.steps.checkout

import com.lsco.test.GBDockersSmoke
import geb.*
import geb.spock.GebSpec;
import org.apache.commons.lang.math.RandomUtils
import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.login.LevisMyAccountPage
import com.lsco.test.page.login.LevisLoginPage
import com.lsco.test.page.register.AccountRegistrationPage
import com.lsco.test.page.CartPage
import com.lsco.test.page.FirstProductPage


class VerifyCheckOutIframeSpec_17735_Dockers_GB extends GebSpec{
	@GBDockersSmoke
	
	def VerifyCheckOutIframeWithIncorectValues(){
	
	when:"User at HomePage & performs a search "
		to LevisHomePage
		at LevisHomePage
		searchProduct()
		clickOnTheSearchedItem()
		
	then: "User goes to First Product Page and adding product to Bag"
		at FirstProductPage
		addProductToBagLatest("1")
		viewBagFunctionality()

    when: "user goes to Cart Page and proceed to check out with negetive billing data"
		to CartPage
		at CartPage
		toCheckoutPageLatest()
	   	fillingShippingAdrressGuest()
	    fillingBillingAddrDetails()
		phone()
		checkAgeCheckbox()
		submitData()
				
	then: "Verifies user not able to checkout, also the error messages"
	   VerifyBillingErrorMassage()
	      }
	}
	


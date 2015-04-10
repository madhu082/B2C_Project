package com.lsco.test.steps.login

import com.lsco.test.GBLevisSmoke
import com.lsco.test.DELevisSmoke
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


class EditBillingAdrressSpec_NEGATIVE_15888_Levi_DE extends GebSpec{
	@DELevisSmoke
	def NegativeShippingAdrress(){
	
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
		
	then:"Updating Billing adrress with negative values & verifying error massage"
	     EditExistingAddressWithNegativeShippingData()
		 VerifyErrorMassage()
	}
}


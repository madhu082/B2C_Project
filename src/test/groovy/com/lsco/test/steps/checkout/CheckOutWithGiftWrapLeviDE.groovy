package com.lsco.test.steps.checkout
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
import com.lsco.test.page.OrderConfirmationPage


class CheckOutWithGiftWrapLeviDE extends GebSpec{
    @DELevisSmoke
	def "checkOutWithGiftWrapLeviDE 15937"()
	{
		when: "going to Levi DE Home Page and clicking on My Account"
		to LevisHomePage
		at LevisHomePage
		toMyAccountPage()
		
		
		then: "user is at Levi DE Login Page and logging in"
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
		
		when: "user goes to Cart Page and proceed to check out"
		to CartPage
		at CartPage
		toCheckoutPageLatest()
		fillingShippingAddrDetails()
		checkGiftWrapCheckboxLatest()
		submitData()
		chooseAmex()
	
		
		then: "order history verification for giftWrap orders"
		fillCreditCardDataLatestAmex()
		at OrderConfirmationPage
		orderHistoryWithGiftWrap()
		}


}

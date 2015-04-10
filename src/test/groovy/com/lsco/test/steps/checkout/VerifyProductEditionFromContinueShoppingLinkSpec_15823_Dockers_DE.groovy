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


class VerifyProductEditionFromContinueShoppingLinkSpec_15823_Dockers_DE extends GebSpec{
	@DEDockersSmoke
	
	def "ContunueShopping" (){
	//given: "opened the dockers login url "
		when: "Entering #Item1 to be Searched"
		to LevisHomePage
		at LevisHomePage
		searchProduct()
		clickOnTheSearchedItem()

		then: "adding Item #Item1 to bag"
		at FirstProductPage
		addProductToBag("1")

		then: "Continue Shopping from shopping cart"
		to CartPage
		at CartPage
		
		def ProductAddedbefore= ProductAdded.size()
		def PriceBefore = costSummaryTotal.text()
		continueShopping()
		
		then: "Entering #Item2 to be Searched"
		at LevisHomePage
		searchAnotherProduct()
		clickOnTheSearchedItem()
		
		then: "adding Item #Item2 to bag"
		at FirstProductPage
		selectAnySize()
		addProductToBag("1")
		to CartPage
		
		then: "Verifies both shopping cartv & total cost gets updated accordingly"
		//to CartPage
		at CartPage
		assert ProductAddedbefore +1 == ProductAdded.size()
		def PriceAfter = costSummaryTotal.text()
		assert PriceAfter > PriceBefore
		}
	
}

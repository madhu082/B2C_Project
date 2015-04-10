package com.lsco.test.steps.checkout

import com.lsco.test.GBLevisSmoke
import com.lsco.test.DEDockersSmoke

import spock.lang.Specification
import geb.spock.GebReportingSpec
import gherkin.formatter.model.Feature
import junit.framework.Test
import geb.spock.GebReportingSpec;
import geb.spock.GebSpec;
import geb.Browser
import org.openqa.selenium.*
import org.openqa.selenium.WebDriver


import com.lsco.test.page.register.AccountRegistrationPage
import com.lsco.test.page.login.LevisLoginPage
import com.lsco.test.page.login.LevisMyAccountPage
import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.FirstProductPage
import com.lsco.test.page.CartPage
import com.lsco.test.page.OrderConfirmationPage
import com.lsco.test.page.navigation.WomenJacketsNVestPage
import com.lsco.test.page.ProductDetailPage
import com.lsco.test.page.navigation.DockersMenShorts
import com.lsco.test.page.search.SearchResultPage
import com.sun.java.util.jar.pack.Driver;
import com.lsco.test.PropertyProvider
 

class CheckoutWithProductLevelPromotion_Restriction_16006_16023_16027 extends GebSpec {
	
	@GBLevisSmoke
	@DEDockersSmoke
	//with Promotions
	def "CheckoutWithProductLevelPromotionWithRestriction_16006_16027"()	
	{
		when: "Going to Levis GB Home Page and clicking on My Account"
		to LevisHomePage
		at LevisHomePage
		toMyAccountPage()
		
		then : "We are on the MyAccount Page and logging in"
		at LevisLoginPage
		FillLoginCredentials()
		submitLoginForm()
		Thread.sleep(4000)
		
		when: "We are into MyProfile and going to cart to remove all items of bag and then go to product List Page"
		to LevisMyAccountPage
		at LevisMyAccountPage
		goTobagDetails()
		at CartPage
		removeAllItem()
		Thread.sleep(4000)
		driver.navigate().back()
		Thread.sleep(4000)
		at LevisMyAccountPage
		Thread.sleep(2000)
		String skuItemName = PropertyProvider.getInstance().getLocalizedPropertyValue("SkuItemName")
		searchProduct(skuItemName)
		Thread.sleep(10000)
		
		then: "Click PDP page on click products in Page"
		
		//to SearchResultPage
		at SearchResultPage
		verifySearchedItem(skuItemName)
		clickSearchedItem()

		when: "Add the product in bag from PDP page to get promo with restriction"
		//For this case promo is enabled for a particular product with 10% of on coupon restriction.
				
		at ProductDetailPage
		Thread.sleep(10000)
		selectAnySize()
		selectProductQuantity("1")
		clickAddToBag()
		Thread.sleep(4000)
		goTobagDetails()
	
		
		then:"View items in bag"
		to CartPage
		at CartPage
		
		when: "Apply promo with promo code"
		//Given promocode
		String promocode= PropertyProvider.getInstance().getLocalizedPropertyValue("Productpromocode")
		applyCoupon(promocode)
		
		then: "Coupon activated"
		//Checked promo-identifier unblocked
		String Couponidentifier= PropertyProvider.getInstance().getLocalizedPropertyValue("ProductCouponidentifier")
		checkCoupon(Couponidentifier)

		
		when: "user goes to Cart Page and proceed to check out"
		toCheckoutPageLatest()
		String savingsamount= savingsAmount()
		fillingShippingAddrDetails()
		submitData()
		//chooseMaestro()
		chooseMasterCard()
		
		then: "Checking out the order with Credit Card Details"
		fillCreditCardDataLatest()
		at OrderConfirmationPage
		chksavingsAmount(savingsamount)
		
		}
	
	
	@DEDockersSmoke
	//without Promotions
	def "CheckoutWithProductLevelPromotionWithoutRestriction_16023"()
	{
		when: "Going to Levis GB Home Page and clicking on My Account"
		to LevisHomePage
		at LevisHomePage
		toMyAccountPage()
		
		then : "We are on the MyAccount Page and logging in"
		at LevisLoginPage
		FillLoginCredentials()
		submitLoginForm()
		Thread.sleep(4000)
		
		when: "We are into MyProfile and going to cart to remove all items of bag and then go to product List Page"
		to LevisMyAccountPage
		at LevisMyAccountPage
		goTobagDetails()
		at CartPage
		removeAllItem()
		Thread.sleep(4000)
		driver.navigate().back()
		Thread.sleep(4000)
		at LevisMyAccountPage
		Thread.sleep(2000)
		String skuItemName = PropertyProvider.getInstance().getLocalizedPropertyValue("SkuItemNameNR")
		searchProduct(skuItemName)
		Thread.sleep(10000)
		
		then: "Click PDP page on click products in Page"
		
		//to SearchResultPage
		at SearchResultPage
		verifySearchedItem(skuItemName)
		clickSearchedItem()

		when: "Add the product in bag from PDP page to get promo with restriction"
		//For this case promo is enabled for a particular product with 10% without any restriction.
				
		at ProductDetailPage
		Thread.sleep(10000)
		selectAnySize()
		selectProductQuantity("1")
		clickAddToBag()
		Thread.sleep(4000)
		goTobagDetails()
	
		
		then:"View items in bag"
		to CartPage
		at CartPage
				
		when: "user goes to Cart Page and proceed to check out"
		toCheckoutPageLatest()
		String savingsamount= savingsAmount()
		fillingShippingAddrDetails()
		submitData()
		//chooseMaestro()
		chooseMasterCard()
	
		
		then: "Checking out the order with Credit Card Details"
		fillCreditCardDataLatest()
		at OrderConfirmationPage
		chksavingsAmount(savingsamount)
		
		}

}

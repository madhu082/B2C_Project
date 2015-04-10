package com.lsco.test.steps.checkout

import com.lsco.test.GBLevisSmoke
import com.lsco.test.DELevisSmoke
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
import com.sun.java.util.jar.pack.Driver;
import com.lsco.test.PropertyProvider
 

class CheckoutWithOrderLevelPromotion_16004_16025_16028_16012_16021 extends GebSpec {
	
	@GBLevisSmoke	
	def "CheckoutWithOrderLevelPromotion_Levis_with_couponcode_Restriction_16004"()
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
		
		when: "We are into MyProfile and going to cart to remove all items of bag and then go to  Women's Jackets & Vests Page"
		to LevisMyAccountPage
		at LevisMyAccountPage
		goTobagDetails()
		at CartPage
		removeAllItem()
		Thread.sleep(2000)
		driver.navigate().back()
		Thread.sleep(2000)
		at LevisMyAccountPage		
		toPage("2","#wJackets\\&VestsLink")
	
		
		then: "Click PDP page on click products in Women's Jackets & Vests Page"
		at WomenJacketsNVestPage
		toPDPPageOnClickingItem(0)
		

		when: "Add the product in bag from PDP page to get promo with restriction"
		//For this case promo is enabled if the order amount in cart>100. Assumed that with 3 products the order amount becomes >100 
				
		at ProductDetailPage
		selectAnySize()
		selectProductQuantity("3")
		clickAddToBag()
		goTobagDetails()
	
		
		then:"View items in bag"
		to CartPage
		at CartPage
		
		when: "Apply promo with promo code"
		//promo is enabled if the order amount in cart>100.Given promocode
		String promocode= PropertyProvider.getInstance().getLocalizedPropertyValue("promocode")
		applyCoupon(promocode)
		
		then: "Coupon activated"
		//promo is enabled if the order amount in cart>100.Given promo-identifier
		String Couponidentifier= PropertyProvider.getInstance().getLocalizedPropertyValue("Couponidentifier")
		checkCoupon(Couponidentifier)

		
		when: "user goes to Cart Page and proceed to check out"
		toCheckoutPageLatest()
		String savingsamount= savingsAmount()
		fillingShippingAddrDetails()
		submitData()
		chooseMaestro()
	
		
		then: "Checking out the order with Credit Card Details"
		fillCreditCardDataLatest()
		at OrderConfirmationPage
		chksavingsAmount(savingsamount)	
		
	}
	
@DELevisSmoke
	def "CheckoutWithOrderLevelPromotion_Levis_with NO_Restriction_16012"()
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
		
		when: "We are into MyProfile and going to cart to remove all items of bag and then go to  Women's Jackets & Vests Page"
		to LevisMyAccountPage
		at LevisMyAccountPage
		goTobagDetails()
		at CartPage
		removeAllItem()
		Thread.sleep(2000)
		driver.navigate().back()
		Thread.sleep(2000)
		at LevisMyAccountPage
		toPage("2","#wJackets\\&VestsLink")
	
		
		then: "Click PDP page on click products in Women's Jackets & Vests Page"
		at WomenJacketsNVestPage
		toPDPPageOnClickingItem(0)
		

		when: "Add the product in bag from PDP page to get promo with No restriction"
		//For this case some% discount Order promotion is enabled if the order amount in cart>100. 
		//Assumed that with 3 products the order amount becomes >100
				
		at ProductDetailPage
		selectAnySize()
		selectProductQuantity("3")
		clickAddToBag()
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

	
	@DEDockersSmoke
	def "CheckoutWithOrderLevelPromotion_Dockers__with_couponcode_Restriction_16025"()
	{
		when: "Going to Home Page and clicking on My Account"
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
		toPage("1","#Dockers\\_Men\\_Shorts")
		Thread.sleep(4000)
		
		then: "Click PDP page on click products in Page"
		at DockersMenShorts
		toPDPPageOnClickingItem(0)
		

		when: "Add the product in bag from PDP page to get promo with restriction"
		//For this case promo is enabled if the order amount in cart>100. Assumed that with 3 products the order amount becomes >100
				
		at ProductDetailPage
		selectAnySize()
		selectProductQuantity("3")
		clickAddToBag()
		Thread.sleep(4000)
		goTobagDetails()
	
		
		then:"View items in bag"
		to CartPage
		at CartPage
		
		when: "Apply promo with promo code"
		//promo is enabled if the order amount in cart>100.Given promocode
		String promocode= PropertyProvider.getInstance().getLocalizedPropertyValue("promocode")
		applyCoupon(promocode)
		
		then: "Coupon activated"
		//promo is enabled if the order amount in cart>100.Given promo-identifier
		String Couponidentifier= PropertyProvider.getInstance().getLocalizedPropertyValue("Couponidentifier")
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
	def "CheckoutWithOrderLevelPromotion_Dockers__with_No_Restriction_16021"()
	{
		when: "Going to Home Page and clicking on My Account"
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
		Thread.sleep(4000)
		searchProduct("Shorts")
		//toPage("1","#Dockers\\_Men\\_Shorts")
		Thread.sleep(4000)
		
		then: "Click PDP page on click products in Page"
		at DockersMenShorts
		toPDPPageOnClickingItem(0)
		

		when: "Add the product in bag from PDP page to get promo with No restriction"
		//For this case some% discount Order promotion is enabled if the order amount in cart>100. 
		//Assumed that with 3 products the order amount becomes >100
						
		at ProductDetailPage
		selectAnySize()
		selectProductQuantity("3")
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

@DEDockersSmoke
	def "Verify Overlay Window with more promotion information on clicking OrderLevelPromotion_Dockers_16028"()
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
		
		when: "We are into MyProfile and going to cart to remove all items of bag and then go to product list Page"
		to LevisMyAccountPage
		at LevisMyAccountPage
		Thread.sleep(10000)
		toPage("1","#Dockers\\_Men\\_Shorts")
		Thread.sleep(4000)
		
		then: "Click PDP page on click products in Page"
		at DockersMenShorts
		toPDPPageOnClickingItem(0)
		
		when: "Clicked promo details to get promo inforations from latest deal"
		//For this case promo is enabled if the order amount in cart>100.	
		//Assumed that details should contains Promo code and Promo identifier
		at ProductDetailPage		
		openPromoDetailsFromLatestDeals()
		Thread.sleep(4000)
		
		then:"View and check promo details"
		String Couponidentifier= PropertyProvider.getInstance().getLocalizedPropertyValue("Couponidentifier")
		String CouponCode= PropertyProvider.getInstance().getLocalizedPropertyValue("promocode")
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[class='fancybox-iframe']")))		
		chkPromodetails(Couponidentifier,CouponCode)
		driver.switchTo().defaultContent()		
		closepromodetails()
		Thread.sleep(4000)
		
		when: "Clicked promo details to get promo inforations from Cart Promo"
		//For this case promo is enabled if the order amount in cart>100.
		//Assumed that details should contains Promo code and Promo identifier
		at ProductDetailPage
		openPromoDetailsFromCartPromo()
		Thread.sleep(4000)
		
		then:"View and check promo details"	
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[class='fancybox-iframe']")))
		chkPromodetails(Couponidentifier,CouponCode)
		driver.switchTo().defaultContent()	
		closepromodetails()
		Thread.sleep(4000)
	}

}

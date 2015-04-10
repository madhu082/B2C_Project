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
import com.sun.java.util.jar.pack.Driver;
import com.lsco.test.PropertyProvider
 

class CheckoutWithVouchers_16008_16010_16029_16031 extends GebSpec {
	
	@GBLevisSmoke	
	def "Checkout with serial voucher_16008"()
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
		Thread.sleep(2000)		
		toPage("2","#wJackets\\&VestsLink")
	
		
		then: "Click PDP page on click products in Women's Jackets & Vests Page"
		at WomenJacketsNVestPage
		toPDPPageOnClickingItem(0)
		

		when: "Add the product in bag from PDP page to get serial voucher activation"
		//For this case A serial voucher with no restriction is used. It gives 10% discount of total order.				
		at ProductDetailPage
		selectAnySize()
		selectProductQuantity("1")
		clickAddToBag()
		goTobagDetails()
	
		
		then:"View items in bag"
		to CartPage
		at CartPage
		
		when: "Apply Serial voucher with code"
		//Serial voucher will be enabled on total order amount in cart.Given Serial voucher code	
		String promocode= PropertyProvider.getInstance().getLocalizedPropertyValue("SerialVouchercode")
		applyCoupon(promocode)
		
		then: "Serial Voucher activated"
		//Serial voucher is enabled on total order amount in cart.
		String Couponidentifier= PropertyProvider.getInstance().getLocalizedPropertyValue("SerialVoucherActivationMsg")
		checkVoucher(Couponidentifier)

		
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
	@GBLevisSmoke
	def "Checkout with promotional voucher_16010"()
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
		Thread.sleep(4000)
		toPage("2","#wJackets\\&VestsLink")
	
		
		then: "Click PDP page on click products in Women's Jackets & Vests Page"
		at WomenJacketsNVestPage
		toPDPPageOnClickingItem(1)
		

		when: "Add the product in bag from PDP page to get Promotional voucher activation"
		//For this case A serial voucher with no restriction is used. It gives 10% discount of total order.		
		at ProductDetailPage
		selectAnySize()
		selectProductQuantity("1")
		clickAddToBag()
		goTobagDetails()
	
		
		then:"View items in bag"
		to CartPage
		at CartPage
		
		when: "Apply Promotional voucher with code"
		//Promotional voucher will be enabled on total order amount in cart.Given Serial voucher code	
		String promocode= PropertyProvider.getInstance().getLocalizedPropertyValue("PromotionalVouchercode")
		applyCoupon(promocode)
		
		then: "Promotional voucher activated"
		//Promotional voucher is enabled on total order amount in cart.
		String Couponidentifier= PropertyProvider.getInstance().getLocalizedPropertyValue("PromotionalVoucherActivationMsg")
		checkVoucher(Couponidentifier)

		
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
	
	@DEDockersSmoke
	def "Checkout with serial voucher_16029"()
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
		
		when: "We are into MyProfile and going to cart to remove all items of bag and then go to Men's shorts List Page"
		to LevisMyAccountPage
		at LevisMyAccountPage
		goTobagDetails()
		at CartPage
		removeAllItem()
		Thread.sleep(10000)
		driver.navigate().back()
		Thread.sleep(4000)
		at LevisMyAccountPage
		Thread.sleep(4000)
		toPage("1","#Dockers\\_Men\\_Shorts")
		Thread.sleep(4000)
		
		then: "Click PDP page on click products in Page"
		at DockersMenShorts
		toPDPPageOnClickingItem(0)
		

		when: "Add the product in bag from PDP page to get serial voucher activation"
		//For this case A serial voucher with no restriction is used. It gives 10% discount of total order.						
		at ProductDetailPage
		selectAnySize()
		selectProductQuantity("1")
		clickAddToBag()
		Thread.sleep(4000)
		goTobagDetails()
	
		
		then:"View items in bag"
		to CartPage
		at CartPage
		
		when: "Apply Serial voucher with code"
		//Serial voucher will be enabled on total order amount in cart.Given Serial voucher code	
		String promocode= PropertyProvider.getInstance().getLocalizedPropertyValue("SerialVouchercode")
		applyCoupon(promocode)
		
		then: "Serial Voucher activated"
		//Serial voucher is enabled on total order amount in cart.
		String Couponidentifier= PropertyProvider.getInstance().getLocalizedPropertyValue("SerialVoucherActivationMsg")
		checkVoucher(Couponidentifier)

		
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
	def "Checkout with promotional voucher_16031"()
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
		
		when: "We are into MyProfile and going to cart to remove all items of bag and then go to Men's shorts List Page"
		to LevisMyAccountPage
		at LevisMyAccountPage
		goTobagDetails()
		at CartPage
		removeAllItem()
		Thread.sleep(10000)
		driver.navigate().back()
		Thread.sleep(4000)
		at LevisMyAccountPage
		Thread.sleep(4000)
		toPage("1","#Dockers\\_Men\\_Shorts")
		Thread.sleep(4000)
		
		then: "Click PDP page on click products in Page"
		at DockersMenShorts
		toPDPPageOnClickingItem(0)
		
		when: "Add the product in bag from PDP page to get Promotional voucher activation"
		//For this case A serial voucher with no restriction is used. It gives 10% discount of total order.		
		at ProductDetailPage
		selectAnySize()
		selectProductQuantity("1")
		clickAddToBag()
		goTobagDetails()
	
		
		then:"View items in bag"
		to CartPage
		at CartPage
		
		when: "Apply Promotional voucher with code"
		//Promotional voucher will be enabled on total order amount in cart.Given Serial voucher code	
		String promocode= PropertyProvider.getInstance().getLocalizedPropertyValue("PromotionalVouchercode")
		applyCoupon(promocode)
		
		then: "Promotional voucher activated"
		//Promotional voucher is enabled on total order amount in cart.
		String Couponidentifier= PropertyProvider.getInstance().getLocalizedPropertyValue("PromotionalVoucherActivationMsg")
		checkVoucher(Couponidentifier)

		
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

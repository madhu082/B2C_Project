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
 

class PromotionPrioritySpecs_16030_16037_16038 extends GebSpec {
	
	@GBLevisSmoke	
	def "One with the highest priority Order level Promotion is displayed in the buy stack_16038"()
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
		
		when: "We are into MyProfile. Go to product details page from Women's Jackets & Vests Page "
		at LevisMyAccountPage		
		toPage("2","#wJackets\\&VestsLink")
		at WomenJacketsNVestPage
		toPDPPageOnClickingItem(0)
		
		then: "Check promotion with the highest priority is displayed in the buy stack on the PDP"
		//For this case order label promo two promos are enabled with different priorities. 
		//Need to give the bar massage of each				
		at ProductDetailPage
		String p1PromoBarMsg= PropertyProvider.getInstance().getLocalizedPropertyValue("p1PromoBarMsg")+" LEARN MORE &" //High priority promo
		String p2PromoBarMsg= PropertyProvider.getInstance().getLocalizedPropertyValue("p2PromoBarMsg")+" LEARN MORE &"  //Low priority promo
		
		chkEnabledCartPromo(p1PromoBarMsg,p2PromoBarMsg)			
	}
	
	@DEDockersSmoke
	def "One with the highest priority Order level Promotion is displayed in the buy stack_16030"()
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
		
		when: "We are into MyProfile. Go to product details page from Mens shorts Page "
		at LevisMyAccountPage
		Thread.sleep(10000)
		toPage("1","#Dockers\\_Men\\_Shorts")
		Thread.sleep(4000)
		at DockersMenShorts
		toPDPPageOnClickingItem(0)
		Thread.sleep(2000)
		
		then: "Check promotion with the highest priority is displayed in the buy stack on the PDP"
		//For this case order label promo two promos are enabled with different priorities.
		//Need to give the bar massage of each
		at ProductDetailPage
		String p1PromoBarMsg= PropertyProvider.getInstance().getLocalizedPropertyValue("p1PromoBarMsg")+" Weitere Informationen &" //High priority promo
		String p2PromoBarMsg= PropertyProvider.getInstance().getLocalizedPropertyValue("p2PromoBarMsg")+" Weitere Informationen &"  //Low priority promo
		
		chkEnabledCartPromo(p1PromoBarMsg,p2PromoBarMsg)
		
		}
	
	@DEDockersSmoke
	def "One with the highest priority product level Promotion is displayed cart page_16037"()
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
		String skuItemName = PropertyProvider.getInstance().getLocalizedPropertyValue("SkuItemName4rPriorityChk")
		searchProduct(skuItemName)
		Thread.sleep(10000)
		
		then: "Click PDP page on click products in Page"
		
		//to SearchResultPage
		at SearchResultPage
		verifySearchedItem(skuItemName)
		clickSearchedItem()

		when: "Add the product in bag from PDP page to get promo with restriction"
				
		//For this case order label promo two promos are enabled with different priorities.
		//Need to give the bar massage of each
				
		at ProductDetailPage
		Thread.sleep(10000)
		selectAnySize()
		selectProductQuantity("1")
		clickAddToBag()
		Thread.sleep(4000)
		goTobagDetails()
	
		
		
		then: "Check promotion with the highest priority is displayed in the buy stack on the PDP"		
		
		at CartPage
		Thread.sleep(4000)
		String p1PromoBarMsg= PropertyProvider.getInstance().getLocalizedPropertyValue("p1ProductPromoFiredMsg") //High priority promo
		String p2PromoBarMsg= PropertyProvider.getInstance().getLocalizedPropertyValue("p2ProductPromoFiredMsg") //Low priority promo
		
		chkEnabledCartPromo(p1PromoBarMsg,p2PromoBarMsg)

	}
	
	

}

package com.lsco.test.steps.navigation

import com.lsco.test.GBLevisSmoke
import com.lsco.test.DELevisSmoke
import com.lsco.test.RULevisSmoke
import com.lsco.test.DEDockersSmoke
import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.ProductDetailPage
import com.lsco.test.page.navigation.WomenJacketsNVestPage
import com.lsco.test.page.navigation.AccessoriesPageSpec
import com.lsco.test.page.CartPage
import com.lsco.test.page.navigation.MenShirtsPage
import geb.spock.GebSpec


//SPRING-15807--[GB] - [GB] - Levi - Add to bag - Verify error message when trying to add a product to the bag without selecting attributes.
//SPRING-15810--[DE] - [DE] - Dockers - Add to bag - Verify adding to the bag from PDP
//SPRING-15815--[DE] - [DE] - Dockers - Add to Bag - Verify that the user cannot add more than 3 products of the same SKU to the bag
//SPRING-15788--[RU] - [RU] - Levi - Bag actions - Verify that the "My bag" button is not displayed for marketing sites.
//SPRING-15804--[RU] - [RU] - Levi - Add to bag - Verify that the qty dropdown menu and "add to bag" button are not displayed for marketing sites.
//SPRING-15817--[DE] - [DE] - Dockers - Add to bag - Verify that the user cannot add more than 6 products of the same PC9 to the bag.
class VerifyAddToBagSpec_15807_15810_15815_15788_15804_15817 extends GebSpec {
    
	@GBLevisSmoke
	def "Verify error message for Add to Bag a product without selecting_SPRING-15807"() 
	{
        when: "Clicking on Link to Women's Jackets & Vests Page"
        to LevisHomePage
        at LevisHomePage
        toPage("2","#wJackets\\&VestsLink")
		Thread.sleep(2000)
		
        then: "Click PDP page on click products in Women's Jackets & Vests Page"
        at WomenJacketsNVestPage
        toPDPPageOnClickingItem(0)
        at ProductDetailPage

        when:"At product Details page"
        at ProductDetailPage
        clickAddToBag()
        Thread.sleep(2000)

        then:"Check error message when trying to add a product to the bag without selecting attributes"
        verifyAddToBagWithoutSelect()
	}


    @DELevisSmoke
    def "Verify Add to Bag a product selecting_SPRING-15810"() {
        when: "Clicking on Link to Women's Jackets & Vests Page"
        to LevisHomePage
        at LevisHomePage
		Thread.sleep(2000)
        toPage("2", "#wJackets\\&VestsLink")
		Thread.sleep(2000)
		
        then: "Click PDP page on click products in Women's Jackets & Vests Page"
        at WomenJacketsNVestPage
        toPDPPageOnClickingItem(5)
        at ProductDetailPage

        when: "Add the product in bag from PDP page"
        at ProductDetailPage
        String productTitle = getProductTitle()
        String productStyle = getProductStyle()
        selectAnySize()
        selectProductQuantity("1")
        clickAddToBag()
        Thread.sleep(2000)

        then: "Verify added Items in bag"
        at ProductDetailPage
        verifyIteminbag(productTitle, productStyle)
    }

   @DEDockersSmoke
    def "Verify user cannot add more than 3 products of the same SKU to the bag_SPRING-15815"() {
        when: "Clicking on Link to Men Accessories ShopAll Link Page"
        to LevisHomePage
        at LevisHomePage
		
		goTobagDetails()
		Thread.sleep(4000)
		at CartPage
		removeAllItem()
		Thread.sleep(10000)
		driver.navigate().back()
		Thread.sleep(4000)
		at LevisHomePage
		toPage("4", "#MenAccessoriesShopAllLink")
		Thread.sleep(2000)
  

        then: "Click PDP page on click products in Men Accessories ShopAll Link Page"
        at AccessoriesPageSpec
        toPDPPageOnClickingItem(0)
        at ProductDetailPage

        when: "Add same product>3 times in bag from PDP page"
        at ProductDetailPage
        selectAnySize()
		Thread.sleep(4000)
        selectProductQuantity("3")
        clickAddToBag()
        Thread.sleep(10000)
        //select more quantity of same SKU
        selectProductQuantity("1")
        clickAddToBag()
        Thread.sleep(2000)

        then: "Verify added Items in bag"
        at ProductDetailPage
        verifyMaxSKUError("MaxSkuError")
    }
   
   
	
	@RULevisSmoke	
	def "Verify that the Bag actions is not displayed for marketing sites_SPRING-16026"() {
		when: "Clicking on Link to Women's Jackets & Vests Page"
		to LevisHomePage
		at LevisHomePage
		toPage("2", "#wJackets\\&VestsLink")

		then: "Ckeck PDP page on click products in Marketing site"
		at WomenJacketsNVestPage
		toPDPPageOnClickingItem(0)
		at ProductDetailPage
		checkProductPromo(false)
	}
	@DEDockersSmoke
	def "Verify that the user cannot add more than 6 products of the same PC9 to the bag_SPRING-15817"() {
		when: "Clicking on Link to Men Accessories ShopAll Page"
		to LevisHomePage
		at LevisHomePage
		
		goTobagDetails()
		at CartPage
		removeAllItem()
		Thread.sleep(10000)
		driver.navigate().back()
		Thread.sleep(4000)
		at LevisHomePage
		toPage("4", "#MenAccessoriesShopAllLink")
		//toPage("1", "#MenShirtsLink")
		Thread.sleep(2000)

		then: "Click PDP page on click products in Men Accessories ShopAll Page"
		at AccessoriesPageSpec
		//at MenShirtsPage
		
		for(int n=0;n<=10;n++){
			//checking upto 10 elements of Men Accessories
			toPDPPageOnClickingItem(n)
			at ProductDetailPage
			if(availablesize(4)){
				break
			}
			else
			{
				Thread.sleep(10000)
				driver.navigate().back()
				Thread.sleep(10000)
				at AccessoriesPageSpec
				//at MenShirtsPage
			}
		}
		

		when: "Add same product different sku>6 times in bag from PDP page"
		at ProductDetailPage
		//select 3 products of first SKU a PC9
		selectSize(0)
		Thread.sleep(5000)
		selectProductQuantity("3")
		clickAddToBag()
		Thread.sleep(10000)
		//select 3 products of second SKU but same PC9
		selectSize(1)
		Thread.sleep(2000)
		selectProductQuantity("3")
		clickAddToBag()
		Thread.sleep(10000)
		//select 1 more product of third SKU but same PC9
		selectSize(2)
		Thread.sleep(2000)
		selectProductQuantity("1")
		clickAddToBag()
		Thread.sleep(10000)
		
		then: "Verify added Items in bag"
		at ProductDetailPage
		verifyMaxSKUError("MaxSkuError1")
	}
	
	
	
}

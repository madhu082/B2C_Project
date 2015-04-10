package com.lsco.test.steps.navigation

import com.lsco.test.DELevisSmoke
import com.lsco.test.GBLevisSmoke
import com.lsco.test.RULevisSmoke
import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.ProductDetailPage
import com.lsco.test.page.navigation.WomenJacketsNVestPage
import org.openqa.selenium.*
import geb.spock.GebSpec

// SPRING-15764--[GB] - Levi - Product Recommendations - Verify that items displayed within "RECENTLY VIEWED PRODUCTS" on PDP
// SPRING-16026--[RU] - Levi - Promotions - Verify that the promotions are not displayed on PDP of marketing sites.

class ProductDetailsPageSpec_15702_16026 extends GebSpec{

	@GBLevisSmoke
	def "Verify RECENTLY VIEWED section on PDP page_SPRING-15702"()
	{
        when: "Clicking on Link to Women's Jackets & Vests Page"
        to LevisHomePage
        at LevisHomePage
		Thread.sleep(2000)
        toPage("2","#wJackets\\&VestsLink")
		Thread.sleep(4000)
		
        then: "Click PDP page on click products in Women's Jackets & Vests Page"
        at WomenJacketsNVestPage
        toPDPPageOnClickingItem(0)
        at ProductDetailPage
        driver.navigate().back();

        Thread.sleep(10000)
        at WomenJacketsNVestPage
        toPDPPageOnClickingItem(1)
        Thread.sleep(10000)

        driver.navigate().back();
        at WomenJacketsNVestPage
        toPDPPageOnClickingItem(2)

        when:"At product Details page"
        at ProductDetailPage

        then:"Check RECENTLY VIEWED section exist"
        println(chkRecentlyviewedSec())
	}
	@RULevisSmoke
	def "Verify promotions are not displayed on PDP of marketing sites_SPRING-16026"() {
		when: "Clicking on Link to Women's Jackets & Vests Page"
		to LevisHomePage
		at LevisHomePage
		toPage("2", "#wJackets\\&VestsLink")

		then: "Ckeck  promotions are not displayed on PDP of marketing sites"
		at WomenJacketsNVestPage
		toPDPPageOnClickingItem(0)
		at ProductDetailPage
		checkProductPromo(false)
	}

}

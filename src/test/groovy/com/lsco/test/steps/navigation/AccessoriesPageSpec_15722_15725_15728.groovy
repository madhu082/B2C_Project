package com.lsco.test.steps.navigation

import com.lsco.test.DELevisSmoke
import com.lsco.test.page.LevisHomePage
import com.lsco.test.DEDockersSmoke
import com.lsco.test.page.ProductDetailPage
import com.lsco.test.page.navigation.AccessoriesPageSpec
import geb.spock.GebSpec

// SPRING-15722--Dockers - DE - Category - Verify that the user is redirected to the "Accessories - Shop All" Category page, when accessing it through the top navigation links
// SPRING-15725--Dockers - DE - Category - Verify that products are displayed when accessing the "Accessories - Shop All" Category page.
class AccessoriesPageSpec_15722_15725_15728 extends GebSpec{
	/*@DEDockersSmoke
	def "Verify Navigation to Accessories Shop all Page SPRING-15722"()
	{
		when: "Clicking on Link to Accessories Shop all Page"
		to LevisHomePage
		at LevisHomePage
        toAccessoriesShopAllPage()
		Thread.sleep(4000)
		
		then: "Navigated to Accessories Shop all Page"
        at AccessoriesPageSpec

	}
    @DEDockersSmoke
    def "Verify products display in Accessories Shop all Page SPRING-15725"()
    {
        when: "Clicking on Link to Accessories Shop all Page"
        to LevisHomePage
        at LevisHomePage
        toAccessoriesShopAllPage()
		Thread.sleep(4000)

        then: "Check all products in Accessories Shop all Page"
        at AccessoriesPageSpec
        checkProducts()

    }*/
    @DEDockersSmoke
    def "Verify PDP page on click products in Accessories Shop all Page SPRING-15728"()
    {
        when: "Clicking on Link to Accessories Shop all Page"
        to LevisHomePage
        at LevisHomePage
        toAccessoriesShopAllPage()
		Thread.sleep(4000)

        then: "Click PDP page on click products in Accessories Shop all Page"
        at AccessoriesPageSpec
        toPDPPageOnClickingItem()
        at ProductDetailPage
        chkdetails()
    }
}

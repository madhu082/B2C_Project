package com.lsco.test.steps.navigation

import com.lsco.test.RULevisSmoke
import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.navigation.WomenJacketsNVestPage
import com.lsco.test.page.navigation.WomenCategoryPage
import geb.spock.GebSpec
import com.lsco.test.page.ProductDetailPage

// Levi - RU [SPRING-15709,15713,15715]

class WomenJacketNVestPageSpec_15709_15713_15715 extends GebSpec{
    @RULevisSmoke
    def "Verify Navigation to Women's Jackets & Vests Page [SPRING-15709]"()
    {
        when: "Clicking on Link to Women's Category Women's Jackets & Vests Page"
        to LevisHomePage
        at LevisHomePage
        toPage(2,"#wJackets\\&VestsLink")

        then: "Check Women's Jackets & Vests Page"
        at WomenJacketsNVestPage
    }

	@RULevisSmoke
    def "Verify products display in Women's Jackets & Vests Page SPRING-15713"()
    {
        when: "Clicking on Link to Women's Jackets & Vests Page"
        to LevisHomePage
        at LevisHomePage
        toPage("2","#wJackets\\&VestsLink")

        then: "Check all products in Women's Jackets & Vests Page"
        at WomenJacketsNVestPage
        checkProducts()

    }
	@RULevisSmoke
    def "Verify PDP page on click products in Women's Jackets & Vests Page SPRING-15715"()
    {
        when: "Clicking on Link to Women's Jackets & Vests Page"
        to LevisHomePage
        at LevisHomePage
        toPage("4","#wJackets\\&VestsLink")

        then: "Click PDP page on click products in Women's Jackets & Vests Page"
        at WomenJacketsNVestPage
        toPDPPageOnClickingItem()
        at ProductDetailPage
        chkdetails()
    }

}

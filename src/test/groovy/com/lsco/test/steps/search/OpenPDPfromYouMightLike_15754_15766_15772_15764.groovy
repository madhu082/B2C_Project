package com.lsco.test.steps.search

import com.lsco.test.GBLevisSmoke
import com.lsco.test.DELevisSmoke
import com.lsco.test.GBDockersSmoke
import com.lsco.test.DEDockersSmoke

import com.lsco.test.page.EmailPage
import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.login.LevisLoginPage
import geb.spock.GebSpec
import com.lsco.test.page.search.FailSearchResultPage
import com.lsco.test.page.ProductDetailPage
/*
 //Jira SPRING-15754, SPRING-15766, SPRING-15772,SPRING-15764
 */

class OpenPDPfromYouMightLike_15754_15766_15772_15764 extends GebSpec{
	@GBLevisSmoke
	@DELevisSmoke
	@DEDockersSmoke
	@GBDockersSmoke
	def OpenPDPfromYouMightLikeFromInValidSearch_15754_15766_15772()
	{
		when: "We are Home page & searching Invalid Product"
		to LevisHomePage
		at LevisHomePage
		searchInvalidProduct()
		
		then: "Check either we are on the Failed search Page"
		at FailSearchResultPage
		checkOtherItemsSection(4)

		when: "We select first item from You might like"
		at FailSearchResultPage
		toPDPPageOnClickingItemFromYouMightLikeSection()

		then: "Check either we are on the PDP page"
		at ProductDetailPage
		chkdetails()

		}
	

}

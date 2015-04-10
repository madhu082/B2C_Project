package com.lsco.test.steps.navigation
import com.lsco.test.RULevisSmoke
import com.lsco.test.page.LevisHomePage
import geb.spock.GebReportingSpec
import geb.spock.GebSpec

class VerifyloadAdditionalProdAtoZLeviRU extends GebSpec{
	@RULevisSmoke
	def "verifyloadAdditionalProdAtoZLeviRU 18042_1"()
	{
		when: "access to Levis RU Site"
		to LevisHomePage
		at LevisHomePage
		dismissPopup()
		//mouse over Men and select Shop all Men
		menShopAllLinkLeviHomePage()
		
		then: "user goes to shop all section and selecting the Sort them by Drop Down"
		//selecting the option "Product A-Z" from sort them by Drop-down
		verifyloadAddtionlProdLeviRUHomePage()
		
				}

}

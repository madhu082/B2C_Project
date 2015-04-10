package com.lsco.test.steps.navigation
import com.lsco.test.RULevisSmoke
import com.lsco.test.page.LevisHomePage
import geb.spock.GebReportingSpec
import geb.spock.GebSpec

class VerifyloadAdditionalWomenLeviRU extends GebSpec {
	@RULevisSmoke
	def "VerifyloadAdditionalWomenLeviRU 18042_2"()
	{
		when: "access to Levis RU Site"
		to LevisHomePage
		at LevisHomePage
		dismissPopup()
		//mouse over Women and select Shop all Women
		womenShopAllLinkLeviHomePage()
		
		then: "user goes to shop all section and selecting the Sort them by Drop Down"
		//selecting the option "Product A-Z" from sort them by Drop-down
		verifyloadAddtionlProdLeviRUHomePage()
		
				}
}

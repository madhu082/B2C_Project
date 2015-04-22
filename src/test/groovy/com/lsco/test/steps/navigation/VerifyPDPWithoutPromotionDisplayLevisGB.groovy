package com.lsco.test.steps.navigation
import com.lsco.test.GBLevisSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import gherkin.formatter.model.Feature
import junit.framework.Test
import geb.spock.GebReportingSpec;
import geb.spock.GebSpec;
import geb.Browser
import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.FirstProductPage
import com.lsco.test.page.hmc.HMCHomePage
import com.lsco.test.page.hmc.HMCLoginPage 

class VerifyPDPWithoutPromotionDisplayLevisGB extends GebSpec {
	@GBLevisSmoke
	def "verifyPDPWithoutPromotionDisplayLevisGB 17504"(){
		
		when: "Enable promotion in hmc- pre req"
		to HMCLoginPage
		at HMCLoginPage
		fillHybrisLoginFields("qa.team","pass1234")
		clickOnTDWithText("Marketing;Promotions")
		disableAllOtherPromotions()
		closeSession()
		
		
		and: "going to Levis GB and selecting the product without Promotion"
		to LevisHomePage
		at LevisHomePage
		dismissPopup()
		searchProductForGuestUsr()
		clickOnTheSearchedItemForGuestUsr()
		
		then: " verifying the item does not have item level promotion"
		at FirstProductPage
		verifyitemdontHaveItemLvlPromo()
		
		
		
		
		
	}

}

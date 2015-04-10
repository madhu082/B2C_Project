package com.lsco.test.steps.navigation
import com.lsco.test.DELevisSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import gherkin.formatter.model.Feature
import junit.framework.Test
import geb.spock.GebReportingSpec;
import geb.spock.GebSpec;
import geb.Browser
import com.lsco.test.page.register.AccountRegistrationPage
import com.lsco.test.page.login.LevisLoginPage
import com.lsco.test.page.login.LevisMyAccountPage
import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.FirstProductPage

class AddingaProductWithoutSelectingAttributeLeviDE extends GebSpec{
	@DELevisSmoke
	def "addingaProductWithoutSelectingAttributeLeviDE 15808"(){
		
		when: "going to Levi DE and selecting a product"
		to LevisHomePage
		at LevisHomePage
		dismissPopup()
		searchProductForGuestUsr()
		clickOnTheSearchedItemForGuestUsr()
		
		then: "User goes to first Product Page and adding a product to Bag without Selecting Attribute"
		at FirstProductPage
		doingAddToBagWithoutSelectingAttribute()
		
		
	}

}

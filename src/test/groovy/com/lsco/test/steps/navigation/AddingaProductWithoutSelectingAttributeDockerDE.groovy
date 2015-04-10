package com.lsco.test.steps.navigation
import com.lsco.test.DEDockersSmoke
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

class AddingaProductWithoutSelectingAttributeDockerDE extends GebSpec {
	@DEDockersSmoke
	def "addingaProductWithoutSelectingAttributeDockerDE 15819"(){
		
		when: "going to Dockers DE and selecting a product"
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

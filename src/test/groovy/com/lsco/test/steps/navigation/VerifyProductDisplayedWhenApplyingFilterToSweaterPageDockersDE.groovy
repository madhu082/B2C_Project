package com.lsco.test.steps.navigation
import com.lsco.test.DEDockersSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import junit.framework.Test
import com.lsco.test.page.navigation.ClothingSweatersCategoryPage
import com.lsco.test.page.LevisHomePage
import geb.spock.GebReportingSpec
import geb.spock.GebSpec
import spock.lang.Unroll
import geb.Browser

class VerifyProductDisplayedWhenApplyingFilterToSweaterPageDockersDE extends GebSpec {
	@DEDockersSmoke
	def "verifyProductDisplayedWhenApplyingFilterToSweaterPageDockersDE 15727"()
	{
		when: "clicking on the Sweater Link from Dockers DE Home Page"
		to LevisHomePage
		at LevisHomePage
		toSweatersPage()
		
		then: "Apply Filter values and check products Display"
		to ClothingSweatersCategoryPage
		at ClothingSweatersCategoryPage
		applyFiltersSweatersPage()
		verifySweaterProductsDisplayed()
		
	}

}

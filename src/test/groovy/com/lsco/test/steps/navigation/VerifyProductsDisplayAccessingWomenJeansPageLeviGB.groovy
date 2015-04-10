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
import com.lsco.test.page.navigation.WomenAllJeansCategoryPage
import com.lsco.test.page.navigation.WomenCategoryPage


class VerifyProductsDisplayAccessingWomenJeansPageLeviGB extends GebSpec {
	@GBLevisSmoke
	def "verifyProductsDisplayAccessingWomenJeansPageLeviGB 15999"()
	{
		when: "navigating to women Jeans Page"
		to LevisHomePage
		at LevisHomePage
		dismissPopup()
		navigatingToWomenJeans()

		then: "user goes to women category page and selecting see all collection"
		to WomenCategoryPage
		at WomenCategoryPage
		jeansSeeAllCollection()
		
	   when: "user is at womens all jeans category page"
	   at WomenAllJeansCategoryPage
		
	   then: "verifying the products in woman all jeans page"
	   verifyNumberOfProductsDisplayed()
	   

	}

}

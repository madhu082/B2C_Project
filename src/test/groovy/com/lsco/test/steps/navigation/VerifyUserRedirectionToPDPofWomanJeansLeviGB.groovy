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

class VerifyUserRedirectionToPDPofWomanJeansLeviGB extends GebSpec {
    @GBLevisSmoke
	def "verifyUserRedirectionToPDPofWomanJeansLeviGB 15997"()
	{
		when: "navigating to Levis Gb women Jeans Page"
		to LevisHomePage
		at LevisHomePage
		dismissPopup()
		navigatingToWomenJeans()

		then: "user goes to women category page and selecting see all collection"
		to WomenCategoryPage
		at WomenCategoryPage
		jeansSeeAllCollection()
		
	   when: "user is at Women all jeans category page"
	   at WomenAllJeansCategoryPage
		
	   then: "selecting a Jeans product and going to PDP"
	   applyFiltersinWomanJeansPage()
	   jeansredirectionToPDP()
	   

	}

}

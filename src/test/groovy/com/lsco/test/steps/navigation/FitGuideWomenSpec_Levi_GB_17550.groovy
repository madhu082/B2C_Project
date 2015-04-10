package com.lsco.test.steps.navigation

import com.lsco.test.GBLevisSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import junit.framework.Test
import com.lsco.test.page.navigation.FitGuideCategoryPage
import com.lsco.test.page.ProductDetailPage
import com.lsco.test.page.LevisHomePage
import com.sun.java.util.jar.pack.Driver;

import geb.spock.GebReportingSpec
import geb.spock.GebSpec
import spock.lang.Unroll
import geb.Browser

import org.openqa.selenium.WebDriver

import org.openqa.selenium.firefox.FirefoxDriver

class FitGuideWomenSpec_Levi_GB_17550 extends GebSpec{
	@GBLevisSmoke
	
	def "FitGuideWomenSpec"()
	{
		when: "Clicking on Link to Fit-Guide_Women from top nav"
		to LevisHomePage
		at LevisHomePage
		toFitGuideWomenPage()
		to FitGuideCategoryPage

		then: "Verifies the facets, result-grids & compare product from inline-PDP "
		at FitGuideCategoryPage
		VerifyFacets()
		ValidateExpandedFacet()
		GridResultViewFromFacet()
		VerifyInlinePDP()
		CompareProductFromInlinePDP()
		
		then: "Access to the PDP section fromInline-PDP"
		AccessToPDPFromInlinePDP()
		at ProductDetailPage
		NavigateBack()
				
		then: "User is at fitGuide Page & adds another product to compare"
		at FitGuideCategoryPage
		AddAnotherProductToCompare()
		VerifyComparisionPage()
		at ProductDetailPage
	}
}

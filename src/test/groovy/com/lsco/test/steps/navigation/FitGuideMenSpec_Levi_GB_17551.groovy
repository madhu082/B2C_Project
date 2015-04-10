package com.lsco.test.steps.navigation

import com.lsco.test.GBLevisSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import junit.framework.Test
import com.lsco.test.page.navigation.FitGuideCategory_MenPage
import com.lsco.test.page.ProductDetail_FitGuidePage
import com.lsco.test.page.LevisHomePage
import com.sun.java.util.jar.pack.Driver;

import geb.spock.GebReportingSpec
import geb.spock.GebSpec
import spock.lang.Unroll
import geb.Browser

import org.openqa.selenium.WebDriver

import org.openqa.selenium.firefox.FirefoxDriver

class FitGuideMenSpec_Levi_GB_17551 extends GebSpec{
	@GBLevisSmoke
	
	def "FitGuideMenSpec"()
	{
		when: "Clicking on Link to Fit-Guide_Women from top nav"
		to LevisHomePage
		at LevisHomePage
		toFitGuideMenPage()
		to FitGuideCategory_MenPage

		then: "Verifies the facets, result-grids & compare product from inline-PDP "
		at FitGuideCategory_MenPage
		VerifyFacetsForMenFitGuide()
		ValidateExpandedFacet()
		GridResultViewFromFacet()
		VerifyInlinePDP()
		CompareProductFromInlinePDP()
		
		then: "Access to the PDP section fromInline-PDP"
		AccessToPDPFromInlinePDP()
		at ProductDetail_FitGuidePage
		NavigateBack()
				
		then: "User is at fitGuide Page & adds another product to compare"
		at FitGuideCategory_MenPage
		AddAnotherProductToCompare()
		VerifyComparisionPage()
		at ProductDetail_FitGuidePage
	}
}

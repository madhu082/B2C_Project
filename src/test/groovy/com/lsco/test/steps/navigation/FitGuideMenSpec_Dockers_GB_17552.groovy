package com.lsco.test.steps.navigation

import com.lsco.test.GBDockersSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import junit.framework.Test
import com.lsco.test.page.navigation.FitGuideCategoryMenDockersPage
import com.lsco.test.page.ProductDetail_FitGuidePage
import com.lsco.test.page.LevisHomePage
import com.sun.java.util.jar.pack.Driver;

import geb.spock.GebReportingSpec
import geb.spock.GebSpec
import spock.lang.Unroll
import geb.Browser

import org.openqa.selenium.WebDriver

import org.openqa.selenium.firefox.FirefoxDriver

class FitGuideMenSpec_Dockers_GB_17552 extends GebSpec{
	@GBDockersSmoke
	
	def "FitGuideMenSpec"()
	{
		when: "Clicking on Link to Fit-Guide_men from top nav"
		to LevisHomePage
		at LevisHomePage
		toFitGuideMenPage()
		to FitGuideCategoryMenDockersPage

		then: "Verifies the facets, result-grids & compare product from inline-PDP "
		at FitGuideCategoryMenDockersPage
		ValidateExpandedFacet()
		GridResultViewFromFacet_Dockers()
		VerifyInlinePDP()
		CompareProductFromInlinePDP()
		
		then: "Access to the PDP section fromInline-PDP"
		AccessToPDPFromInlinePDP()
		at ProductDetail_FitGuidePage
		NavigateBack()
				
		then: "User is at fitGuide Page & adds another product to compare"
		at FitGuideCategoryMenDockersPage
		AddAnotherProductToCompare()
		VerifyComparisionPage()
		at ProductDetail_FitGuidePage
	}
}

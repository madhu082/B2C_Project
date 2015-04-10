package com.lsco.test.page.navigation

import geb.Page

import com.lsco.test.PropertyProvider

class FitGuideCategoryPage extends Page{

	static url = "fitguide/women/clothing"

	
	static content = {
		headline { $(".chooser-container>header>h1")}
	}
	
	static at = {
		Thread.sleep(1000)
		String okValue = PropertyProvider.getInstance().getLocalizedPropertyValue("FitGuide").toUpperCase()
		assert $(".chooser-container>header>h1").text().toUpperCase().contains(okValue) 
		return true 
	}

	def VerifyFacets(){
		
		waitFor(60,3){$("#drawerContainer>ul>li:nth-child(1)")}
		assert $("#drawerContainer>ul>li:nth-child(1)").isDisplayed() == true
		waitFor(60,3){ $("#drawerContainer>ul>li:nth-child(2)")} 
		assert $("#drawerContainer>ul>li:nth-child(2)").isDisplayed() == true
		waitFor(60,3){ $("#drawerContainer>ul>li:nth-child(1)")}
		assert $("#drawerContainer>ul>li:nth-child(3)").isDisplayed() == true
		return true
		}
	
	def ValidateExpandedFacet(){
		$(".ppgStyle-drawer.facet.collapsed.visible>header>a>span>img").click()
		waitFor(60,3){$(".ppgStyle-drawer.facet.visible.open")}
		assert $(".ppgStyle-drawer.facet.visible.open").isDisplayed()== true
		assert $(".H-ScrollWindow>ul>li").isDisplayed()==true
		return true
		}
	
	def GridResultViewFromFacet(){
		$(".H-ScrollWindow>ul>li:nth-child(1)>label>div").click()
		waitFor(60,3){$(".grid-wrapper.expanded").isDisplayed()}
		
		$(".js-grid-add-checkbox").click()
		$(".compare-count.has-elements>a>span").text()== 1
		return true
	}
	
	def VerifyInlinePDP(){
		Thread.sleep(500)
		$(".grid-wrapper.expanded").isDisplayed()
		$(".grid-container>ul>li>header>div>div>img").click() 
		
        $("#js-qv-productViewGroup>div:nth-child(1)").isDisplayed()
		$(".js-qv-product-view.js-animate360>img").isDisplayed()
		
		$("#w-body-shape>div").isDisplayed()
		$("#w-rise>div").isDisplayed()
		$("#w-leg-shape>div").isDisplayed()
				
		$("#qv-shopnow>div>div>a").isDisplayed()
		$("#qv-shopnow>div>div>div:nth-child(1)").isDisplayed()
		$("#qv-shopnow>div>div>div:nth-child(2)").isDisplayed()
		return true
	}
	
	def CompareProductFromInlinePDP(){
		$("#quickview").isDisplayed()
		waitFor(60,3){$("#qv-shopnow>div>div>div:nth-child(1)")}
	    $("#qv-shopnow>div>div>div:nth-child(1)").click()
		$(".compare-count.has-elements>a>span").text()== 1
		return true
		}
	
	def AccessToPDPFromInlinePDP(){
		$("#quickview").isDisplayed()
		$("#qv-shopnow>div>div>a").click()
		return true
		}
	
	def VerifyComparisionPage(){
		Thread.sleep(500)
		$(".compare-count.has-elements>a").click()
		waitFor(60,3){$("#compare-slider").isDisplayed()}
		
		$(".js-360-group>div:nth-child(1)").isDisplayed()
		$(".js-360-group>div:nth-child(2)").isDisplayed()
		
		$(".viewport>ul>li:nth-child(1)>div>div:nth-child(4)").isDisplayed()
		$(".viewport>ul>li:nth-child(2)>div>div:nth-child(4)").isDisplayed()
		
		$(".viewport>ul>li:nth-child(2)>div>div:nth-child(6)").isDisplayed()
		$(".viewport>ul>li:nth-child(2)>div>div:nth-child(6)").isDisplayed()
		
		$(".viewport>ul>li:nth-child(1)>div>a").isDisplayed()
		$(".viewport>ul>li:nth-child(2)>div>a").isDisplayed()
		
		$(".viewport>ul>li>div>a").click()
		return true
		}
	def AddAnotherProductToCompare(){
		//Thread.sleep(1000)
		$(".grid-wrapper.expanded").isDisplayed()
        $(".grid-wrapper.expanded>div:nth-child(2)>ul>li:nth-child(2)>ul>form>li>label>span:nth-child(3)").click()
		$(".compare-count.has-elements>a>span").text()== 2
		
		return true
		}
	
	}

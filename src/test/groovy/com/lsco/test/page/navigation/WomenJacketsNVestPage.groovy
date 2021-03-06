package com.lsco.test.page.navigation

import com.lsco.test.PropertyProvider
import com.lsco.test.page.FirstProductPage
import geb.Page
//import com.google.api.translate.Language;
//import com.google.api.translate.Translate;
import org.openqa.selenium.WebDriver
// added By Suprito after 12thMarch 2015
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.*

class WomenJacketsNVestPage extends Page{

	static url = "category/women/clothing/jackets-vests/all"

	
	static content = {
		headline { $("header>article>div>h1")}
        containerResults (required: false) {$("section#facets-products>ul>li")}
	}
	
	static at = {
        driver.currentUrl.contains("jackets-vests")
        assert $("header>article>div>h1").text().toUpperCase() == PropertyProvider.getInstance().getLocalizedPropertyValue("JacketsNVests")
		return true
	}


    def checkProducts(){
        assert containerResults.size() >0
        return true
    }
    def toPDPPageOnClickingItem(int index)
    {
        //$(".product-images>a>img").click(FirstProductPage)
        $(".product-images>a>img",index ).click(FirstProductPage)

    }
    def toPDPPageOnClickingItem()
    {
        $(".product-images>a>img").click(FirstProductPage)

    }


	def toAllCollectionsPage(){
		$("a.cta.see-all").click()
		//waitFor(30){ $("#main-container > article:nth-child(1) > header > article > div > h1").text() == "JEANS"}
	}
	
	def selectWaistFilter(){
		interact {
			click($(".facet-links"))
		}

		waitFor(30, 10){
			$("#facet-waist > ul > li:nth-child(2) > label:nth-child(1)").isDisplayed()
		}
		$("#facet-waist > ul > li:nth-child(2) > label:nth-child(1)").click()
	}
	def validateProductAfterWaistFilters(){
		String sWaistFilteredExpected=$("#facet-waist > ul > li:nth-child(2) > label:nth-child(1)").text()
		//Navigate to PDP page for the 1st product filtered
		$("#container_results > li:nth-child(1) > div:nth-child(1) > a > img").click()
		String sWaistFilteredActual=$(".size-swatch-wrapper.notouch-device.selected>a").text()
		//Compare waist filtered to waist in PDP
		assert sWaistFilteredExpected==sWaistFilteredActual
		println "Expected Waist : "+sWaistFilteredExpected
		println "Actual Waist : "+sWaistFilteredActual
		return true
	}


//--------------- added By Suprito after 12th March 2015
		
		def verifyJacketProductsDisplayed()
		{
			Thread.sleep(7000)
			((JavascriptExecutor) driver).executeScript("scroll(0,4500);")
			Thread.sleep(6000)
			((JavascriptExecutor) driver).executeScript("scroll(4500,9000);")
			Thread.sleep(7000)
			def jacketsProdSize = $("#container_results>li").size()
			println jacketsProdSize
			println $("#container_results").text()
			
			assert jacketsProdSize != 0
			return true
			
		}
		
		def js( String script ){
			(driver as JavascriptExecutor).executeScript( script )
		}
		
		
		def applyFiltersinWomanJacketsPage()
		{
			Thread.sleep(5000)
			
			// selecting filter option
			$(".dk_toggle").click()
			Thread.sleep(3000)
			driver.findElement(By.xpath(".//*[@id='dk_container_sort-select']/div/ul/li[5]/a")).click()
			Thread.sleep(7000)
			
			return true
			}
		
		def redirectionToPDP(){
			
			def jacketName= $(".name").text().toUpperCase()
			println jacketName
			$("#container_results>li:nth-child(1)>div:nth-child(1)>a>img").click()
			Thread.sleep(5000)
			def jacketInPDP= $(".title").text().toUpperCase()
			println jacketInPDP
			assert jacketInPDP.contains(jacketName)
			return true
		}
		def toPage(String headerIndex,String pageId) {
		interact {
			moveToElement($("body > div.header-fluid > header > header > ul > li:nth-child("+headerIndex+") > h2"))
		 //   $("#wJackets&VestsLink").click()
		}
	   $(pageId).click()
		Thread.sleep(3000)
	}
}

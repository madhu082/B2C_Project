package com.lsco.test.page.navigation

import geb.Page

import com.lsco.test.PropertyProvider
//added By Suprito after 12th March 2015
import org.openqa.selenium.*
class ClothingSweatersCategoryPage extends Page{
	
		static url = "category/men/clothing/sweaters/all"
	
		
		static content = {
			headline { $(".content > h1")}
		}
		
		static at = {
			String okValue = PropertyProvider.getInstance().getLocalizedPropertyValue("clothing.sweaters")
			assert $(".content > h1").text().toUpperCase() == okValue
			return true
		}
	
//		def toAllCollectionsPage(){
//			$("#shop-by-collection>a.cta.see-all").click()
//			//waitFor(30){ $("#main-container > article:nth-child(1) > header > article > div > h1").text() == "JEANS"}
//		}
		def selectSweaterItemTypeFilter(){
			interact {
				click($(".facet-links"))
			}
	
			waitFor(30, 10){
				$("#facet-itemType > ul > li:nth-child(3) > label:nth-child(1)").isDisplayed()
			}
			$("#facet-itemType > ul > li:nth-child(3) > label:nth-child(1)").click()
		}
		def validateProductAfterItemTypeFilters(){
			assert $("p.name").text().contains("SWEATER")
			return true			
		}

//---------------Suprito 12 th March 2015
		
		def applyFiltersSweatersPage()
		{
			Thread.sleep(5000)
			
			// selecting filter option
			$(".dk_toggle").click()
			Thread.sleep(3000)
			driver.findElement(By.xpath(".//*[@id='dk_container_sort-select']/div/ul/li[5]/a")).click()
			Thread.sleep(7000)
			
			return true
			}
		
		def js( String script ){
			(driver as JavascriptExecutor).executeScript( script )
		}
		
		def verifySweaterProductsDisplayed()
		{
			Thread.sleep(7000)
			((JavascriptExecutor) driver).executeScript("scroll(0,4500);")
			Thread.sleep(6000)
			((JavascriptExecutor) driver).executeScript("scroll(4500,9000);")
			Thread.sleep(7000)
			def sweaterProdSize = $("#container_results>li").size()
			println sweaterProdSize
			println $("#container_results").text()
			
			assert sweaterProdSize != 0
			return true
			
		}
		


}

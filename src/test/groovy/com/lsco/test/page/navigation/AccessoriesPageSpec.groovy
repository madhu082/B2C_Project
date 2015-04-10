package com.lsco.test.page.navigation

import com.lsco.test.page.CategoryItemModule
import com.lsco.test.page.FirstProductPage
import com.lsco.test.page.JeansFilterItemModule
import com.lsco.test.page.model.CategoryDataModel
import com.lsco.test.page.model.ProductDataModel
import com.lsco.test.page.model.ProductDataModelMap
import geb.Page
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver

class AccessoriesPageSpec extends Page{

	static at = {
        driver.currentUrl.contains("accessories")
		$("#main-container > article:nth-child(1) > header > article > div > h1").text().toUpperCase() == "ACCESSOIRES"
    }

	static content = {
		containerResults (required: false) {$("section#facets-products>ul>li")}
	}

    def checkProducts(){
       assert containerResults.size() >0
        return true
    }
    def toPDPPageOnClickingItem()
    {
        $(".product-images>a>img").click(FirstProductPage)
    }
	def toPDPPageOnClickingItem(int index)
	{
		$(".product-images>a>img",index ).click(FirstProductPage)

	}

}

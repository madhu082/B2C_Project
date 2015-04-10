package com.lsco.test.page.navigation

import com.lsco.test.PropertyProvider
import com.lsco.test.page.FirstProductPage

import geb.Page
//import com.google.api.translate.Language;
//import com.google.api.translate.Translate;

import org.openqa.selenium.WebDriver

class DockersMenShorts extends Page{

	static url = "category/men/clothing/itemtype/shorts"

	
	static content = {
		headline { $("header>article>div>h1")}
        containerResults (required: false) {$("section#facets-products>ul>li")}
	}
	
	static at = {
        driver.currentUrl.contains("/shorts")
        assert $("header>article>div>h1").text().toUpperCase() == PropertyProvider.getInstance().getLocalizedPropertyValue("MenShorts")
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
	def toPage(String headerIndex,String pageId) {
		interact {
			moveToElement($("body > div.header-fluid > header > header > ul > li:nth-child("+headerIndex+") > h2"))
		 //   $("#wJackets&VestsLink").click()
		}
	   $(pageId).click()
		Thread.sleep(3000)
	}
	
}

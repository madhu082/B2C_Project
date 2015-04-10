package com.lsco.test.page

import geb.Page

import com.lsco.test.PropertyProvider
import org.openqa.selenium.WebDriver

class ProductDetail_FitGuidePage extends Page{


	String getPageUrl() {
		String url = browser.config.rawConfig.baseUrl + "p/" + PropertyProvider.getInstance().getGeneralSitePropertyValue("FitGuideProduct")
		println url
		url
	}

	static content = { productForm {$("#pdp-buystack-form") }
		             title {(".title")}
	}

	static at = { title }
	def  NavigateBack(){
		driver.navigate().back()
		return true
	}
}

	


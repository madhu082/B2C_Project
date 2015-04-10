package com.lsco.test.page

import geb.Page

import org.apache.commons.lang.RandomStringUtils

import com.lsco.test.PropertyProvider
import com.lsco.test.page.login.LevisLoginPage
import com.lsco.test.page.model.ProductDataModelMap
import com.lsco.test.page.register.AccountRegistrationPage
import com.lsco.test.page.register.RegistrationDataModel

import org.openqa.selenium.WebDriver
import org.apache.commons.lang.math.RandomUtils


class LevisHomePageWithCookies extends Page {

	static url = ""
	
	static at = {		
		def okBrand = PropertyProvider.getInstance().getLocalizedPropertyValue("brand")
		assert title.toUpperCase().contains(okBrand)
		return true
	}

	static content = { pageData { js.pageData } }


	def verifyCookiePopup(boolean yesno){
		Thread.sleep(3000)
		assert $("div.email-signup").isDisplayed() == yesno
		return true
	}
	def verifyFuncLinksofCookiePopup(){
		Thread.sleep(4000)
		assert $("div.email-signup a[href*='/legal/terms-conditions/popup']").isDisplayed()
		$("div.email-signup a[href*='/legal/terms-conditions/popup']").click()
		Thread.sleep(3000)
		assert $("iframe[src*='/legal/terms-conditions/popup']").isDisplayed()
		$("div[title='Close']").click()
		Thread.sleep(4000)
		assert $("div.email-signup a[href*='/legal/privacy/popup']").isDisplayed()
		$("div.email-signup a[href*='/legal/privacy/popup']").click()
		Thread.sleep(3000)
		assert $("iframe[src*='/legal/privacy/popup']").isDisplayed()
		$("div[title='Close']").click()
		return true
	}
	def userSubscriptionChecking(){
		Thread.sleep(1000)		
		$("div.email-signup #link-email-popup").click()		
		assert $("div.email-signup #age-check-popup").isDisplayed()		
		$("div.email-signup label[for='links-privacy-terms-lightbox']").click()
		$("div.email-signup #link-email-popup").click()	
		Thread.sleep(1000)
		assert $("div.email-signup #email-fail-popup").isDisplayed()		
		def okBrand = PropertyProvider.getInstance().getLocalizedPropertyValue("username_pm")
		$("div.email-signup #input-email-popup").value(okBrand)
		$("div.email-signup #link-email-popup").click()
		Thread.sleep(3000)
		assert $("div.email-signup div.tooltip-wrapper>p").isDisplayed()
		$("div.email-signup span.close").click()		
		return true
	}
	def toPage(String headerIndex) {		
		$("body > div.header-fluid > header > header > ul > li:nth-child("+headerIndex+") >a").click()
		Thread.sleep(3000)
	}

	
}
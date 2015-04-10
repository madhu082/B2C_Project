
package com.lsco.test.page

import geb.Page

import org.openqa.selenium.JavascriptExecutor
import com.lsco.test.GBLevisSmoke
import spock.lang.Specification
import geb.spock.GebReportingSpec
import gherkin.formatter.model.Feature
import junit.framework.Test
import geb.spock.GebReportingSpec;
import geb.spock.GebSpec;
import geb.Browser
import com.lsco.test.page.register.AccountRegistrationPage
import com.lsco.test.page.login.LevisLoginPage
import com.lsco.test.page.login.LevisMyAccountPage
import com.lsco.test.page.LevisHomePage

import com.lsco.test.GBLevisSmoke
import com.lsco.test.DEDockersSmoke
import geb.*
import geb.spock.GebSpec;

import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.login.LevisMyAccountPage
import com.lsco.test.page.login.LevisLoginPage
import com.lsco.test.PropertyProvider

class EmailPage extends Page {
	
	static at = {
		$(".myaccount-top>h2").text().equalsIgnoreCase("Email options")
	}
	
	static content = {
		emailInput (wait: true){ $("#emailUnsubscribeForm").find("input", id: "email") }
		editoptionbtn {$(".btn-dbbdr")}
		
		}
	
	def okwithinputEmailId(String inputFld){	
		def okVal=	PropertyProvider.getInstance().getLocalizedPropertyValue(inputFld)
		emailInput.value(okVal)
		editoptionbtn.click()		
		Thread.sleep(4000)
		return true
	}
	def updateinfoPageCheck(){
		$("#check>h3").displayed
		return true
	}
	
	def clicksave(){		
		driver.switchTo().frame("manageEmailOptsIframe")
		Thread.sleep(1000)
		$("input", 18).click()
		//$("#userDetails>input[type='image']").click()
		Thread.sleep(4000)
		return true
	}
	
	def verifyerrmsg(String errMsgType){
		Thread.sleep(1000)
		println($(".js-form-warning.error").text().trim())
		println($(".desc.js-msg").text().trim())
		assert ($(".js-form-warning.error").text().trim().equalsIgnoreCase(PropertyProvider.getInstance().getLocalizedPropertyValue("negativeoldpwErrorMsg")))
		assert ($(".desc.js-msg").text().trim().equalsIgnoreCase(PropertyProvider.getInstance().getLocalizedPropertyValue(errMsgType)))
		Thread.sleep(3000)
		return true
		}
	
	def blankinfosaveerrmsg(){	
		Thread.sleep(1000)	
		println($("div#errorTxt").text().trim())
		println(PropertyProvider.getInstance().getLocalizedPropertyValue("blankinfosavetypeErrMSG"))		
		Thread.sleep(1000)
		return true
	}
	}
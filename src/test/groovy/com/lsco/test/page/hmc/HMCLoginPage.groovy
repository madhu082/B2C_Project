package com.lsco.test.page.hmc

import geb.Page

import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.*

class HMCLoginPage extends Page {


	String getPageUrl() {
		def url = browser.config.rawConfig.basicUrl + "/hmc/hybris"
		if(browser.config.rawConfig.basicUrl.contains("preprod")){
			String sBaseUrl=browser.config.rawConfig.basicUrl
			sBaseUrl=sBaseUrl.replaceAll("web", "store")+ ":9001"
			//println(sBaseUrl)
			url=sBaseUrl+ "/hmc/hybris"
		}
		println(url)
		url
	}

	/*static at = {
		assert title == "Login - hybris Management Console (hMC)"
	}*/
	static at = { waitFor(20){title} == "Login - hybris Management Console (hMC)" }
	

	def fillHybrisLoginFields(username,password) {
		$("#Main_user").value(username)
		$("#Main_password").value(password)

		assert $("#Main_user").value() == username
		assert $("#Main_password").value() == password
		$("#Main_a").click()
		return true
	}
def clickOnTDWithText(menu){
		def temp=menu.split(";")
		for(int i=0;i<temp.size();i++){
			waitFor(20){$("a",text:contains(temp[i]))}
			$("a",text:contains(temp[i])).click()
		}
		return true
	}
	def clickSearch(){
		$("a",text:contains("Search")).click()
	}
	def selectType(val){
		$(".pageContext > select",0).value(val);
	}
	
	def setIdentifier(val){
		$("input",0,type:"text").value(val)
	}
	def setTitle(val){
		$("input",1,type:"text").value(val)
	}
	def clickSearchButton(){
		$("span",text:contains("Search")).click()
	}
	def clickResult(){
		Actions ac=new Actions(driver)
		ac.doubleClick($('.listtable.selecttable tr',1).firstElement()).build().perform()
			
	}
	def setCheckboxEnabled(val){
		waitFor(20){$("input",type:"checkbox")}
		if(val=="OFF"){
			if(driver.findElements(By.xpath("//div[@class='booleanEditorChip']/input[@value='true']/../input[2]")).size()==1)
			{	//if($("input",type:"checkbox").previous().@value=="true")
				//$("input",type:"checkbox").click()
				driver.findElement(By.xpath("//div[@class='booleanEditorChip']/input[@value='true']/../input[2]")).click()
				Thread.sleep(2000)
			}
		}else
		{
			if(driver.findElements(By.xpath("//div[@class='booleanEditorChip']/input[@value='false']/../input[2]")).size()==1)
			{	//if($("input",type:"checkbox").previous().@value=="false")
				//$("input",type:"checkbox").click()
				driver.findElement(By.xpath("//div[@class='booleanEditorChip']/input[@value='false']/../input[2]")).click()
				Thread.sleep(2000)
			}
		}
	}
	def clickSave(){
		
	Thread.sleep(1000)
	$("td div.label",text:"Save").click()
	Thread.sleep(2000)
	return true
	}
	def closeSession()
	{
		$("a",title:"Close Session").click()
		return true
	}
	def expandSearchOption(){
		$(".component:nth-child(1) .pinIcon .chip-event").click()
	}
	
	def expandSearchResult(yesno){
		if(yesno=="Yes"){
			if($(".component:nth-child(2) .arrowIcon .chip-event a.expand").isDisplayed()==true){
				$(".component:nth-child(2) .arrowIcon .chip-event a").click()
				println("Expanded")
			}
			
		}else{
			if($(".component:nth-child(2) .arrowIcon .chip-event a.expand").isDisplayed()==false){
				$(".component:nth-child(2) .arrowIcon .chip-event a").click()
				println("Clopsed")
			}
		}
		
	}
	
	def searchPromoStatus(value){
		waitFor(2000){$(".row:nth-child(4)>.comparator select")}
		$(".row:nth-child(4)>.comparator select").click()
		Select enableChk= new Select(driver.findElement(By.xpath("//select[@id='Content/GenericCondition[AbstractPromotion.enabled][operator]_select']")))
		enableChk.selectByValue(value)
		Thread.sleep(5000)
	}
	
	def disableAllOtherPromotions(){
	searchPromoStatus("1")
	clickSearchButton()
	Thread.sleep(10000)
	def allenebledpromotions= $("div.olcResultList .listtable.selecttable tr").size()
		for(int i=2;i<=allenebledpromotions;i++){
			Actions ac=new Actions(driver)
			ac.doubleClick($("div.olcResultList .listtable.selecttable tr:nth-child("+i+")").firstElement()).build().perform()
			Thread.sleep(4000)
			setCheckboxEnabled("OFF")
			clickSave()
			Thread.sleep(4000)
			expandSearchResult("Yes")
			Thread.sleep(4000)
		}
		return true
	}
	
	
}
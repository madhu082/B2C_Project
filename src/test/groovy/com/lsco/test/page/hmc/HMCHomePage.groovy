package com.lsco.test.page.hmc

import com.lsco.test.PropertyProvider
import com.lsco.test.page.model.UserDataModel
import com.lsco.test.page.model.UserDataModelMap
import geb.Page
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.*

public class HMCHomePage extends Page {

	static at = {
		assert $(".page-header-left").text().trim().contains("/master")
		//assert $(".page-header-left").text().trim() == "Explorer: levi.team@[n/a]/master"
	}
	

	String getPageUrl() {
		def url = browser.config.rawConfig.basicUrl + "/hmc/hybris"
		url
	}

	def deleteUser(id) {
		$("#Tree\\/GenericExplorerMenuTreeNode\\[user\\]_label").click()
		$("#Tree\\/GenericLeafNode\\[Customer\\]_label").click()
		$("#Content\\/StringEditor\\[in\\ Content\\/GenericCondition\\[Customer\\.uid\\]\\]_input").value(id)
		$(".xp-button > a:nth-child(1)").click()
		interact {
			doubleClick($(".button-on-white > a:nth-child(1)"))
		}
		$("#Content\\/OrganizerItemChip\\\$2\\[organizer\\.editor\\.delete\\.title\\]\\[1\\]_label").click()
	}

	def deleteRandomUserInHybris() {
		deleteUser(browser.config.rawConfig.site+ "-" +(UserDataModelMap.getInstance().getUserMap().get("NEW_USER_DATA").getEmail()).toString().toLowerCase())
		UserDataModelMap.getInstance().getUserMap().clear()
	}

	def submitLoginForm() {
		$("#console").click()
	}
	
	def clickOnTDWithText(String menu){
		
		def temp=menu.split("_")
		for(int i=0;i<temp.size();i++){
			println(temp[i])
			waitFor(200){$("a",text:contains(temp[i]))}
			$("a",text:contains(temp[i])).click()
		}
	}
	
	def clickOnTd(String menu){
		
			println(menu)
			waitFor(200){$("a",text:contains(menu))}
			$("a",text:contains(menu)).click()
		
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
		//$("span",text:contains("Search")).click()
		waitFor(2000){$(".xp-button.chip-event>a")}
		$(".xp-button.chip-event>a").click()
	}
	def clickResult(){
		Actions ac=new Actions(driver)
		ac.doubleClick($('.listtable.selecttable tr',1).firstElement()).build().perform()
			
	}
	
	def setCheckboxEnabled(val){
/*		waitFor(20){$("input",type:"checkbox")}
		if(val=="OFF"){
			if($("input",type:"checkbox").@value=="true"){
			$("input",type:"checkbox").click()
			}
		}else{
			if($("input",type:"checkbox").@value=="false"){
			$("input",type:"checkbox").click()
			}
		}*/
		waitFor(20){$("input",type:"checkbox")}
		if(val=="OFF"){
			String val1= true
			if(driver.findElements(By.xpath("//div[@class='booleanEditorChip']/input[1][@value='"+val1+"']")).size()==1){
				$("input",type:"checkbox").click()
			}
		}else{
			String val2= false
			if(driver.findElements(By.xpath("//div[@class='booleanEditorChip']/input[1][@value='"+val2+"']")).size()==1){
				$("input",type:"checkbox").click()
			}
		}
		Thread.sleep(2000)
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
def expandSearchResult(){
	$(".component:nth-child(4) .pinIcon .chip-event").click()
}

}
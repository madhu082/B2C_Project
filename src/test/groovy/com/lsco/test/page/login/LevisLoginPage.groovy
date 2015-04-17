package com.lsco.test.page.login

import com.lsco.test.page.model.UserDataModel
import com.lsco.test.page.model.UserDataModelMap

import geb.Page

import com.lsco.test.PropertyProvider

import org.apache.commons.lang.RandomStringUtils
import org.apache.commons.lang.math.RandomUtils
import org.openqa.selenium.*
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.internal.FindsByXPath
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions

class LevisLoginPage extends Page {

	//static url = "login"

	static at = {
		def okValue = PropertyProvider.getInstance().getLocalizedPropertyValue("my.account")
		$("#main-container > div > article > h1").text().toUpperCase() == okValue
	}
	
	//-------added by Suprito 19th Feb 2015-------------------------
	static content = {
		usernameLevi { $("#loginForm").find("input", id: "j_username")}
		passwordLevi { $("#loginForm").find("input", id: "j_password")}
		
		loginForm  { $("#loginForm") }
		
		//---------------------------Suprito 6th March 2015
		marketingEmailTxtBox { $("#emailUnsubscribeForm").find("input", id: "email")}
	
	}
	
	//-------------------------------------------------------------------------------------
	
	def fillLoginFields(username,password) {
		$("#loginForm").j_username = username
		$("#loginForm").j_password = password
		
		assert $("#loginForm").j_username == username
		assert $("#loginForm").j_password == password
	}
	
	def submitLoginForm() {
		$("#link-signin").click()
		Thread.sleep(2000)
		return true
	}
	
	def signInWithFacebook() {
		$("button", text:'Sign in with Facebook').click()
	}

	def signUpAsRegisteredRandomUser(){
		def email=UserDataModelMap.getInstance().getUserMap().get("NEW_USER_DATA").getEmail().toLowerCase()
		def password=UserDataModelMap.getInstance().getUserMap().get("NEW_USER_DATA").getPassword()
		fillLoginFields(email,password)
	}
	
	
	//Added by Suprito 19th Feb 2015##########################################
	
	def leviLogin() {
		
		def emailLevi = PropertyProvider.getInstance().getLocalizedPropertyValue("emailId")
		usernameLevi.value(emailLevi)
		
		println emailLevi
		
		def pwdLevi = PropertyProvider.getInstance().getLocalizedPropertyValue("passwordLogin")
		passwordLevi.value(pwdLevi)
		}
	def submitLoginFormButton(){
		$("#link-signin").click()
		return true
	}
	def submitLoginFormButtonWithPopupCheck(){
		$("#link-signin").click()
		Thread.sleep(5000)
		driver.navigate().refresh()
		
		Thread.sleep(4000)
		
		if ($("#dismiss-btn").isDisplayed())
		
		{	
			$("#dismiss-btn").click()
		}		
		else{
			println "pop-up not present."
		}
		return true
	}
	
	def FillLoginFields() {
		def okValue1 = PropertyProvider.getInstance().getLocalizedPropertyValue("username_dipa")
		usernameLevi.value(okValue1)
		def okValue2 = PropertyProvider.getInstance().getLocalizedPropertyValue("password")
		passwordLevi.value(okValue2)
		
	}
	def FillLoginFieldsToCheckOrderHistory() {
		def okValue1 = PropertyProvider.getInstance().getLocalizedPropertyValue("usernameOrderHistory")
		usernameLevi.value(okValue1)
		def okValue2 = PropertyProvider.getInstance().getLocalizedPropertyValue("pwOrderHistory")
		passwordLevi.value(okValue2)
		
	}
	
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	
	//Suprito--6thMarch 2015--------------
	
	def selectEmailOptions(){
		
	Thread.sleep(3000)
			
		$("#EmailOptionLink").click()
	
	Thread.sleep(2000)
	
	return true
	}
	
	
	def marketingEmailOptInDockersGB(){
		Thread.sleep(3000)
		// entering random email id under Email Options section
	String Randomresult4 = RandomStringUtils.random(64, false, true);
			Randomresult4 = RandomStringUtils.random(32, 0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray())
			marketingEmailTxtBox.value(Randomresult4+"@gmail.com")
			
			// clicking on Edit Options Button
			$(".btn-dbbdr").click()

			Thread.sleep(3000)
			// checking all the checkboxes under send me Dockers News and Offers
			driver.switchTo().frame("manageEmailOptsIframe")
			Thread.sleep(2000)
			def emailTxt = $("#check>table>tbody>tr:nth-child(1)>td:nth-child(2)").text()
			println emailTxt
			
			
			$("input#New_Arrivals").click()
			$("input#Men").click()
			$("input#Promotions_Sales").click()
			$("input#Social_Campaigns").click()
			$("input#Brand_Campaign_Events").click()
			$("input#Brand_Messages").click()
			
			Thread.sleep(2000)
			//click on save button
			$("input[type='image']").click()
			driver.switchTo().defaultContent()
			Thread.sleep(5000)
			WebDriverWait wait=new WebDriverWait(driver,20)
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("manageEmailOptsIframe")))
		
			waitFor(50){$("#content").displayed}
			//verifying the succesful optin message
			def cnfrmMsg = $("#content").text()
			assert cnfrmMsg.contains("Your preferences have been saved")
			
			driver.switchTo().defaultContent()
			Thread.sleep(4000)
			// again verifying whether the checkboxes are checked for the same email id
			$("#EmailOptionLink").click()
			Thread.sleep(8000)
			marketingEmailTxtBox.value(emailTxt)
			$(".btn-dbbdr").click()
			Thread.sleep(7000)
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("manageEmailOptsIframe")))
			Thread.sleep(2000)
			println $("input#New_Arrivals").attr('checked')
		assert $("input#New_Arrivals").attr('checked') == "true"
		assert $("input#Men").attr('checked') == "true"
		assert $("input#Promotions_Sales").attr('checked') == "true"
		assert $("input#Social_Campaigns").attr('checked') == "true"
		assert $("input#Brand_Campaign_Events").attr('checked') == "true"
		assert $("input#Brand_Messages").attr('checked') == "true"
	
		return true
			
	}
	
	def marketingEmailOptOutDockersGB(){
		Thread.sleep(3000)
		// entering random email id under Email Options section
	String Randomresult5 = RandomStringUtils.random(64, false, true);
			Randomresult5 = RandomStringUtils.random(32, 0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray())
			marketingEmailTxtBox.value(Randomresult5+"@gmail.com")
			
			// clicking on Edit Options Button
			$(".btn-dbbdr").click()

			Thread.sleep(3000)
			// checking all the checkboxes under send me Dockers News and Offers
			driver.switchTo().frame("manageEmailOptsIframe")
			Thread.sleep(2000)
			def emailTxt1 = $("#check>table>tbody>tr:nth-child(1)>td:nth-child(2)").text()
			println emailTxt1
			$("input#New_Arrivals").click()
			$("input#Men").click()
			$("input#Promotions_Sales").click()
			$("input#Social_Campaigns").click()
			$("input#Brand_Campaign_Events").click()
			$("input#Brand_Messages").click()
			Thread.sleep(2000)
			//click on save button
				Thread.sleep(2000)
			if($("input[type='image']").displayed)
			$("input[type='image']").click()
			Thread.sleep(5000)
			//verifying the succesful optin message
		driver.switchTo().defaultContent()
			Thread.sleep(5000)
			WebDriverWait wait=new WebDriverWait(driver,20)
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("manageEmailOptsIframe")))
			waitFor(100){$("#content>span").text()!=null}
			def cnfrmMsg = $("#content>span").text()
			assert cnfrmMsg.contains("Your preferences have been saved")
			
			driver.switchTo().defaultContent()
			Thread.sleep(4000)
			// Now Un-checking the checkboxes doing the Opt-Out Flow
			$("#EmailOptionLink").click()
			Thread.sleep(8000)
			marketingEmailTxtBox.value(emailTxt1)
			$(".btn-dbbdr").click()
			Thread.sleep(7000)
			driver.switchTo().frame("manageEmailOptsIframe")
			Thread.sleep(2000)
						$("#Unsubscribe").click()
												waitFor(20){$("input[type='image']")}
						$("input[type='image']").click()
						driver.switchTo().defaultContent()
						Thread.sleep(5000)
				
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("manageEmailOptsIframe")))
						
						waitFor(50){$("#content")}
						def optOutMsg = $("#content").text()

			assert optOutMsg.contains("You will no longer receive")
			driver.switchTo().defaultContent()
			Thread.sleep(5000)
// Now verifying whether the checkboxes are unchecked by re-accesing the email id
						$("#EmailOptionLink").click()
			Thread.sleep(8000)
			marketingEmailTxtBox.value(emailTxt1)
			$(".btn-dbbdr").click()
			Thread.sleep(7000)
			driver.switchTo().frame("manageEmailOptsIframe")
			Thread.sleep(2000)
					   println $("input#New_Arrivals").attr('checked')
		assert $("input#New_Arrivals").attr('checked') != "true"
		assert $("input#Men").attr('checked') != "true"
		assert $("input#Promotions_Sales").attr('checked') != "true"
		assert $("input#Social_Campaigns").attr('checked') != "true"
		assert $("input#Brand_Campaign_Events").attr('checked') != "true"
		assert $("input#Brand_Messages").attr('checked') != "true"
	return true
			
	}
	
	
	def marketingEmailOptInLevisGB(){
		Thread.sleep(3000)
		// entering random email id under Email Options section
	String Randomresult6 = RandomStringUtils.random(64, false, true);
			Randomresult6 = RandomStringUtils.random(32, 0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray())
			marketingEmailTxtBox.value(Randomresult6+"@gmail.com")
			
			// clicking on Edit Options Button
			$(".btn-dbbdr").click()

			Thread.sleep(3000)
			// checking all the checkboxes under send me Dockers News and Offers
			driver.switchTo().frame("manageEmailOptsIframe")
			Thread.sleep(2000)
			def emailTxt2 = $("#check>table>tbody>tr:nth-child(1)>td:nth-child(2)").text()
			println emailTxt2
			
			
			$("input#Men").click()
			$("input#New_Arrivals").click()
			$("input#Women").click()
			$("input#Promotions_Sales").click()
			$("input#Kids_Baby").click()
			$("input#Social_Campaigns").click()
						$("input#Brand_Campaign_Events").click()
			$("input#Brand_Messages").click()
			
			Thread.sleep(4000)
			//click on save button
			$("input[type='image']").click()
			
			Thread.sleep(7000)
			//verifying the succesful optin message
				driver.switchTo().defaultContent()
			Thread.sleep(5000)
			WebDriverWait wait=new WebDriverWait(driver,20)
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("manageEmailOptsIframe")))
			waitFor(100){$("#content")}
			def cnfrmMsg = $("#content").text()
			assert cnfrmMsg.contains("Your preferences have been saved")
			
			driver.switchTo().defaultContent()
			Thread.sleep(4000)
			// again verifying whether the checkboxes are checked for the same email id
			$("#EmailOptionLink").click()
			Thread.sleep(8000)
			marketingEmailTxtBox.value(emailTxt2)
			$(".btn-dbbdr").click()
			Thread.sleep(7000)
			driver.switchTo().frame("manageEmailOptsIframe")
			Thread.sleep(2000)
			println $("input#New_Arrivals").attr('checked')
		assert $("input#Men").attr('checked') == "true"
		assert $("input#New_Arrivals").attr('checked') == "true"
		assert $("input#Women").attr('checked') == "true"
		assert $("input#Promotions_Sales").attr('checked') == "true"
		assert $("input#Kids_Baby").attr('checked') == "true"
		assert $("input#Social_Campaigns").attr('checked') == "true"
				assert $("input#Brand_Campaign_Events").attr('checked') == "true"
				assert $("input#Brand_Messages").attr('checked') == "true"
	
		return true
			
	}
	
	
	def marketingEmailOptOutLevisGB(){
		Thread.sleep(3000)
		// entering random email id under Email Options section
	String Randomresult6 = RandomStringUtils.random(64, false, true);
			Randomresult6 = RandomStringUtils.random(32, 0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray())
			marketingEmailTxtBox.value(Randomresult6+"@gmail.com")
			
			// clicking on Edit Options Button
			$(".btn-dbbdr").click()

			Thread.sleep(3000)
			// checking all the checkboxes under send me Dockers News and Offers
			driver.switchTo().frame("manageEmailOptsIframe")
			Thread.sleep(2000)
			def emailTxt3 = $("#check>table>tbody>tr:nth-child(1)>td:nth-child(2)").text()
			println emailTxt3
				
			$("input#Men").click()
			$("input#New_Arrivals").click()
			$("input#Women").click()
			$("input#Promotions_Sales").click()
			$("input#Kids_Baby").click()
			$("input#Social_Campaigns").click()
			$("input#Brand_Campaign_Events").click()
			$("input#Brand_Messages").click()
			

			Thread.sleep(2000)
			//click on save button
			$("input[type='image']").click()
			
				Thread.sleep(1000);
		driver.switchTo().defaultContent()
			Thread.sleep(5000)
			WebDriverWait wait=new WebDriverWait(driver,20)
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("manageEmailOptsIframe")))
			waitFor(100){$("#content")}
			waitFor{$("#content>span").isDisplayed()}
			//verifying the succesful optin message
			def cnfrmMsg = $("#content").text()
			assert cnfrmMsg.contains("Your preferences have been saved")
			
			driver.switchTo().defaultContent()
			Thread.sleep(4000)
			// Now Un-checking the checkboxes doing the Opt-Out Flow
			$("#EmailOptionLink").click()
			Thread.sleep(8000)
			marketingEmailTxtBox.value(emailTxt3)
			$(".btn-dbbdr").click()
			Thread.sleep(7000)
			driver.switchTo().frame("manageEmailOptsIframe")
			Thread.sleep(2000)
			$("#Unsubscribe").click()
			$("input[type='image']").click()
			Thread.sleep(5000)
			driver.switchTo().defaultContent()
			Thread.sleep(5000)
			
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("manageEmailOptsIframe")))
			waitFor(100){$("#content")}
			def optOutMsg = $("#content").text()
			assert optOutMsg.contains("You will no longer receive")
			Thread.sleep(2000)
			driver.switchTo().defaultContent()
			Thread.sleep(5000)
// Now verifying whether the checkboxes are unchecked by re-accesing the email id
						$("#EmailOptionLink").click()
			Thread.sleep(8000)
			marketingEmailTxtBox.value(emailTxt3)
			$(".btn-dbbdr").click()
			Thread.sleep(7000)
			driver.switchTo().frame("manageEmailOptsIframe")
			Thread.sleep(2000)
					   println $("input#New_Arrivals").attr('checked')
		assert $("input#Men").attr('checked') != "true"
		assert $("input#New_Arrivals").attr('checked') != "true"
		assert $("input#Women").attr('checked') != "true"
		assert $("input#Promotions_Sales").attr('checked') != "true"
		assert $("input#Kids_Baby").attr('checked') != "true"
		assert $("input#Social_Campaigns").attr('checked') != "true"
				assert $("input#Brand_Campaign_Events").attr('checked') != "true"
				assert $("input#Brand_Messages").attr('checked') != "true"
	return true
			
	}
	
	
	def marketingEmailOptInFlowDockersGB(){
			
		//$("input", type:"checkbox", name:"New_Arrivals").value(true)
			//$("input[name='New_Arrivals']")
			Thread.sleep(2000)
			//$("#Promotions_Sales").attr("value",true)
			//$("input[id='namedattr_New_Arrivals']").click()
			
			//$('input.checkbox').attr('checked',true);
			
			//$("#Social_Campaigns").value(true)
			//$("input#Brand_Campaign_Events&&type='checkbox'").value(1)
			
			//println $("input#Brand_Campaign_Events").text()
			waitFor(100){$("#Brand_Messages")}
			$("#Brand_Messages").click()
			Thread.sleep(4000)
			
		// clicking on save button
			$("input[type='image']").click()
			
			Thread.sleep(3000)
			
			return true
	}
	
	def FillLoginCredentials() {
		def okValue1 = PropertyProvider.getInstance().getLocalizedPropertyValue("username_pm")
		usernameLevi.value(okValue1)
		def okValue2 = PropertyProvider.getInstance().getLocalizedPropertyValue("password_pm")
		passwordLevi.value(okValue2)
		
	}
	
	def clickIAgree(){
		Thread.sleep(4000)
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
		//.click()
		//Thread.sleep(1000)
		$("input.btn-dbbdr").click()
		Thread.sleep(1000)
		assert $(".desc.js-msg").isDisplayed()
		return true
		
	}

	//-------------------added By Suprito after 12th March 2015------------
	
	def loginForProfileNegativeFlows() {
		
		def emailLevi = PropertyProvider.getInstance().getLocalizedPropertyValue("usernameValue")
		usernameLevi.value(emailLevi)
		
		println emailLevi
		
		def pwdLevi = PropertyProvider.getInstance().getLocalizedPropertyValue("passwordValue")
		passwordLevi.value(pwdLevi)
		}
	

	def localizationLanguageVerification()
	{
		// going to profile section and checking the language of the text under profile section
		Thread.sleep(5000)
		$("#ProfileLink").click()
		Thread.sleep(7000)
		def profileText = PropertyProvider.getInstance().getLocalizedPropertyValue("profileSectionTxt").toUpperCase()
		def profileScreenTxt = $(".myaccount-top>p").text().toUpperCase()
		assert profileText == profileScreenTxt
		// going to address section and verifying the language of the text under address section
		$("#AddressLink").click()
		Thread.sleep(7000)
		def addressText = PropertyProvider.getInstance().getLocalizedPropertyValue("addressSectionTxt").toUpperCase()
		def addressScreenTxt = $(".btn-dbbdr").text().toUpperCase()
		assert addressText == addressScreenTxt
		//going to email options section and verifying the language of the text under email options section
		$("#EmailOptionLink").click()
		Thread.sleep(7000)
		def emailText = PropertyProvider.getInstance().getLocalizedPropertyValue("emailOptionsTxt").toUpperCase()
	    def emailScreenTxt=	$(".myaccount-top>p").text().toUpperCase()
	    assert emailScreenTxt.contains(emailText)
		String Randomresult7 = RandomStringUtils.random(64, false, true);
		Randomresult7 = RandomStringUtils.random(32, 0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray())
		marketingEmailTxtBox.value(Randomresult7+"@gmail.com")
		$(".btn-dbbdr").click()
		Thread.sleep(9000)
		driver.switchTo().frame("manageEmailOptsIframe")
	    def updateEmailScreentxt = $("#check>table>tbody>tr:nth-child(1)>td:nth-child(1)").text().toUpperCase()
		def emailEditOptionsText = PropertyProvider.getInstance().getLocalizedPropertyValue("emaileditTxt").toUpperCase()
		assert updateEmailScreentxt == emailEditOptionsText
		driver.switchTo().defaultContent()
		Thread.sleep(3000)
		//goint to password section and verifying the language of the text under password section
		$("#PasswordLink").click()
		Thread.sleep(7000)
		def passwordText = PropertyProvider.getInstance().getLocalizedPropertyValue("passwordSectionTxt").toUpperCase()
		def passwordScreentxt = $(".myaccount-top>p").text().toUpperCase()
		assert passwordText == passwordScreentxt
		//going to the orders section and verifying the language of the text under orders section
		$("#OrdersLink").click()
		Thread.sleep(7000)
		def ordersText = PropertyProvider.getInstance().getLocalizedPropertyValue("ordersTxt").toUpperCase()
		def ordersScreentxt = $(".myaccount-top>p").text().toUpperCase()
		assert ordersText == ordersScreentxt
		return true}
		
	//-----------------------added By Suprito after 31 st March 2015
	
		  def dockersOptInNegativeFlow()
		  {   //Enter invalid email address in numeric
			  Thread.sleep(8000)
			  def incorretMail = PropertyProvider.getInstance().getLocalizedPropertyValue("wrngUsrNamVal")
			  marketingEmailTxtBox.value(incorretMail)
			  $(".btn-dbbdr").click()
			  Thread.sleep(3000)
			  // verifying the error Message
			  boolean errorBox = $(".form-error-box.error.js-error").isDisplayed()
			  assert errorBox == true
			  //verifying that email field shows the error message Txt for invalid email
			  boolean  errorClass = $("#emailUnsubscribeForm").find("input", id: "email").hasClass( "warning" )
			  assert errorClass == true
			  // verifying Update Your Information Form is not displayed
			  boolean updateYourInfoNeg = $("#check>h3").isDisplayed()
			  assert updateYourInfoNeg == false
			  
			  // entering Correct Email Address
			  String Randomresult6 = RandomStringUtils.random(64, false, true);
			  Randomresult6 = RandomStringUtils.random(32, 0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray())
			  marketingEmailTxtBox.value(Randomresult6+"@gmail.com")
			  $(".btn-dbbdr").click()
			  Thread.sleep(8000)
			  driver.switchTo().frame("manageEmailOptsIframe")
			  //verifying Update Your Information Form Is Displayed
			  waitFor{$("#check>h3").isDisplayed()}
			  boolean updateYourInfo = $("#check>h3").isDisplayed()
			  assert updateYourInfo == true
			  // click on Save button without doing any updates/changes
			  $("input[type='image']").click()
			  Thread.sleep(6000)
			  // Error message is displayed
			def errorMsgScrntxt =  $("#errorTxt").text().toUpperCase()
			def msgIncorrect = PropertyProvider.getInstance().getLocalizedPropertyValue("errMsgge").toUpperCase()
			assert errorMsgScrntxt == msgIncorrect
			driver.switchTo().defaultContent()
			return true
		  }
	
		  def FillLoginCredentials1(String username,String password) {
			  def okValue1 = PropertyProvider.getInstance().getLocalizedPropertyValue(username)
			  usernameLevi.value(okValue1)
			  def okValue2 = PropertyProvider.getInstance().getLocalizedPropertyValue(password)
			  passwordLevi.value(okValue2)
		  }




}

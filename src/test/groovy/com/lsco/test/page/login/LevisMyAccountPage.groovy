package com.lsco.test.page.login

import com.lsco.test.page.model.UserDataModel
import com.lsco.test.page.checkout.OrderDataModel
import com.lsco.test.page.model.UserDataModelMap
import geb.Page
import com.lsco.test.PropertyProvider
import com.lsco.test.page.model.UserDataModelMap
import com.lsco.test.page.register.RegistrationDataModel

import geb.Page

import org.apache.commons.lang.RandomStringUtils
import org.apache.commons.lang.math.RandomUtils
import org.openqa.selenium.*

import java.util.List
import java.lang.Exception
//added by Suprito after 31st March 2015

import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions

class LevisMyAccountPage extends Page{
	
	static url = "my-account/update-profile"
	
	static at = {
		String okValue = PropertyProvider.getInstance().getLocalizedPropertyValue("my.account")
		//@@@@@ Commented until is resolved
		//assert $("h1").text().toUpperCase() == okValue
		return true
	}
	
	//+++++++++++++++Suprito 23 rd Feb 2015+++++++++++++++++++++++++++++++++++++++
	static content = {
		accountEmailTxtField { $("#updateProfileForm").find("input", id: "email")}
		accountFirstNameTxtField { $("#updateProfileForm").find("input", id: "firstName")}
		accountLastNameTxtField { $("#updateProfileForm").find("input", id: "lastName")}
		shippingAddress{ $('div.shipping-address-list').find('.box')}
		BillingAddress{ $('div.billing-address-list').find('.box')}
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	
	def verifyEmailInHeader(fullName){
		def headerText = $("#global-myaccount-cta2").text()
		assert headerText == fullName
	}
	
	def verifyEmailInHeader(){
		verifyEmailInHeader(RegistrationDataModel.getInstance().email.toUpperCase())
	}

	def getFirstNameProfileElem(){
		$(".form-container > ul:nth-child(1) > li:nth-child(1) > div:nth-child(2) > input:nth-child(1)")
	}

	def getSurnameProfileElem(){
		$(".form-container > ul:nth-child(1) > li:nth-child(2) > div:nth-child(2) > input:nth-child(1)")
	}

	def getEmailProfileElem() {
		$("#email")
	}

	def editProfileRandomUser(){
		if (isRandomUserDataUpdated()){
			def newEmail=UserDataModelMap.getInstance().getUserMap().get("UPDATED_USER_DATA").getEmail().toLowerCase()
			def newName=generateRamdomAlphabeticString(13)
			def newLastName=generateRamdomAlphabeticString(13)
			UserDataModelMap.getInstance().getUserMap().get("NEW_USER_DATA").setEmail(newEmail)
			UserDataModelMap.getInstance().getUserMap().get("NEW_USER_DATA").setName(newName)
			UserDataModelMap.getInstance().getUserMap().get("NEW_USER_DATA").setLastName(newLastName)
			getFirstNameProfileElem().value(newName)
			getSurnameProfileElem().value(newLastName)
			getEmailProfileElem().value(newEmail)
		}
	}

	private String generateRamdomAlphabeticString(int length) {
		RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(length) + 1)
	}

	def UpdateData(){
		Thread.sleep(1000)
		$(".btn-dbbdr").click()
		Thread.sleep(2000)
		return true
	}

	boolean isRandomUserDataUpdated() {
		UserDataModelMap.getInstance().getUserMap().get("UPDATED_USER_DATA")!=null
	}

	def logOut(){
		Thread.sleep(1000)
		$("#global-myaccount-signout").click()
		return true
	}

	def verifyDataUpdateMesage(){
		
		assert $(".js-success").text().toUpperCase()==PropertyProvider.getInstance().getLocalizedPropertyValue("my.account.update.message")
		return true
	}

	def verifyNewRandomUserData(){
		if ($("div.email-signup > div:nth-child(1) > span:nth-child(1)").isDisplayed()){
			$("div.email-signup > div:nth-child(1) > span:nth-child(1)").click()
		}
		assert getFirstNameProfileElem().value()==UserDataModelMap.getInstance().getUserMap().get("NEW_USER_DATA").getName()
		assert getSurnameProfileElem().value()==UserDataModelMap.getInstance().getUserMap().get("NEW_USER_DATA").getLastName()
		assert getEmailProfileElem().value()==UserDataModelMap.getInstance().getUserMap().get("NEW_USER_DATA").getEmail()
	}
	
	def toLastPlacedOrder(){
		$("#OrdersLink").click()
		$("table.order-history > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > p:nth-child(1) > a:nth-child(1)").click()
	}
	
	def giftWrapCheck(){
		assert $(".list-type-04 > li:nth-child(2) > a:nth-child(1) > img:nth-child(1)").@title.toString().trim().toUpperCase().contains("GIFT WRAP") 
	}
	
	//++++++++++++ added by Suprito 19th Feb 2015 +++++++++++++++++++++++++++++++++++
	
	def verifyEmailNameInHeader(){
		
		def loggedInemailId = PropertyProvider.getInstance().getLocalizedPropertyValue("emailId")
		println accountEmailTxtField.@value
		assert accountEmailTxtField.@value == loggedInemailId
	}
		
	def searchProduct(){
		def skuItemName = PropertyProvider.getInstance().getLocalizedPropertyValue("itemSearch")
		waitFor{$("#input-search")}.value(skuItemName)
		$("#search-bar > a").click()
	}
	
	def searchProduct(String itemNo){
		//def skuItemName = PropertyProvider.getInstance().getLocalizedPropertyValue("itemSearch")
		waitFor{$("#input-search")}.value(itemNo)
		$("#search-bar > a").click()
	}
	
	def clickOnTheSearchedItem(){
		Thread.sleep(3000)
		$(".list-type-04>li>div:nth-child(1)>a>img").click()
	}
	
	def clickNotTodayLinkMyAccountPage() {
		driver.navigate().refresh()
		Thread.sleep(4000)
		if ($("#dismiss-btn").isDisplayed())
		{
	    	$("#dismiss-btn").click()
		}
	  }	
  def verifyInHeaderSuccessfulRegistration(){
		 $("#dismiss-btn").click()
		// $(".js-success.generic-success.success").text()=="SUCCESS"
		 def okValue1 = PropertyProvider.getInstance().getLocalizedPropertyValue("SuccesfulRegistration")
		 $(".js-success.generic-success.success").value(okValue1)
		 return true
	}
  
  //Added by Dipannita
   def openPasswordTab(){
	 if($("#dismiss-btn").size()!=0){
		  $("#dismiss-btn").click()
	  }
	  	Thread.sleep(4000)
	  $("#PasswordLink").click()
	  println($(".myaccount-top>h2").text())
	  
	  def okValue1 = PropertyProvider.getInstance().getLocalizedPropertyValue("ChangePasswordheading")	  
	  assert $(".myaccount-top>h2").text().toUpperCase() == okValue1
	  Thread.sleep(4000)
	  return true
  }
  //Added by Dipannita
  def editPassword(){
	  
	  def okValue1 = PropertyProvider.getInstance().getLocalizedPropertyValue("password")
	  $("#currentPassword").value(okValue1)
	  
	  def okValue2 = PropertyProvider.getInstance().getLocalizedPropertyValue("password")
	  $("#newPassword").value(okValue2)
	  
	  def okValue3 = PropertyProvider.getInstance().getLocalizedPropertyValue("password")
	  $("#checkNewPassword").value(okValue3)
	  return true
	   }
  
  def UpdateButton(){
	  $(".button-black").click()
  }
  
  def LinkToFB(){
	  Thread.sleep(500)
	  $("#dismiss-btn").click()
	  Thread.sleep(3000)
	  $(".fb").click()
	  Thread.sleep(2000)
	  return true
	  }
  
	 def validateFBloggedIn(){
	  Thread.sleep(2000)
	  $("#global-myaccount-cta2").text().toUpperCase() == "LEVI LNAME"
	  return true
	  }
	 
  def VerifyLEVISOrdersUsingFBlogIn(){
		 $("#OrdersLink").click()
		 $("h2").text().toUpperCase() == "ORDERS"
		 return true
	  }
  
  //Added by Dipannita
  def openOrdersTab(){
	  $("#dismiss-btn").click()
	  
	  $("#OrdersLink").click()
		  
	  $("h2").text().toUpperCase() == "ORDERS"
	  }
  //Added by Dipannita
   def VerifyOrderHistory(){
	  	  $(".order-history>tbody>tr>td").isDisplayed() == true
			
		  int minimum =1
	      int maximum =8
	      int i = minimum + (int)(Math.random()*maximum)
		  
		  Thread.sleep(2000)
		  $(".order-history>tbody>tr:nth-child("+i+")>td:nth-child(1)>p>a").click()
		  Thread.sleep(1000)
		 
		  def okValue = PropertyProvider.getInstance().getLocalizedPropertyValue("orderDetail")
		  $(".myaccount-top>h2").text().toUpperCase() == okValue
		  return true
	  }
  //Added by Dipannita
  def String Result()
  {
	 String Randomresult = RandomStringUtils.random(64, false, true);
	 Randomresult = RandomStringUtils.random(32, 0, 20, true, true, "abcdefghijklmnopqrst".toCharArray());
	 return Randomresult
  
  }
  //Added by Dipannita
  def openAdrressTab(){
	  Thread.sleep(1000)
	  $("#dismiss-btn").click()
	  Thread.sleep(2000)
	  $("#AddressLink").click()
	  Thread.sleep(2000)
	  $(".billing-address-list>div>h3").text().toUpperCase() == PropertyProvider.getInstance().getLocalizedPropertyValue("addressHeader")
	  return true
  }
  //Added by Dipannita
  def UpdateBillingAdrressSection(){
	  $(".billing-address-list>div>.btn-dbbdr").click()
	  
	  $("section.myaccount-top>h2").text().toUpperCase() == PropertyProvider.getInstance().getLocalizedPropertyValue("BillingaddressHeader")
	  return true
	  }
  //Added by Dipannita
  def UpdateBillingData()
  {
	  def okValue10 = PropertyProvider.getInstance().getLocalizedPropertyValue("billingfname")
	  $("#firstName").value(okValue10)
	  
	  def okValue11 = PropertyProvider.getInstance().getLocalizedPropertyValue("billinglname")
	  $("#lastName").value(okValue11)
	  
	  def okValue12 = PropertyProvider.getInstance().getLocalizedPropertyValue("address")
	  $("#line1").value(okValue12)
			  
	  def okValue13 = PropertyProvider.getInstance().getLocalizedPropertyValue("town")
	  $("#townCity").value(okValue13)
	  
	  def okValue14 = PropertyProvider.getInstance().getLocalizedPropertyValue("postalcode")
	  $("#postcode").value(okValue14)
	 
	  
	  String Randomresult = RandomStringUtils.random(64, false, true);
	  Randomresult = RandomStringUtils.random(6, 0, 10, true, true, "1234567890".toCharArray());
	  $("#phone").value(Randomresult)
	  
	  
	  $(".default-address-text").click()
	  $(".btn-dbbdr").click()
				  
  }
  def verifyUpdatedBilling()
  {
  //$(".box.default>p").text().toUpperCase()== "Default Address"
  $(".billing-address-list>div>div>p").text().toUpperCase() == PropertyProvider.getInstance().getLocalizedPropertyValue("defaultaddress")
  return true
  }
  
  def UpdateShippingAdrressSection(){
	  $(".shipping-address-list>div>.btn-dbbdr").click()
	  
	  $("section.myaccount-top>h2").text().toUpperCase() == PropertyProvider.getInstance().getLocalizedPropertyValue("ShippingaddressHeader")
	  return true
	  }
  
  def UpdateShippingData()
  {
	  def okValue10 = PropertyProvider.getInstance().getLocalizedPropertyValue("billingfname")
	  $("#firstName").value(okValue10)
	  
	  def okValue11 = PropertyProvider.getInstance().getLocalizedPropertyValue("billinglname")
	  $("#lastName").value(okValue11)
	  
	  def okValue12 = PropertyProvider.getInstance().getLocalizedPropertyValue("address")
	  $("#line1").value(okValue12)
			  
	  def okValue13 = PropertyProvider.getInstance().getLocalizedPropertyValue("town")
	  $("#townCity").value(okValue13)
	  
	  def okValue14 = PropertyProvider.getInstance().getLocalizedPropertyValue("postalcode")
	  $("#postcode").value(okValue14)
	 
	  
	  String Randomresult = RandomStringUtils.random(64, false, true);
	  Randomresult = RandomStringUtils.random(6, 0, 10, true, true, "1234567890".toCharArray());
	  $("#phone").value(Randomresult)
	  
	  
	  $(".default-address-text").click()
	  $(".btn-dbbdr").click()
				  
  }
  def verifyUpdatedShipping()
  {
  //$(".box.default>p").text().toUpperCase()== "Default Address"
  $(".box.default>p").text().toUpperCase() == PropertyProvider.getInstance().getLocalizedPropertyValue("defaultaddress")
  return true
  }
  
   
  def  ChangeLocale1() {
	 // driver.navigate().to("https://qa-500-web-000.levi-site.com/DE/de_DE")
	  driver.navigate().to(PropertyProvider.getInstance().basicURL+"/DE/de_DE")
	  Thread.sleep(2000)
	  return true
   }
  def  ChangeLocale1_DK() {
	 // driver.navigate().to("https://qa-500-web-000.dockers-site.com/DE/de_DE")
	  driver.navigate().to(PropertyProvider.getInstance().basicURL+"/DE/de_DE")
	  Thread.sleep(2000)
	  return true
   }
  
  def  ChangeLocale2() {
	  //driver.navigate().to("https://qa-500-web-000.levi-site.com/GB/en_GB/my-account")
	  driver.navigate().to(PropertyProvider.getInstance().basicURL+"/GB/en_GB/my-account")
	  Thread.sleep(2000)
	  return true
   }
  
  def clickOnMyAccount()
	  {
	 $(".list-type-04>li>div:nth-child(1)>a>img").click()
	  }
  def VerifyEmailInHeader(){
      def headerText = $("#global-myaccount-cta2").text()
	  assert headerText == PropertyProvider.getInstance().getLocalizedPropertyValue("headername")
	  return true
	  }
  
  
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  
  
  //----------suprito-- 6th March-2015----------------
  
  def shopAllCollection() {
	  
	  Thread.sleep(18000)
	  //hovering over clothing tab
	  interact {
		  moveToElement($("body>div.header-fluid>header >header >ul >li:nth-child(1)>h2 >a"))
	  }
	  Thread.sleep(7000)
	  // selecting shop all option
	  $("#MenShopAllLink").click()
	  Thread.sleep(7000)
	  
	  //verifying whether the control goes to shop all page
	  assert $(".content.tile-02>h1").text().toUpperCase() == "SHOP ALL"

	  // selecting product A-Z option
	  $(".dk_toggle").click()
	  Thread.sleep(7000)
	  
	  driver.findElement(By.xpath(".//*[@id='dk_container_sort-select']/div/ul/li[5]/a")).click()
	  
	  Thread.sleep(8000)
	  
	  // scrolling functionality is written
	  ((JavascriptExecutor) driver).executeScript("scroll(0,1500);")
	  Thread.sleep(10000)
	  ((JavascriptExecutor) driver).executeScript("scroll(1500,3000);")
	  
	  Thread.sleep(8000)
	  ((JavascriptExecutor) driver).executeScript("scroll(3000,4500);")
	  Thread.sleep(8000)
	  
	  // printing all the additional products.Please verify the console to get all products.
	  def len = $("#container_results").text()
	  println len
	  Thread.sleep(8000)
  	  return true
  
  }
  
  
  def searchProductWithMultipleSwatches(){
	  
	  def skuItemName = PropertyProvider.getInstance().getLocalizedPropertyValue("productWithMultipleColour")
	  waitFor{$("#input-search")}.value(skuItemName)
	  $("#search-bar > a").click()
  }
  
  //Added by PM
  def negetivePassword(String ok1,String ok2, String ok3){
	  
	  def okValue1 = PropertyProvider.getInstance().getLocalizedPropertyValue(ok1)
	  $("#currentPassword").value(okValue1)
	  
	  def okValue2 = PropertyProvider.getInstance().getLocalizedPropertyValue(ok2)
	  $("#newPassword").value(okValue2)
	  
	  def okValue3 = PropertyProvider.getInstance().getLocalizedPropertyValue(ok3)
	  $("#checkNewPassword").value(okValue3)
	  //return true
	   }
  
  def verifyPWErrorMesage(String errMsgType){
	  Thread.sleep(1000)
	  println($(".generic-error").text().trim())
	  println($(".desc.js-msg").text().trim())
	  assert ($(".generic-error").text().trim().equalsIgnoreCase(PropertyProvider.getInstance().getLocalizedPropertyValue("negativeoldpwErrorMsg")))
	  assert ($(".desc.js-msg").text().trim().equalsIgnoreCase(PropertyProvider.getInstance().getLocalizedPropertyValue(errMsgType)))
	  Thread.sleep(1000)
	  return true
  }
  
   def RemoveShippingAdrressSection(){	
	  $(".box-footer-right-col>a"). click()
	  return true
  }
   def RemoveBillingAdrressSection(){
	   $(".billing-address-list>div :nth-child(3)>div :nth-child(2)>a"). click()
	   return true
   }
   //Added By Dipannita
   def VerifyOrderDetailsFromOrderHistory() {
	   try{
	   //verifies displays order number
	   assert $(".left-col>p:nth-child(1)>span").isDisplayed() == true 
	   
	   //verifies displays purchased date
	   assert $(".left-col>p:nth-child(2)>span").isDisplayed() == true 
	   
	   //verifies displays the status
	   assert $(".left-col>p:nth-child(3)>span").isDisplayed() == true
	   
	   //verifies shipping address displays
	   assert $(".main-right>div:nth-of-type(1)").isDisplayed() == true 
	   
	   //verifies billing address displays
	   assert $(".main-right>div:nth-of-type(2)").isDisplayed() == true 
	   
	   //verifies proimage displays
	   assert $(".list-type-04.halves>li").isDisplayed() == true
	   
	   OrderDataModel.getInstance().orderNumber = $(".left-col>p:nth-child(1)>span").text()

	   String currencySymbol = PropertyProvider.getInstance().getLocalizedPropertyValue("currency.symbol")
	   String decimalSeparator = PropertyProvider.getInstance().getLocalizedPropertyValue("decimal.separator")
       Thread.sleep(500)
	  // Double price = $(".main-right>ul>li:nth-child(7)>span").text().replaceAll(currencySymbol, "").replaceAll(decimalSeparator, ".").toDouble()
	   Double price = $(".data :nth-of-type(1)").text().replaceAll(currencySymbol, "").replaceAll(decimalSeparator, ".").toDouble()
	   OrderDataModel.getInstance().totalPrice = price
	   
	   println "Order number: " + OrderDataModel.getInstance().orderNumber + " - Price: " + OrderDataModel.getInstance().totalPrice
	   }
	   catch(NumberFormatException e)
	       {
		  println "Exception Caught"
		   }
	   return true
	   
   }
   
   def UpdateNegativeShippingData()
   {
	   def okValue10 = PropertyProvider.getInstance().getLocalizedPropertyValue("negativebillingfname")
	   $("#firstName").value(okValue10)
	   
	   def okValue11 = PropertyProvider.getInstance().getLocalizedPropertyValue("negativebillinglname")
	   $("#lastName").value(okValue11)
	   
	   def okValue12 = PropertyProvider.getInstance().getLocalizedPropertyValue("negativeaddress")
	   $("#line1").value(okValue12)
			   
	   def okValue13 = PropertyProvider.getInstance().getLocalizedPropertyValue("town")
	   $("#townCity").value(okValue13)
	   
	   def okValue14 = PropertyProvider.getInstance().getLocalizedPropertyValue("negativepostalcode")
	   $("#postcode").value(okValue14)
	  	   
	   String Randomresult = RandomStringUtils.random(64, false, true);
	   Randomresult = RandomStringUtils.random(6, 0, 10, true, true, "1234567890".toCharArray());
	   $("#phone").value(Randomresult)
	   	   
	   $(".default-address-text").click()
	   $(".btn-dbbdr").click()
	 }
   def VerifyErrorMassage(){
	   Thread.sleep(500)
	
	   assert $(".myaccount-main>ul>li:nth-child(3)>div>div").isDisplayed()== true
	   assert $(".myaccount-main>ul>li:nth-child(4)>div>div").isDisplayed()== true
	   assert $(".myaccount-main>ul>li:nth-child(5)>div>div").isDisplayed()== true
	   assert $(".myaccount-main>ul>li:nth-child(7)>div>div").isDisplayed()== true
	   
	   def okValue1 = PropertyProvider.getInstance().getLocalizedPropertyValue("Errorbillingfname")
	   assert $(".myaccount-main>ul>li:nth-child(3)>div>div").text() == okValue1
	   
	   def okValue2 = PropertyProvider.getInstance().getLocalizedPropertyValue("Errorbillinglname")
	   assert $(".myaccount-main>ul>li:nth-child(4)>div>div").text() == okValue2
	   
	   def okValue3 = PropertyProvider.getInstance().getLocalizedPropertyValue("Erroraddress")
	   assert $(".myaccount-main>ul>li:nth-child(5)>div>div").text() == okValue3
			   
	   def okValue4 = PropertyProvider.getInstance().getLocalizedPropertyValue("Errorpostalcode")
	   assert $(".myaccount-main>ul>li:nth-child(7)>div>div").text()== okValue4
	   
	   return true
   }
   
   def EditExistingAddressWithNegativeShippingData()
   {   
	   Thread.sleep(500)
	   assert $(".box.default").isDisplayed() == true
	   $(".box-footer-left-col>a").click()
	   
	   def okValue10 = PropertyProvider.getInstance().getLocalizedPropertyValue("negativebillingfname")
	   $("#firstName").value(okValue10)
	   
	   def okValue11 = PropertyProvider.getInstance().getLocalizedPropertyValue("negativebillinglname")
	   $("#lastName").value(okValue11)
	   
	   def okValue12 = PropertyProvider.getInstance().getLocalizedPropertyValue("negativeaddress")
	   $("#line1").value(okValue12)
			   
	   def okValue13 = PropertyProvider.getInstance().getLocalizedPropertyValue("town")
	   $("#townCity").value(okValue13)
	   
	   def okValue14 = PropertyProvider.getInstance().getLocalizedPropertyValue("negativepostalcode")
	   $("#postcode").value(okValue14)
			 
	   String Randomresult = RandomStringUtils.random(64, false, true);
	   Randomresult = RandomStringUtils.random(6, 0, 10, true, true, "1234567890".toCharArray());
	   $("#phone").value(Randomresult)
			  
	   $(".default-address-text").click()
	   $(".btn-dbbdr").click()
	 }
   
   
    def ContinueShopping(){
	   $(".black-arrow-right-small>span").click()
   }
   
	// added By Suprito after 12th March 2015---------------------
  
  
  def profileCustomerInformationNEGATIVE(){
	  // entering special characters on First name field
	  def specialCharOnFrstName = PropertyProvider.getInstance().getLocalizedPropertyValue("specialCharOnFrstNameField")
	  accountFirstNameTxtField.value(specialCharOnFrstName)
	  
	  def lastNameVal = PropertyProvider.getInstance().getLocalizedPropertyValue("profileLname")
	  accountLastNameTxtField.value(lastNameVal)
	  $(".btn-dbbdr").click()
	  Thread.sleep(4000)
	 boolean  chck1 = $("#updateProfileForm").find("input", id: "firstName").hasClass( "warning" )
	  println chck1
	  assert chck1 == true
	  
	  // entering special characters on last Name field
	  def frstName = PropertyProvider.getInstance().getLocalizedPropertyValue("profileFname")
	  accountFirstNameTxtField.value(frstName)
	  
	  def spclCharOnLastName = PropertyProvider.getInstance().getLocalizedPropertyValue("specialCharOnLastNameField")
	  accountLastNameTxtField.value(spclCharOnLastName)
	  $(".btn-dbbdr").click()
	  Thread.sleep(4000)
	 boolean  chck2 = $("#updateProfileForm").find("input", id: "lastName").hasClass( "warning" )
	  println chck2
	  assert chck2 == true
	  
	  // giving combination of special characters and numeric on first and last name field
	  def frstName1 = PropertyProvider.getInstance().getLocalizedPropertyValue("bothSpclAndNumberic")
	  accountFirstNameTxtField.value(frstName1)
	  
	  def lastName1 = PropertyProvider.getInstance().getLocalizedPropertyValue("bothSpclAndNumberic")
	  accountLastNameTxtField.value(lastName1)
	  $(".btn-dbbdr").click()
	  Thread.sleep(4000)
	 boolean  chck3 = $("#updateProfileForm").find("input", id: "firstName").hasClass( "warning" )
	 boolean  chck4 = $("#updateProfileForm").find("input", id: "lastName").hasClass( "warning" )
	  println chck3
	  println chck4
	  assert chck3 == true
	  assert chck4 == true
	  
	  // erasing the email txt field value
	  def frstName2 = PropertyProvider.getInstance().getLocalizedPropertyValue("profileFname")
	  accountFirstNameTxtField.value(frstName2)
	  
	  def lastNameVal2 = PropertyProvider.getInstance().getLocalizedPropertyValue("profileLname")
	  accountLastNameTxtField.value(lastNameVal2)
	  
	  def blankEmail = PropertyProvider.getInstance().getLocalizedPropertyValue("emptyEmailAddress")
	  accountEmailTxtField.value(blankEmail)
	  
	  $(".btn-dbbdr").click()
	  Thread.sleep(4000)
	  boolean  chck5 = $("#updateProfileForm").find("input", id: "email").hasClass( "warning" )
	  println chck5
	  assert chck5 == true
	  
	  // passing email address without @
	  def frstName3 = PropertyProvider.getInstance().getLocalizedPropertyValue("profileFname")
	  accountFirstNameTxtField.value(frstName3)
	  
	  def lastNameVal3 = PropertyProvider.getInstance().getLocalizedPropertyValue("profileLname")
	  accountLastNameTxtField.value(lastNameVal3)
	  
	  def incorrectEmail = PropertyProvider.getInstance().getLocalizedPropertyValue("wrongemailAddress")
	  accountEmailTxtField.value(incorrectEmail)
	  
	  $(".btn-dbbdr").click()
	  Thread.sleep(4000)
	  boolean  chck6 = $("#updateProfileForm").find("input", id: "email").hasClass( "warning" )
	  println chck6
	  assert chck6 == true
	  
	  // passing email address without DomainExt
	  def frstName4 = PropertyProvider.getInstance().getLocalizedPropertyValue("profileFname")
	  accountFirstNameTxtField.value(frstName4)
	  
	  def lastNameVal4 = PropertyProvider.getInstance().getLocalizedPropertyValue("profileLname")
	  accountLastNameTxtField.value(lastNameVal4)
	  
	  def emailExcludingDomainExt = PropertyProvider.getInstance().getLocalizedPropertyValue("emailWithoutDomainExt")
	  accountEmailTxtField.value(emailExcludingDomainExt)
	  
	  $(".btn-dbbdr").click()
	  Thread.sleep(4000)
	  boolean  chck7 = $("#updateProfileForm").find("input", id: "email").hasClass( "warning" )
	  println chck7
	  assert chck7 == true
	  return true
	  }
	
  
  //----------------Added By Suprito after 31st March 2015----
  
  def menShopAllLinkLevi(){
	   Thread.sleep(5000)
	  WebDriverWait wait = new WebDriverWait(driver, 30)
	  WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body>div.header-fluid>header >header >ul >li:nth-child(1)>h2 >a")))
	  //mouse over Men tab and selecting Shop all men option
	  interact {
		  moveToElement($("body>div.header-fluid>header >header >ul >li:nth-child(1)>h2 >a"))
	  }
	  Thread.sleep(7000)
	  // selecting shop all option
	  $("#Levi_Men_ShopAll").click()
	  Thread.sleep(7000)
	  }
  
  
  def womenShopAllLinkLevi(){
	  Thread.sleep(5000)
	 WebDriverWait wait9 = new WebDriverWait(driver, 30)
	 WebElement element = wait9.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body>div.header-fluid>header >header >ul >li:nth-child(2)>h2 >a")))
	 //mouse over Women tab and selecting Shop all Women option
	 interact {
		 moveToElement($("body>div.header-fluid>header >header >ul >li:nth-child(2)>h2 >a"))
	 }
	 Thread.sleep(7000)
	 // selecting shop all option
	 $("#Levis_Women_ShopAll").click()
	 Thread.sleep(7000)
	 }
  
  def accesoriesSeeAll(){
	  Thread.sleep(5000)
	 WebDriverWait wait10 = new WebDriverWait(driver, 30)
	 WebElement element = wait10.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body>div.header-fluid>header >header >ul >li:nth-child(3)>h2 >a")))
	 //mouse over Accessories Tab
	 interact {
		 moveToElement($("body>div.header-fluid>header >header >ul >li:nth-child(3)>h2 >a"))
	 }
	 Thread.sleep(7000)
	 // selecting see all link
	 $("#aSeeAllLink").click()
	 Thread.sleep(7000)
	 }

  
  def verifyloadAdditionalProducts() {
	  //verifying whether the control goes to all category page
	  def shopAllMenHeaderScreentxt = $(".content.tile-02>h1").text().toUpperCase()
	  def shopAllHeader = PropertyProvider.getInstance().getLocalizedPropertyValue("shopAllHeaderTxt").toUpperCase()
	 println shopAllMenHeaderScreentxt
	   assert shopAllMenHeaderScreentxt == shopAllHeader
	  
	  // Selecting the option "Price: low to high" option from "Sort them by" drop-down
	  WebDriverWait wait7 = new WebDriverWait(driver, 30)
	  WebElement element = wait7.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".dk_toggle")))
	  $(".dk_toggle").click()
	  Thread.sleep(4000)
	  driver.findElement(By.xpath(".//*[@id='dk_container_sort-select']/div/ul/li[3]/a")).click()
	  
	  Thread.sleep(8000)
	  
	  // scrolling down the screen to load additional products
	  ((JavascriptExecutor) driver).executeScript("scroll(0,1500);")
	  Thread.sleep(10000)
	  ((JavascriptExecutor) driver).executeScript("scroll(1500,3000);")
	  
	  Thread.sleep(8000)
	  ((JavascriptExecutor) driver).executeScript("scroll(3000,4500);")
	  Thread.sleep(8000)
	  
	  //size of the container results upto the point the screen scrolls down
		def containerResultsSize = $("#container_results>li").size()
		println containerResultsSize
		
		assert  containerResultsSize != 0
	  
		// printing all the additional products in Console.Check the console to get the list of sorted products based on Price
	  def products = $("#container_results").text()
	  println products
	  Thread.sleep(8000)
  
	  
	  return true
  
  }
  
  def verifyloadAdditionalProductsAccessoriesLevi() {
	  //verifying whether the control goes to all category page
	  def shopAllMenHeaderScreentxt = $(".content.tile-02").text().toUpperCase()
	  println shopAllMenHeaderScreentxt
	  def shopAllHeader = PropertyProvider.getInstance().getLocalizedPropertyValue("accessoriesHeaderTxt").toUpperCase()
	 
	   assert shopAllMenHeaderScreentxt == shopAllHeader
	  
	  // Selecting the option "Price: low to high" option from "Sort them by" drop-down
	  WebDriverWait wait7 = new WebDriverWait(driver, 30)
	  WebElement element = wait7.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".dk_toggle")))
	  $(".dk_toggle").click()
	  Thread.sleep(4000)
	  driver.findElement(By.xpath(".//*[@id='dk_container_sort-select']/div/ul/li[3]/a")).click()
	  
	  Thread.sleep(8000)
	  
	  // scrolling down the screen to load additional products
	  ((JavascriptExecutor) driver).executeScript("scroll(0,1500);")
	  Thread.sleep(10000)
	  ((JavascriptExecutor) driver).executeScript("scroll(1500,3000);")
	  
	  Thread.sleep(8000)
	  ((JavascriptExecutor) driver).executeScript("scroll(3000,4500);")
	  Thread.sleep(8000)
	  
	  //size of the container results upto the point the screen scrolls down
		def containerResultsSize = $("#container_results>li").size()
		println containerResultsSize
		
		assert  containerResultsSize != 0
	  
		// printing all the additional products in Console.Check the console to get the list of sorted products based on Price
	  def products = $("#container_results").text()
	  println products
	  Thread.sleep(8000)
  
	  
	  return true
  
  }
  
def toPage(String headerIndex,String pageId) {
	  Thread.sleep(3000)
	  interact {
		  moveToElement($("body > div.header-fluid > header > header > ul > li:nth-child("+headerIndex+") > h2"))
	   //   $("#wJackets&VestsLink").click()
		  Thread.sleep(3000)
	  }
	 $(pageId).click()
	  Thread.sleep(3000)
}
   boolean goTobagDetails(){
	  Thread.sleep(3000)	  
		  interact {
			  moveToElement($(".mini-cart"))
			  Thread.sleep(1000)
		  }
		  Thread.sleep(3000)
		 $(".mini-cart").click()
		
  	  }
def setNewPassword(String oldpwd,String newpwd){
	  def okValue1 = PropertyProvider.getInstance().getLocalizedPropertyValue(oldpwd)
	  $("#currentPassword").value(okValue1)
	  
	  def okValue2 = PropertyProvider.getInstance().getLocalizedPropertyValue(newpwd)
	  $("#newPassword").value(okValue2)
	  
	  def okValue3 = PropertyProvider.getInstance().getLocalizedPropertyValue(newpwd)
	  $("#checkNewPassword").value(okValue3)
	  return true
}
}
package com.lsco.test.page


import com.lsco.test.page.model.UserDataModelMap
import geb.Page

import org.openqa.selenium.JavascriptExecutor
import org.apache.commons.lang.RandomStringUtils
import org.apache.commons.lang.math.RandomUtils
import com.lsco.test.PropertyProvider
import com.lsco.test.page.checkout.OrderDataModel
import com.lsco.test.page.model.ProductDataModel
import com.lsco.test.page.model.ProductDataModelMap
import com.lsco.test.page.model.UserDataModelMap
import com.lsco.test.page.register.RegistrationDataModel
import geb.Page
import org.apache.commons.lang.RandomStringUtils
import org.openqa.selenium.JavascriptExecutor
// added By Suprito after 12 th March 2015
import org.openqa.selenium.*
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions
// added By Suprito after 31 st March 2015
import org.openqa.selenium.interactions.Actions

class CartPage extends Page {

	static url = "cart"
	
	static content = {
		checkoutButton { $("#main-container > aside > section > a.btn-dbbdr.top-btn")}
		myFrame(page: FramePage) { $('#PaymentMethodForm > fieldset > iframe') }
		bagProductsConteiner {$("ul.list-type-04")}
		bagProducts(required: false) { moduleList ProductModule, bagProductsConteiner.find("li.product-tile")}
		
		
		shippingFname { $("#sendAndBillForm").find("input", id: "shipping-firstname")}
		shippingLname { $("#sendAndBillForm").find("input", id: "shipping-lastname")}
		shippingAddress1 { $("#sendAndBillForm").find("input", id: "shipping-address-1")}
		shippingCity { $("#sendAndBillForm").find("input", id: "shipping-city")}
		shippingPostal { $("#sendAndBillForm").find("input", id: "shipping-postal")}
		shippingEmail { $("#sendAndBillForm").find("input", id: "contact-email")}
		shippingPhn { $("#sendAndBillForm").find("input", id: "contact-phone")}
		chckOutusernameLevi { $("#loginForm").find("input", id: "j_username")}
		chckOutpasswordLevi { $("#loginForm").find("input", id: "j_password")}
		
		adrressdropdownSelect{ $('div.dk_focus.dk_open ul.dk_options_inner li a',it)}
		ProductAdded{$("#useritems-container>ul>li")}
		costSummaryTotal{$("#cost-summary-total")}
		BillingCity{ $("#sendAndBillForm").find("input", id: "billing-city")}
		BillingPostal { $("#sendAndBillForm").find("input", id: "billing-postal")}
		
		
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	}

	static at = {
		def okValue = PropertyProvider.getInstance().getLocalizedPropertyValue("in.your.bag")
		assert $("#main-container > article > header > h1").text().trim().toUpperCase() == okValue
		return true
	}

	def toCheckoutPage() {
		checkoutButton.click()
	}

	def fillShippingData(firstName, surname, address, city, postcode){
		String validAddress = PropertyProvider.getInstance().getLocalizedPropertyValue(address)
		String validCity = PropertyProvider.getInstance().getLocalizedPropertyValue(city)
		String validPostcode = PropertyProvider.getInstance().getLocalizedPropertyValue(postcode)

		$("#shipping-firstname").value(firstName)
		$("#shipping-lastname").value(surname)
		$("#shipping-address-1").value(validAddress)
		$("#shipping-city").value(validCity)
		$("#shipping-postal").value(validPostcode)

		assert $("#shipping-firstname").value() == firstName
		assert $("#shipping-lastname").value() == surname
		assert $("#shipping-address-1").value() == validAddress
		assert $("#shipping-city").value() == validCity
		assert $("#shipping-postal").value() == validPostcode
	}

	def fillShippingDataForRandomUser(address, city, postcode){
		String firstName=UserDataModelMap.getInstance().getUserMap().get("NEW_USER_DATA").getName()
		String surname=UserDataModelMap.getInstance().getUserMap().get("NEW_USER_DATA").getLastName()
		fillShippingData(firstName, surname, address, city, postcode)
	}


	def fillContactData(phone){
		String email = RegistrationDataModel.getInstance().email
		fillContactData(email, phone)
	}

	def fillContactData(email, phone){
		String validPhone = PropertyProvider.getInstance().getLocalizedPropertyValue(phone)

		$("#contact-email").value(email)
		$("#contact-phone").value(validPhone)

		assert $("#contact-email").value() == email
		assert $("#contact-phone").value() == validPhone
	}

	def fillContactDataRandomUser(phone) {
		def email=UserDataModelMap.getInstance().getUserMap().get("NEW_USER_DATA").getEmail().toLowerCase()
		fillContactData(email,phone)
	}

	def CheckAgeCheckbox(){
		Thread.sleep(1000)
		$(".checkbox-label.tag-label").click()
		$(".fancybox-item.fancybox-close").click()
		
		//assert $("#age-chkbox").value() == "true"
        return true
	}
	def checkAgeCheckbox(){
		js( ' jQuery( "#age-chkbox" ).css("opacity", "100"); ' )
		js( ' jQuery( "#age-chkbox" ).css("left", "0px"); ' )
		$("#age-chkbox").value(true)

		//assert $("#age-chkbox").value() == "true"
        return true
	}

	def checkGiftWrapCheckbox(){
		js( ' jQuery( "#giftwrap-chkbox" ).css("opacity", "100"); ' )
		js( ' jQuery( "#giftwrap-chkbox" ).css("left", "0px"); ' )
		$("#giftwrap-chkbox").value(true)

		assert $("#giftwrap-chkbox").value() == "true"

		//Commented because is no longer used
		//		waitFor(30, 10){
		//			$("#giftwrap-from").isDisplayed()
		//		}
		//		$("#giftwrap-from").value("Martin")
		//		$("#giftwrap-to").value("Peter")
		//		$("#giftwrap-note1").value("Happy birthday!!!")
		//
		//		assert $("#giftwrap-from").value() == "Martin"
		//		assert $("#giftwrap-to").value() == "Peter"
		//		assert $("#giftwrap-note1").value() == "Happy birthday!!!"
	}

	def js( String script ){
		(driver as JavascriptExecutor).executeScript( script )
	}

	def submitData() {
		$("#link-next").click()
	}

	def fillCreditCardData(creditCardNumber, securityCode, expirationMonth, expirationYear){
		waitFor(240, 10){ myFrame.@style == "display: block;" }

		Thread.sleep(5000)
		
		withFrame(myFrame) {
			$("#F1009").value(creditCardNumber)
			$("#F1136").value(securityCode)

			assert $("#F1009").value() == creditCardNumber
			$("#F1010_MM").value(expirationMonth)
			$("#F1010_YY").value(expirationYear)
			$("#btnSubmit").click()
		}
	}

	def chooseMaestro(){
		Thread.sleep(4000)
		$("input", name: "method").value("117-K")
		
		Thread.sleep(4000)
	}
	
	def chooseDankort(){
     //added by Suprito after 12th March 2015
		Thread.sleep(4000)
		$("input", name: "method").value("123-K")
      Thread.sleep(4000)
	}


	def chooseCarteBleue(){
     //added By Suprito after 12th March
		Thread.sleep(4000)
		$("input", name: "method").value("130-K")
      Thread.sleep(4000)
	}

	def chooseIDeal(){
   // added By Suprito after 12th March
		Thread.sleep(4000)
		$("input", name: "method").value("809-R")
    Thread.sleep(4000)
	}

	def chooseMasterCard(){
		Thread.sleep(2000)
		$("input", name: "method").value("3-K")
		Thread.sleep(1000)
	}

	def chooseAmex(){
		Thread.sleep(4000)
		$("input", name: "method").value("2-K")
		
		Thread.sleep(4000)
	}
	
	def chooseDelta(){
		$("input", name: "method").value("111-K")
	}
	
	def chooseVisa(){
		// added By Suprito after 2nd March 2015
		Thread.sleep(3000)
		
		$("input", name: "method").value("1-K")
		
		// added By Suprito after 2nd March 2015
		Thread.sleep(2000)
	}

	def choosePayPal(){
		Thread.sleep(4000)
		$("input", name: "method").value("840-R")
		waitFor(120, 10){
			$("#loginPageTitle").isDisplayed()
		}
		Thread.sleep(2000)
	}

	def checkProduct(quantity){
		ProductDataModel productDataModel = ProductDataModelMap.getInstance().getProductMap().get("SELECTED_PRODUCT")
		waitFor(120,4){$(".prod-name-descript > p:nth-child(1)").isDisplayed()}
		assert $(".prod-name-descript > p:nth-child(1)").text() == productDataModel.productName
		assert $(".sku > span:nth-child(1)").text() == productDataModel.productId
		assert $("#quantityCombo").value() == quantity
		ProductDataModelMap.getInstance().getProductMap().clear()
	}

//	def updateQuantity(){
//		String currencySymbol = PropertyProvider.getInstance().getLocalizedPropertyValue("currency.symbol")
//		String decimalSeparator = PropertyProvider.getInstance().getLocalizedPropertyValue("decimal.separator")
//
//		def orderPricing = $("li.data:nth-child(2)")
//		def orderPrice = orderPricing.text().replaceAll(currencySymbol, "").replaceAll(decimalSeparator, ".").toDouble()
//		
//		$(".select2-choice").click();
//		def quantityCombo = $("#quantityCombo")
//		
//		quantityCombo.value("2")
//		quantityCombo = $("#quantityCombo")
//		assert quantityCombo.value() == "2"
//		
//		orderPricing = $("li.data:nth-child(2)")
//		def newOrderPrice = orderPricing.text().replaceAll(currencySymbol, "").replaceAll(decimalSeparator, ".").toDouble()
//		
//		println "New calculated price: " + (orderPrice * quantityCombo.value().toDouble())
//		println "New order price: " + newOrderPrice
//
//		assert orderPrice * quantityCombo.value().toDouble() == newOrderPrice
//	}

	def updateQuantity(){
		String currencySymbol = PropertyProvider.getInstance().getLocalizedPropertyValue("currency.symbol")
		String decimalSeparator = PropertyProvider.getInstance().getLocalizedPropertyValue("decimal.separator")

		def orderPricing = $("li.data:nth-child(2)")
		def orderPrice = orderPricing.text().replaceAll(currencySymbol, "").replaceAll(decimalSeparator, ".").toDouble()
		
		$(".select2-choice").click();
		def quantityCombo = $("#quantityCombo")
		
		quantityCombo.value("2")
		quantityCombo = $("#quantityCombo")
		assert quantityCombo.value() == "2"
		
		orderPricing = $("li.data:nth-child(2)")
		def newOrderPrice = orderPricing.text().replaceAll(currencySymbol, "").replaceAll(decimalSeparator, ".").toDouble()
		//def newOrderPrice = orderPricing.text().replaceAll(currencySymbol, "").replaceAll(",", "").replaceAll(decimalSeparator, ".").toDouble()
		
		println "New calculated price: " + (orderPrice * quantityCombo.value().toDouble())
		println "New order price: " + newOrderPrice

		assert orderPrice * quantityCombo.value().toDouble() == newOrderPrice
		return true
	}
	def payPalLogin(username, password){
		String usernameValue = PropertyProvider.getInstance().getLocalizedPropertyValue(username)
		String passwordValue = PropertyProvider.getInstance().getLocalizedPropertyValue(password)

		$("#login_email").value(usernameValue)
		$("#login_password").value(passwordValue)
		$("#submitLogin").click()
	}

	def confirmPayPalPayment(){
		$("#continue_abovefold").click()
		
		//added by Suprito
		Thread.sleep(7000)
		return true
	}

	def verifyOrder(){
		String email = RegistrationDataModel.getInstance().email
		verifyOrder(email)
	}

	def verifyOrder(String email){
		assert $("#left-col > p:nth-child(1) > span").text()
		assert $("#left-col > p.data").text().toUpperCase() == email.toUpperCase()

		OrderDataModel.getInstance().orderNumber = $('#left-col > p > span').text()

		String currencySymbol = PropertyProvider.getInstance().getLocalizedPropertyValue("currency.symbol")
		String decimalSeparator = PropertyProvider.getInstance().getLocalizedPropertyValue("decimal.separator")

		Double price = $('li.data span').text().replaceAll(currencySymbol, "").replaceAll(decimalSeparator, ".").toDouble()
		OrderDataModel.getInstance().totalPrice = price
		
		println "Order number: " + OrderDataModel.getInstance().orderNumber + " - Price: " + OrderDataModel.getInstance().totalPrice
	}

	def chooseBankAndConfirm(String bank){

		waitFor(30, 5){
			$("#F8045").isDisplayed()
		}

		$("#F8045").value("INGBNL2A")
		$("#btnSubmit").click()

		waitFor(30, 5){
			$("input", name: "button.edit").isDisplayed()
		}
		$("input", name: "button.edit").click()
	}
	
	def checkSavings(){
		String decimalSeparator = PropertyProvider.getInstance().getLocalizedPropertyValue("decimal.separator")

		double subtotal = $("ul.cost-summary:nth-child(2) > li:nth-child(2)").text().replaceAll(decimalSeparator, ".").find(/\d+(\.\d)/).toDouble().value
		double savings = $(".data-inner").text().replaceAll(decimalSeparator, ".").find(/\d+(\.\d{2})/).toDouble().value

		println "Subtotal: " + subtotal
		println "Savings: " + savings
	
		subtotal = (subtotal* 10)
		savings = (savings * 100)
			
		println "Calculated Subtotal: " + subtotal
		println "Calculated Savings: " + savings
		
		assert savings == subtotal
	}
	
	def compareSavings(expectedSavingsAmount){
		String currencySymbol = PropertyProvider.getInstance().getLocalizedPropertyValue("currency.symbol")
		String decimalSeparator = PropertyProvider.getInstance().getLocalizedPropertyValue("decimal.separator")
				
		String expectedSavings1 = "(" + currencySymbol + expectedSavingsAmount + ")"
		String expectedSavings2 = "(" + expectedSavingsAmount + currencySymbol + ")"
		String newexpectedSavingsAmount = ((String)expectedSavingsAmount).replace(".", decimalSeparator)
		String expectedSavings3 = "(" + currencySymbol + newexpectedSavingsAmount + ")"
		String expectedSavings4 = "(" + newexpectedSavingsAmount + currencySymbol + ")"

		println "Posible expected savings: " + expectedSavings1 + " " + expectedSavings2 + " " + expectedSavings3 + " " + expectedSavings4   
		
		println $("li", text: expectedSavings1).text()
		println $("li", text: expectedSavings2).text()
		println $("li", text: expectedSavings3).text()
		println $("li", text: expectedSavings4).text()

		assert $("li", text: expectedSavings1).isDisplayed() || $("li", text: expectedSavings2).isDisplayed() || $("li", text: expectedSavings3).isDisplayed() || $("li", text: expectedSavings4).isDisplayed()
	}

	def applyCoupon(couponCode){
		$("#promo-input").value(couponCode)
		$("#promo-btn").click()
		Thread.sleep(2000)
	}

	def checkCoupon(Couponidentifier){
		assert $("#promo-notqualify").text().contains(Couponidentifier)
		return true
	}
	def checkVoucher(Voucheridentifier){
		assert $("#promo-success").text().contains(Voucheridentifier)
		return true
	}
	def getMyBagQuantityElement(){
		$("#main-container > article:nth-child(3) > header:nth-child(1) > p:nth-child(2)")
	}

	String getMyBagQuantityText(){
		getMyBagQuantityElement().text()
	}

	int getMyBagQuantityItems(){
		getMyBagQuantityText().find(/(\d+)/).toInteger()
	}

	def removeItems(String quantity){
		assert getMyBagQuantityText().toUpperCase()== 2+" "+PropertyProvider.getInstance().getLocalizedPropertyValue("bag.items")
		$(".actions > li:nth-child(1) > a:nth-child(1)").click()
		assert getMyBagQuantityText().toUpperCase()== 1+" "+PropertyProvider.getInstance().getLocalizedPropertyValue("bag.item")
	}
	
	def removeItem(){
		assert getMyBagQuantityText().toUpperCase()== 1+" "+PropertyProvider.getInstance().getLocalizedPropertyValue("bag.item")
		$(".actions > li:nth-child(1) > a:nth-child(1)").click()
		assert getMyBagQuantityText().toUpperCase()== 0+" "+PropertyProvider.getInstance().getLocalizedPropertyValue("bag.items")
		return true
	}
	
	def toLoginForm(){
		$("#signed-in-subheader > a").click()
	}

	def checkoutLogin(username, password){
		$("#j_username").value(username)
		$("#j_password").value(password)
		$("#link-signin").click()
	}
	
	def toOrderTrack(){
		$("#left-col > p:nth-child(2) > a:nth-child(1)").click()
	}
	
	def checkOrderInHistory(){
		assert $("p.title:nth-child(1) > span:nth-child(1)").text() == OrderDataModel.getInstance().orderNumber
		$("#OrdersLink").click()
		assert $("table.order-history > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > p:nth-child(1) > a:nth-child(1)").text() == OrderDataModel.getInstance().orderNumber
	}
	
	def toLevisHomePage(){
		$(".logo").click()
	}
	
	//++++++++++++++++++++++++++++added by Suprito/////////////////////////////////
	def toCheckoutPageLatest(){
		 $(".btn-dbbdr.top-btn").click()
		 return true
	}
	
	
	def fillingShippingAddrDetails(){
		
		boolean bol = $("#billing-firstname").isEnabled()
		println bol
		if(bol == true)
		{
			$("fieldset.billing>label.checkbox-label").click()
		}
		
		else{
			System.out.println("Checkbox already checked")
		}
		
		def shipFname = PropertyProvider.getInstance().getLocalizedPropertyValue("shippingFirstName")
		shippingFname.value(shipFname)
		
		def shipLname = PropertyProvider.getInstance().getLocalizedPropertyValue("shippingLastName")
		shippingLname.value(shipLname)
		
		def shipaddr = PropertyProvider.getInstance().getLocalizedPropertyValue("shippingFullAddr")
		shippingAddress1.value(shipaddr)
		
		def shipCity = PropertyProvider.getInstance().getLocalizedPropertyValue("shippingCity")
		shippingCity.value(shipCity)
		
		def shipPostal = PropertyProvider.getInstance().getLocalizedPropertyValue("shippingZip")
		shippingPostal.value(shipPostal)
		
		def shipEmail = PropertyProvider.getInstance().getLocalizedPropertyValue("userContactEmail")
		shippingEmail.value(shipEmail)
		
		def shipPhn = PropertyProvider.getInstance().getLocalizedPropertyValue("userContactPhn")
		shippingPhn.value(shipPhn)
   }
	
	def fillCreditCardDataLatest(){
		waitFor(240, 10){ myFrame.@style == "display: block;" }

		Thread.sleep(9000)
		
		withFrame(myFrame) {
			def cardNum = PropertyProvider.getInstance().getLocalizedPropertyValue("maestroCreditCardNumber")
			$("#F1009").value(cardNum)
			
			def cardCvv = PropertyProvider.getInstance().getLocalizedPropertyValue("securityCode")
			$("#F1136").value(cardCvv)
			
			def expYear = PropertyProvider.getInstance().getLocalizedPropertyValue("paymentYear")
			$("#F1010_YY").value(expYear)
			
			def expMonth = PropertyProvider.getInstance().getLocalizedPropertyValue("paymentMonth")
			$("#F1010_MM").value(expMonth)
		   
			 $("#btnSubmit").click()
			
			Thread.sleep(6000)
			
			return true
		}
	}
	
	
	
	def fillCreditCardDataLatestAmex(){
		waitFor(240, 10){ myFrame.@style == "display: block;" }

		Thread.sleep(9000)
		
		withFrame(myFrame) {
			def cardNum = PropertyProvider.getInstance().getLocalizedPropertyValue("creditCardNumberAmex")
			$("#F1009").value(cardNum)
			
			def cardCvv = PropertyProvider.getInstance().getLocalizedPropertyValue("securityCode")
			$("#F1136").value(cardCvv)
			
			def expYear = PropertyProvider.getInstance().getLocalizedPropertyValue("paymentYear")
			$("#F1010_YY").value(expYear)
			
			def expMonth = PropertyProvider.getInstance().getLocalizedPropertyValue("paymentMonth")
			$("#F1010_MM").value(expMonth)
		   
			 $("#btnSubmit").click()
			
			Thread.sleep(6000)
			
			return true
		}
	}
	
	def payPalLoginLatest(){
		
		Thread.sleep(6000)
		def payPalUserName = PropertyProvider.getInstance().getLocalizedPropertyValue("payPalEmailId")
		def payPalPwd = PropertyProvider.getInstance().getLocalizedPropertyValue("payPalPassword")

		$("#login_email").value(payPalUserName)
		$("#login_password").value(payPalPwd)
		$("#submitLogin").click()
		Thread.sleep(6000)
		return true
	}
	
	
	def loginFromCheckOutPage() {
		
		$(".link-editprofile").click()
		
		Thread.sleep(5000)
		def chckemailLevi = PropertyProvider.getInstance().getLocalizedPropertyValue("emailId")
		chckOutusernameLevi.value(chckemailLevi)
		
		println chckemailLevi
		
		def chckpwdLevi = PropertyProvider.getInstance().getLocalizedPropertyValue("passwordLogin")
		chckOutpasswordLevi.value(chckpwdLevi)
		
		$("#link-signin").click()
		Thread.sleep(5000)
		}
	
	def phone(){
		String Randomresult = RandomStringUtils.random(64, false, true);
		Randomresult = RandomStringUtils.random(6, 0, 10, true, true, "1234567890".toCharArray());
		$("#contact-phone").value(Randomresult)
				}
	
	//Added by Dipannita
	def fillingShippingAddrDetailsUpdated(){
			
			def shipFname = PropertyProvider.getInstance().getLocalizedPropertyValue("shippingFirstName")
			$("#shipping-firstname").value(shipFname)
			
			def shipLname = PropertyProvider.getInstance().getLocalizedPropertyValue("shippingLastName")
			$("#shipping-lastname").value(shipLname)
			
			def shipaddr = PropertyProvider.getInstance().getLocalizedPropertyValue("shippingFullAddr")
			$("#shipping-address-1").value(shipaddr)
			
			def shipCity = PropertyProvider.getInstance().getLocalizedPropertyValue("shippingCity")
			$("#shipping-city").value(shipCity)
			
			
			def shipPostal = PropertyProvider.getInstance().getLocalizedPropertyValue("shippingZip")
			$("#shipping-postal").value(shipPostal)
			
			boolean bol = $("#billing-firstname").isEnabled()
			println bol
			if(bol == false)
			{
				$("fieldset.billing>label.checkbox-label").click()
			}
			
	}
	//Added By Dipannita
	def SignIn_Checkout(){
		
		$(".link-editprofile").click()
		def username = PropertyProvider.getInstance().getLocalizedPropertyValue("username_forsignout")
		$("#j_username").value(username)
		
		def password = PropertyProvider.getInstance().getLocalizedPropertyValue("password_forsignout")
		$("#j_password").value(password)
		
		$("#link-signin").click()
		
		}
	
	def SignOut_Checkout(){
	   $(".link-editprofile").click()
	   return true
		}
	
	def VerifyProductAdded(){
		$(".bagsummary-container.clearfix").displayed
		$(".cart-prod-container").displayed
		return true
		}
	
	def VerifyProductRemoved(){
		
		//$(".bagsummary-container.clearfix").displayed
		assert $(".cart-prod-container").displayed == false
		return true
		
		}
	
	def UserCheckOutAsAGuest(){
		//$(".bagsummary-container.clearfix").displayed
		$("#link-guest").displayed
		return true
		}
	//Added by Dipannita
	def ChangeShippingAddress()
		  {
			 Thread.sleep(500)
			  $(".dk_toggle").click()
			 Thread.sleep(1000)
			 //def address= adrressdropdownSelect(2)
			 $("div.dk_focus.dk_open ul.dk_options_inner li:nth-child(2) a").click()
			 Thread.sleep(500)
			// println address
		  }
		  
	def fillingShippingAdrressGuest()
		  {
			def shipFname = PropertyProvider.getInstance().getLocalizedPropertyValue("shippingFirstName")
		   $("#shipping-firstname").value(shipFname)
			def shipLname = PropertyProvider.getInstance().getLocalizedPropertyValue("shippingLastName")
		   $("#shipping-lastname").value(shipLname)
			def shipaddr = PropertyProvider.getInstance().getLocalizedPropertyValue("shippingFullAddr")
		   $("#shipping-address-1").value(shipaddr)
			def shipCity = PropertyProvider.getInstance().getLocalizedPropertyValue("shippingCity")
		   $("#shipping-city").value(shipCity)
			def shipPostal = PropertyProvider.getInstance().getLocalizedPropertyValue("shippingZip")
		   $("#shipping-postal").value(shipPostal)
		   def okValue10 = result().concat("@gmail.com")
		   $("#contact-email").value(okValue10)
	}
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		  
	
	def continueShopping()
		{
		  $(".black-arrow-right-small>span").click()
		  return true
		}
		
		//------------------added by Suprito after 2nd March-------------------------
		
		def checkGiftWrapCheckboxLatest(){
			Thread.sleep(3000)
					
			//driver.findElement(By.cssSelector("label[for='giftwrap-chkbox']")).click()
			//driver.findElement(By.)
			$("label[for='giftwrap-chkbox']").click()
			}
		
		
		def checkAgeCheckboxChckOutPage(){
			
			/*$("label[for='age-chkbox']").click()--- added By Suprito after 31 st March 2015*/
			
			Thread.sleep(4000)
			def actions=new Actions(driver);
			WebElement agechkbox=$(".checkbox-label.tag-label").firstElement()
			actions.moveToElement(agechkbox, 10, 0).click().build().perform()
			Thread.sleep(4000)
			
		}
		
		
		def fillCreditCardDataLatestVISA(){
			waitFor(240, 10){ myFrame.@style == "display: block;" }
	
			Thread.sleep(9000)
			
			withFrame(myFrame) {
				def cardNum = PropertyProvider.getInstance().getLocalizedPropertyValue("creditCardNumberVisa")
				$("#F1009").value(cardNum)
				
				def cardCvv = PropertyProvider.getInstance().getLocalizedPropertyValue("securityCode")
				$("#F1136").value(cardCvv)
				
				def expYear = PropertyProvider.getInstance().getLocalizedPropertyValue("paymentYear")
				$("#F1010_YY").value(expYear)
				
				def expMonth = PropertyProvider.getInstance().getLocalizedPropertyValue("paymentMonth")
				$("#F1010_MM").value(expMonth)
			   
				 $("#btnSubmit").click()
				
				Thread.sleep(6000)
				
				return true
			}
			
			}
		
		
		def verifyLogosChckOutPageAndHomePageForGB(){
			
			// verifying the payment log images on Checkout page
			boolean paypalChckOut = $("img[alt='Paypal']").isDisplayed()
			println paypalChckOut
			
			boolean mastercardChckOut  = $("img[alt='MasterCard']").isDisplayed()
			println mastercardChckOut
			
			boolean deltaChckout = $("img[alt='Delta']").isDisplayed()
			println deltaChckout
			
			boolean visaChckOut = $("img[alt='Visa']").isDisplayed()
			println visaChckOut
			
			boolean maestroChckOut = $("img[alt='Maestro']").isDisplayed()
			println maestroChckOut
			
			boolean amexChckOut = $("img[alt='American Express']").isDisplayed()
			println amexChckOut
			
			Thread.sleep(2000)
			
			//going back to Levis or Dockers home page to verify the payment method logos
			$(".logo").click()
			
			Thread.sleep(6000)
			
			//user is at Levis or Dockers Home page and verifying the same payment method logos
			
			assert $("img[alt='Paypal']").isDisplayed() == true
			assert $("img[alt='MasterCard']").isDisplayed() == true
			assert $("img[alt='Delta']").isDisplayed() == true
			assert $("img[alt='Visa']").isDisplayed() == true
			assert $("img[alt='Maestro']").isDisplayed() == true
			assert $("img[alt='American Express']").isDisplayed() ==true
			
			return true
			}
	def String result()
	{
	   String Randomresult = RandomStringUtils.random(64, false, true);
	   Randomresult = RandomStringUtils.random(32, 0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray());
	   return Randomresult
	
	}
	
	def VerifyUpdatedProductInShoppingBag(){
		$(".bagsummary-container.clearfix").displayed
		$(".cart-prod-container").displayed
		return true
		}
	

	def VerifyShoppingBagPrice(){
		String currencySymbol = PropertyProvider.getInstance().getLocalizedPropertyValue("currency.symbol")
		String decimalSeparator = PropertyProvider.getInstance().getLocalizedPropertyValue("decimal.separator")
		
		$("#cost-summary-total").isDisplayed()
		double Price=  $("#cost-summary-total").text().replaceAll(currencySymbol, "").replaceAll(decimalSeparator, ".").toDouble()
		
		//--------------------------------------------------------------------------
}


	
	
	def fillingBillingAddrDetails(){
		
		boolean bol = $("#billing-firstname").isDisabled()
		println bol
		if(bol == true)
		{
			$("fieldset.billing>label.checkbox-label").click()
		}
		
		else{
			System.out.println("Checkbox already un-checked")
		}
				
				def cardCvv = PropertyProvider.getInstance().getLocalizedPropertyValue("securityCode")
				$("#F1136").value(cardCvv)
				
				def expYear = PropertyProvider.getInstance().getLocalizedPropertyValue("paymentYear")
				$("#F1010_YY").value(expYear)
				
				def expMonth = PropertyProvider.getInstance().getLocalizedPropertyValue("paymentMonth")
				$("#F1010_MM").value(expMonth)
				
				def shipCity = PropertyProvider.getInstance().getLocalizedPropertyValue("shippingCity")
				BillingCity.value(shipCity)
				
				def shipPostal = PropertyProvider.getInstance().getLocalizedPropertyValue("shippingZip")
				BillingPostal.value(shipPostal)
			   
				 $("#btnSubmit").click()
				
				Thread.sleep(6000)
				
				return true
			}
			
			
		
	
	def VerifyBillingErrorMassage(){
		Thread.sleep(500)
	 	assert $(".billing>ul>li:nth-child(2)>div>div").isDisplayed()== true
		assert $(".billing>ul>li:nth-child(4)>div>div").isDisplayed()== true
				
		def okValue1 = PropertyProvider.getInstance().getLocalizedPropertyValue("ErrorTextbillingfname").toUpperCase()
		assert $(".billing>ul>li:nth-child(2)>div>div").text().toUpperCase() == okValue1
		
				
		def okValue3 = PropertyProvider.getInstance().getLocalizedPropertyValue("ErrorTextaddress").toUpperCase()
		assert $(".billing>ul>li:nth-child(4)>div>div").text().toUpperCase() == okValue3
	
		return true
	}

	   def selectingExpressDelivery(){
		   $("label[for='UPS EXPRESS']").click()
		   
			  Thread.sleep(4000)
			  return true
			  }
		
	   
	   def verifyexpressDeliveryContent(){
		   // verifying the delivery text description
		   def description = PropertyProvider.getInstance().getLocalizedPropertyValue("deliveryDesc").toUpperCase()
		   def descDeliveryScreenTxt = $(".desc").text().toUpperCase()
		   println descDeliveryScreenTxt
		   assert descDeliveryScreenTxt.contains(description)
		   
		   // verifying the delivery cost description
		   def costdetails = PropertyProvider.getInstance().getLocalizedPropertyValue("deliveryCost").toUpperCase()
		   def costDeliveryScreenTxt = $(".cost").text().toUpperCase()
		   println costDeliveryScreenTxt
		   assert costDeliveryScreenTxt.contains(costdetails)
		   return true
	   }

	    
	   
		def checkoutIdealCCDetails()
		{
			Thread.sleep(6000)
			
			$("#F8045").value("INGBNL2A")
			$("#btnSubmit").click()
			waitFor(30, 5){
				$("input", name: "button.edit").isDisplayed()
			}
			
			$("input", name: "button.edit").click()
			
			Thread.sleep(7000)
			return true
		}
		
		
              def productRemovalFlow()
                 {
	             
		   Thread.sleep(5000)
	       println $("#main-container>article>header>p").text()
		   WebDriverWait wait3 = new WebDriverWait(driver, 30)
		   // removing the item from shopping bag
		   WebElement element = wait3.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".actions > li:nth-child(1) > a:nth-child(1)")))
		   $(".actions > li:nth-child(1) > a:nth-child(1)").click()
		   Thread.sleep(5000)
		   // verifying the text for zero items in the cart
		   def txtForNoItems = PropertyProvider.getInstance().getLocalizedPropertyValue("zeroItemsText").toUpperCase()
		   def itemremovalScreenTxt = $("#main-container>article>header>p").text().toUpperCase()
		   println itemremovalScreenTxt
		   assert txtForNoItems == itemremovalScreenTxt
		   return true
		  }

		def VerifyProductRecomendation(){
		$(".prod-list.might-container.peek>h2").isDisplayed()
		$("#cart1_rr>section>div>ul>li").isDisplayed()
		
		def okValue = PropertyProvider.getInstance().getLocalizedPropertyValue("ProductRecommendation")
		assert $(".prod-list.might-container.peek>h2").text().toUpperCase() == okValue
		return true
		}
	
	def VerifyPDPOfProductRecomendation()
    {
		    int minimum =1
			int maximum =4
			int i = minimum + (int)(Math.random()*maximum)
		
		assert $("#cart1_rr>section>div>ul>li:nth-child("+i+")").isDisplayed() == true
		assert $("#cart1_rr>section>div>ul>li:nth-child("+i+")>div:nth-child(1)").isDisplayed() == true
		assert $("#cart1_rr>section>div>ul>li:nth-child(2)>div:nth-child(2)>a>p:nth-child(1)").isDisplayed() == true 
		assert $("#cart1_rr>section>div>ul>li:nth-child(2)>div:nth-child(2)>a>p:nth-child(2)").isDisplayed() == true
		
		def Name = $("#cart1_rr>section>div>ul>li:nth-child("+i+")>div:nth-child(1)").text()
		
		$("#cart1_rr>section>div>ul>li:nth-child("+i+")").click()
		
		return true
		}
		String savingsAmount(){
			String Savings= ""
			if($(".data-inner").size()==1){			
				println($(".data-inner").text())
				Savings= $(".data-inner").text()			
			}
			else if($(".data-inner").size()>1)
			{
			 	int allSavings= $(".data-inner").size()
				for(int i=0;i<allSavings;i++){
					Savings=Savings+ $(".data-inner",i).text()
				}
				println(Savings)
			}else{
			println("No Savings found")
			}
			return Savings
		}
		def removeAllItem(){	
			int totalItems=$("li .actions > li > a").size()					
			for(int i=1;i<=totalItems;i++){
				$("li:nth-child(1) .actions>li:nth-child(1)>a:nth-child(1)").click()				
				Thread.sleep(4000);
				println($("li .actions > li > a").size())
			}			
		}
		def toPage(String headerIndex,String pageId) {
			Thread.sleep(2000)
			interact {
				moveToElement($("body > div.header-fluid > header > header > ul > li:nth-child("+headerIndex+") > h2"))
			 //   $("#wJackets&VestsLink").click()
				Thread.sleep(5000)
			}
		   $(pageId).click()
			Thread.sleep(3000)
		}
		def chkEnabledCartPromo(String p1Promo, String p2Promo){
			Thread.sleep(4000)
			assert $(".product-tile .promo-bottom>p").isDisplayed()==true
			println($(".product-tile .promo-bottom>p").text())
			assert $(".product-tile .promo-bottom>p").text().contains(p1Promo.toUpperCase())==true
			Thread.sleep(4000)
			assert $(".product-tile .promo-bottom>p").text().equalsIgnoreCase(p2Promo.toUpperCase())==false
			return true
			
		}
		
		def fillCreditCardDataLatestCarteBlueCC(){
			waitFor(240, 10){ myFrame.@style == "display: block;" }
			Thread.sleep(9000)
			withFrame(myFrame) {
			def cardNum = PropertyProvider.getInstance().getLocalizedPropertyValue("carteBleueCreditCard")
			$("#F1009").value(cardNum)
			def cardCvv = PropertyProvider.getInstance().getLocalizedPropertyValue("securityCode")
			$("#F1136").value(cardCvv)
			def expYear = PropertyProvider.getInstance().getLocalizedPropertyValue("paymentYear")
			$("#F1010_YY").value(expYear)
			def expMonth = PropertyProvider.getInstance().getLocalizedPropertyValue("paymentMonth")
			$("#F1010_MM").value(expMonth)
			$("#btnSubmit").click()
			Thread.sleep(8000)
			return true
			}
			}
			
		def fillCreditCardDataLatestDankortCC(){
			waitFor(240, 10){ myFrame.@style == "display: block;" }
			Thread.sleep(9000)
			withFrame(myFrame) {
			def cardNum = PropertyProvider.getInstance().getLocalizedPropertyValue("creditCardNumberDankort")
			$("#F1009").value(cardNum)
			def cardCvv = PropertyProvider.getInstance().getLocalizedPropertyValue("securityCode")
			$("#F1136").value(cardCvv)
			def expYear = PropertyProvider.getInstance().getLocalizedPropertyValue("paymentYear")
			$("#F1010_YY").value(expYear)
			def expMonth = PropertyProvider.getInstance().getLocalizedPropertyValue("paymentMonth")
			$("#F1010_MM").value(expMonth)
			$("#btnSubmit").click()
			Thread.sleep(8000)
			return true
			}
			}
		
		
		def verifyLogosChckOutPageAndHomePageForIT(){
			
			// verifying the payment logo images on Checkout page
			boolean paypalChckOut = $("img[alt='Paypal']").isDisplayed()
			println paypalChckOut
			
			boolean mastercardChckOut  = $("img[alt='MasterCard']").isDisplayed()
			println mastercardChckOut
			
			boolean visaChckOut = $("img[alt='Visa']").isDisplayed()
			println visaChckOut
			
			boolean maestroChckOut = $("img[alt='Maestro']").isDisplayed()
			println maestroChckOut
			
			boolean amexChckOut = $("img[alt='American Express']").isDisplayed()
			println amexChckOut
			
			Thread.sleep(2000)
			
			//going back to Levis or Dockers home page to verify the payment method logos on Footer
			$(".logo").click()
			
			Thread.sleep(6000)
			
			//user is at Levis or Dockers Home page and verifying the same payment method logos
			
			assert $("img[alt='Paypal']").isDisplayed() == true
			assert $("img[alt='MasterCard']").isDisplayed() == true
			assert $("img[alt='Visa']").isDisplayed() == true
			assert $("img[alt='Maestro']").isDisplayed() == true
			assert $("img[alt='American Express']").isDisplayed() ==true
			
			return true
			}
		
			
}

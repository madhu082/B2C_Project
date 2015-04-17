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
//added by Suprito after 12 th March 2015
import org.openqa.selenium.*
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions

class LevisHomePage extends Page {

	static url = ""
	
	static at = {
		driver.navigate().refresh()
		def okBrand = PropertyProvider.getInstance().getLocalizedPropertyValue("brand")
		//def okCountry = PropertyProvider.getInstance().getLocalizedPropertyValue("country")
		assert title.toUpperCase().contains(okBrand)
		//assert $("#global-lang-cta").text().toUpperCase() == okCountry
		return true
	}

	static content = { pageData { js.pageData } }


	def toMyAccountPage(){
		// added By Suprito after 31 st March 2015
		driver.navigate().refresh()
		Thread.sleep(5000)
		//waitFor(100){$("#global-myaccount-cta")}
		if ($("#global-myaccount-signout").isDisplayed()){
			$("#global-myaccount-signout").click()
		}
		
		else{
			println "SignOut Link is NOT PRSENT"
		}
		
		Thread.sleep(5000)
		$("#global-myaccount-cta").click(LevisLoginPage)
	
		Thread.sleep(3000)
	}

	def toRegistrationPage(){
		$("#global-myaccount-cta").click(AccountRegistrationPage)
	}
	
	def toFirstRootCategoryPage() {
		$("body > div.header-fluid > header > header > ul > li:nth-child(1) > h2 > a").click()
	}

	def toWomenCategoryPage() {
		//waitFor(30){ $(".email-lightbox").@style == "display: none;" }
		driver.navigate().refresh()
		Thread.sleep(500)
		
		interact {
			moveToElement($("body > div.header-fluid > header > header > ul > li:nth-child(2) > h2"))
		}
		waitFor(60,3){$("#wJeansLink").isDisplayed()}
		$("#wJeansLink").click()
	}

    def toAccessoriesShopAllPage() {
        interact {
            moveToElement($("body > div.header-fluid > header > header > ul > li:nth-child(4) > h2"))
        }
        $("#MenAccessoriesShopAllLink").click()
    }
	def toClothingTrousersCategoryPage() {
		driver.navigate().refresh()
		
		interact {
			moveToElement($("body > div.header-fluid > header > header > ul > li:nth-child(1) > h2 > a"))
		}
		$("#MenTrousersLink").click()
	}
	
	def toClothingSweatersCategoryPage() {
		driver.navigate().refresh()
		
		interact {
			moveToElement($("body > div.header-fluid > header > header > ul > li:nth-child(1) > h2 > a"))
		}
		$("#MenSweatersLink").click()
	}
	
	def dismissPopup(){
		//Added By Suprito
		Thread.sleep(3000)
		if ($("div.email-signup > div:nth-child(1) > span:nth-child(1)").isDisplayed()){
			$("div.email-signup > div:nth-child(1) > span:nth-child(1)").click()
		}
	}

	def searchTerm(term){
		$("#input-search").value(term)
		$("#search-bar > a").click()
	}

	def searchRandomTerm(int length){
		searchTerm("jn!t@#"+generateRamdomString(length))
	}

	private String generateRamdomString(int length) {
		RandomStringUtils.randomAlphanumeric(RandomUtils.nextInt(length) + 1)
	}

	def verifyPopulation(searchTerm){
		def searchTag = "search:"
		def searchTermTag = "term:"
		def searchResultTag = "result_count:"

		StringBuilder pageDataText = new StringBuilder(pageData.toString());

		def searchTagInitialIndex = pageDataText.indexOf(searchTag)

		//verify that searchTag is present
		assert searchTagInitialIndex != -1

		pageDataText.replace(0, pageDataText.length(),  pageDataText.substring(searchTagInitialIndex))
		def searchFinalIndex =  pageDataText.indexOf("]")
		pageDataText.replace(0, pageDataText.length(), pageDataText.substring(0, searchFinalIndex +1))
		def searchTermTagInitialIndex = pageDataText.indexOf(searchTermTag)
		def searchResultTagInitialIndex = pageDataText.indexOf(searchResultTag)

		//verify that termTag is present
		assert searchTermTagInitialIndex != -1
		//verify that resultTag is present
		assert searchResultTagInitialIndex != -1

		String searchTermTagValue = pageDataText.substring(searchTermTagInitialIndex + searchTermTag.size(), pageDataText.indexOf(","))
		String searchResultTagValue = pageDataText.substring(searchResultTagInitialIndex + searchResultTag.size(), pageDataText.indexOf("]"))

		assert searchTerm == searchTermTagValue
		assert searchResultTagValue.toInteger() > 0
		assert $("span.productCount").text() == searchResultTagValue
	}

	def toAllCollectionsPage(){
		$("a.cta.see-all").click()
		//waitFor(30){ $("#main-container > article:nth-child(1) > header > article > div > h1").text() == "JEANS"}
	}
	
	def createNewEmail(arg1){
		def newEmail = RandomStringUtils.randomAlphanumeric(20)
		RegistrationDataModel.getInstance().email = newEmail
		$("#inboxfield").value(newEmail)
		$("btn.btn.btn-success").click()
	}
	
	def checkIfSignoutIsRequired(){
		if ($("#global-myaccount-signout").isDisplayed()){
			$("#global-myaccount-signout").click()
		}
	}

	def emptyTheBag(){
		  def myBagQuantity = $("#cart-container > a > div > h3 > span")
		  int bagQuantity = myBagQuantity.text().trim().toInteger()
		  while(bagQuantity > 0){
			$("#cart-container > a > div").click()
			$(".actions > li:nth-child(1) > a:nth-child(1)").click()
			$(".logo").click()
			myBagQuantity = $("#cart-container > a > div > h3 > span")
			bagQuantity = myBagQuantity.text().trim().toInteger()
		}
		ProductDataModelMap.getInstance().getProductMap().clear()
	}

	def deleteCookies(){
		getBrowser().clearCookies()
	}
	
	def toMyAccount(){
		$("#global-myaccount-signout").click()
	}
	
	
	//added by Suprito------------------------
	
	def toAccountPageLatest(){
		driver.navigate().refresh()
		$("#global-myaccount-cta").click()
	}
	
	def searchProductForGuestUsr(){
		
		def skuItemName = PropertyProvider.getInstance().getLocalizedPropertyValue("itemSearch")
		waitFor{$("#input-search")}.value(skuItemName)
		$("#search-bar > a").click()
	}
	
	
	def clickOnTheSearchedItemForGuestUsr(){
		Thread.sleep(3000)
		//$(".list-type-04>li>div:nth-child(1)>a>img").click()
		$(".stage>img").click()
	}
	//Added by dipannita
	def SearchProductIndeca()
	{
	driver.navigate().refresh()
	def skuItemName = PropertyProvider.getInstance().getLocalizedPropertyValue("searchinIndeca")
	waitFor{$("#input-search")}.value(skuItemName)
	$("#search-bar > a").click()
	return true
	}
	
	
	//Added by dipannita
	 def HandleNewsletterPopUp()
	    {
		
	String Randomresult = RandomStringUtils.random(64, false, true);
	Randomresult = RandomStringUtils.random(6, 0, 10, true, true, "abcdefghij".toCharArray());
	
	def okValue1 = Randomresult.concat("@gmail.com")
	 $("#input-email-popup").value(okValue1)
	
	 Thread.sleep(2000)
	// $(".global-nav>ul:nth-child(2)>div:nth-of-type(2)>div>form#EmailSignupForm>label:nth-of-type(2)").click()
	 //$("input[name='ageCheck']").click()
	 driver.findElement(By.xpath(".//*[@id='EmailSignupForm']/label[1]")).click()
	  Thread.sleep(1000)
	  $("#link-email-popup").click()
	  }
	
		
	//Added by dipannita
	def NewsletterSubscribed()
	   {
	  $(".tooltip-wrapper>h6").text().toUpperCase() == "THANKS!"
	 //	 return true
	   }
	   
	  //Added by dipannita
	def VerifyPopUpLinks()
	   {
	 $(".tooltip-wrapper>h6").text().toUpperCase() == "SUBSCRIBE & SAVE"
	 
	 $(".privacy-terms-text>a:nth-child(1)").click()
	 assert $(".content.wysiwyg>h1").text().toUpperCase() == "TERMS & CONDITIONS"
	 $(".fancybox-item.fancybox-close").click()
	 
	 $(".privacy-terms-text>a:nth-child(2)").click()
	 assert $(".content.wysiwyg>h1").text().toUpperCase() == "PRIVACY POLICY"
	 $(".fancybox-item.fancybox-close").click()
	 
	 return true
	  }
	   
	   //Added by dipannita
	 def ClothingCategoryPage() {
		   driver.navigate().refresh()
		   $(".subnav-list.notouch-device>li>h2>a").click()
		   $("#shop-by-style>ul>li>a>h4").click()
       }
	   
	   //Added by dipannita
    def filterbyColor() {
		   Thread.sleep(4000)
		   $(".centeredlist>ul>li:nth-child(3)>div>a").click()
		   Thread.sleep(2000)
		   //def color = filterdropdownSelect(2)
		   $(".centeredlist>ul>li:nth-child(3)>div>ul>li:nth-child(3)").click()
	   }
	   //Added by dipannita
   def filterbyColor_LEVI() {
		   Thread.sleep(4000)
		   $(".centeredlist>ul>li:nth-child(7)>div>a").click()
		   Thread.sleep(1000)
		   //def color = filterdropdownSelect(2)
		   $(".centeredlist>ul>li:nth-child(7)>div>ul>li:nth-child(3)").click()
	   return true
	   }
   
   //Added by dipannita
   def clickOnTheFilteredItem(String){
	   
	   $(".product-images>a>img").click()
	   String Name= $(".title").text()
	   println Name
	   return true
	   
	   }
   def searchProduct()
   {
   driver.navigate().refresh()
   def skuItemName = PropertyProvider.getInstance().getLocalizedPropertyValue("itemSearch")
   waitFor{$("#input-search")}.value(skuItemName)
   $("#search-bar > a").click()
   return true
   }
   
   def searchAnotherProduct()
   {
   driver.navigate().refresh()
   def skuItemName = PropertyProvider.getInstance().getLocalizedPropertyValue("AnotheritemSearch")
   waitFor{$("#input-search")}.value(skuItemName)
   $("#search-bar > a").click()
   return true
   }
   
   //Added by dipannita
   def clickOnTheSearchedItem(){
	   
	   //$(".list-type-04>li>div:nth-child(1)>a>img").click()
	   $(".stage>img").click()
	   return true
	   
	   }
   
   //Added by dipannita
   def LoginAfterLocaleChange()
   {
	 driver.navigate().refresh()
	 $("#global-myaccount-cta2").click()
	 Thread.sleep(1000)
	 driver.navigate().refresh()
	 $(".section-header.center-header").text().toUpperCase() == "MEIN KONTO"
	Thread.sleep(1000)
	$("#AddressLink").click()
	$(".shipping-address-list>div>.btn-dbbdr").click()
	//def okValue10 = PropertyProvider.getInstance().getLocalizedPropertyValue("billingfname")
	$("#firstName").value("Fname")
	
	//def okValue11 = PropertyProvider.getInstance().getLocalizedPropertyValue("billinglname")
	$("#lastName").value("Lname")
	
	//def okValue12 = PropertyProvider.getInstance().getLocalizedPropertyValue("address")
	$("#line1").value("ABCD Road")
			
	//def okValue13 = PropertyProvider.getInstance().getLocalizedPropertyValue("town")
	$("#townCity").value("Berlin")
	
	//def okValue14 = PropertyProvider.getInstance().getLocalizedPropertyValue("postalcode")
	$("#postcode").value("10117")
   
	
	String Randomresult = RandomStringUtils.random(64, false, true);
	Randomresult = RandomStringUtils.random(6, 0, 10, true, true, "1234567890".toCharArray());
	$("#phone").value(Randomresult)
	
	
	$(".default-address-text").click()
	$(".btn-dbbdr").click()
	//return true
	}

//Added by dipannita
   def  ChangeLocale2() {
	   driver.navigate().to(PropertyProvider.getInstance().basicURL+"/GB/en_GB/login")
	   //driver.navigate().to("https://qa-500-web-000.levi-site.com/GB/en_GB/login")
	   Thread.sleep(2000)
	   return true
	}
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
   
   //------------added by Suprito after 6th March-------------------
   
   def myAccountLinkabsentForRussia()
   {
	   boolean myAccountLinkDisplay =  $("#global-myaccount-cta").isDisplayed()
	   println myAccountLinkDisplay
   
	   assert myAccountLinkDisplay == false
	   
   return true
   }
   
   def searchInvalidProduct(){
	   
	   def skuItemName = PropertyProvider.getInstance().getLocalizedPropertyValue("invalidItem")
	   waitFor{$("#input-search")}.value(skuItemName)
	   $("#search-bar > a").click()
	   
	   Thread.sleep(5000)
   }

    def toPage(String headerIndex,String pageId) {
        driver.manage().window().maximize();
		interact {
            moveToElement($("body > div.header-fluid > header > header > ul > li:nth-child("+headerIndex+") > h2"))
         //   $("#wJackets&VestsLink").click()
			Thread.sleep(2000)
        }
		
       $(pageId).click()
        Thread.sleep(3000)
    }
	
	def callUsFooterCheck(){
		String okBrand1 = PropertyProvider.getInstance().getLocalizedPropertyValue("CallUS")
		String okBrand2 = PropertyProvider.getInstance().getLocalizedPropertyValue("MonFriTime")
		String okBrand3 = PropertyProvider.getInstance().getLocalizedPropertyValue("SatTime")
		
		println($("ul.footer-service>li>span",1).text())
		println($("ul.footer-service>li>span",2).text())
		println($("ul.footer-service>li>span",3).text())
		println($("ul.footer-service>li>span",3).text())
		
		
		assert $("ul.footer-service>li>span#phone-icon").isDisplayed() 
		assert $("ul.footer-service>li>span",1).text()== okBrand1
		assert $("ul.footer-service>li>span",2).text().length() >=1
		assert $("ul.footer-service>li>span",3).text().contains(okBrand2)
		assert $("ul.footer-service>li>span",3).text().contains(okBrand3)
		return true	
		
	}
	def callUsFooterExist(boolean yesno){
		assert $("ul.footer-service>li>span#phone-icon").isDisplayed() == yesno
		return true
	}

 //------------added By Suprito after 12 th March 2015--------
   
   def navigatingToWomenJeans() {
	   Thread.sleep(5000)
	   WebDriverWait wait2 = new WebDriverWait(driver, 30)
	   
	   WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > div.header-fluid > header > header > ul > li:nth-child(2) > h2")))
	   interact {
		   moveToElement($("body > div.header-fluid > header > header > ul > li:nth-child(2) > h2"))
	   }
	   $("#wJeansLink").click()
	   
	   Thread.sleep(5000)
   }
   	
 
   

   def navigatingToWomenJackets() {
	   
	   Thread.sleep(5000)
	   WebDriverWait wait = new WebDriverWait(driver, 30)
	   WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > div.header-fluid > header > header > ul > li:nth-child(2) > h2")))
	   
	   interact {
		   moveToElement($("body > div.header-fluid > header > header > ul > li:nth-child(2) > h2"))
	   }
	   $("a[href*='category/women/clothing/jackets-vests/all']").click()
	   
	   Thread.sleep(5000)
   }
	 
   
   def toSweatersPage() {
	   driver.navigate().refresh()
	   
	   Thread.sleep(5000)
	   interact {
		   moveToElement($("body > div.header-fluid > header > header > ul > li:nth-child(1) > h2 > a"))
	   }
	   $("#MenSweatersLink").click()
	   Thread.sleep(5000)
   }
   
   
   def clickShippingLink()
   {
	   Thread.sleep(5000)
	   WebDriverWait wait3 = new WebDriverWait(driver, 30)
	   WebElement element = wait3.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#ShippingLink")))
	   $("#ShippingLink").click()
   }
   
   
   def verifyContentsOnSHIPPINGsERVICEandCOSTS()
   {
	   Thread.sleep(5000)
	   //WebDriverWait wait4 = new WebDriverWait(driver, 30)
	   //WebElement element = wait4.until(ExpectedConditions.elementIfVisible(By.cssSelector(".clearfix>section")))
	 // fetching the entire text of Shipping Service and Costs Screen
	   def entireContentShippingSrviceAndCost = $(".clearfix>section").text().toUpperCase()
	   println entireContentShippingSrviceAndCost
	 //checking whether the Shipping service and Cost text is present
	 def shippingSrvcHeaderTxt = PropertyProvider.getInstance().getLocalizedPropertyValue("shippingSrviceTxt").toUpperCase()
	 assert entireContentShippingSrviceAndCost.contains(shippingSrvcHeaderTxt)
	 //checking whether express delivery rule1 is present
	 def expDelvryRule1 = PropertyProvider.getInstance().getLocalizedPropertyValue("expressDelvryRule1").toUpperCase()
	 assert entireContentShippingSrviceAndCost.contains(expDelvryRule1)
	 //checking whether express delivery rule 2 is present
	 def expDelvryRule2 = PropertyProvider.getInstance().getLocalizedPropertyValue("expressDelvryRule2").toUpperCase()
	 assert entireContentShippingSrviceAndCost.contains(expDelvryRule2)
	 //checking whether express delivery rule3 is present
	 def expDelvryRule3 = PropertyProvider.getInstance().getLocalizedPropertyValue("expressDelvryRule3").toUpperCase()
	 assert entireContentShippingSrviceAndCost.contains(expDelvryRule3)
	 //clicking the ahip to diff address link and verifying header text
	 $("a[href*='customer-service/shipping/shipdiffaddress']").click()
	 Thread.sleep(5000)
	 def shipDiffAddr = PropertyProvider.getInstance().getLocalizedPropertyValue("shipToDifAddr").toUpperCase()
	 def shipDiffAddrScreenTxt = $(".clearfix>section").text().toUpperCase()
	 println shipDiffAddrScreenTxt
	 assert shipDiffAddrScreenTxt.contains(shipDiffAddr)
	 //click to delivery prob link and verifying header txt
	 $("a[href*='customer-service/shipping/deliveryproblem']").click()
	 Thread.sleep(5000)
	 def delivryprobText = PropertyProvider.getInstance().getLocalizedPropertyValue("delveryprob").toUpperCase()
	 def deliveryScreenTxt = $(".clearfix>section").text().toUpperCase()
	 println deliveryScreenTxt
	 assert deliveryScreenTxt.contains(delivryprobText)
	// click to order processing link and verifying the header txt 
	 $("a[href*='customer-service/shipping/orderprocessing']").click()
	 Thread.sleep(5000)
	 def orderprocess = PropertyProvider.getInstance().getLocalizedPropertyValue("orderProcessTime").toUpperCase()
	 def orderProcessingScreenTxt = $(".clearfix>section").text().toUpperCase()
	 println orderProcessingScreenTxt
	 assert orderProcessingScreenTxt.contains(orderprocess)
	 
	 return true
	   
   }
     
   
   def navigatingToFitGuideMensAndWomen() {
	   Thread.sleep(5000)
	   WebDriverWait wait5 = new WebDriverWait(driver, 30)
	   // clicking on FitGuide section under Women Department
	   WebElement element = wait5.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > div.header-fluid > header > header > ul > li:nth-child(2) > h2")))
	   interact {
		   moveToElement($("body > div.header-fluid > header > header > ul > li:nth-child(2) > h2"))
	   }
	   $("a[href*='fitguide/women/clothing#']").click()
	   Thread.sleep(7000)
	   def women = PropertyProvider.getInstance().getLocalizedPropertyValue("fitGuideWomensGenderTxt").toUpperCase()
	   def fitGuideWomenScreenTxt =  $(".chooser-container>header>span").text().toUpperCase()
	   println fitGuideWomenScreenTxt
	   assert fitGuideWomenScreenTxt == women
	   
	   Thread.sleep(4000)
	  // clicking on FitGuide section under Mens Department
	    WebElement element1 = wait5.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > div.header-fluid > header > header > ul > li:nth-child(1) > h2")))
	   interact {
		   moveToElement($("body > div.header-fluid > header > header > ul > li:nth-child(1) > h2"))
	   }
	   $("a[href*='fitguide/men/clothing#']").click()
	   Thread.sleep(7000)
	   def men = PropertyProvider.getInstance().getLocalizedPropertyValue("fitGuideMensGenderTxt").toUpperCase()
	   def fitGuideMenScreenTxt =  $(".chooser-container>header>span").text().toUpperCase()
	   println fitGuideMenScreenTxt
	   assert fitGuideMenScreenTxt == men
	   return true
   }
   
   
   def searchProductWithMultipleSwatchesHomePage(){
	   
	   def skuItemName = PropertyProvider.getInstance().getLocalizedPropertyValue("productWithMultipleColour")
	   waitFor{$("#input-search")}.value(skuItemName)
	   $("#search-bar > a").click()
   }
   
   
   def clickOnTheSearchedItemHomePage(){
	   Thread.sleep(3000)
	  // $(".list-type-04>li>div:nth-child(1)>a>img").click()
	   $(".stage>img").click()
   }

  def SearchProductWithMultipleSwatches(){
	   
	   def skuItemName = PropertyProvider.getInstance().getLocalizedPropertyValue("productWithMultipleColour")
	   waitFor{$("#input-search")}.value(skuItemName)
	   $("#search-bar > a").click()
   }
   def toFitGuideWomenPage() {
	   driver.navigate().refresh()
	   interact {
		   moveToElement($("body > div.header-fluid > header > header > ul > li:nth-child(2) > h2 > a"))
	   }
	   $("#Levi_Women_Fitguide").click()
   }
   def toFitGuideMenPage() {
	   driver.navigate().refresh()
	   interact {
		   moveToElement($("body > div.header-fluid > header > header > ul > li:nth-child(1) > h2 > a"))
	   }
	   $("#Levi_Men_FitGuide").click()
   }

boolean goTobagDetails(){
		Thread.sleep(3000)
			interact {
				moveToElement($(".mini-cart"))
				Thread.sleep(1000)
			}
			Thread.sleep(3000)
		   $(".mini-cart").click()
		   // $(".button.btn-highlight>p").click()
		  }
	

//---------------------added By Suprito after 31 st March 2015

def accesoriesSeeAllHomePage(){
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


def womenShopAllLinkLeviHomePage(){
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

def menShopAllLinkLeviHomePage(){
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

 
def verifyloadAddtionlProdLeviRUHomePage() {
	//verifying whether the control goes to all category page
	def shopAllMenHeaderScreentxt = $(".content.tile-02>h1").text().toUpperCase()
	println shopAllMenHeaderScreentxt
	def shopAllHeader = PropertyProvider.getInstance().getLocalizedPropertyValue("shopAllHeaderTxt").toUpperCase()
   
	 assert shopAllMenHeaderScreentxt == shopAllHeader
	
	// Selecting the option "Product A-Z" option from "Sort them by" drop-down
	WebDriverWait wait11 = new WebDriverWait(driver, 30)
	WebElement element = wait11.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".dk_toggle")))
	$(".dk_toggle").click()
	Thread.sleep(4000)
	driver.findElement(By.xpath(".//*[@id='dk_container_sort-select']/div/ul/li[2]/a")).click()
	
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
	
	  // printing all the additional products in Console.Check the console to get the list of sorted products.
	def products = $("#container_results").text()
	println products
	Thread.sleep(8000)

	
	return true

}


def vrifyloadAdtionlProdAccsoriesLeviRUHome() {
	//verifying whether the control goes to all category page
	def accesoriesScreentxt = $("#main-container>article:nth-child(1)>header>article>div>h1").text().toUpperCase().trim()
	println accesoriesScreentxt
	def accesoriesHeader = PropertyProvider.getInstance().getLocalizedPropertyValue("accessoriesHeaderTxt").toUpperCase().trim()
	println accesoriesHeader
	accesoriesScreentxt == accesoriesHeader
	
	 // Selecting the option "Product A-Z" option from "Sort them by" drop-down
	WebDriverWait wait12 = new WebDriverWait(driver, 30)
	WebElement element = wait12.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".dk_toggle")))
	$(".dk_toggle").click()
	Thread.sleep(4000)
	driver.findElement(By.xpath(".//*[@id='dk_container_sort-select']/div/ul/li[2]/a")).click()
	
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
	
	  // printing all the additional products in Console.Check the console to get the sorted products based on filter
	def products = $("#container_results").text()
	println products
	Thread.sleep(8000)

	
	return true

}

	
}
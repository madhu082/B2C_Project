package com.lsco.test.page

import geb.Page
import org.apache.commons.lang.RandomStringUtils
import com.lsco.test.PropertyProvider
import org.openqa.selenium.WebDriver
import com.lsco.test.PropertyProvider

class ProductDetailPage extends Page{


	String getPageUrl() {
		String url = browser.config.rawConfig.baseUrl + "p/" + PropertyProvider.getInstance().getGeneralSitePropertyValue("promotion.product.code")
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

def chkdetails(){
        Thread.sleep(20000)
        assert $(".alternate-images>ul>li").size() >=1
        String okval= PropertyProvider.getInstance().getLocalizedPropertyValue("Details").trim()
        assert $(".pdp-details>.pdp-description>h3").text()== okval
        assert $(".pdp-details>ul>li").size() >=1
        //Thread.sleep(2000)
        return true
    }

    Boolean chkRecentlyviewedSec(){
        Thread.sleep(20000)
        println($("div#product3_rr>div>h2").text())
        String okval= PropertyProvider.getInstance().getLocalizedPropertyValue("Recentlyviewedh2").trim()
        println(okval)
        if($("div#product3_rr>div>h2").text().contains(okval)){
            return true
        }else{
            return false
        }
        //assert $("div#product3_rr>div>div>div>ul>li").size()>=1
        //return true
    }

    String selectAnySize() {
		if($("#pdp-buystack-size-values>li").size()!=0)
		{
			if($("#pdp-buystack-size-values>li[class='size-swatch-wrapper notouch-device selected']").size()==0)
			{
				$("#pdp-buystack-size-values>li[class='size-swatch-wrapper notouch-device']",0).click()
				Thread.sleep(2000)
			}
		}
		if($("#pdp-buystack-waist-values>li").size()!=0)
		{
			if($("#pdp-buystack-waist-values>li[class='size-swatch-wrapper notouch-device selected']").size()==0)
			{
				$("#pdp-buystack-waist-values>li[class='size-swatch-wrapper notouch-device']",0).click()
				Thread.sleep(2000)
			}
		}
		if($("#pdp-buystack-length-values>li").size()!=0)
		{
			if($("#pdp-buystack-length-values>li[class='size-swatch-wrapper notouch-device selected']").size()==0)
			{
				$("#pdp-buystack-length-values>li[class='size-swatch-wrapper notouch-device']",0).click()
				Thread.sleep(2000)
			}
		}	

    }
    def selectProductQuantity(String index) {
        $(".select-arrow-box").click()
		Thread.sleep(2000)
        $(".faux-select-group >li:nth-child(" + index + ")").click()
    }
    def clickAddToBag(){
        $(".button-large.btn-highlight.add-to-bag").click()

    }
    String getProductTitle(){
        String title = $(".title").text()
        return title
    }
    String getProductStyle(){
        String title = $(".style-no").text()
        return title
    }
    boolean verifyAddToBagWithoutSelect() {
        Thread.sleep(4000)
        boolean breturn= false
        if ( $("#pdp-buystack-button-error>p").isDisplayed() ){
            println("Error Msg displayed")
            breturn= true
        }
        else{
            println("Error Msg not displayed")
            breturn= false
        }
        return breturn
        }

    boolean verifyIteminbag(String pdname, String pdStyle){
            Thread.sleep(3000)
            interact {
                moveToElement($(".mini-cart"))
                Thread.sleep(1000)
            }
            Thread.sleep(3000)
            $(".button.btn-highlight>p").click()

            Thread.sleep(4000)

        assert  $(".sku>span", text: iContains(pdStyle)).size() ==1
        assert  $("div.prod-name-descript>.name", text: iContains(pdname)).size() ==1
        return true
    }
    boolean verifyMaxSKUError(String MaxSkuError){
        Thread.sleep(3000)
        interact {
            moveToElement($(".mini-cart"))
            Thread.sleep(1000)
        }
        Thread.sleep(3000)
        assert  $("#maxSkuError>span").isDisplayed()
		
		String okval= PropertyProvider.getInstance().getLocalizedPropertyValue(MaxSkuError).trim().toUpperCase()
		//String okval= PropertyProvider.getInstance().getLocalizedPropertyValue(MaxSkuError).toUpperCase()
        //assert  $("#maxSkuError>span", text: iContains(okval)).size() ==1
		println("Error msg displayed="+ $("#maxSkuError>span").text())
		println("Error msg given in input="+okval)
		assert  $("#maxSkuError>span").text().contains(okval)
        return true
    }
	def checkPDPofMarking(){
		assert $(".mini-cart").size()==0
		assert $(".select-arrow-box").size()==0
		assert $(".button-large.btn-highlight.add-to-bag").size()==0
		return true
		
	}
	def checkProductPromo(boolean yesno){
		assert $(".cart-promo .subsale h4 a p").isDisplayed()== yesno		
		return true
		
	}
	boolean goTobagDetails(){		
		interact {
			moveToElement($(".mini-cart"))
			Thread.sleep(3000)
		}
		Thread.sleep(1000)
		$(".mini-cart").click()
		//$(".button.btn-highlight>p").click()
	}
	boolean verifySaleNotice(){
		interact {
			moveToElement($(".mini-cart"))
			Thread.sleep(1000)
		}
		return ($("div.cart-notices .cart-sale-notice").isDisplayed())
	}
	
	def openPromoDetailsFromLatestDeals(){
		$("div.latest-deal-details .button-large.txt-highlight.link-lightbox>p").click()
		Thread.sleep(4000)
		
	}
	def chkPromodetails(String promoIdenifier,String Promocode){
		
		//assert $("#promo-shipping>h4").text().contains(promoIdenifier)
		assert $("#promo-shipping>h4").isDisplayed()
		assert $("#promo-shipping>p").size()>=1		
		return true
	}
	def openPromoDetailsFromCartPromo(){
		$("div.cart-promo .button.txt-highlight.link-lightbox>p").click()
		Thread.sleep(4000)			
	}

	def closepromodetails(){
		$("div.fancybox-item.fancybox-close").click()
		
	}
	
	def chkEnabledCartPromo(String p1Promo, String p2Promo){
		Thread.sleep(4000)
		println($("div.cart-promo h4").text())
		assert $("div.cart-promo h4").text().equalsIgnoreCase(p1Promo)==true
		Thread.sleep(4000)
		assert $("div.cart-promo h4").text().equalsIgnoreCase(p2Promo)==false
		return true	
	}
	
	boolean availablesize(int size1){
		println($("#pdp-buystack-size-values>li[class='size-swatch-wrapper notouch-device']").size())
		//assert ($("#pdp-buystack-size-values>li[class='size-swatch-wrapper notouch-device']").size()>=size1) ==true
		return $("#pdp-buystack-size-values>li[class='size-swatch-wrapper notouch-device']").size()>=size1
	}
	
	
	String selectSize(int size1) {
		if($("#pdp-buystack-size-values>li").size()!=0)
		{
			
				$("#pdp-buystack-size-values>li[class='size-swatch-wrapper notouch-device']",size1).click()
				Thread.sleep(2000)
			
		}
		if($("#pdp-buystack-waist-values>li").size()!=0)
		{
				$("#pdp-buystack-waist-values>li[class='size-swatch-wrapper notouch-device']",size1).click()
				Thread.sleep(2000)
			
		}
		if($("#pdp-buystack-length-values>li").size()!=0)
		{
			
				$("#pdp-buystack-length-values>li[class='size-swatch-wrapper notouch-device']",size1).click()
				Thread.sleep(2000)
			
		}
	}
}
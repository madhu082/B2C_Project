package com.lsco.test.page.navigation

import geb.Page

import com.lsco.test.PropertyProvider

class ClothingCategoryPage extends Page{
	
		static url = "category/men/clothing/all"
	
		
		static content = {
			headline { $(".content > h1")}
		}

    static at = {
        String okValue = PropertyProvider.getInstance().getLocalizedPropertyValue("clothing.trousers")
        assert $(".content > h1").text().toUpperCase() == okValue
        return true
    }
	
//		def toAllCollectionsPage(){
//			$("#shop-by-collection>a.cta.see-all").click()
//			//waitFor(30){ $("#main-container > article:nth-child(1) > header > article > div > h1").text() == "JEANS"}
//		}
		def selectSweaterItemTypeFilter(){
			interact {
				click($(".facet-links"))
			}
	
			waitFor(30, 10){
				$("#facet-itemType > ul > li:nth-child(3) > label:nth-child(1)").isDisplayed()
			}
			$("#facet-itemType > ul > li:nth-child(3) > label:nth-child(1)").click()
		}
		def validateProductAfterItemTypeFilters(){
			assert $("p.name").text().contains("SWEATER")
			return true			
		}
    def toAllCollectionsPage(){
        $("#shop-by-collection>a.cta.see-all").click()
    }

    def ProductsUnderTrousersCategoryPage(){
        int minimum =1
        int maximum =4

        int i = minimum + (int)(Math.random()*maximum)
        assert $("#main-container>section:nth-of-type("+i+")").isDisplayed() == true

        return true
    }

    def PDPofAnyProduct(){

        int minimum =1
        int maximum =12

        int i = minimum + (int)(Math.random()*maximum)
        $("#container_results>li:nth-child("+i+")>div>a").click()
       // $("#dismiss-btn").click()
        assert $(".title").isDisplayed() ==true
        return true
    }

     def FilterAtoZ(){
        $(".dk_toggle").click()
        $(".dk_options ul.dk_options_inner li:nth-child(2) a").click()
        Thread.sleep(1000)
    }
   /* def ScrollDown(){
        // scrolling functionality is written to see additional products are getting loaded
        ((JavascriptExecutor) driver).executeScript("scroll(0,1500);")
        Thread.sleep(10000)
        ((JavascriptExecutor) driver).executeScript("scroll(1500,3000);")
        Thread.sleep(8000)
        ((JavascriptExecutor) driver).executeScript("scroll(3000,4500);")
        Thread.sleep(8000)
        assert $("#container_results").isDisplayed()== true
        return true
    }*/
}

package com.lsco.test.page.search

import com.lsco.test.PropertyProvider
import com.lsco.test.page.FirstProductPage
import geb.Page

/**
 * Created by edu on 18/11/14.
 */

class FailSearchResultPage extends Page {

    static content={
        failSearchPageTitleElem{$(".section-header")}
        productsList{$(".list-type-05 li")}
    }

    static at = {
        failSearchPageTitleElem.text().toLowerCase() == PropertyProvider.getInstance().getLocalizedPropertyValue("search.error.title").toLowerCase()
    }

    def verifyNoItemFound(){
        assert $("section#details").isDisplayed()
        return true
    }

    String getOtherItemsSectionName(){
        $(".prod-list > h2:nth-child(1)").text()
    }
    def verifyYouMightLikesection(){
        assert getOtherItemsSectionName()==PropertyProvider.getInstance().getLocalizedPropertyValue("youmightlikeh2").toUpperCase()
        assert $("div#nosearch1_rr>section>ul>li").size()!=0
        return true
    }
    def checkOtherItemsSection(int quantity){
        assert getOtherItemsSectionName().toLowerCase()==PropertyProvider.getInstance().getLocalizedPropertyValue("search.other.Items.section.name").toLowerCase()
        assert productsList.size()>=quantity
		return true
    }
	def toPDPPageOnClickingItemFromYouMightLikeSection()
	{
		$(".product-images>a>img").click(FirstProductPage)
	}
    def ValidateNoGrid()
    {
        assert $("#container_results").isDisplayed() == false
        return true
    }
// added By Suprito after 12th March 2015---------------------------------
	
	
	def verifyItemsNotDisplayedForInvalidSrch()
	{
		Thread.sleep(4000)
		boolean productNo = $(".list-type-04>li>div:nth-child(1)>a>img").isDisplayed()
		println productNo
		
		assert productNo == false
		return true
	}


}

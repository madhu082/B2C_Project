package com.lsco.test.steps.login

import com.lsco.test.GBDockersSmoke
import com.lsco.test.DEDockersSmoke
import geb.*
import geb.spock.GebSpec;

import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.login.LevisMyAccountPage
import com.lsco.test.page.login.LevisLoginPage
import com.lsco.test.page.login.FacebookLoginPage


class FacebookLoginSpec_15638_Dockers_DE extends GebSpec{
	//@DEDockersSmoke
	def FacebookLogin(){
	
	when: "We are Home page & click on MyAccount"
		to LevisHomePage
		at LevisHomePage
		toMyAccountPage()

	then : "we are on the Login Page"
	    at LevisLoginPage
		signInWithFacebook()
		
	when: "We are at Facebook Page"
	    at FacebookLoginPage
	    FillFacebookLogin()
	     
	then:"Verify We are at MyACcount Page after Facebook login"
	     at LevisMyAccountPage
		 VerifyEmailInHeader()
		
		}
	}
	
	

package com.lsco.test.steps.login

import com.lsco.test.GBLevisSmoke
import com.lsco.test.DEDockersSmoke
import geb.*
import geb.spock.GebSpec;

import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.login.LevisMyAccountPage
import com.lsco.test.page.login.LevisLoginPage

/*
//Jira SPRING-15968,15962
*/
class NegativePasswordChangeSpec_15968_15962 extends GebSpec{
	@DEDockersSmoke
	def passwordchange1(){
	when: "We are Home page & click on MyAccount"
		to LevisHomePage
		at LevisHomePage
		toMyAccountPage()

	then : "we are on the MyAccount Page"
	    at LevisLoginPage
		FillLoginCredentials()
		submitLoginForm()
		
	when: "We are into MyProfile-tab"
	    to LevisMyAccountPage
	    at LevisMyAccountPage
	    openPasswordTab()
		
	then:"Verify invalid 'Current Password.' "
	println("Verify invalid 'Current Password.")
		negetivePassword("negativeoldpw","password","password")
		UpdateButton()
		verifyPWErrorMesage("pwMismatchErrMSG")
			
	and: "Verify 'New Password' not equals 'Confirm Password'"
	println("Verify 'New Password' not equals 'Confirm Password'")
		negetivePassword("password_pm","negativeoldpw","negativeoldpw1")
		UpdateButton()
		verifyPWErrorMesage("pwConfirmErrMSG")
			
	and: "Verify password less that 8 characters without numeric and special char"
	println("Verify password less that 8 characters without numeric and special char")
		negetivePassword("password_pm","invalidPw","invalidPw")
		UpdateButton()
		verifyPWErrorMesage("pwIncalidtypeErrMSG")
		}
	}
	
	

package com.lsco.test.steps.login
import com.lsco.test.GBDockersSmoke
import geb.*
import geb.spock.GebSpec;

import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.login.LevisMyAccountPage
import com.lsco.test.page.login.LevisLoginPage




class PasswordChangeSpec_15965_Dockers_GB extends GebSpec{
	@GBDockersSmoke
	def passwordchange(){
		
		when: "We are Home page & click on MyAccount"
			to LevisHomePage
			at LevisHomePage
			toMyAccountPage()
	
		then : "we are on the MyAccount Page"
			at LevisLoginPage
			FillLoginCredentials1("username_pm","password_pm")
			submitLoginForm()
			
		when: "We are into MyProfile-tab"
			to LevisMyAccountPage
			at LevisMyAccountPage
			openPasswordTab()
			
		then:"Updating Password"
			setNewPassword("password_pm","newpassword_pm")
			UpdateButton()
			verifyDataUpdateMesage()
			logOut()
			
		when:"Loging with new password"
			at LevisLoginPage
			FillLoginCredentials1("username_pm","newpassword_pm")
			submitLoginForm()
			
		then:"Check Login successful"
			to LevisMyAccountPage
			at LevisMyAccountPage
			openPasswordTab()
			
		and:"Revert the old password"
			setNewPassword("newpassword_pm","password_pm")
			UpdateButton()
			verifyDataUpdateMesage()
			logOut()
			
			}
	}
	
	

package com.lsco.test.steps.login
import com.lsco.test.GBLevisSmoke
import com.lsco.test.DELevisSmoke
import com.lsco.test.page.LevisHomePage
import com.lsco.test.page.login.LevisLoginPage
import com.lsco.test.page.login.LevisMyAccountPage
import geb.spock.GebReportingSpec

class PasswordChangeSpec_15960_15963Levi_GB extends GebReportingSpec{
	@GBLevisSmoke
	@DELevisSmoke
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
	
	

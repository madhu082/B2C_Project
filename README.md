How to setup project in eclipse
=========

Open up a new workspace and import test-automation as a new project into eclipse.
At this point there may be multiple classpath errors, if this is the case you will need to remove all JARs which are "(missing)" and manually re insert them by hand.

They are located in /.gradle/caches/modules-2/files-2.1/... 

from here you can manually add each JAR which is missing by referencing the Errors listed in eclipse

Once all the classpath errors have been resolved you may have an error where it says the groovy compiler version is 1.8 but needs to be 2.1. To fix this, right click on the test-automation project and select properties, then click on Groovy Compiler and change the compiler level to 2.1.

How to run this initial test suite
=========

PhantomJS
=========

	Running
    ./gradlew phantomJS

    Junit Report
    <localfolder>/qa/test-automation/build/test-results/phantomJsTest/*.xml

    HTML Report
    <localfolder>/qa/test-automation/build/reports/phantomJsTest/tests/index.html

Chrome
=========

	Running
    ./gradlew chrome

    Junit Report
    <localfolder>/qa/test-automation/build/test-results/chromeTest/*.xml

    HTML Report
    <localfolder>/qa/test-automation/build/reports/chromeTest/tests/index.html

Firefox
=========

	Running
    ./gradlew firefox

    Junit Report
    <localfolder>/qa/test-automation/build/test-results/firefoxTest/*.xml

    HTML Report
    <localfolder>/qa/test-automation/build/reports/firefoxTest/tests/index.html

FirefoxSauce
============

	Running
    ./gradlew clean firefoxSauceTest -PbuildName="<BUILD NAME>" -PbuildNumber="<BUILD NUMBER>" -PbaseUrl="<URL>" -Pcountry="<COUNTRY>" -Plocale="<LOCALE>" -Psite="<SITE>" -PtestSuite=<TEST SUITE>
    ./gradlew clean firefoxSauceTest -PbuildName="Springboard Functional Test CL" -PbuildNumber="1234" -PbaseUrl="http://levi.com" -Pcountry="GB" -Plocale="en_GB" -Psite="levi" -PtestSuite="Regression"

    Junit Report
    <localfolder>/qa/test-automation/build/test-results/firefoxSauceTest/*.xml

    HTML Report
    <localfolder>/qa/test-automation/build/reports/firefoxSauceTest/tests/index.html


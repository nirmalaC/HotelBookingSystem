Selenium-Cucumber-PageFactory-Java-Maven
=================
I have used
selenium-cucumber : Automation Testing Using Java

selenium-cucumber is a behavior driven development (BDD) approach to write automation test script to test Web.
It enables you to write and execute automated acceptance/unit tests.
It is cross-platform, open source and free.
Automate your test cases with minimal coding.

Automation Test Architecture
--------------
	HotelBookingSystem
		|_src/main/
		|_src/test/java
		|	|pageobjects
		|	|	|BookingHomePage
		|	|	|helpers
		|   |stepDefinitions
        |	|	|Hooks
        |	|	|StepDefinitions
        |	|	|TestRunner
		|_src/test/resources
		|	|_features
		|	|	|hotelBooking.feature
		|	|Config.Properties

* **src/test/resources/features** - all the cucumber features files (files .feature ext) goes here.
* **src/test/java/stepDefinitions/StepDefinitions - you can define step defintion .
* **src/test/java/env** - this package contains cucumber runner (RunCukeTest.java) where you can configure your glue code location (step defintions), define test result output format.(html, json, xml). Hooks where you can configure all before and after test settings Hooks.java, DriverUtil.java contains code for intializing driver instances for respective driver.
* **src/main/java/platformConfigs** - If you want to run your test on saucelab and browserstack platforms, you need to add its configuration such as username, access key here.
* **src/main/java/browserConfig** - When you run your test on remote browser/platform you have to provide capabilities and platform information here.
* **src/main/java/appUnderTest** - If you are testing mobile based application you can keep your app build here.

Writing a test
--------------
The cucumber features goes in the `Features` library and should have the ".feature" extension. The


Running test
--------------

You can run the test by using test runner file:
- Go to TestRunner file and click run button

OR Go to your project directory from terminal and hit following commands
- mvn test "-Dbrowser=chrome" (to use any other browser)

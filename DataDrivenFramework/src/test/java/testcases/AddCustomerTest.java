package testcases;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.TestUtil;

public class AddCustomerTest extends BaseTest{
	
	@Test(dataProviderClass=TestUtil.class, dataProvider="dp")
	public void addCustomerTest(String firstName, String lastName, String postCode) {
		
		click("bmLoginBtn_XPATH"); //ManagerLoginbtn
		click("AddCustBtn_XPATH"); //Add Customer click
		type("firstname_XPATH",firstName); //Firstname
		type("lastname_XPATH",lastName); //Lastname
		type("postCode_XPATH",postCode); //Postcode
		click("SveAddCust_XPATH"); //Submit button
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
	Alert alert =	driver.switchTo().alert();
	Assert.assertTrue(alert.getText().contains("Customer added successfully"),"Customer not added");
	alert.accept();
	}
}
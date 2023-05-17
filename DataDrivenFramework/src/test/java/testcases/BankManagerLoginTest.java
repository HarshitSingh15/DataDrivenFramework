package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;


public class BankManagerLoginTest extends BaseTest {


	
@Test	
public void bnkMngrloginTest() {
		
		click("bmLoginBtn_XPATH");
		Assert.assertTrue(isElementPresent("AddCustBtn_XPATH"), "Test faile as AddCustomer missing");
		Assert.assertTrue(isElementPresent("OpenAccBtn_XPATH"), "Test faile as AddCustomer missing");
		Assert.assertTrue(isElementPresent("CstmrBtn_XPATH"), "Test faile as AddCustomer missing");
	
}
}

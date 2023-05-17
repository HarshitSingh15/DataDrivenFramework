package testcases;

import org.testng.annotations.Test;

import base.BaseTest;
import utilities.TestUtil;

public class LoginTest extends BaseTest {

	@Test(dataProviderClass = TestUtil.class, dataProvider="dp")
	public void doLogin(String username, String password) {
		type("email_ID", username);
		type("password_ID", password);
		//click("loginBtn_ID");
	}
	
	

}
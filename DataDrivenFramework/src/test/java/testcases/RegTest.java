package testcases;

import org.testng.annotations.Test;

import base.BaseTest;

import utilities.TestUtil;


@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
public class RegTest extends BaseTest {
	
	public void doRegUser(String firstName, String lastName) {
		
		System.out.println(firstName+" -------- "+lastName);
		
	}
	
}

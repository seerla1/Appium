package com.qa.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.time.Duration;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pages.LoginPage;
import com.pages.ProductDetailPage;
import com.pages.ProductPage;
import com.qa.BaseTest;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class LoginTests extends BaseTest {

	LoginPage loginPage;
	ProductPage productPage;
	ProductDetailPage productDetailPage;
	InputStream details;
	JSONObject loginUsers;

	@BeforeClass
	public void beforeClass() throws FileNotFoundException {

		details = new FileInputStream(
				"C:\\Users\\0035EJ744\\git\\SauceLabTestFramework_v\\SauceLabTool\\src\\test\\resources\\Data/data.json");
		JSONTokener jsonToken = new JSONTokener(details);
		loginUsers = new JSONObject(jsonToken);
		closeApp();
		launchApp();

	}

	@BeforeMethod
	public void beforeMethod(Method m) {
		System.out.println("*************** starting Test: " + m.getName() + "   *****************");
		loginPage = new LoginPage();
		productPage = new ProductPage();
	}

	/*@Test
	public void invalidUserName() {

		loginPage.enterUserName(loginUsers.getJSONObject("invalidUser").getString("username"));
		loginPage.enterPassword(loginUsers.getJSONObject("invalidUser").getString("password"));
		loginPage.pressLogin();

		String actualErrorMsg = loginPage.gerErrorText();
		String expectedErrorMsg = "Username and password do not match any user in this service.";

		Assert.assertEquals(actualErrorMsg, expectedErrorMsg);

	}

	@Test
	public void invalidPassord() {

		loginPage.enterUserName(loginUsers.getJSONObject("invalidPassword").getString("username"));
		loginPage.enterPassword(loginUsers.getJSONObject("invalidPassword").getString("password"));

		loginPage.pressLogin();

		String actualErrorMsg = loginPage.gerErrorText();
		String expectedErrorMsg = "Username and password do not match any user in this service.";

		Assert.assertEquals(actualErrorMsg, expectedErrorMsg);

	}*/

	@Test

	public void validUser() {

		loginPage.enterUserName(loginUsers.getJSONObject("validUser").getString("username"));
		loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
		loginPage.pressLogin();
		loginPage.checkout();
		loginPage.cart();
		loginPage.checkout2();
		loginPage.details();
		
		TouchAction t=new TouchAction(driver);
		Dimension size = driver.manage().window().getSize();
        int X = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        
		for (int i = 0; i < 5; i++) {

			System.out.println(i);
			try {
				if (loginPage.finish.isDisplayed()) {
					break;
				}

			} catch (Exception e) {
				t.press(PointOption.point(X, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
						.moveTo(PointOption.point(X,endY)).release().perform();

			}

		}
		loginPage.finish();
		String actualMsg = loginPage.getMsg();
		String expectedMsg = "THANK YOU FOR YOU ORDER";
		System.out.println(actualMsg);

		Assert.assertEquals(actualMsg, expectedMsg);
		

		/*String actualProductTitle = productPage.getTitle();
		String expectedProductTitle = "PRODUCTS";

		Assert.assertEquals(actualProductTitle, expectedProductTitle);*/
	}

	
	
	@AfterClass
	public void afterClass() throws IOException {

		details.close();

	}

}

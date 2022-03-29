package com.pages;


import com.qa.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.offset.PointOption;

public class LoginPage extends BaseTest {

	@AndroidFindBy(accessibility = "test-Username")
	private MobileElement usernameTxtFld;

	@AndroidFindBy(accessibility = "test-Password")
	private MobileElement passwordTxtFld;

	@AndroidFindBy(accessibility = "test-LOGIN")
	private MobileElement loginBtn;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
	private MobileElement errorMsg;
	
	@AndroidFindBy(xpath="(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]")
	private MobileElement item;
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView")
	private MobileElement cart;
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"test-CHECKOUT\"]")
	private MobileElement checkout;
	@AndroidFindBy(xpath="//android.widget.EditText[@content-desc=\"test-First Name\"]")
	private MobileElement firstName;
	@AndroidFindBy(xpath="//android.widget.EditText[@content-desc=\"test-Last Name\"]")
	private MobileElement lastName;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@content-desc=\"test-Zip/Postal Code\"]")
	private MobileElement Zip;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"test-CONTINUE\"]")
	private MobileElement con;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"test-FINISH\"]")
	public MobileElement finish;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"test-REMOVE\"]")
	private MobileElement remove;
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"test-CONTINUE SHOPPING\"]")
	private MobileElement conshopping;
	
	@AndroidFindBy(xpath="//android.widget.ScrollView[@content-desc=\"test-CHECKOUT: COMPLETE!\"]/android.view.ViewGroup/android.widget.TextView[1]")
	private MobileElement successmsg;
	
	

	
	
	
	////*[@text=\"Animation\"]
	
	public void enterUserName(String userName) {
		clear(usernameTxtFld);
		sendKeys(usernameTxtFld, userName);
	}

	public void enterPassword(String password) {
		clear(passwordTxtFld);
		sendKeys(passwordTxtFld, password);
	}

	public void pressLogin() {
		click(loginBtn);

	}
	public void checkout() {
		click(item);
		

	}
	
	public void cart() {
		click(cart);

	}

	public void checkout2() {
		click(checkout);

	}
	
	public void details() {
		sendKeys(firstName, "abc");
		sendKeys(lastName, "def");
		sendKeys(Zip, "400087");
		click(con);

	}
	
	public void finish() {
		
		click(finish);

	}

	public void remove() {
		click(remove);

	}
	public void conshopping() {
		click(conshopping);

	}

	public void login(String userName, String password) {
		enterUserName(userName);
		enterPassword(password);
		pressLogin();
	}

	public String gerErrorText() {

		String errMasage = getText(errorMsg);

		return errMasage;
	}
	

	public String getMsg() {
		// TODO Auto-generated method stub
		String msg = getText(successmsg);
		return msg;
	}
}

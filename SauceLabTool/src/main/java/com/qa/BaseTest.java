package com.qa;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class BaseTest {

	public static AppiumDriver driver;
	protected static Properties prop;
	InputStream inputstream;
	private static AppiumDriverLocalService server;

	public BaseTest() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@BeforeSuite
	//@Parameters({"PlatformName","udid","deviceName"})
	public void setup() throws IOException {

		server = getAppiumServerDefault();
		//server = getAppiumService();

		if (!checkIfAppiumServerIsRunning(4723)) {
			server.start();
			server.clearOutPutStreams();
			System.out.println("Appium Server Started");
		}

		else {
			System.out.println("Appium Server is already running");
		}

		prop = new Properties();
		inputstream = getClass().getClassLoader().getResourceAsStream("config.properties");
		prop.load(inputstream);
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_4");
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
		caps.setCapability(MobileCapabilityType.APP, "C:/Users/0035EJ744/Downloads/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
		caps.setCapability("appPackage", prop.getProperty("androidAppPackage"));
		caps.setCapability("appActivity", prop.getProperty("androidAppActivity"));

		URL url = new URL("http://0.0.0.0:4723/wd/hub");
		driver = new AndroidDriver(url, caps);

	}

	private boolean checkIfAppiumServerIsRunning(int port) {

		boolean flag = false;

		ServerSocket socket;

		try {
			socket = new ServerSocket(port);
			socket.close();
		} catch (Exception e) {
			flag = true;
		} finally {
			socket = null;
		}

		return flag;
	}

	// windows
	public AppiumDriverLocalService getAppiumServerDefault() {
		return AppiumDriverLocalService.buildDefaultService();

	}

	// MAC
	public AppiumDriverLocalService getAppiumService() {
		HashMap<String, String> environment = new HashMap<String, String>();
		environment.put("PATH",
				"/Library/Internet Plug-Ins/JavaAppletPlugin.plugin/Contents/Home/bin:/Users/riyaanghosh/Library/Android/sdk/tools:/Users/riyaanghosh/Library/Android/sdk/platform-tools:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/Library/Apple/usr/bin"
						+ System.getenv("PATH"));
		environment.put("ANDROID_HOME", "/Users/riyaanghosh/Library/Android/sdk");
		return AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder().usingDriverExecutable(new File("/usr/local/bin/node"))
						.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js")).usingPort(4723)
						.withArgument(GeneralServerFlag.SESSION_OVERRIDE)
//				.withArgument(() -> "--allow-insecure","chromedriver_autodownload")
						.withEnvironment(environment).withLogFile(new File("ServerLogs/server.log")));
	}

	@AfterSuite
	public void tearDown() {
		server.stop();
	}

	void waitForVisiility(MobileElement e) {

		WebDriverWait wait = new WebDriverWait(driver, 10);

		wait.until(ExpectedConditions.visibilityOf(e));

	}

	public void sendKeys(MobileElement element, String userName) {
		waitForVisiility(element);
		element.sendKeys(userName);
	}

	public void clear(MobileElement element) {
		waitForVisiility(element);
		element.clear();
	}

	public void click(MobileElement element) {
		waitForVisiility(element);
		element.click();
	}
	
	public String getText(MobileElement e) {
		
		String str=null;
		
		str=getAttribute(e,"text");
		
		return str;
	
	}
	
	
	private String getAttribute(MobileElement e, String txt) {
		waitForVisiility(e);
		
		return e.getAttribute(txt);
		
	}

	public void closeApp() {
		((InteractsWithApps) driver).closeApp();
	}
	
	

	public void launchApp() {
		((InteractsWithApps) driver).launchApp();
	}
	public void itemcheckout(MobileElement element) {
		waitForVisiility(element);
		element.click();
		
		
	}
	public void cart(MobileElement element) {
		waitForVisiility(element);
		element.click();
		
		
	}
	public void checkout(MobileElement element) {
		waitForVisiility(element);
		element.click();
		
		
	}
	public void details1(MobileElement element,String firstName) {
		waitForVisiility(element);
		element.sendKeys(firstName);

	}
	public void details2(MobileElement element,String lastName) {
		waitForVisiility(element);
		element.sendKeys(lastName);

	}
	public void details3(MobileElement element,String zip) {
		waitForVisiility(element);
		element.sendKeys(zip);

	}
	public void continu(MobileElement element) {
		waitForVisiility(element);
		element.click();
		
		
	}
	public void finish(MobileElement element) {
		waitForVisiility(element);
		
		element.click();
		
		
	}
	public void remove(MobileElement element) {
		waitForVisiility(element);
		
		element.click();
		
		
	}
	
	public void conshopping(MobileElement element) {
		waitForVisiility(element);
		
		element.click();
		
		
	}
	
	public String getMsg(MobileElement e) {
		
		String str=null;
		
		str=getAttribute(e,"text");
		
		return str;
	
	}


}

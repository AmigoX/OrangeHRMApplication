package com.BaseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.Utility.Logs;

public class DriverSetup {
	public WebDriver driver;
	@BeforeTest
	public void Setup() {
		System.setProperty("webdriver.gecko.driver","C:\\Users\\RAMBABU\\Desktop\\OrangeHRM\\OrangaHRM\\WebDrivers\\geckodriver.exe");
		driver=new FirefoxDriver();
		driver.get("http://127.0.0.1/orangehrm-4.2.0.1/symfony/web/index.php/auth/login");
//		System.out.println("******Successfully launched the OrangeHRM application******");
		Logs.Info("******Successfully launched the OrangeHRM application******");
	}
	@AfterTest(enabled=false)
	public void tearDown() {
		driver.close();
//		System.out.println("******Successfully Closed the OrangeHRM application******");
		Logs.Info("******Successfully Closed the OrangeHRM application******");
	}
}

package org.ch.tan.common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	public static WebDriver get(Browser browser) {
		WebDriver driver = null;
		switch (browser) {
		case chrome:
			driver = new ChromeDriver();
			break;
		case firefox:
			driver = new FirefoxDriver();
			break;
		default:
			break;
		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

}

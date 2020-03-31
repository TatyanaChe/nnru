package org.ch.tan.pages;

import org.openqa.selenium.WebDriver;

public class ReportPage extends PageObject{

	public ReportPage(WebDriver driver) {
		super(driver);
	}

	public void open() throws InterruptedException {
//		driver.get("file:///D:/ws/selenium/nnru/src/test/resources/report_transport.html");
		driver.get("file:///D:/ws/selenium/nnru/src/test/resources/report_obuv.html");
		Thread.sleep(10000);
	}

	public void close() {
		driver.close();
		
	}

}

package org.ch.tan.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModerPage extends PageObject {

	private static Logger logger = LoggerFactory.getLogger(ModerPage.class);
	
	String titleModerPage = " Интерфейс помощника модератора";
	
	@FindBy(xpath = "//input[@value='[x] Закрыть']")
	private WebElement closeThemeBtn;

	@FindBy(xpath = "//input[@value='Закрыть окно']")
	private WebElement closeWindowBtn;

	public ModerPage(WebDriver driver) {
		super(driver);
	}

	public void clickCloseBtn() throws InterruptedException {
		try {
			Thread.sleep(3000);
			closeThemeBtn.click();
			logger.info("closeThemeBtn.click()");
		} finally {
			Thread.sleep(3000);
//			driver.close();
////			closeWindowBtn.click();
//			logger.info("closeThemeBtn.click(closeWindowBtn.click())");
//			logger.info("driver.close()");
		}
	}


}

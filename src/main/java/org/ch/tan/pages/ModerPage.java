package org.ch.tan.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ModerPage extends PageObject {

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
			System.out.println("closeThemeBtn.click()");
		} finally {
			Thread.sleep(3000);
			closeWindowBtn.click();
			System.out.println("closeThemeBtn.click(closeWindowBtn.click())");
		}
	}


}

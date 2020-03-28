package org.ch.tan.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPage extends PageObject {

	@FindBy(xpath = "*//a[@href='/admin/blog/entry/add/']")
	private WebElement buttonAddEntry;

	public AdminPage(WebDriver driver) {
		super(driver);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void addEntry() {
		buttonAddEntry.click();
	}

}

package org.ch.tan.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopicPage extends PageObject {

	private static Logger logger = LoggerFactory.getLogger(TopicPage.class);
	
	@FindBy(xpath = "*//*[@itemprop='description']")
	private WebElement itemPropDesc;

	@FindBy(xpath = "*//div[text()='Mod']")
	private WebElement itemPropModBtn;

	@FindBy(xpath = "//div[@class='comment-tools__content']/ul/li/*[text()='Edit']")
	private WebElement EditBtn;

	public TopicPage(WebDriver driver) {
		super(driver);
	}

	public void open(String delLn) {
		driver.get(delLn);
	}

	public void moveToItemPropsDesc() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		By locatorItemPropDesc = By.xpath("*//*[@itemprop='description']");
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locatorItemPropDesc));

		Actions action = new Actions(driver);
		action.moveToElement(itemPropDesc);
		Thread.sleep(5000);
		logger.info("actionItemPropDesc");
		action.perform();
		Thread.sleep(5000);

	}

	public void moveToMod() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		By locatorMod = By.xpath("*//div[text()='Mod']");
//		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locatorMod));

		Actions action = new Actions(driver);
		action.moveToElement(itemPropModBtn);
//		Thread.sleep(5000);
		logger.info("itemPropModBtn");
		action.perform();
//		Thread.sleep(5000);

	}

	public void moveToEdit() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		By locatorEdit = By.xpath("//div[@class='comment-tools__content']/ul/li/*[text()='Edit']");
//		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locatorEdit));

		Actions action = new Actions(driver);
		action.moveToElement(EditBtn);
//		Thread.sleep(5000);
		logger.info("EditBtn");
		action.perform();
//		Thread.sleep(5000);
	}

	public void clickEditBtn() {
		EditBtn.click();

	}

}

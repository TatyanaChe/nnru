package org.ch.tan.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends PageObject {

//	@FindBy(xpath = "(//*[text()='Investors'])[2]")
//	private WebElement linkInvestors;
	@FindBy(xpath = "//*[@title='Вход']")
	private WebElement enterLink;
	
	@FindBy(xpath = "//input[@name='login']")
	private WebElement login;
	
	@FindBy(xpath = "//input[@name='password']")
	private WebElement password;
	
	@FindBy(xpath = "//input[@value='Войти']")
	private WebElement blueBtn;
	
	@FindBy(xpath = "//*[@class='sort-btn']")
	private WebElement sortBtn;

	@FindBy(xpath = "//*[@class='ugolok']")
	public WebElement ugolok;
	
	@FindBy(xpath = "//div[@class='sort-select']/span[2]")
	public WebElement sortSelectByTopic;
	

	
	
	

	public MainPage(WebDriver driver) {
		super(driver);
	}

	public void open() {
		driver.get("https://www.nn.ru/community/my_baby/detskiy-transport/");
	}

	public String getTransportPage() {
		return driver.getCurrentUrl();
		}

	public void clickLogin() {
		enterLink.click();
		
	}

	public void enterLogin(String lgn) {
		login.sendKeys(lgn);
	}

	public void enterPassword(String pwd) {
		password.sendKeys(pwd);
		
	}

	public void clickEnterBtn() {
		blueBtn.click();
		
	}

	public void clickSortBtn() {
		sortBtn.click();
	}
	public void clickUgolok() throws InterruptedException {
	ugolok.click();
	System.out.println("clickUgolok");
		Thread.sleep(10000);
	}

	public void clickSortSelectByTopic() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10000);
		By locator = By.xpath("//div[@class='sort-select']/span[2]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		//Thread.sleep(10000);
		sortSelectByTopic.click();
		System.out.println("clickSortSelectByTopic");
		Thread.sleep(10000);
	}

}


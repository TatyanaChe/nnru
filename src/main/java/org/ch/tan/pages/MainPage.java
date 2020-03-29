package org.ch.tan.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends PageObject {

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

	@FindBy(xpath = "//*[@class='topics-filter__arrow']")
	public WebElement topicFilter;

	@FindBy(xpath = "//*[@class='topics-filter__button ns-btn ns-btn_white js-filter-all']")
	public WebElement buttonFilterAll;

	@FindBy(xpath = "//*[@class='topics-filter__item-title js-filter-title'][12]")
	public WebElement filterByTitleSeek;

	@FindBy(xpath = "//*[text()='Куплю/Ищу']/../div[1]")
	public WebElement topicBuySearch;

	@FindBy(xpath = "//*[@class='topics-filter__button ns-btn ns-btn_white js-filter-revers']")
	public WebElement buttonViceVersa;

	@FindBy(xpath = "//*[@class='topics-filter__button ns-btn ns-btn_blue js-filter-submit']")
	public WebElement buttonApply;

	;

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
		Thread.sleep(1000);
	}

	public void clickSortSelectByTopic() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		By locatorSelectByTopic = By.xpath("//div[@class='sort-select']/span[2]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(locatorSelectByTopic));
		// Thread.sleep(10000);
		sortSelectByTopic.click();
		System.out.println("clickSortSelectByTopic");
		Thread.sleep(1000);
	}

	public void clickTopicFilter() throws InterruptedException {
//		WebDriverWait wait = new WebDriverWait(driver, 5000);
//		By locatorTopicFilter = By.xpath("//*[@class='topics-filter__arrow");
//		wait.until(ExpectedConditions.visibilityOfElementLocated(locatorTopicFilter));
		Thread.sleep(1000);
		topicFilter.click();
		System.out.println("clickTopicFilter");
		Thread.sleep(1000);
	}

	public void clickFilterByTitleSeek() throws InterruptedException {
		Thread.sleep(1000);
		topicBuySearch.click();
		System.out.println("clickFilterByTitleSeek");
		Thread.sleep(1000);

	}

	public void clickButtonViceVersa() throws InterruptedException {
		Thread.sleep(1000);
		buttonViceVersa.click();
		System.out.println("clickButtonViceVersa");
		Thread.sleep(1000);
	}

	public void clickButtonApply() throws InterruptedException {
		Thread.sleep(1000);
		buttonApply.click();
		System.out.println("clickButtonApply");
		Thread.sleep(1000);
	}

	public void clickFilterAll() throws InterruptedException {
		Thread.sleep(1000);
		buttonFilterAll.click();
		System.out.println("clickFilterAll");
		Thread.sleep(1000);
	}

}

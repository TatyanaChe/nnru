package org.ch.tan.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainPageOdezhda extends PageObject {

	private static Logger logger = LoggerFactory.getLogger(MainPageOdezhda.class);
	
	@FindBy(xpath = "//*[@title='Вход']")
	private WebElement enterLink;

	@FindBy(xpath = "//input[@name='login']")
	private WebElement login;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement password;

	@FindBy(xpath = "//input[@value='Войти']")
	public WebElement blueBtn;

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

	@FindBy(xpath = "//*[text()='Ищу']/../div[1]")
	public WebElement topicBuySearch;

	@FindBy(xpath = "//*[@class='topics-filter__button ns-btn ns-btn_white js-filter-revers']")
	public WebElement buttonViceVersa;

	@FindBy(xpath = "//*[@class='topics-filter__button ns-btn ns-btn_blue js-filter-submit']")
	public WebElement buttonApply;

	;

	public MainPageOdezhda(WebDriver driver) {
		super(driver);
	}

	public void open() {
		driver.get("https://www.nn.ru/community/my_baby/detskaya_odezhda/");
	}

	public String getOdezhdaPage() {
		return driver.getCurrentUrl();
	}

	public void clickLogin() {
//		WebDriverWait wait = new WebDriverWait(driver, 10000);
//		By locatorSelectByTopic = By.xpath("//*[@title='Вход']");
//		wait.until(ExpectedConditions.visibilityOfElementLocated(locatorSelectByTopic));

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
		logger.info("clickUgolok");
		Thread.sleep(1000);
	}

	public void clickSortSelectByTopic() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		By locatorSelectByTopic = By.xpath("//div[@class='sort-select']/span[2]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(locatorSelectByTopic));
		// Thread.sleep(10000);
		sortSelectByTopic.click();
		logger.info("clickSortSelectByTopic");
		Thread.sleep(1000);
	}

	public void clickTopicFilter() throws InterruptedException {
//		WebDriverWait wait = new WebDriverWait(driver, 5000);
//		By locatorTopicFilter = By.xpath("//*[@class='topics-filter__arrow");
//		wait.until(ExpectedConditions.visibilityOfElementLocated(locatorTopicFilter));
		Thread.sleep(1000);
		topicFilter.click();
		logger.info("clickTopicFilter");
		Thread.sleep(1000);
	}

	public void clickFilterByTitleSeek() throws InterruptedException {
		Thread.sleep(1000);
		topicBuySearch.click();
		logger.info("clickFilterByTitleSeek");
		Thread.sleep(1000);

	}

	public void clickButtonViceVersa() throws InterruptedException {
		Thread.sleep(1000);
		buttonViceVersa.click();
		logger.info("clickButtonViceVersa");
		Thread.sleep(1000);
	}

	public void clickButtonApply() throws InterruptedException {
		Thread.sleep(1000);
		buttonApply.click();
		logger.info("clickButtonApply");
		Thread.sleep(1000);
	}

	public void clickFilterAll() throws InterruptedException {
		Thread.sleep(1000);
		buttonFilterAll.click();
		logger.info("clickFilterAll");
		Thread.sleep(1000);
	}

}

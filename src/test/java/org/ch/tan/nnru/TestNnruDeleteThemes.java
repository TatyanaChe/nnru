package org.ch.tan.nnru;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.ch.tan.common.Browser;
import org.ch.tan.common.DriverFactory;
import org.ch.tan.common.Props;
import org.ch.tan.pages.MainPage;
import org.ch.tan.pages.ModerPage;
import org.ch.tan.pages.ReportPage;
import org.ch.tan.pages.TopicPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNnruDeleteThemes {

	private static Logger logger = LoggerFactory.getLogger(TestNnru.class);
	
	String titleModerPage = " Интерфейс помощника модератора";

	private WebDriver driver;
//	MainPage mainPage = new MainPage(driver);

	@FindBy(xpath = "//*[@class='heading-cont']")
	public List<WebElement> listDeleteLinks;

	private TopicPage topicPage;

	@Before
	public void beforeTest() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = DriverFactory.get(Browser.chrome);

	}

	@After
	public void afterTest() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void test() throws InterruptedException, IOException {
//		Список ссылок на удаление из файла report
		ReportPage reportPage = new ReportPage(driver);
		reportPage.open();
		Thread.sleep(10000);

		List<WebElement> deleteLinks = new ArrayList<WebElement>();
		deleteLinks = driver.findElements(By.tagName("a"));
		logger.info("deleteLinks: " + deleteLinks);
		List<String> dellnList = new ArrayList<String>();
		// Составляем список ссылок для удаления
		for (WebElement delln : deleteLinks) {
			dellnList.add(delln.getAttribute("href"));
			logger.info("dellnList: " + dellnList);
		}
//		Открываем nn.ru
		MainPage mainPage = new MainPage(driver);
		mainPage.open();
		Thread.sleep(1000);
//		// Авторизация
		mainPage.clickLogin();
		mainPage.enterLogin(Props.getLogin());
		mainPage.enterPassword(Props.getPassword());
		mainPage.clickEnterBtn();
		logger.info("Успешная авторизация");

//	Закрываем темы
		TopicPage topicPage = new TopicPage(driver);
		for (String delLnStr : dellnList) {
			topicPage.open(delLnStr);
			logger.info("delLnStr opened: " + delLnStr);
			topicPage.moveToItemPropsDesc();
			logger.info("topicPage.moveToItemPropsDesc()");
			topicPage.moveToMod();
			logger.info("topicPage.moveToMod()");
			topicPage.moveToEdit();
			logger.info("topicPage.moveToEdit()");
			topicPage.clickEditBtn();
			logger.info("topicPage.clickEditBtn()");

			ModerPage moderPage = new ModerPage(driver);
			String current = driver.getWindowHandle();
			logger.info("current: " + current);
			Set<String> handles = driver.getWindowHandles();
			Iterator<String> it = handles.iterator();
			String popup = "";
			while (it.hasNext()) {
				String h = (String) it.next();
				logger.info("handle: " + h);
				if (!h.equals(current)) {
					popup = h;
				}
			}
			driver.switchTo().window(popup);
			logger.info("driver.switchTo().window(winHandle) in if " + driver.getTitle());
			moderPage.clickCloseBtn();
			logger.info("moderPage.clickCloseBtn()");
			logger.info("Theme closed: " + driver.getTitle());
			driver.switchTo().window(current);
		}

	}

}
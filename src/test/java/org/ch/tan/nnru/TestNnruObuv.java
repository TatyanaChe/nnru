package org.ch.tan.nnru;

import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ch.tan.common.Browser;
import org.ch.tan.common.DriverFactory;
import org.ch.tan.common.Props;
import org.ch.tan.pages.ForumPage;
import org.ch.tan.pages.ForumPageObuv;
import org.ch.tan.pages.MainPage;
import org.ch.tan.pages.MainPageObuv;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.manipulation.Ordering.Context;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNnruObuv {

	private static Logger logger = LoggerFactory.getLogger(TestNnruObuv.class);

	private WebDriver driver;
//	MainPage mainPage = new MainPage(driver);

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
		MainPageObuv mainPageObuv = new MainPageObuv(driver);
		mainPageObuv.open();
		Thread.sleep(1000);
		String transportUrl = "https://www.nn.ru/community/my_baby/detskaya_obuv/";
		assertTrue("The page is not obuv", mainPageObuv.getTransportPage().equals(transportUrl));
		logger.info("obuv is opened");

		// Авторизация
		mainPageObuv.clickLogin();
		mainPageObuv.enterLogin(Props.getLogin());
		mainPageObuv.enterPassword(Props.getPassword());
		mainPageObuv.clickEnterBtn();
		logger.info("Успешная авторизация");

		// Сортировка
		Thread.sleep(5000);
		mainPageObuv.clickSortBtn();
		logger.info("clickSortBtn");
		Thread.sleep(1000);
		mainPageObuv.clickUgolok();
		logger.info("mainPage.clickUgolok");
		mainPageObuv.clickSortSelectByTopic();
		logger.info("Сортировка");
		Thread.sleep(1000);

		// Фильтр Ищу
		mainPageObuv.clickTopicFilter();
		logger.info("mainPageObuv.clickTopicFilter");
		mainPageObuv.clickFilterAll();
//		mainPageObuv.clickButtonViceVersa();
//		mainPageObuv.clickFilterByTitleSeek();
		mainPageObuv.clickButtonApply();

		// Найти линки по всем страницам
		ForumPageObuv forumPageObuv = new ForumPageObuv(driver, 1);
		List<String> allLinks = new ArrayList<String>();
		while (!forumPageObuv.hasNextPage() && (forumPageObuv.getPageNumber() <= 20)) {
			logger.info("getPageNumber: " + forumPageObuv.getPageNumber());
			forumPageObuv = forumPageObuv.nextPage();
			List<String> list = forumPageObuv.foundLinks();
			allLinks.addAll(list);
		}
		int i = 1;
		logger.info("  allLinks.size: " + allLinks.size());
		for (String string : allLinks) {
			logger.info("   " + i++ + string);
		}
		logger.info("===== stream ===========");
		allLinks.stream().forEach(ln -> logger.info(ln));

	}

}
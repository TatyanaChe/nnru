package org.ch.tan.nnru;

import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.ch.tan.common.Browser;
import org.ch.tan.common.DriverFactory;
import org.ch.tan.common.Props;
import org.ch.tan.pages.ForumPage;
import org.ch.tan.pages.MainPage;
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

public class TestNnru {

	private static Logger logger = LoggerFactory.getLogger(TestNnru.class);
	
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
		logger.info("starting test .. ");
		MainPage mainPage = new MainPage(driver);
		mainPage.open();
		Thread.sleep(1000);
		String transportUrl = "https://www.nn.ru/community/my_baby/detskiy-transport/";
		assertTrue("The page is not detskiy-transport", mainPage.getTransportPage().equals(transportUrl));
		System.out.println("detskiy-transport is opened");

		// Авторизация
		mainPage.clickLogin();
		mainPage.enterLogin(Props.getLogin());
		mainPage.enterPassword(Props.getPassword());
		mainPage.clickEnterBtn();
		System.out.println("Успешная авторизация");

		// Сортировка
		Thread.sleep(5000);
		mainPage.clickSortBtn();
		System.out.println("clickSortBtn");
		Thread.sleep(1000);
		mainPage.clickUgolok();
		System.out.println("mainPage.clickUgolok");
		mainPage.clickSortSelectByTopic();
		System.out.println("Сортировка");
		Thread.sleep(1000);

		// Фильтр Ищу
		mainPage.clickTopicFilter();
		System.out.println("mainPage.clickTopicFilter");
		mainPage.clickFilterAll();
//		mainPage.clickButtonViceVersa();
//		mainPage.clickFilterByTitleSeek();
		mainPage.clickButtonApply();

		// Найти линки по всем страницам
		ForumPage forumPage = new ForumPage(driver, 1);
		List<String> allLinks = new ArrayList<String>();
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		Calendar dateNow = Calendar.getInstance();
		System.out.println(df.format(dateNow.getTime()));
		Date dateBefore = new Date();
//		dateBefore = dateNow.getTime() - ;
//		System.out.println(df.format(dateBefore));
		while (!forumPage.hasNextPage() && (forumPage.getPageNumber() <= 20)) {
//				&& (mainPage.dateTopic() < dateBefore)) {
			System.out.println("getPageNumber: " + forumPage.getPageNumber());
			forumPage = forumPage.nextPage();
			List<String> list = forumPage.foundLinks();
			allLinks.addAll(list);
		}
		int i = 1;
		System.out.println("  allLinks.size: " + allLinks.size());
		for (String string : allLinks) {
			System.out.println("   " + i++ + string);
		}
		System.out.println("===== stream ===========");
		allLinks.stream().forEach(ln -> System.out.println(ln));

	}
	
	@Test
	public void testLog() {
		logger.info("starting test .. ");
		logger.info("starting test .. 2 ....");
	}


}
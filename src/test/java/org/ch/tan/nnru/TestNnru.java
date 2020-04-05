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
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
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
		logger.info("detskiy-transport is opened");

		// Авторизация
		mainPage.clickLogin();
		mainPage.enterLogin(Props.getLogin());
		mainPage.enterPassword(Props.getPassword());
		mainPage.clickEnterBtn();
		logger.info("Успешная авторизация");

		// Сортировка
		Thread.sleep(5000);
		mainPage.clickSortBtn();
		logger.info("clickSortBtn");
		Thread.sleep(1000);
		mainPage.clickUgolok();
		logger.info("mainPage.clickUgolok");
		mainPage.clickSortSelectByTopic();
		logger.info("Сортировка");
		Thread.sleep(1000);

		// Фильтр Ищу
		mainPage.clickTopicFilter();
		logger.info("mainPage.clickTopicFilter");
		mainPage.clickFilterAll();
//		mainPage.clickButtonViceVersa();
//		mainPage.clickFilterByTitleSeek();
		mainPage.clickButtonApply();

		// Найти линки по всем страницам
		ForumPage forumPage = new ForumPage(driver, 1);
		List<String> allLinks = new ArrayList<String>();
		while (!forumPage.hasNextPage() && (forumPage.getPageNumber() <= 5)) {
			logger.info("getPageNumber: " + forumPage.getPageNumber());
			forumPage = forumPage.nextPage();
			List<String> list = forumPage.foundLinks();
			allLinks.addAll(list);
		}
		int i = 1;
		logger.info("  allLinks.size: " + allLinks.size());
		for (String string : allLinks) {
			logger.info("   " + i++ + string);
		}
		logger.info("===== stream ===========");
		String html = convertToHtml(allLinks);
		FileUtils.write(new File("D:/ws/selenium/nnru/src/test/resources/report_transport.html"), html,
				"UTF-8");
		logger.info(" new html: " + html);

	}

	private String convertToHtml(List<String> allLinks) throws IOException {
		
		StringBuffer sb = new StringBuffer();
		sb.append("<!DOCTYPE html>\r\n");
		sb.append("<html lang=\"ru\">\r\n");
		sb.append("<head>\r\n");
		sb.append("<meta charset=\"UTF-8\">\r\n");
		sb.append("</head>\r\n");
		sb.append("<body>");
		for (String link : allLinks) {
			sb.append("<a href=\"");
			sb.append(link);
			sb.append("\">");
			sb.append(link);
			sb.append("</a><br>\n");
		}
		sb.append("</body>\r\n" + "</html>");
		return sb.toString();
	}

//	@Test
//	public void printTest() throws IOException {
//		File file = new File("D:/ws/selenium/nnru/src/test/resources/report_odezhda.txt");
//		List<String> lines = FileUtils.readLines(file, "UTF-8");
//		StringBuffer sb = new StringBuffer();
//		sb.append("<!DOCTYPE html>\r\n");
//		sb.append("<html lang=\"ru\">\r\n");
//		sb.append("<head>\r\n");
//		sb.append("<meta charset=\"UTF-8\">\r\n");
//		sb.append("</head>\r\n");
//		sb.append("<body>");
//		File html = new File("D:/ws/selenium/nnru/src/test/resources/report_odezhda.html");
//
//		for (String line : lines) {
//			FileUtils.write(html, sb.append("<a href=\"").toString(), "UTF-8");
//			FileUtils.write(html, sb.append(line).toString(), "UTF-8");
//			FileUtils.write(html, sb.append("\">").toString(), "UTF-8");
//			FileUtils.write(html, sb.append(line).toString(), "UTF-8");
//			FileUtils.write(html, sb.append("</a><br>").toString(), "UTF-8");
//		}
//		sb.append("</body>\r\n" + "</html>");
//	}

}
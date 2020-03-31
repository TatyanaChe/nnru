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

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNnruObuv {

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
		System.out.println("obuv is opened");

		// Авторизация
		mainPageObuv.clickLogin();
		mainPageObuv.enterLogin(Props.getLogin());
		mainPageObuv.enterPassword(Props.getPassword());
		mainPageObuv.clickEnterBtn();
		System.out.println("Успешная авторизация");

		// Сортировка
		Thread.sleep(5000);
		mainPageObuv.clickSortBtn();
		System.out.println("clickSortBtn");
		Thread.sleep(1000);
		mainPageObuv.clickUgolok();
		System.out.println("mainPage.clickUgolok");
		mainPageObuv.clickSortSelectByTopic();
		System.out.println("Сортировка");
		Thread.sleep(1000);

		// Фильтр Ищу
		mainPageObuv.clickTopicFilter();
		System.out.println("mainPageObuv.clickTopicFilter");
		mainPageObuv.clickFilterAll();
		mainPageObuv.clickButtonViceVersa();
		mainPageObuv.clickFilterByTitleSeek();
		mainPageObuv.clickButtonApply();

		// Найти линки по всем страницам
		ForumPageObuv forumPageObuv = new ForumPageObuv(driver, 1);
		List<String> allLinks = new ArrayList<String>();
		while (!forumPageObuv.hasNextPage()) {
			System.out.println("getPageNumber: " + forumPageObuv.getPageNumber());
			forumPageObuv = forumPageObuv.nextPage();
			List<String> list = forumPageObuv.foundLinks();
			allLinks.addAll(list);
		}
		int i = 1;
		System.out.println("  allLinks.size: " + allLinks.size());
		for (String string : allLinks) {
			System.out.println("   " + i++ + string);
		}
		System.out.println("===== stream ===========");
		allLinks.stream().forEach(ln -> System.out.println(ln));
	
		// Печать html списка

//		File filename = new File("D:\\ws\\selenuim\\nnru\\src\\test\\resources\\report.html");
//		FileWriter fr = null;
//		
//		String begin = "<!DOCTYPE html>\r\n" + 
//				"<html lang=\"ru\">\r\n" + 
//				"<head>\r\n" + 
//				"<meta charset=\"UTF-8\">\r\n" + 
//				"\r\n" + 
//				"</head>\r\n" + 
//				"<body>\r\n" + 
//				"</body>\r\n" + 
//				"</html>";
//		
//		
//		try {
//			fr = new FileWriter(filename);
//			fr.write(begin);
//		}
//		finally {
//			fr.close();
//		}
		
//		String begin = "<!DOCTYPE html>\r\n" + 
//				"<html lang=\"ru\">\r\n" + 
//				"<head>\r\n" + 
//				"<meta charset=\"UTF-8\">\r\n" + 
//				"\r\n" + 
//				"</head>\r\n" + 
//				"<body>\r\n" + 
//				"</body>\r\n" + 
//				"</html>";
//		String end = "</body>\\r\\n" + "\r\n" + 
//				"</html>";
//		FileOutputStream outputStream;
//		 try {
//	          outputStream = openFileOutput(filename, Context.MODE_APPEND);
//	          outputStream.write(begin);
//	          outputStream.write(end);
//	          outputStream.close();
//	        } catch (Exception e) {
//	          e.printStackTrace();
//	        }
}

}
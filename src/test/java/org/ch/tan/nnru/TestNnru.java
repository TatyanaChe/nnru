package org.ch.tan.nnru;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.ch.tan.common.Browser;
import org.ch.tan.common.DriverFactory;
import org.ch.tan.common.Props;
import org.ch.tan.pages.ForumPage;
import org.ch.tan.pages.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNnru {

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
	public void test() throws InterruptedException {
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
//	Сортировка	
		Thread.sleep(5000);
		mainPage.clickSortBtn();
		System.out.println("clickSortBtn");
		Thread.sleep(1000);
		mainPage.clickUgolok();
		System.out.println("mainPage.clickUgolok");
		mainPage.clickSortSelectByTopic();
		System.out.println("Сортировка");
		Thread.sleep(1000);
//	Фильтр Ищу
		mainPage.clickTopicFilter();
		System.out.println("mainPage.clickTopicFilter");
		mainPage.clickFilterAll();
		mainPage.clickButtonViceVersa();
		mainPage.clickFilterByTitleSeek();
		mainPage.clickButtonApply();
//	Список тем с закрывающими комментами
//		Открыть последнюю тему в новой вкладке
//		ForumPage lastPage = new ForumPage(driver);
//		lastPage.open();
//		Thread.sleep(1000);
//		System.out.println("listAnchors: " + lastPage.listAnchors.size());
//		System.out.println(lastPage.listAnchors);
////		найти текст "тема не актуальна"
//		Thread.sleep(5000);
//		List<String> foundLinks = lastPage.foundLinks();
//		System.out.println("lastPage.foundLinks() " + foundLinks);
	
//	Найти линки по всем страницам
		ForumPage forumPage = new ForumPage(driver, 1);
//		forumPage.open();
//		Thread.sleep(1000);
//		forumPage.foundLinks();
		List<String> allLinks = new ArrayList<String>();
		while (!forumPage.hasNextPage()) {
			System.out.println("getPageNumber: " + forumPage.getPageNumber());
			forumPage = forumPage.nextPage();
			List<String> list = forumPage.foundLinks();
			allLinks.addAll(list);
		}
		
		int i =1;
		System.out.println("  allLinks.size: " + allLinks.size());
		for (String string : allLinks) {
			System.out.println("   " + i++ + string);
		}
		System.out.println("===== stream ===========" );
		allLinks.stream().forEach(ln -> System.out.println(ln));
	
	}

}

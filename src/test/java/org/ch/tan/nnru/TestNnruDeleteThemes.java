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
import org.ch.tan.pages.MainPage;
import org.ch.tan.pages.ModerPage;
import org.ch.tan.pages.ReportPage;
import org.ch.tan.pages.TopicPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.manipulation.Ordering.Context;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNnruDeleteThemes {

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
		Thread.sleep(1000);

		List<WebElement> deleteLinks = new ArrayList<WebElement>();
		deleteLinks = driver.findElements(By.tagName("a"));
		System.out.println("deleteLinks: " + deleteLinks);
		List<String> dellnList = new ArrayList<String>();
		for (WebElement delln : deleteLinks) {
			dellnList.add(delln.getAttribute("href"));
			System.out.println("dellnList: " + dellnList);
//			topicPage.open(driver, delln);
		}
//		reportPage.close();
//		Открываем nn.ru

		MainPage mainPage = new MainPage(driver);
		mainPage.open();
		Thread.sleep(1000);
//		// Авторизация
		mainPage.clickLogin();
		mainPage.enterLogin(Props.getLogin());
		mainPage.enterPassword(Props.getPassword());
		mainPage.clickEnterBtn();
		System.out.println("Успешная авторизация");

//	Закрываем темы
		TopicPage topicPage = new TopicPage(driver);
		for (String delLnStr : dellnList) {
			topicPage.open(delLnStr);
			System.out.println("delLnStr opened: " + delLnStr);
			String winHandleBefore = driver.getWindowHandle();
			System.out.println("winHandleBefore " + driver.getCurrentUrl());
//			Thread.sleep(3000);
			topicPage.moveToItemPropsDesc();
			System.out.println("topicPage.moveToItemPropsDesc()");
			topicPage.moveToMod();
			System.out.println("topicPage.moveToMod()");
			topicPage.moveToEdit();
			System.out.println("topicPage.moveToEdit()");
			topicPage.clickEditBtn();
			System.out.println("topicPage.clickEditBtn()");
//			Thread.sleep(3000);

			ModerPage moderPage = new ModerPage(driver);
			String winHandle = driver.getWindowHandle();
			driver.switchTo().window(winHandle);
			System.out.println("driver.switchTo().window(winHandle) " + driver.getCurrentUrl());
			moderPage.clickCloseBtn();
//			driver.close();
			Thread.sleep(3000);
//			System.out.println("moderPage.clickCloseBtn()");
			driver.switchTo().window(winHandleBefore);
			System.out.println("driver.switchTo().window(winHandleBefore)");

		}

	}

}
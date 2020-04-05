package org.ch.tan.nnru;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.ch.tan.common.Browser;
import org.ch.tan.common.DriverFactory;
import org.ch.tan.common.Props;
import org.ch.tan.pages.ForumPageOdezhda;
import org.ch.tan.pages.MainPageOdezhda;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNnruOdezhda {

	private static Logger logger = LoggerFactory.getLogger(TestNnruOdezhda.class);

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
		MainPageOdezhda mainPageOdezhda = new MainPageOdezhda(driver);
		mainPageOdezhda.open();
		Thread.sleep(1000);
		String odezhdaUrl = "https://www.nn.ru/community/my_baby/detskaya_odezhda/";
		assertTrue("The page is not detskaya odezhda", mainPageOdezhda.getOdezhdaPage().equals(odezhdaUrl));
		logger.info("detskaya odezhda is opened");
		Thread.sleep(5000);

		// Авторизация
		mainPageOdezhda.clickLogin();
//		Thread.sleep(5000);
		mainPageOdezhda.enterLogin(Props.getLogin());
//		Thread.sleep(5000);
		mainPageOdezhda.enterPassword(Props.getPassword());
//		Thread.sleep(5000);
//		
//		Переключение с свплывающего окна на окно форума
//		String current = driver.getWindowHandle();
//		logger.info("current: " + driver.getTitle());
//		Set<String> handles = driver.getWindowHandles();
//		logger.info("handles size" + handles.size());
//		Iterator<String> it = handles.iterator();
//		String popup = "";
//		while (it.hasNext()) {
//			String h = (String) it.next();
//			logger.info("handle: " + driver.getTitle() + " h: " + h);
//			if (!h.equals(current)) {
//				popup = h;
//			}
//		}
//		driver.switchTo().window(current);
//		logger.info("driver.switchTo().window(current)" + current);
//		Thread.sleep(10000);
//		
//		Нажимаем на кнопку Войти мышкой
//		Actions action = new Actions(driver);
//		action.moveToElement(mainPageOdezhda.blueBtn);
//		Thread.sleep(5000);
//		logger.info("move to mainPageOdezhda.blueBtn");
//		action.perform();
//		Thread.sleep(5000);
//		action.click(mainPageOdezhda.blueBtn).perform();
//		logger.info(" click on mainPageOdezhda.blueBtn");
		
//		Нажимаем на кнопку Войти методом submit
		mainPageOdezhda.blueBtn.submit();

//		mainPageOdezhda.clickEnterBtn();
		Thread.sleep(5000);
		logger.info("Успешная авторизация");

		// Сортировка
//		Thread.sleep(10000);
//
//		
		mainPageOdezhda.clickSortBtn();
		logger.info("clickSortBtn");
		Thread.sleep(1000);
		mainPageOdezhda.clickUgolok();
		logger.info("mainPage.clickUgolok");
		mainPageOdezhda.clickSortSelectByTopic();
		logger.info("Сортировка");
		Thread.sleep(1000);

		// Фильтр Ищу
		mainPageOdezhda.clickTopicFilter();
		logger.info("mainPage.clickTopicFilter");
		mainPageOdezhda.clickFilterAll();
//		mainPage.clickButtonViceVersa();
//		mainPage.clickFilterByTitleSeek();
		mainPageOdezhda.clickButtonApply();

		// Найти линки по всем страницам
		ForumPageOdezhda forumPageOdezhda = new ForumPageOdezhda(driver, 1);
		List<String> allLinks = new ArrayList<String>();
		while (!forumPageOdezhda.hasNextPage() && (forumPageOdezhda.getPageNumber() <= 20)) {
			logger.info("getPageNumber: " + forumPageOdezhda.getPageNumber());
			forumPageOdezhda = forumPageOdezhda.nextPage();
			List<String> list = forumPageOdezhda.foundLinks();
			allLinks.addAll(list);
		}
		int i = 1;
		logger.info("  allLinks.size: " + allLinks.size());
		for (String string : allLinks) {
			logger.info("   " + i++ + string);
		}
		logger.info("===== stream ===========");
		String html = convertToHtml(allLinks);
		FileUtils.write(new File("D:/ws/selenium/nnru/src/test/resources/report_odezhda.html"), html,
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

}
package org.ch.tan.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForumPageOdezhda extends PageObject {

	private static Logger logger = LoggerFactory.getLogger(ForumPageOdezhda.class);

	private int pageNumber;
	private static final String URL_TEMPLATE = "https://www.nn.ru/community/my_baby/detskaya_odezhda/?Part=%s";

//	@FindBy(xpath = "*//*[@class='heading-cont']/a[1]")
//	public List<WebElement> forumList;

	@FindBy(xpath = "*//*[@class='heading-cont']")
	public List<WebElement> forumList;

//	@FindBy(xpath = "//*[@class='comment__send']")
//	public WebElement sendGiftBtn;

	@FindBy(xpath = "//div[contains(text(),'[x]')]")
	public WebElement topicClosedElement;

	public ForumPageOdezhda(WebDriver driver, int pageNumber) {
		super(driver);
		this.pageNumber = pageNumber;
	}

	public void open() {
		driver.get("https://www.nn.ru/community/my_baby/detskaya_odezhda/?Part=1");
	}

	public void clickLastAnchor() {
		forumList.get(17).click();
		logger.info("getCurrentUrl" + driver.getCurrentUrl());

	}

	public List<String> foundLinks() throws InterruptedException {

		boolean result = false;
		logger.info("listAnchors: " + forumList);
		List<String> links = new ArrayList<String>();
		for (WebElement forumLink : forumList) {
			String text = forumLink.getText();
			logger.info("forumLink text: " + text);
			boolean isClosed = text.contains("[x]");
			logger.info("isClosed = text.contains(\"[x]\") : " + isClosed);
			String forumLinkhref = forumLink.findElement(By.xpath("./a")).getAttribute("href");
//			String href = forumLinkhref.getAttribute("href");
			if (!isClosed) {
//				String href = forumLink.getAttribute("href");
				links.add(forumLinkhref);
				logger.info("links: " + links);
				logger.info("not closed: " + forumLinkhref);
			} else {
//				String href = forumLink.getAttribute("href");
				logger.info("closed : " + forumLinkhref);
			}
		}
		List<String> found = new ArrayList<String>();
		int it = 1;
		for (String ln : links) {
			try {
				driver.get(ln);
				logger.info("ln " + it + ": " + driver.getCurrentUrl());
				String bodyText = driver.findElement(By.tagName("body")).getText();
//				logger.info("bodyText: " + bodyText);
//				result = bodyText.contains("продан");
				result = (bodyText.contains("пристроен") 
						|| bodyText.contains("продан") 
						|| bodyText.contains("закрыть") 
						|| bodyText.contains("тема не актуальна"));
//				result = bodyText.contains("тема не актуальна");
//				result = bodyText.contains("купили");
//				result = bodyText.contains("нашл");
				if (result) {
					found.add(ln);
					logger.info("found: " + found);
				}
				logger.info("result: " + result);
			} catch (Exception e) {
				// TODO: handle exception
			}
			it = it + 1;
		}
		return found;

	}

	public boolean hasNextPage() {
		boolean resultPageNotExist = false;
		String bodyPageNotExist = driver.findElement(By.tagName("body")).getText();
		if (bodyPageNotExist.contains("На этом форуме нет тем")) {
			resultPageNotExist = true;
		}
		return resultPageNotExist;
	}

	public ForumPageOdezhda nextPage() {
		String nextUrl = String.format(URL_TEMPLATE, pageNumber++);
		driver.get(nextUrl);
		ForumPageOdezhda page = new ForumPageOdezhda(driver, pageNumber);
		return page;

	}

	public int getPageNumber() {
		return pageNumber;
	}

}

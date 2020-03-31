package org.ch.tan.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForumPageObuv extends PageObject {

	private int pageNumber;
	private static final String URL_TEMPLATE = "https://www.nn.ru/community/my_baby/detskaya_obuv/?Part=%s";

//	@FindBy(xpath = "*//*[@class='heading-cont']/a[1]")
//	public List<WebElement> forumList;
	
	@FindBy(xpath = "*//*[@class='heading-cont']")
	public List<WebElement> forumList;

//	@FindBy(xpath = "//*[@class='comment__send']")
//	public WebElement sendGiftBtn;

	@FindBy(xpath = "//div[contains(text(),'[x]')]")
	public WebElement topicClosedElement;

	public ForumPageObuv(WebDriver driver, int pageNumber) {
		super(driver);
		this.pageNumber = pageNumber;
	}

	public void open() {
		driver.get("https://www.nn.ru/community/my_baby/detskiy-transport/?Part=7");
	}
//	*//a[contains(@href)]
//	*//*[@class='heading-cont']//a[contains(@href)]

	public void clickLastAnchor() {
		forumList.get(17).click();
		System.out.println("getCurrentUrl" + driver.getCurrentUrl());

	}

	public List<String> foundLinks() throws InterruptedException {

		boolean result = false;
		System.out.println("listAnchors: " + forumList);
		List<String> links = new ArrayList<String>();
		for (WebElement forumLink : forumList) {
			System.out.println("isTopicClosed: " + topicClosedElement.getText());
			String text = forumLink.getText();
			System.out.println("forumLink text: " + text);
			boolean isClosed = text.contains("[x]");
			System.out.println("isClosed = text.contains(\"[x]\") : " + isClosed);
			String forumLinkhref = forumLink.findElement(By.xpath("./a")).getAttribute("href");
//			String href = forumLinkhref.getAttribute("href");
			if (!isClosed) {
//				String href = forumLink.getAttribute("href");
				links.add(forumLinkhref);
				System.out.println("links: " + links);
				System.out.println("not closed: " + forumLinkhref);
			} else {
//				String href = forumLink.getAttribute("href");
				System.out.println("closed : " + forumLinkhref);
			}
		}
		List<String> found = new ArrayList<String>();
		int it = 1;
		for (String ln : links) {
			try {
				driver.get(ln);
				System.out.println("ln " + it + ": " + driver.getCurrentUrl());
				String bodyText = driver.findElement(By.tagName("body")).getText();
//				System.out.println("bodyText: " + bodyText);
//				result = bodyText.contains("закрыть");
//				result = bodyText.contains("тема не актуальна");
				result = bodyText.contains("купили");
//				result = bodyText.contains("нашл");
				if (result) {
					found.add(ln);
					System.out.println("found: " + found);
				}
				System.out.println("result: " + result);
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

	public ForumPageObuv nextPage() {
		String nextUrl = String.format(URL_TEMPLATE, pageNumber++);
		driver.get(nextUrl);
		ForumPageObuv page = new ForumPageObuv(driver, pageNumber);
		return page;

	}

	public int getPageNumber() {
		return pageNumber;
	}

//	public void printHtml() {
//		for (int i = 0; i < allLinks.length; i++) {
//			
//		}
		
//	}

//	public List<String> foundOpenedThemes() {
//		boolean result = false;
//		List<String> links = new ArrayList<String>();
//		for (WebElement listAnchor : listAnchors) {
//			String href = listAnchor.getAttribute("href");
//			links.add(href);
//			System.out.println(" + href: " + href);
//		}
//		List<String> foundOpened = new ArrayList<String>();
//		int it = 1;
//		for (String ln : links) {
//			try {
//				driver.get(ln);
//				System.out.println("ln " + it + ": " + driver.getCurrentUrl());
////				String bodyText = driver.findElement(By.tagName("body")).getText();
////				System.out.println("bodyText: " + bodyText);
////				result = bodyText.contains("закрыть");
////				result = bodyText.contains("тема не актуальна");
////				result = bodyText.contains("купили");
////				result = bodyText.contains("                        [x]                            ");
////				result = bodyText.contains("comment__send comment__send_gift giftUrl");
////				result = sendGiftBtn.isDisplayed() && sendGiftBtn.isEnabled();
//				if (result) {
//					foundOpened.add(ln);
////					System.out.println("foundOpened:  in if " + foundOpened);
//				}
//				System.out.println("result opened: in try " + result);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			it = it + 1;
//			System.out.println("foundOpened:  in if " + foundOpened);
//		}
//		return foundOpened;
//
//	}

}

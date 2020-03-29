package org.ch.tan.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LastPage extends PageObject {

	@FindBy(xpath = "*//*[@class='heading-cont']/a[1]")
	public List<WebElement> listAnchors;

	public LastPage(WebDriver driver) {
		super(driver);
	}

	public void open() {
		driver.get("https://www.nn.ru/community/my_baby/detskiy-transport/?Part=7");
	}
//	*//a[contains(@href)]
//	*//*[@class='heading-cont']//a[contains(@href)]

	public void clickLastAnchor() {
		listAnchors.get(17).click();
		System.out.println("getCurrentUrl" + driver.getCurrentUrl());

	}

	public List<String> foundLinks() throws InterruptedException {

		boolean result = false;
		System.out.println("listAnchors: " + listAnchors);
		List<String> links = new ArrayList<String>();
		for (WebElement listAnchor : listAnchors) {
			// listAnchor.click();
			String href = listAnchor.getAttribute("href");
			links.add(href);
			System.out.println(" + href: " + href);
		}
		List<String> found = new ArrayList<String>();
		for (String ln : links) {
			try {
				driver.get(ln);
				System.out.println("ln: " + driver.getCurrentUrl());
				String bodyText = driver.findElement(By.tagName("body")).getText();
//				System.out.println("bodyText: " + bodyText);
				result = bodyText.contains("тема не актуальна");
				if (result) {
					found.add(ln);
					System.out.println("found: " + found);
				}
				System.out.println("result: " + result);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return found;

	}

	public void findClosingComment() {
		// TODO Auto-generated method stub

	}

}

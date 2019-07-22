package com.clearTrip;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClearTrip {

	public static void main(String[] args) throws Exception {

		WebDriver driver = new ChromeDriver();

		System.getProperty("webdriver.chrome.driver", "E:\\Java By Kiran\\ClearTrip\\chromedriver.exe");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.manage().deleteAllCookies();

		driver.get("https://www.cleartrip.com");

		Thread.sleep(2000);

		// Actions act = new Actions(driver);

		// act.keyDown(Keys.CONTROL).sendKeys(Keys.F5);

		WebDriverWait wait = new WebDriverWait(driver, 30);

		// -----------------------------------------------------------------

		WebElement roadTripButton = driver.findElement(By.id("RoundTrip"));

		roadTripButton.click();

		WebElement FromBox = driver.findElement(By.id("FromTag"));

		FromBox.click();

		FromBox.sendKeys("mumbai");

		Thread.sleep(2000);

		WebElement mumbai = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Mumbai,')]")));

		Actions action = new Actions(driver);

		action.moveToElement(mumbai).click().build().perform();

		WebElement toTag = driver.findElement(By.id("ToTag"));

		toTag.sendKeys("new Delhi");

		Thread.sleep(2000);

		WebElement delhi = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//a[contains(text(),'New Delhi, IN - Indira Gandhi Airport (DEL)')]")));

		action.moveToElement(delhi).click().build().perform();

		// -----------------------------------------------------------------

		DateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy");

		Date date = new Date();

		String currentDate = dateformat.format(date);

		System.out.println("Current Date is >> " + currentDate);

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DAY_OF_MONTH, 6);

		String departureDate = sdf.format(cal.getTime());

		System.out.println("Departure Date >> " + departureDate);

		Calendar cal1 = Calendar.getInstance();

		cal1.add(Calendar.DAY_OF_MONTH, 7);

		String returnDate = sdf.format(cal1.getTime());

		System.out.println("Return Date >> " + returnDate);

		// -----------------------------------------------------------------

		String departureDateArr[] = departureDate.split("-");

		String day = departureDateArr[0];
		String month = departureDateArr[1];
		String year = departureDateArr[2];

		String beforeXpath = "//div[@id=\"ui-datepicker-div\"]//table[@class=\"calendar\"]//tbody//tr[";

		String afterXpath = "]//td[";

		boolean flag = false;

		for (int row = 1; row <= 7; row++) {

			for (int col = 1; col <= 7; col++) {

				String dayValue = driver.findElement(By.xpath(beforeXpath + row + afterXpath + col + "]")).getText();

				System.out.println(dayValue);

				if (dayValue.equals(day)) {

					WebElement ClickOnValue = wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath(beforeXpath + row + afterXpath + col + "]")));

					ClickOnValue.click();

					flag = true;

					break;
				}
			}

			if (flag) {

				break;
			}
		}

		// -----------------------------------------------------------------

		String returnDateArr[] = returnDate.split("-");

		String returnDay = returnDateArr[0];
		String returnMonth = returnDateArr[1];
		String returnYear = returnDateArr[2];

		String beforeXpath1 = "//div[@class=\"monthBlock first\"]//table[@class=\"calendar\"]//tbody//tr[";

		String afterXpath1 = "]//td[";

		boolean flag1 = false;

		for (int row = 1; row <= 7; row++) {

			for (int col = 1; col <= 7; col++) {

				String dayValue = driver.findElement(By.xpath(beforeXpath1 + row + afterXpath1 + col + "]")).getText();

				System.out.println(dayValue);

				if (dayValue.equals(returnDay)) {

					WebElement ClickOnValue = wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath(beforeXpath + row + afterXpath + col + "]")));

					ClickOnValue.click();

					flag1 = true;

					break;
				}
			}

			if (flag1) {

				break;
			}
		}

		// -----------------------------------------------------------------

		WebElement adults = driver.findElement(By.id("Adults"));

		Select select = new Select(adults);

		select.selectByVisibleText("2");

		List<WebElement> al = select.getOptions();

		System.out.println("Adults Drop Down List >> ");

		for (WebElement element : al) {

			System.out.println(element.getText());
		}

		System.out.println("=========================================");

		WebElement SearchBtn = driver.findElement(By.id("SearchBtn"));

		SearchBtn.click();

		// ===============================================================================================

		List<WebElement> allFlights = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By
				.xpath("//div[@class=\"filterContent clearFix  hasList  \"]//span[@class=\" span span18 truncate\"]")));

		System.out.println("All Flights are >>");

		for (WebElement element : allFlights) {

			if (!element.getText().equals("IndiGo") && !element.getText().equals("SpiceJet")) {

				element.click();

			}
			System.out.println(element.getText());
		}

		System.out.println("=========================================");

		List<WebElement> IndiGoList = wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='colZero leg col12']//img[@title='IndiGo']")));

		for (int i = 0; i < IndiGoList.size(); i++) {

			if (i == 1) {

				IndiGoList.get(i).click();
			}

		}

		System.out.println("=========================================");

		List<WebElement> SpiceJetList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//div[@class='colZero leg col12 last']//img[@title='SpiceJet']")));

		for (int i = 0; i < SpiceJetList.size(); i++) {

			if (i == 1) {

				SpiceJetList.get(i).click();
			}

		}

		WebElement bookTicket = driver.findElement(
				By.xpath("//div[@style=\"display: block;\"]//button[@class='booking fRight' and @type='submit']"));

		bookTicket.click();

		System.out.println("=========================================");

		WebElement insurance_box = driver.findElement(By.id("insurance_box"));

		insurance_box.click();

		WebElement insurance_confirm = driver.findElement(By.id("insurance_confirm"));

		insurance_confirm.click();

		WebElement itineraryBtn = driver.findElement(By.id("itineraryBtn"));

		itineraryBtn.click();
	}

}

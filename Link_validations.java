package seo_task;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ExcelUtil.writeExcel;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Link_validations extends writeExcel {

	public void verifyLink() {

		writeExcel objwriteExcel = new writeExcel();

		String homePage = "https://www.stfc.in/";
		String url = "";
		//String url1 = "http://";
		HttpURLConnection huc = null;
		int respCode = 200;

		WebDriver driver;

		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get(homePage);

		System.out.println(driver.getTitle());

		List<WebElement> links = driver.findElements(By.tagName("a"));

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		Iterator<WebElement> it = links.iterator();

		int count = 0;

		String path = "D:\\";
		String filename = "eclipse-workspace\\SEO_Master\\Data\\BrokenLink_Result.xls";
		String ValidURL = "ValidURL";
		String BrokenURL = "BrokenURL";
		String sheetotherDomain = "OtherDomainURL";
		String sheetNullURL = "NullURL";
		String sheetHttpHead = "HttpHeader";

		while (it.hasNext()) {

			System.out.println(count);

			url = it.next().getAttribute("href");

//        System.out.println(url);

			try {
				if (url == null || url.isEmpty()) {

					objwriteExcel.write_to_Excel(path, filename, sheetNullURL, url);

					System.out.println("URL is either not configured for anchor tag or it is empty");
					continue;
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				if (!url.startsWith(homePage)) {
					objwriteExcel.write_to_Excel(path, filename, sheetotherDomain, url);
					System.out.println(url + "" + "other domain");
					System.out.println("URL belongs to another domain, skipping it.");
					continue;
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				huc = (HttpURLConnection) (new URL(url).openConnection());

				huc.setRequestMethod("HEAD");

				huc.connect();

				respCode = huc.getResponseCode();
				
				if (respCode >= 400) {
					objwriteExcel.write_to_Excel(path, filename, BrokenURL, url);
					System.out.println(url + " is a broken link" + " " + respCode);
				} else {
					objwriteExcel.write_to_Excel(path, filename, ValidURL, url);
					System.out.println(url + " is a valid link" + " " + respCode);

				}

				if (url.contains("http://")) {
					System.out.println(url);
					objwriteExcel.write_to_Excel(path, filename, sheetHttpHead, url);
					System.out.println(url + " is a HTTP request" + " " + respCode);
				}

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count++;
		}

		driver.quit();
	}
}

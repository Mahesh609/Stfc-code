package seo_task;


import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import ExcelUtil.Excel_Reader;
import ExcelUtil.writeExcel;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Broken_images_validation extends writeExcel {

	public void verifyImages() {
		
		String path = "E:\\";
		String filename = "Automation_Projects\\SEO_Master\\Data\\BrokenImage_Result.xls";
		String ImageURL = "ImageURL";
		String BrokenImageURL = "BrokenImageURL";
		String SheetURL = "InputURL";
		String columName = "PageURL";

		System.out.println(path+filename);
		
		Excel_Reader readexcel = new Excel_Reader(path+filename);
		
		String homeURL = readexcel.getCellData(SheetURL, columName, 2);
		
		System.out.println(homeURL);
		
		writeExcel objwriteExcel = new writeExcel();

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(homeURL);

		// Storing all elements with img tag in a list of WebElements

		List<WebElement> images = driver.findElements(By.tagName("img"));

		System.out.println("Total number of Images on the Page are " + images.size());

		// checking the links fetched.
		for (int index = 0; index < images.size(); index++) {

			WebElement image = images.get(index);
			String imageURL = image.getAttribute("src");

			System.out.println("URL of Image " + (index + 1) + " is: " + imageURL);

			verifyLinks(imageURL);

			// Validate image display using JavaScript executor
			try {
				boolean imageDisplayed = (Boolean) ((JavascriptExecutor) driver).executeScript(
						"return (typeof arguments[0].naturalWidth !=\"undefined\" && arguments[0].naturalWidth > 0);",
						image);
				if (imageDisplayed) {
					objwriteExcel.write_to_Excel(path, filename, ImageURL, imageURL);
					System.out.println("DISPLAY - OK");
				} else {
					objwriteExcel.write_to_Excel(path, filename, ImageURL, BrokenImageURL);
					System.out.println("DISPLAY - BROKEN");
				}
			} catch (Exception e) {
				System.out.println("Error Occured");
			}
		}

		driver.quit();
	}

	public void verifyLinks(String linkUrl) {
		try {
			URL url = new URL(linkUrl);

			// Now we will be creating url connection and getting the response code
			HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
			httpURLConnect.setConnectTimeout(5000);
			httpURLConnect.connect();
			if (httpURLConnect.getResponseCode() >= 400) {
				System.out.println("HTTP STATUS - " + httpURLConnect.getResponseMessage() + "is a broken link");
			}

			// Fetching and Printing the response code obtained
			else {
				System.out.println("HTTP STATUS - " + httpURLConnect.getResponseMessage());
			}
		} catch (Exception e) {
		}
	}

}

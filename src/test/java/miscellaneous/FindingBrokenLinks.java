package miscellaneous;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FindingBrokenLinks {

	public static void main(String[] args) throws MalformedURLException, IOException {
		int responseCode;
		String url;
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://www.zlti.com");
		List<WebElement> links = driver.findElements(By.tagName("a"));

		Iterator<WebElement> linksIterator = links.iterator();

		while (linksIterator.hasNext()) {
			try {
				url = linksIterator.next().getAttribute("href");
				HttpURLConnection openConnection = (HttpURLConnection) (new URL(url).openConnection());
				openConnection.setRequestMethod("HEAD");
				openConnection.connect();
				responseCode = openConnection.getResponseCode();

				if (responseCode >= 400) {
					System.out.println(url + " is a broken link");
				} else {
					//System.out.println(url + " is a valid link");
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		driver.close();

	}

}

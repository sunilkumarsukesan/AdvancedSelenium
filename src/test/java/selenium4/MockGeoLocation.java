package selenium4;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MockGeoLocation {
	
	@Test
	public void mockGeoLocation() {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		Map<String, Object> coordinates = new HashMap<String, Object>();
		coordinates.put("latitude", 40.378660);
		coordinates.put("longitude", -111.817410);
		coordinates.put("accuracy", 100);
		
	  ((ChromiumDriver) driver).executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
	  driver.get("https://oldnavy.gap.com/stores");
		
	}
	

}
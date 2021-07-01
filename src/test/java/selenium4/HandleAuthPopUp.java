package selenium4;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.Headers;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandleAuthPopUp {

	private static final String username = "admin";
	private static final String password = "admin";

	@Test
	public void handleAuthPopUp() {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		devTools.send(Network.enable(Optional.<Integer>empty(), Optional.<Integer>empty(), Optional.<Integer>empty()));

		Map<String, Object> headers = new HashMap<String, Object>();
		String basicAuth = "Basic "	+ new String(new Base64().encode(String.format("%s:%s", username, password).getBytes()));
		headers.put("Authorization", basicAuth);

		devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

		driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");

	}

}

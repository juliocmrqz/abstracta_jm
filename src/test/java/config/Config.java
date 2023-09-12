package config;

import com.google.gson.GsonBuilder;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class Config {
  private static WebDriver driver;
  private static Properties properties;

  public static void Setup() throws Exception {
    try {
      /*
          I have the configuration variables in a separate json in order to avoid having to change a
          local variable, but having the liberty to change properties on demand.
      */
      String file = "./src/test/java/resources/appSettings.json";
      String json = new String(Files.readAllBytes(Paths.get(file)));
      properties = new GsonBuilder().serializeNulls().create().fromJson(json, Properties.class);
      switch (properties.browser) {
        case "chrome":
          WebDriverManager.chromedriver().setup();
          ChromeOptions options = new ChromeOptions();
          options.addArguments("--remote-allow-origins=*");
          driver = new ChromeDriver(options);
          driver.get(properties.projectUrl);
          driver.manage().window().maximize();
          driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(properties.implicitWait));
          break;
        case "edge":
          /*
              JM: In case there's another browser to test this application,
              here it would be that configuration
          */
          WebDriverManager.edgedriver().setup();
          break;
      }
    } catch (Exception error) {
      throw new Exception("The driver couldn't be initialized, reason: " + error.getMessage());
    }
  }

  public static WebDriver getDriver() {
    return driver;
  }

  public static Properties getProperties() {
    return properties;
  }

  public static void closeDriver() {
    if (driver != null) {
      driver.quit();
    }
  }
}

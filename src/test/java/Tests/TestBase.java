package Tests;

import config.Config;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
  @BeforeSuite
  public void beforeSuite() throws Exception {
    Config.Setup();
  }

  @AfterSuite
  public void afterSuite() throws Exception {
    Config.closeDriver();
  }
}

package POM;

import config.Config;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MLMainPage extends PageBase {
  @FindBy(css = "[role=search] input")
  private WebElement mlSearch;

  @FindBy(css = ".nav-search-btn")
  private WebElement mlSearchButton;

  public MLMainPage() throws Exception {
    super();
    try {
      Config.getDriver().findElement(By.cssSelector("[data-siteid=\"MLU\"]"));
    } catch (Exception error) {
      throw new Exception("La página no está disponible");
    }
  }

  @Step
  public static MLMainPage verifyPage () throws Exception {
    return new MLMainPage();
  }

  @Step
  public MLMainPage typeInSearchBox(String text) throws Exception {
    sendKeys(mlSearch, text);
    return this;
  }

  @Step
  public MLResultsPage clickOnSearchButton() throws Exception {
    clickElement(mlSearchButton);
    return new MLResultsPage();
  }
}

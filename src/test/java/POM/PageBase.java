package POM;

import config.Config;
import config.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PageBase {
  private final WebDriver driver;
  private final WebDriverWait wait;
  private final Actions actions;
  protected Properties PROPS;

  public PageBase() {
    driver = Config.getDriver();
    PageFactory.initElements(driver, this);
    PROPS = Config.getProperties();
    wait = new WebDriverWait(driver, Duration.ofSeconds(PROPS.implicitWait));
    actions = new Actions(driver);
  }

  protected WebElement getElement(By by) throws Exception {
    try {
      wait.until(ExpectedConditions.presenceOfElementLocated(by));
      return driver.findElement(by);
    } catch (Exception error) {
      return null;
    }
  }

  protected List<WebElement> getElements(By by) throws Exception {
    try {
      wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
      return driver.findElements(by);
    } catch (Exception error) {
      throw new Exception("No se consiguieron los elementos: " + by.toString());
    }
  }

  protected void clickElement(WebElement element) throws Exception {
    try {
      wait.until(ExpectedConditions.visibilityOf(element));
      element.click();
    } catch (Exception error) {
      throw new Exception("No se pudo hacer click al elemento");
    }
  }

  protected void sendKeys(WebElement element, String text) throws Exception {
    try {
      wait.until(ExpectedConditions.visibilityOf(element));
      element.sendKeys(text);
    } catch (Exception error) {
      throw new Exception("No se pudo escribir en el elemento");
    }
  }

  protected WebElement getChildren(WebElement parentElement, By childrenLocator) throws Exception {
    try {
      return parentElement.findElement(childrenLocator);
    } catch (Exception error) {
      throw new Exception("No se pudo conseguir el elemento");
    }
  }

  protected String getAttribute(WebElement element, String attribute) throws Exception {
    try {
      return element.getAttribute(attribute);
    } catch (Exception error) {
      throw new Exception("No se consiguió el atributo");
    }
  }

  protected String getElementText(WebElement element) throws Exception {
    try {
      return element.getText();
    } catch (Exception error) {
      throw new Exception("No se puede obtener el texto del elemento");
    }
  }

  protected void scrollToElement(WebElement element) throws Exception {
    try {
      actions.moveToElement(element).perform();
    } catch (Exception error) {
      throw new Exception("No se pudo scrollear al elemento");
    }
  }
}

package POM;

import config.Config;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Result;
import utils.Results;

import java.util.List;

public class MLResultsPage extends PageBase {
  private final By resultsList = By.cssSelector("ol li");
  private final By link = By.cssSelector("a:has(h2)");

  private final By price = By.cssSelector("span.andes-money-amount span.andes-money-amount__fraction");

  @FindBy(css = "a[title='Siguiente']")
  private WebElement nextButton;

  public MLResultsPage() throws Exception {
    super();

    try {
      Config.getDriver().findElement(By.cssSelector("button.andes-dropdown__trigger"));
    } catch (Exception error) {
      throw new Exception("La página de resultados no está disponible");
    }
  }

  @Step
  public static MLResultsPage verifyPage () throws Exception {
    return new MLResultsPage();
  }

  @Step
  public MLResultsPage getAllElementsFromPage() throws Exception {
    List<WebElement> list = getElements(resultsList);
    for (WebElement e : list) {
      scrollToElement(e);
      WebElement anchor = getChildren(e, link);
      WebElement priceSpan = getChildren(e, price);
      Results.results.add(
        new Result(
          getAttribute(anchor, "title"),
          getElementText(priceSpan),
          getAttribute(anchor, "href")
        )
      );
    }
    return this;
  }

  @Step
  public MLResultsPage clickOnNextButton() throws Exception {
    WebElement cookieBtn = getElement(By.cssSelector(".cookie-consent-banner-opt-out__action[data-testid=\"action:understood-button\"]"));
    if (cookieBtn != null) {
     clickElement(cookieBtn);
    }
    scrollToElement(nextButton);
    clickElement(nextButton);
    return this;
  }

  @Step
  public boolean generateFile() {
    try {
      Results.printToFile();
      return true;
    } catch (Exception error) {
      return false;
    }
  }
}

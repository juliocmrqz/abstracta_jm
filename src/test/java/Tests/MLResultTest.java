package Tests;

import POM.MLMainPage;
import POM.MLResultsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(io.qameta.allure.testng.AllureTestNg.class)
public class MLResultTest extends TestBase {


  @Test
  @Description("This test gets information from ML to CSV file")
  @Severity(SeverityLevel.NORMAL)
  public void GenerateTextFileWithResults() throws Exception {
    MLMainPage
      .verifyPage()
      .typeInSearchBox("camisetas")
      .clickOnSearchButton();

    boolean isFileGenerated = MLResultsPage
      .verifyPage()
      .getAllElementsFromPage()
      .clickOnNextButton()
      .getAllElementsFromPage()
      .clickOnNextButton()
      .getAllElementsFromPage()
      .generateFile();

    Assert.assertTrue(
      isFileGenerated,
      "El archivo no se pudo generar"
    );
  }
}

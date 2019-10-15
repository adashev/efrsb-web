package ru.qa.test.bankrot.pages.report;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qa.test.bankrot.appmanager.HelperBase;

public class ReportListPage extends HelperBase {

  public ReportListPage(WebDriver wd, WebDriverWait wait, Actions actions) {
    super(wd, wait, actions);
  }

  @Step("щелкнуть на ссылке 'Создать отчет'")
  public void clickAddReport() throws InterruptedException { // нажатие на кнопку "добавить сообщение"
    click(By.id("ctl00_ctl00_ctplhMain_CentralContentPlaceHolder_ReportListControl_lnkCreateNewReport"));
  }

}

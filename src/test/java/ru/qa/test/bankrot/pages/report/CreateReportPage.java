package ru.qa.test.bankrot.pages.report;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qa.test.bankrot.appmanager.HelperBase;

public class CreateReportPage extends HelperBase {
  public CreateReportPage(WebDriver wd, WebDriverWait wait, Actions actions) {
    super(wd, wait, actions);
  }

  public void fillBasicReportData(String date) {
//    setCorrAddress();
    inputCurrentDate(date);
    inputText();
    inputTime();
  }
}

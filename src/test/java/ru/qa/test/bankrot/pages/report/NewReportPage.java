package ru.qa.test.bankrot.pages.report;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qa.test.bankrot.appmanager.HelperBase;

public class NewReportPage extends HelperBase {

  public NewReportPage(WebDriver wd, WebDriverWait wait, Actions actions) {
    super(wd, wait, actions);
  }

  public void selectReportOptions() throws InterruptedException {
    isOptionsPage();
    selectLastDebtor("report"); // выбираем "последнего" должника
    selectCourtCase("report"); // выбор Номера дела
    selectProcedure();
    clickNextButton();
  }

  private void selectProcedure() {
    click(By.xpath(contentPlace+"_ucNewAuReport_ddlProcedureTypes']/option[2]"));
  }



}

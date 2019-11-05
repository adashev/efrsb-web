package ru.qa.test.bankrot.tests.au;


import io.qameta.allure.Description;
import org.testng.annotations.Test;
import ru.qa.test.bankrot.tests.TestBase;

public class ReportPublishingTest extends TestBase {

  @Test(groups = {"au"}, priority = 100)
  @Description("Тест создания и подписания отчета по процедуре 'Наблюдение'")
  public void testFinalWatchingReport2() throws InterruptedException {
    Thread.sleep(60000);
    app.getHelperBase().gotoReportList();
    app.getReportListPage().clickAddReport();
    app.getNewReportPage().selectReportOptions();
    app.getCreateReportPage().fillBasicReportData(app.getHelperBase().formCurDate);

//    app.getMessagesListPage().clickAddMessage();


  }


}

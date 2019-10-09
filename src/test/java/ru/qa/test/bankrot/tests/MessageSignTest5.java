package ru.qa.test.bankrot.tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class MessageSignTest5 extends TestBase {

  @Test(priority = 53)
  @Description("Тест создания и подписания Сведений о порядке и месте ознакомления с проектом плана реструктуризации")
  public void testViewDraftRestructuringPlan() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(13, 0);
    app.getCreateMessage().fillBasicData("Сведения о порядке и месте ознакомления с проектом плана реструктуризации", "none");
    app.getCreateMessage().fillDataForViewDraftRestructuringPlan();
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }


}




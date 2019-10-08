package ru.qa.test.bankrot.tests;

import io.qameta.allure.Description;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.Test;

public class MessageSignTest4 extends TestBase {

  @Test(priority = 42)
  @Description("Тест создания и подписания Решения о назначении временной администрации")
  public void testAppointAdministration() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(12, 0);
    app.getCreateMessage().fillBasicData("Решение о назначении временной администрации", app.getHelperBase().formCurDate);
    app.getCreateMessage().fillDataForAppointAdministration();
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }

  @Test(priority = 43)
  @Description("Тест создания и подписания Сообщения о намерении исполнить обязательства кредитной организации")
  public void testIntentionCreditOrg() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(12, 1);
    app.getCreateMessage().fillBasicData("Сообщение о намерении исполнить обязательства кредитной организации", app.getHelperBase().formCurDate);
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }


}




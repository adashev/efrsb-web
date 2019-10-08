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
    app.getCreateMessage().fillBasicData("Сообщение о намерении исполнить обязательства кредитной организации", "none");
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }

  @Test(priority = 44)
  @Description("Тест создания и подписания Сообщения о признании исполнения заявителем обязательств кредитной организации несостоявшимся")
  public void testLiabilitiesCreditOrg() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(12, 2);
    app.getCreateMessage().fillBasicData("Сообщение о признании исполнения заявителем обязательств кредитной организации несостоявшимся", "none");
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }

  @Test(priority = 45)
  @Description("Тест создания и подписания Сообщения об исполнении обязательств кредитной организации")
  public void testPerformanceCreditOrg() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(12, 3);
    app.getCreateMessage().fillBasicData("Сообщение об исполнении обязательств кредитной организации", "none");
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }


}




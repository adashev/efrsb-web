package ru.qa.test.bankrot.tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class MessageSignTest5 extends TestBase {

  @Test(priority = 52)
  @Description("Тест создания и подписания Сведений о порядке и месте ознакомления с проектом плана реструктуризации")
  public void testViewDraftRestructuringPlan() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(13, 0);
    app.getCreateMessage().fillBasicData("Сведения о порядке и месте ознакомления с проектом плана реструктуризации", "none");
    app.getCreateMessage().fillDataForViewDraftRestructuringPlan();
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }

  @Test(priority = 53)
  @Description("Тест создания и подписания Сведений о порядке и месте ознакомления с отчетом о результатах исполнения плана реструктуризации")
  public void testViewExecRestructuringPlan() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(13, 1);
    app.getCreateMessage().fillBasicData("Сведения о порядке и месте ознакомления с отчетом о результатах исполнения плана реструктуризации",
         app.getHelperBase().formCurDate);
    app.getCreateMessage().fillDataForViewExecRestructuringPlan();
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }

  @Test(priority = 54)
  @Description("Тест создания и подписания Сообщения о наличии или об отсутствии признаков преднамеренного или фиктивного банкротства")
  public void testDeliberateBankruptcy() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(14, 0);
    app.getCreateMessage().fillBasicData("Сообщение о наличии или об отсутствии признаков преднамеренного или фиктивного банкротства","none");
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }

  @Test(priority = 55)
  @Description("Тест создания и подписания Сообщения об отмене сообщения о наличии или об отсутствии признаков преднамеренного или фиктивного банкротства")
  public void testCancelDeliberateBankruptcy() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(14, 1);
    app.getCreateMessage().fillBasicData("Сообщение об отмене сообщения о наличии или об отсутствии признаков преднамеренного или фиктивного банкротства","none");
    app.getCreateMessage().selectMessageFromTheList("для отмены");
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }

  @Test(priority = 56)
  @Description("Тест создания и подписания Сообщения об изменении сообщения о наличии или об отсутствии признаков преднамеренного или фиктивного банкротства")
  public void testChangeDeliberateBankruptcy() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(14, 2);
    app.getCreateMessage().fillBasicData("Сообщение об изменении сообщения о наличии или об отсутствии признаков преднамеренного или фиктивного банкротства","none");
    app.getCreateMessage().selectMessageFromTheList("для изменения");
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }




}




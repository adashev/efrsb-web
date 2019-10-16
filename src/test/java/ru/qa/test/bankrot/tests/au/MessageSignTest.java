package ru.qa.test.bankrot.tests.au;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import ru.qa.test.bankrot.tests.TestBase;


public class MessageSignTest extends TestBase {
 @Test(priority = 1)
 @Description("Тест создания и подписания Сообщения о судебном акте")
 public void testMessageArbitralDecree() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageOptionsAndGoNext(0);
    app.getCreateMessagePage().fillBasicData("Сообщение о судебном акте", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().selectTypeArbitralDecree();
//  app.getCreateMessagePage().clickSaveMessAndCloseAlert();
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 2)
  @Description("Тест создания и подписания Уведомления о получении требований кредитора")
  public void testReceivingCreditorDemand() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageOptionsAndGoNext(1);
    app.getCreateMessagePage()
       .fillBasicData("Уведомление о получении требований кредитора", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 3)
  @Description("Тест создания и подписания Иного сообщения")
  public void testMessageOther() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageOptionsAndGoNext(2); // для ОТ - 0,  для АУ - 2
    app.getCreateMessagePage().fillBasicData("Иное сообщение", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 4)
  @Description("Тест создания и подписания cообщения 'Аннулирование ранее опубликованного сообщения'")
  public void testMessageAnnul() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageOptionsAndGoNext(3);
    app.getCreateMessagePage().fillBasicData("Аннулирование ранее опубликованного сообщения", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().selectMessageFromTheList("для аннулирования");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 5)
  @Description("Тест создания и подписания cообщения 'Опровержение по решению суда опубликованных ранее сведений'")
  public void testRebuttalMessage() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(4, 0);
    app.getCreateMessagePage()
       .fillBasicData("Опровержение по решению суда опубликованных ранее сведений", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().selectMessageFromTheList("для опровержения");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 6)
  @Description("Тест создания и подписания Сообщения о дисквалификации арбитражного управляющего")
  public void testDisqualificArbitManager() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(4, 1);
    app.getCreateMessagePage()
     .fillBasicData("Сообщение о дисквалификации арбитражного управляющего", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().setPeriodOfDisqualification();
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }
}




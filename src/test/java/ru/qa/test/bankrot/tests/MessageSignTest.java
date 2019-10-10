package ru.qa.test.bankrot.tests;

import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.annotations.Video;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


public class MessageSignTest extends TestBase {
 @Test(priority = 1)
 @Description("Тест создания и подписания Сообщения о судебном акте")
 public void testMessageArbitralDecree() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageOptionsAndGoNext(0);
    app.getCreateMessage().fillBasicData("Сообщение о судебном акте", app.getHelperBase().formCurDate);
    app.getCreateMessage().selectTypeArbitralDecree();
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }

  @Test(priority = 2)
  @Description("Тест создания и подписания Уведомления о получении требований кредитора")
  public void testReceivingCreditorDemand() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageOptionsAndGoNext(1);
    app.getCreateMessage()
       .fillBasicData("Уведомление о получении требований кредитора", app.getHelperBase().formCurDate);
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }

  @Test(priority = 3)
  @Description("Тест создания и подписания Иного сообщения")
  public void testMessageOther() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageOptionsAndGoNext(2);//для ОТ - 0,  для АУ - 2
    app.getCreateMessage().fillBasicData("Иное сообщение", app.getHelperBase().formCurDate);
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }

  @Test(priority = 4)
  @Description("Тест создания и подписания cообщения 'Аннулирование ранее опубликованного сообщения'")
  public void testMessageAnnul() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageOptionsAndGoNext(3);
    app.getCreateMessage().fillBasicData("Аннулирование ранее опубликованного сообщения", app.getHelperBase().formCurDate);
    app.getCreateMessage().selectMessageFromTheList("для аннулирования");
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }

  @Test(priority = 5)
  @Description("Тест создания и подписания cообщения 'Опровержение по решению суда опубликованных ранее сведений'")
  public void testRebuttalMessage() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(4, 0);
    app.getCreateMessage()
       .fillBasicData("Опровержение по решению суда опубликованных ранее сведений", app.getHelperBase().formCurDate);
    app.getCreateMessage().selectMessageFromTheList("для опровержения");
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }

  @Test(priority = 6)
  @Description("Тест создания и подписания Сообщения о дисквалификации арбитражного управляющего")
  public void testDisqualificArbitManager() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(4, 1);
    app.getCreateMessage()
     .fillBasicData("Сообщение о дисквалификации арбитражного управляющего", app.getHelperBase().formCurDate);
    app.getCreateMessage().setPeriodOfDisqualification();
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }
}




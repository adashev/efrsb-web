package ru.qa.test.bankrot.tests.otur;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import ru.qa.test.bankrot.tests.TestBase;


public class MessageSignTest extends TestBase {
  @Test(priority = 1)
  @Description("Тест создания и подписания Иного сообщения")
  public void testMessageOther() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectTypeMessage("Иное сообщение");
    app.getCreateMessagePage().fillBasicData("Иное сообщение", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 2)
  @Description("Тест создания и подписания cообщения 'Аннулирование ранее опубликованного сообщения'")
  public void testMessageAnnul() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectTypeMessage("Аннулирование ранее опубликованного сообщения");
    app.getCreateMessagePage().fillBasicData("Аннулирование ранее опубликованного сообщения", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().selectMessageFromTheList("для аннулирования");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }
}




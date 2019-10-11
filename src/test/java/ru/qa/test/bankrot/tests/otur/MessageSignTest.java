package ru.qa.test.bankrot.tests.otur;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import ru.qa.test.bankrot.tests.TestBase;


public class MessageSignTest extends TestBase {
  @Test(priority = 1)
  @Description("Тест создания и подписания Иного сообщения")
  public void testMessageOther() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageOptionsAndGoNext(0);
    app.getCreateMessage().fillBasicData("Иное сообщение", app.getHelperBase().formCurDate);
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }

  @Test(priority = 2)
  @Description("Тест создания и подписания cообщения 'Аннулирование ранее опубликованного сообщения'")
  public void testMessageAnnul() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageOptionsAndGoNext(1);
    app.getCreateMessage().fillBasicData("Аннулирование ранее опубликованного сообщения", app.getHelperBase().formCurDate);
    app.getCreateMessage().selectMessageFromTheList("для аннулирования");
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }
}




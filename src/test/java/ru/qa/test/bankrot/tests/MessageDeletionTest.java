package ru.qa.test.bankrot.tests;

import com.automation.remarks.video.annotations.Video;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class MessageDeletionTest extends TestBase {

  @Test(priority = 999)
  @Description("Тест удаления созданных сообщений в АРМ АУ")
  public void testMessageDeletion() throws Exception {
    for (int i = 0; i < 60; i++){
      app.getMessagesListPage().clickDeleteMessage();
    }
  }
}

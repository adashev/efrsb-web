package ru.qa.test.bankrot.tests.au;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import ru.qa.test.bankrot.tests.TestBase;

public class MessageSignTest1 extends TestBase {
  @Test(priority = 7)
  @Description("Тест создания и подписания Сообщения о собрании кредиторов")
  public void testMeetingMessage() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(5, 0);
    app.getCreateMessagePage().fillBasicData("Сообщение о собрании кредиторов", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().fillMeetingAttributes();
//    app.getCreateMessage().clickSaveMessAndCloseAlert();
    app.getCreateMessagePage().clickSignMessage();
    app.getHelperBase()
     .closeAlert("Сообщение должно быть опубликовано не менее чем за 14 дней до даты проведения собрания кредиторов");
    app.getSignMessagePage().signMessage(); //signMessageAndReturnMessList()
  }

  @Test(priority = 8)
  @Description("Тест создания и подписания Сообщение о результатах проведения собрания кредиторов")
  public void testMeetingResult() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(5, 1);
    app.getCreateMessagePage()
     .fillBasicData("Сообщение о результатах проведения собрания кредиторов", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 9)
  @Description("Тест создания и подписания Уведомления о проведении комитета кредиторов")
  public void testCommitteeMessage() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(5, 2);
    app.getCreateMessagePage()
     .fillBasicData("Уведомление о проведении комитета кредиторов", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().fillCommitteeAttributes();
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 10)
  @Description("Тест создания и подписания Сообщения о результатах проведения комитета кредиторов")
  public void testCommitteeResult() throws InterruptedException {

    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(5, 3);
    app.getCreateMessagePage()
     .fillBasicData("Сообщение о результатах проведения комитета кредиторов", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 11)
  @Description("Тест создания и подписания Сведений о принятии заявления о признании должника банкротом")
  public void testCourtAcceptanceStatement() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(6, 0);
    app.getCreateMessagePage()
     .fillBasicData("Сведения о принятии заявления о признании должника банкротом", "none");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 12)
  @Description("Тест создания и подписания Уведомления о проведении собрания участников строительства")
  public void testMeetingParticipantsBuilding() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(6, 1);
    app.getCreateMessagePage()
.fillBasicData("Уведомление о проведении собрания участников строительства", app.getHelperBase().addDays(15));
    app.getCreateMessagePage().fillMeetingParticipantsBuildAttributes();
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 13)
  @Description("Тест создания и подписания Сообщения о результатах проведения собрания участников строительства")
  public void testMeetingPartBuildResult() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(6, 2);
    app.getCreateMessagePage().fillBasicData("Сообщение о результатах проведения собрания участников строительства", "none");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 14)
  @Description("Тест создания и подписания Извещения участникам строительства о возможности предъявления денежного требования")
  public void testPartBuildMonetaryClaim() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(6, 3);
    app.getCreateMessagePage()
    .fillBasicData("Извещение участникам строительства о возможности предъявления денежного требования", "none");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 15)
  @Description("Тест создания и подписания Сообщения о переходе права собственности на объект незавершенного строительства и прав на земельный участок")
  public void testTransferOwnershipRealEstate() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(6, 4);
    app.getCreateMessagePage()
 .fillBasicData("Сообщение о переходе права собственности на объект незавершенного строительства и прав на земельный участок", app
 .getHelperBase().formCurDate);
    app.getCreateMessagePage().fillTransferOwnershipRealEstateAttributes();
    app.getCreateMessagePage().clickSignMessage();
    app.getHelperBase()
       .closeAlert("\n"+"Дата государственной регистрации перехода прав указана меньше Даты вынесения определения суда о передаче имущества и обязательств застройщика");
    app.getSignMessagePage().signMessage();
  }
}




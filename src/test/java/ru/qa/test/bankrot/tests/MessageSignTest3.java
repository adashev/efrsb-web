package ru.qa.test.bankrot.tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class MessageSignTest3 extends TestBase {

  @Test(priority = 28)
  @Description("Тест создания и подписания Уведомления о проведении собрания работников, бывших работников должника")
  public void testMeetingWorker() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(9, 0);
    app.getCreateMessage().fillBasicData("Уведомление о проведении собрания работников, бывших работников должника", app.getHelperBase().addDays(1));
    app.getCreateMessage().fillDataForMeetingWorker();
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }

  @Test(priority = 29)
  @Description("Тест создания и подписания Сведений о решениях, принятых собранием работников, бывших работников должника")
  public void testMeetingWorkerResult() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(9, 1);
    app.getCreateMessage().fillBasicData("Сведения о решениях, принятых собранием работников, бывших работников должника", app.getHelperBase().formCurDate);
    app.getCreateMessage().fillDataForMeetingWorkerResult();
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }

  @Test(priority = 30)
  @Description("Тест создания и подписания Заявления о признании сделки должника недействительной")
  public void testDealInvalidMessage() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(10, 0);
    app.getCreateMessage().fillBasicData("Заявление о признании сделки должника недействительной", app.getHelperBase().formCurDate);
    app.getCreateMessage().fillDataForDealInvalid();
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }

  @Test(priority = 31)
  @Description("Тест создания и подписания Судебного акта по результатам рассм. заявления об оспаривании сделки должника")
  public void testActDealInvalid2() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(10, 1);
    app.getCreateMessage().fillBasicData("Судебный акт по результатам рассмотрения заявления об оспаривании сделки должника", app.getHelperBase().formCurDate);
    app.getCreateMessage().selectMessageFromListWithConfirm("для ссылки на заявление о признании сделки недействительной");
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }

  @Test(priority = 32)
  @Description("Тест создания и подписания Судебного акта по результатам пересмотра рассм. заявления об оспаривании сделки должника")
  public void testActReviewDealInvalid2() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(10, 2);
    app.getCreateMessage().fillBasicData("Судебный акт по результатам пересмотра рассмотрения заявления об оспаривании сделки должника", app.getHelperBase().formCurDate);
    app.getCreateMessage().selectMessageFromListWithConfirm("для ссылки на cудебный акт по результатам рассмотрения заявления");
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }

  @Test(priority = 33)
  @Description("Тест создания и подписания Заявления о привлечении контролирующих должника лиц, а также иных лиц, к ответственности в виде возмещения убытков")
  public void testDeclarationPersonDamages() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(11, 0);
    app.getCreateMessage()
       .fillBasicData("Заявление о привлечении контролирующих должника лиц, а также иных лиц, к ответственности в виде возмещения убытков", app.getHelperBase().formCurDate);
    app.getCreateMessage().addDeclarationPerson("Damages");
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }

  @Test(priority = 34)
  @Description("Тест создания и подписания Судебного акта по результатам рассм. заявления о привлечении контролирующих должника лиц, а также иных лиц, к ответственности в виде возмещения убытков")
  public void testActPersonDamages() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(11, 1);
    app.getCreateMessage()
       .fillBasicData("Судебный акт по результатам рассмотрения заявления о привлечении контролирующих должника лиц, а также иных лиц, к ответственности в виде возмещения убытков", "none");
    app.getCreateMessage().selectMessageFromTheList("для ссылки на заявление о привлечении контролирующих лиц");
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }

  @Test(priority = 35)
  @Description("Тест создания и подписания Судебного акта по результатам пересмотра рассм. заявления о привлечении контролирующих должника лиц, а также иных лиц, к ответственности в виде возмещения убытков")
  public void testActReviewPersonDamages() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(11, 2);
    app.getCreateMessage()
    .fillBasicData("Судебный акт по результатам пересмотра рассмотрения заявления о привлечении контролирующих должника лиц, а также иных лиц, к ответственности в виде возмещения убытков", "none");
    app.getCreateMessage().selectMessageFromTheList("для ссылки на судебный акт по результатам рассмотрения заявления");
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }

  @Test(priority = 36)
  @Description("Тест создания и подписания Заявления о привлечении контролирующих должника лиц к субсидиарной ответственности")
  public void testDeclarationPersonSubsidiary() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(11, 3);
    app.getCreateMessage()
    .fillBasicData("Заявление о привлечении контролирующих должника лиц к субсидиарной ответственности", "none");
    app.getCreateMessage().addDeclarationPerson("Subsidiary");
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }

  @Test(priority = 37)
  @Description("Тест создания и подписания Судебного акта по результатам рассмотрения заявления о привлечении контролирующих должника лиц к субсидиарной ответственности")
  public void testActPersonSubsidiary() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(11, 4);
    app.getCreateMessage()
    .fillBasicData("Судебный акт по результатам рассмотрения заявления о привлечении контролирующих должника лиц к субсидиарной ответственности", "none");
    app.getCreateMessage().selectMessageFromTheList("для ссылки на судебный акт по результатам рассмотрения заявления");
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }

  @Test(priority = 38)
  @Description("Тест создания и подписания Судебного акта по результатам пересмотра рассмотрения заявления о привлечении контролирующих должника лиц к субсидиарной ответственности")
  public void testActReviewPersonSubsidiary() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(11, 5);
    app.getCreateMessage()
    .fillBasicData("Судебный акт по результатам пересмотра рассмотрения заявления о привлечении контролирующих должника лиц к субсидиарной ответственности", "none");
    app.getCreateMessage().selectMessageFromTheList("для ссылки на судебный акт по результатам пересмотра рассмотрения");
    app.getCreateMessage().clickSignMessage();
    app.getSignMessage().signMessage();
  }



}




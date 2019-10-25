package ru.qa.test.bankrot.tests.au;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import ru.qa.test.bankrot.tests.TestBase;


public class MessagePublishingTests extends TestBase {
  private String nameForRightUnsoldAsset =
   "Объявление о наличии непроданного имущества и праве собственника имущества должника – унитарного предприятия, учредителей (участников) должника получить такое имущество";
  private String nameForSaleOrderPledgedProperty =
    "Об определении начальной продажной цены, утверждении порядка и условий проведения торгов по реализации предмета залога, порядка и условий обеспечения сохранности предмета залога";

 @Test(priority = 1)
 @Description("Опубликовать \"Сообщение о судебном акте\"")
 public void testMessageArbitralDecree() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Сообщение о судебном акте");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().selectTypeArbitralDecree();
    app.getCreateMessagePage().setDecisionDate();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(priority = 2)
  @Description("Опубликовать \"Уведомление о получении требований кредитора\"")
  public void testReceivingCreditorDemand() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Уведомление о получении требований кредитора");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().setDateOfReceiptOfCreditorClaims();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }
  @Test(priority = 3)
  @Description("Опубликовать \"Иное сообщение\"")
  public void testMessageOther() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Иное сообщение");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(priority = 4)
  @Description("Опубликовать \"Аннулирование ранее опубликованного сообщения\"")
  public void testMessageAnnul() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Аннулирование ранее опубликованного сообщения");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().selectMessageForAnnulment();
    app.getCreateMessagePage().hideTextMessage();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(priority = 5)
  @Description("Опубликовать \"Опровержение по решению суда опубликованных ранее сведений\"")
  public void testRebuttalMessage() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Опровержение по решению суда опубликованных ранее сведений");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().selectMessageForRebuttal();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(priority = 6)
  @Description("Опубликовать \"Сообщение о дисквалификации арбитражного управляющего\"")
  public void testDisqualificArbitManager() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectTypeMessage("Сообщение о дисквалификации арбитражного управляющего");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().setPeriodOfDisqualification();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(priority = 7)
  @Description("Опубликовать \"Сообщение о собрании кредиторов\"")
  public void testMeetingMessage() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Сообщение о собрании кредиторов");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().setMeetingStartDate();
    app.getCreateMessagePage().inputLocation();
    app.getCreateMessagePage().inputPlaceOfRegistration();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getHelperBase()
     .closeAlert("Сообщение должно быть опубликовано не менее чем за 14 дней до даты проведения собрания кредиторов");
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(priority = 8)
  @Description("Опубликовать \"Сообщение о результатах проведения собрания кредиторов\"")
  public void testMeetingResult() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Сообщение о результатах проведения собрания кредиторов");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

 @Test(priority = 9)
  @Description("Опубликовать \"Уведомление о проведении комитета кредиторов\"")
  public void testCommitteeMessage() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Уведомление о проведении комитета кредиторов");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().setDateAndTimeOfMeeting();
    app.getCreateMessagePage().inputLocationCommittee();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(priority = 10)
  @Description("Опубликовать \"Сообщение о результатах проведения комитета кредиторов\"")
  public void testCommitteeResult() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Сообщение о результатах проведения комитета кредиторов");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(priority = 11)
  @Description("Опубликовать \"Сведения о принятии заявления о признании должника банкротом\"")
  public void testCourtAcceptanceStatement() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Сведения о принятии заявления о признании должника банкротом");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(priority = 12)
  @Description("Опубликовать \"Уведомление о проведении собрания участников строительства\"")
  public void testMeetingParticipantsBuilding() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Уведомление о проведении собрания участников строительства");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().setDateAndTimeOfMeetingParticipantsBuilding();
    app.getCreateMessagePage().inputLocationMeetingParticipantsBuild();
    app.getCreateMessagePage().inputConstructionMeetingAgenda();
    app.getCreateMessagePage().inputProcedureForAcquaintanceWithMaterials();
    app.getCreateMessagePage().inputProcedureForRegisteringMeeting();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(priority = 13)
  @Description("Опубликовать \"Сообщение о результатах проведения собрания участников строительства\"")
  public void testMeetingPartBuildResult() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Сообщение о результатах проведения собрания участников строительства");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(priority = 14)
  @Description("Опубликовать \"Извещение участникам строительства о возможности предъявления денежного требования\"")
  public void testPartBuildMonetaryClaim() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Извещение участникам строительства о возможности предъявления денежного требования");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().ConsequencesOfnotMakingClaims();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(priority = 15)
  @Description("Опубликовать \"Сообщение о переходе права собственности на объект незавершенного строительства и прав на земельный участок\"")
  public void testTransferOwnershipRealEstate() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Сообщение о переходе права собственности на объект незавершенного строительства и прав на земельный участок");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().setDateOfCourtRuling();
    app.getCreateMessagePage().setDateOfStateRegistration();
    app.getCreateMessagePage().inputNameOfAcquirer();
    app.getCreateMessagePage().inputBuyerAddress();
    app.getCreateMessagePage().inputOgrnOfAcquirer();
    app.getCreateMessagePage().inputInnOfAcquirer();
    app.getCreateMessagePage().inputBlockConstructionInProgress();
    app.getCreateMessagePage().inputBlockLandPlot();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getHelperBase()
    .closeAlert("\n"+"Дата государственной регистрации перехода прав указана меньше Даты вынесения определения суда о передаче имущества и обязательств застройщика");
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(priority = 16)
  @Description("Опубликовать \"Сведения о результатах инвентаризации имущества должника\"")
  public void testPropertyInventoryResult() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Сведения о результатах инвентаризации имущества должника");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(priority = 17)
  @Description("Опубликовать \"Отчет оценщика об оценке имущества должника\"")
  public void testPropertyEvaluationReport() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Отчет оценщика об оценке имущества должника");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(priority = 18)
  @Description("Опубликовать \"Уведомление о передаче страхового портфеля страховой организации\"")
  public void testTransferInsurancePortfolio() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Уведомление о передаче страхового портфеля страховой организации");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().inputGroundsForTransferringInsurancePortfolio();
    app.getCreateMessagePage().inputAuthorizationRestrictionInfoExecutiveBodyOfDebtor();
    app.getCreateMessagePage().inputBlockManagingInsuranceOrganization();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  /* @Test(priority = 19)
  @Description("Тест создания и подписания Сведений о кредитной организации, в которой открыт специальный банковский счет должника")
  public void testBankOpenAccountDebtor() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage()
            .fillBasicData("Сведения о кредитной организации, в которой открыт специальный банковский счет должника", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().fillDataForCreditOrg();
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 20)
  @Description("Тест создания и подписания Объявления о проведении торгов")
  public void testAuctionMessage() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Объявление о проведении торгов", app.getHelperBase().formCurDate);
    if(app.browser.equals(BrowserType.FIREFOX)){
      app.getCreateMessagePage().copyLot();
    } else {
      app.getCreateMessagePage().fillAuctionLot();
    }
    app.getCreateMessagePage().fillLocation();
    app.getCreateMessagePage().clickSignMessage();
    app.getHelperBase().closeAlert(app.getHelperBase().auctionNotif);
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 21)
  @Description("Тест создания и подписания Сообщения о результатах торгов")
  public void testTradeResultMessage() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Сообщение о результатах торгов", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().fillDataForTradeResult();
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
//  app.getSignMessage().signAndPayMessage();
  }

  @Test(priority = 22)
  @Description("Тест создания и подписания сообщения 'Об определении начальной продажной цены, утверждении порядка и условий проведения торгов по реализации предмета залога, порядка и условий обеспечения сохранности предмета залога'")
  public void testSaleOrderPledgedProperty() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData(nameForSaleOrderPledgedProperty, app.getHelperBase().formCurDate);
    if(app.browser.equals(BrowserType.CHROME)){
      app.getCreateMessagePage().fillSaleOrderPledgedProperty();
      app.getCreateMessagePage().clickSignMessage();
      app.getSignMessagePage().signMessage();
    } else {
      app.getCreateMessagePage().clickSaveMessAndCloseAlert();
    }
  }

  @Test(priority = 23)//, dependsOnMethods = {"testAuctionMessage"}
  @Description("Тест создания и подписания Сообщения об отмене сообщения об объявлении торгов или сообщения о результатах торгов")
  public void testCancelAuctionTradeResult() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Сообщение об отмене сообщения об объявлении торгов или сообщения о результатах торгов", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().selectMessageFromTheList("для отмены");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 24) //, dependsOnMethods = {"testAuctionMessage"}
  @Description("Тест создания и подписания Сообщения об изменении объявления о проведении торгов")
  public void testChangeAuction() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Сообщение об изменении объявления о проведении торгов", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().selectMessageFromTheList("для изменения");
    app.getCreateMessagePage().fillDataForChangeAuction();
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 25)
  @Description("Тест создания и подписания Сведений о заключении договора купли-продажи")
  public void testSaleContractResult() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Сведения о заключении договора купли-продажи", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().selectMessageFromTheList("для ссылки на объявление о проведении торгов");
    app.getCreateMessagePage().fillDataForSaleContractResult();
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 26)
  @Description("Тест создания и подписания Объявления о наличии непроданного имущества и праве собственника имущества должника – ун. предприятия, учредителей должника получить такое имущество")
  public void testRightUnsoldAsset() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData(nameForRightUnsoldAsset, app.getHelperBase().formCurDate);
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 27)
  @Description("Тест создания и подписания Предложения о погашении требований кредиторов путем предоставления отступного")
  public void testProcedureGrantingIndemnity() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Предложение о погашении требований кредиторов путем предоставления отступного", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().fillDataForProcedureGrantingIndemn();
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 28)
  @Description("Тест создания и подписания Уведомления о проведении собрания работников, бывших работников должника")
  public void testMeetingWorker() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Уведомление о проведении собрания работников, бывших работников должника", app.getHelperBase().addDays(1));
    app.getCreateMessagePage().fillDataForMeetingWorker();
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 29)
  @Description("Тест создания и подписания Сведений о решениях, принятых собранием работников, бывших работников должника")
  public void testMeetingWorkerResult() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Сведения о решениях, принятых собранием работников, бывших работников должника", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().fillDataForMeetingWorkerResult();
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 30)
  @Description("Тест создания и подписания Заявления о признании сделки должника недействительной")
  public void testDealInvalidMessage() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Заявление о признании сделки должника недействительной", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().fillDataForDealInvalid();
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 31)
  @Description("Тест создания и подписания Судебного акта по результатам рассм. заявления об оспаривании сделки должника")
  public void testActDealInvalid2() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Судебный акт по результатам рассмотрения заявления об оспаривании сделки должника", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().selectMessageFromListWithConfirm("для ссылки на заявление о признании сделки недействительной");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 32)
  @Description("Тест создания и подписания Судебного акта по результатам пересмотра рассм. заявления об оспаривании сделки должника")
  public void testActReviewDealInvalid2() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Судебный акт по результатам пересмотра рассмотрения заявления об оспаривании сделки должника", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().selectMessageFromListWithConfirm("для ссылки на cудебный акт по результатам рассмотрения заявления");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 33)
  @Description("Тест создания и подписания Заявления о привлечении контролирующих должника лиц, а также иных лиц, к ответственности в виде возмещения убытков")
  public void testDeclarationPersonDamages() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage()
            .fillBasicData("Заявление о привлечении контролирующих должника лиц, а также иных лиц, к ответственности в виде возмещения убытков", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().addDeclarationPerson("Damages");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 34)
  @Description("Тест создания и подписания Судебного акта по результатам рассм. заявления о привлечении контролирующих должника лиц, а также иных лиц, к ответственности в виде возмещения убытков")
  public void testActPersonDamages() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage()
            .fillBasicData("Судебный акт по результатам рассмотрения заявления о привлечении контролирующих должника лиц, а также иных лиц, к ответственности в виде возмещения убытков", "none");
    app.getCreateMessagePage().selectMessageFromTheList("для ссылки на заявление о привлечении контролирующих лиц");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 35)
  @Description("Тест создания и подписания Судебного акта по результатам пересмотра рассм. заявления о привлечении контролирующих должника лиц, а также иных лиц, к ответственности в виде возмещения убытков")
  public void testActReviewPersonDamages() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage()
            .fillBasicData("Судебный акт по результатам пересмотра рассмотрения заявления о привлечении контролирующих должника лиц, а также иных лиц, к ответственности в виде возмещения убытков", "none");
    app.getCreateMessagePage().selectMessageFromTheList("для ссылки на судебный акт по результатам рассмотрения заявления");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 36)
  @Description("Тест создания и подписания Заявления о привлечении контролирующих должника лиц к субсидиарной ответственности")
  public void testDeclarationPersonSubsidiary() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage()
            .fillBasicData("Заявление о привлечении контролирующих должника лиц к субсидиарной ответственности", "none");
    app.getCreateMessagePage().addDeclarationPerson("Subsidiary");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 37)
  @Description("Тест создания и подписания Судебного акта по результатам рассмотрения заявления о привлечении контролирующих должника лиц к субсидиарной ответственности")
  public void testActPersonSubsidiary() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage()
            .fillBasicData("Судебный акт по результатам рассмотрения заявления о привлечении контролирующих должника лиц к субсидиарной ответственности", "none");
    app.getCreateMessagePage().selectMessageFromTheList("для ссылки на судебный акт по результатам рассмотрения заявления");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 38)
  @Description("Тест создания и подписания Судебного акта по результатам пересмотра рассмотрения заявления о привлечении контролирующих должника лиц к субсидиарной ответственности")
  public void testActReviewPersonSubsidiary() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage()
            .fillBasicData("Судебный акт по результатам пересмотра рассмотрения заявления о привлечении контролирующих должника лиц к субсидиарной ответственности", "none");
    app.getCreateMessagePage().selectMessageFromTheList("для ссылки на судебный акт по результатам пересмотра рассмотрения");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 39)
  @Description("Тест создания и подписания Сообщения о праве кредитора выбрать способ распоряжения правом требования о привлечении к субсидиарной ответственности")
  public void testCreditorChoiceRightSubsidiary() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage()
            .fillBasicData("Сообщение о праве кредитора выбрать способ распоряжения правом требования о привлечении к субсидиарной ответственности", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().selectMessageFromTheList("для ссылки на сообщение о субсидиарной ответственности");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 40)
  @Description("Тест создания и подписания Предложения о присоединении к заявлению о привлечении контролирующих лиц должника к субсидиарной ответственности")
  public void testAccessionDeclarationSubsidiary() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage()
            .fillBasicData("Предложение о присоединении к заявлению о привлечении контролирующих лиц должника к субсидиарной ответственности", "none");
    app.getCreateMessagePage().selectMessageFromTheList("для ссылки на заявление о привлечении контролирующих лиц");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 41)
  @Description("Тест создания и подписания Решения о назначении временной администрации")
  public void testAppointAdministration() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Решение о назначении временной администрации", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().fillDataForAppointAdministration();
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 42)
  @Description("Тест создания и подписания Сообщения о намерении исполнить обязательства кредитной организации")
  public void testIntentionCreditOrg() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Сообщение о намерении исполнить обязательства кредитной организации", "none");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 43)
  @Description("Тест создания и подписания Сообщения о признании исполнения заявителем обязательств кредитной организации несостоявшимся")
  public void testLiabilitiesCreditOrg() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Сообщение о признании исполнения заявителем обязательств кредитной организации несостоявшимся", "none");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 44)
  @Description("Тест создания и подписания Сообщения об исполнении обязательств кредитной организации")
  public void testPerformanceCreditOrg() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Сообщение об исполнении обязательств кредитной организации", "none");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 45)
  @Description("Тест создания и подписания Сведений о смете текущих расходов кредитной организации или иной финансовой организации")
  public void testEstimatesCurrentExpenses() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Сведения о смете текущих расходов кредитной организации или иной финансовой организации", "none");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 46)
  @Description("Тест создания и подписания Сведений о скорректированной смете текущих расходов кредитной организации или иной финансовой организации")
  public void testChangeEstimatesCurrentExpenses() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Сведения о скорректированной смете текущих расходов кредитной организации или иной финансовой организации", "none");
    app.getCreateMessagePage().selectMessageFromTheList("для ссылки на сведения о корректируемой смете");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 47)
  @Description("Тест создания и подписания Сведений о порядке и сроках расчетов с кредиторами")
  public void testOrderAndTimingCalculations() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Сведения о порядке и сроках расчетов с кредиторами", "none");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 48)
  @Description("Тест создания и подписания Информации о ходе конкурсного производства")
  public void testInformationAboutBankruptcy() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Информация о ходе конкурсного производства", "none");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 49)
  @Description("Тест создания и подписания Сведений об исполнении сметы текущих расходов и стоимости нереализованного имущества кредитной организации")
  public void testEstimatesAndUnsoldAssets() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Сведения об исполнении сметы текущих расходов и стоимости нереализованного имущества кредитной организации", "none");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 50)
  @Description("Тест создания и подписания Сообщения о начале расчетов")
  public void testStartSettlement() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Сообщения о начале расчетов", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 51)
  @Description("Тест создания и подписания Сведений о ходе инвентаризации имущества должника")
  public void testProcessInventoryDebtor() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Сведения о ходе инвентаризации имущества должника", "none");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 52)
  @Description("Тест создания и подписания Сведений о порядке и месте ознакомления с проектом плана реструктуризации")
  public void testViewDraftRestructuringPlan() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Сведения о порядке и месте ознакомления с проектом плана реструктуризации", "none");
    app.getCreateMessagePage().fillDataForViewDraftRestructuringPlan();
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 53)
  @Description("Тест создания и подписания Сведений о порядке и месте ознакомления с отчетом о результатах исполнения плана реструктуризации")
  public void testViewExecRestructuringPlan() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Сведения о порядке и месте ознакомления с отчетом о результатах исполнения плана реструктуризации",
            app.getHelperBase().formCurDate);
    app.getCreateMessagePage().fillDataForViewExecRestructuringPlan();
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 54)
  @Description("Тест создания и подписания Сообщения о наличии или об отсутствии признаков преднамеренного или фиктивного банкротства")
  public void testDeliberateBankruptcy() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Сообщение о наличии или об отсутствии признаков преднамеренного или фиктивного банкротства","none");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 55)
  @Description("Тест создания и подписания Сообщения об отмене сообщения о наличии или об отсутствии признаков преднамеренного или фиктивного банкротства")
  public void testCancelDeliberateBankruptcy() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Сообщение об отмене сообщения о наличии или об отсутствии признаков преднамеренного или фиктивного банкротства","none");
    app.getCreateMessagePage().selectMessageFromTheList("для отмены");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 56)
  @Description("Тест создания и подписания Сообщения об изменении сообщения о наличии или об отсутствии признаков преднамеренного или фиктивного банкротства")
  public void testChangeDeliberateBankruptcy() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext();
    app.getCreateMessagePage().fillBasicData("Сообщение об изменении сообщения о наличии или об отсутствии признаков преднамеренного или фиктивного банкротства","none");
    app.getCreateMessagePage().selectMessageFromTheList("для изменения");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }


*/

}




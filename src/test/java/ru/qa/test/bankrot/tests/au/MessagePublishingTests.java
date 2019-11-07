package ru.qa.test.bankrot.tests.au;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import ru.qa.test.bankrot.tests.TestBase;


public class MessagePublishingTests extends TestBase {
  private String nameForRightUnsoldAsset =
      "Объявление о наличии непроданного имущества и праве собственника имущества должника – унитарного предприятия, учредителей (участников) должника получить такое имущество";
  private String nameForSaleOrderPledgedProperty =
      "Об определении начальной продажной цены, утверждении порядка и условий проведения торгов по реализации предмета залога, порядка и условий обеспечения сохранности предмета залога";

  @Test(groups = {"au"}, priority = 1)
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
    app.getCreateMessagePage().attachFile();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au"}, priority = 2)
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

  @Test(groups = {"au", "otfl", "otul"}, priority = 3)
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

  @Test(groups = {"au", "otfl", "otul"}, priority = 4)
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

  @Test(groups = {"au"}, priority = 5)
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

  @Test(groups = {"au"}, priority = 6)
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

  @Test(groups = {"au"}, priority = 7)
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

  @Test(groups = {"au"}, priority = 8)
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

  @Test(groups = {"au"}, priority = 9)
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

  @Test(groups = {"au"}, priority = 10)
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

  @Test(groups = {"au"}, priority = 11)
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

  @Test(groups = {"au"}, priority = 12)
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

  @Test(groups = {"au"}, priority = 13)
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

  @Test(groups = {"au"}, priority = 14)
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

  @Test(groups = {"au"}, priority = 15)
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
        .closeAlert("\n" + "Дата государственной регистрации перехода прав указана меньше Даты вынесения определения суда о передаче имущества и обязательств застройщика");
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au"}, priority = 16)
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

  @Test(groups = {"au"}, priority = 17)
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

  @Test(groups = {"au"}, priority = 18)
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

  @Test(groups = {"au"}, priority = 19)
  @Description("Опубликовать \"Сведения о кредитной организации, в которой открыт специальный банковский счет должника\"")
  public void testBankOpenAccountDebtor() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Сведения о кредитной организации, в которой открыт специальный банковский счет должника");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().inputNameOfCreditOrganization();
    app.getCreateMessagePage().inputInnOfCreditOrganization();
    app.getCreateMessagePage().inputOgrnOfCreditOrganization();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au", "otfl", "otul"}, priority = 20)
  @Description("Опубликовать \"Объявление о проведении торгов\"")
  public void testAuctionMessage() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Объявление о проведении торгов");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().fillLocation();
    app.getCreateMessagePage().fillAuctionLot();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getHelperBase().closeAlert();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au", "otfl", "otul"}, priority = 21)
  @Description("Опубликовать \"Сообщение о результатах торгов\"")
  public void testTradeResultMessage() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Сообщение о результатах торгов");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().fillDataForTradeResult();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au"}, priority = 22)
  @Description("Опубликовать \"Об определении начальной продажной цены, утверждении порядка и условий проведения торгов по реализации предмета залога, порядка и условий обеспечения сохранности предмета залога\"")
  public void testSaleOrderPledgedProperty() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Об определении начальной продажной цены, утверждении порядка и условий проведения торгов по реализации предмета залога, порядка и условий обеспечения сохранности предмета залога");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().fillSaleOrderPledgedProperty();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au", "otfl", "otul"}, priority = 23)
  @Description("Опубликовать \"Сообщение об отмене сообщения об объявлении торгов или сообщения о результатах торгов\"")
  public void testCancelAuctionTradeResult() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Сообщение об отмене сообщения об объявлении торгов или сообщения о результатах торгов");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().selectMessageForCancelAuctionTradeResult();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au", "otfl", "otul"}, priority = 24)
  @Description("Опубликовать \"Сообщение об изменении объявления о проведении торгов\"")
  public void testChangeAuction() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Сообщение об изменении объявления о проведении торгов");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().selectMessageForChangeAuction();
    app.getCreateMessagePage().inputReasonForChange();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au", "otfl", "otul"}, priority = 25)
  @Description("Опубликовать \"Сведения о заключении договора купли-продажи\"")
  public void testSaleContractResult() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Сведения о заключении договора купли-продажи");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().selectMessageForSaleContractResult();
    app.getCreateMessagePage().fillDataForSaleContractResult();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au"}, priority = 26)
  @Description("Опубликовать \"Объявление о наличии непроданного имущества и праве собственника имущества должника – унитарного предприятия, учредителей (участников) должника получить такое имущество\"")
  public void testRightUnsoldAsset() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Объявление о наличии непроданного имущества и праве собственника имущества должника – унитарного предприятия, учредителей (участников) должника получить такое имущество");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au"}, priority = 27)
  @Description("Опубликовать \"Предложение о погашении требований кредиторов путем предоставления отступного\"")
  public void testProcedureGrantingIndemnity() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Предложение о погашении требований кредиторов путем предоставления отступного");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().inputPropertyOfferedAsCompensation();
    app.getCreateMessagePage().inputPropertyAcquisitionProcedure();
    app.getCreateMessagePage().inputDeadlineForSubmittingConsentStatements();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au"}, priority = 28)
  @Description("Опубликовать \"Уведомление о проведении собрания работников, бывших работников должника\"")
  public void testMeetingWorker() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Уведомление о проведении собрания работников, бывших работников должника");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().setDateOfHoldingMeeting();
    app.getCreateMessagePage().inputMeetingPlace();
    app.getCreateMessagePage().inputMeetingAgenda();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au"}, priority = 29)
  @Description("Опубликовать \"Сведения о решениях, принятых собранием работников, бывших работников должника\"")
  public void testMeetingWorkerResult() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Сведения о решениях, принятых собранием работников, бывших работников должника");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().setDateOfMeeting();
    app.getCreateMessagePage().inputNumberOfEmployeesPresent();
    app.getCreateMessagePage().inputSumOfRequirementsOfSecondStage();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au"}, priority = 30)
  @Description("Опубликовать \"Заявление о признании сделки должника недействительной\"")
  public void testDealInvalidMessage() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Заявление о признании сделки должника недействительной");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().setDateOfFilingAnApplication();
    app.getCreateMessagePage().fillDataForDealInvalid();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au"}, priority = 31)
  @Description("Опубликовать \"Судебный акт по результатам рассмотрения заявления об оспаривании сделки должника\"")
  public void testActDealInvalid2() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Судебный акт по результатам рассмотрения заявления об оспаривании сделки должника");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().selectMessageForActDealInvalid();
    app.getCreateMessagePage().setDateOfReceiptOfInformationOnCourtDecision();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au"}, priority = 32)
  @Description("Опубликовать \"Судебный акт по результатам пересмотра рассмотрения заявления об оспаривании сделки должника\"")
  public void testActReviewDealInvalid2() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Судебный акт по результатам пересмотра рассмотрения заявления об оспаривании сделки должника");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().selectMessageForActReviewDealInvalid();
    app.getCreateMessagePage().setDateOfReceiptOfInfoOnCourtDecision();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au"}, priority = 33)
  @Description("Опубликовать \"Заявление о привлечении контролирующих должника лиц, а также иных лиц, к ответственности в виде возмещения убытков\"")
  public void testDeclarationPersonDamages() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Заявление о привлечении контролирующих должника лиц, а также иных лиц, к ответственности в виде возмещения убытков");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().inputDebtorControllingPerson();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au"}, priority = 34)
  @Description("Опубликовать \"Судебный акт по результатам рассмотрения заявления о привлечении контролирующих должника лиц, а также иных лиц, к ответственности в виде возмещения убытков\"")
  public void testActPersonDamages() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Судебный акт по результатам рассмотрения заявления о привлечении контролирующих должника лиц, а также иных лиц, к ответственности в виде возмещения убытков");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().selectMessageForActPersonDamages();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au"}, priority = 35)
  @Description("Опубликовать \"Судебный акт по результатам пересмотра рассмотрения заявления о привлечении контролирующих должника лиц, а также иных лиц, к ответственности в виде возмещения убытков\"")
  public void testActReviewPersonDamages() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Судебный акт по результатам пересмотра рассмотрения заявления о привлечении контролирующих должника лиц, а также иных лиц, к ответственности в виде возмещения убытков");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().selectMessageForActReviewPersonDamages();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au", "otfl", "otul"}, priority = 36)
  @Description("Опубликовать \"Заявление о привлечении контролирующих должника лиц к субсидиарной ответственности\"")
  public void testDeclarationPersonSubsidiary() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Заявление о привлечении контролирующих должника лиц к субсидиарной ответственности");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().inputDebtorControllingPersonSubsidiary();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au", "otfl", "otul"}, priority = 37)
  @Description("Опубликовать \"Судебный акт по результатам рассмотрения заявления о привлечении контролирующих должника лиц к субсидиарной ответственности\"")
  public void testActPersonSubsidiary() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Судебный акт по результатам рассмотрения заявления о привлечении контролирующих должника лиц к субсидиарной ответственности");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().selectMessageForActPersonSubsidiary();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au", "otfl", "otul"}, priority = 38)
  @Description("Опубликовать \"Судебный акт по результатам пересмотра рассмотрения заявления о привлечении контролирующих должника лиц к субсидиарной ответственности\"")
  public void testActReviewPersonSubsidiary() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Судебный акт по результатам рассмотрения заявления о привлечении контролирующих должника лиц к субсидиарной ответственности");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().selectMessageForActReviewPersonSubsidiary();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  /*@Test(groups = {"au"}, priority = 39)
  @Description("Опубликовать \"Сообщение о праве кредитора выбрать способ распоряжения правом требования о привлечении к субсидиарной ответственности\"")
  public void testCreditorChoiceRightSubsidiary() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Сообщение о праве кредитора выбрать способ распоряжения правом требования о привлечении к субсидиарной ответственности");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().selectMessageForCreditorChoiceRightSubsidiary();
    app.getCreateMessagePage().setDateAdoptionOfAnAct();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au", "otfl", "otul"}, priority = 40)
  @Description("Опубликовать \"Предложение о присоединении к заявлению о привлечении контролирующих лиц должника к субсидиарной ответственности\"")
  public void testAccessionDeclarationSubsidiary() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Предложение о присоединении к заявлению о привлечении контролирующих лиц должника к субсидиарной ответственности");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().selectMessageForAccessionDeclarationSubsidiary();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au"}, priority = 41)
  @Description("Опубликовать \"Решение о назначении временной администрации\"")
  public void testAppointAdministration() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Решение о назначении временной администрации");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().inputAppointmentActInterimAdministration();
    app.getCreateMessagePage().setDateOfAppointmentInterimAdministration();
    app.getCreateMessagePage().inputValidityInterimAdministration();
    app.getCreateMessagePage().inputGroundsForAppointmentInterimAdministration();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au"}, priority = 42)
  @Description("Опубликовать \"Сообщение о намерении исполнить обязательства кредитной организации\"")
  public void testIntentionCreditOrg() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Сообщение о намерении исполнить обязательства кредитной организации");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au"}, priority = 43)
  @Description("Опубликовать \"Сообщение о признании исполнения заявителем обязательств кредитной организации несостоявшимся\"")
  public void testLiabilitiesCreditOrg() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Сообщение о намерении исполнить обязательства кредитной организации");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au"}, priority = 44)
  @Description("Опубликовать \"Сообщение об исполнении обязательств кредитной организации\"")
  public void testPerformanceCreditOrg() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Сообщение об исполнении обязательств кредитной организации");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au"}, priority = 45)
  @Description("Опубликовать \"Сведения о смете текущих расходов кредитной организации или иной финансовой организации\"")
  public void testEstimatesCurrentExpenses() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Сведения о смете текущих расходов кредитной организации или иной финансовой организации");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().attachFile();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au"}, priority = 46)
  @Description("Опубликовать \"Сведения о скорректированной смете текущих расходов кредитной организации или иной финансовой организации\"")
  public void testChangeEstimatesCurrentExpenses() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Сведения о скорректированной смете текущих расходов кредитной организации или иной финансовой организации");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().selectMessageForChangeEstimatesCurrentExpenses();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au"}, priority = 47)
  @Description("Опубликовать \"Сведения о порядке и сроках расчетов с кредиторами\"")
  public void testOrderAndTimingCalculations() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Сведения о порядке и сроках расчетов с кредиторами");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au"}, priority = 48)
  @Description("Опубликовать \"Информация о ходе конкурсного производства\"")
  public void testInformationAboutBankruptcy() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Информация о ходе конкурсного производства");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au"}, priority = 49)
  @Description("Опубликовать \"Сведения об исполнении сметы текущих расходов и стоимости нереализованного имущества кредитной организации\"")
  public void testEstimatesAndUnsoldAssets() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Сведения об исполнении сметы текущих расходов и стоимости нереализованного имущества кредитной организации");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au"}, priority = 50)
  @Description("Опубликовать \"Сообщения о начале расчетов\"")
  public void testStartSettlement() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Сообщения о начале расчетов");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().setSettlementDate();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au"}, priority = 51)
  @Description("Опубликовать \"Сведения о ходе инвентаризации имущества должника\"")
  public void testProcessInventoryDebtor() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Сведения о ходе инвентаризации имущества должника");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }

  @Test(groups = {"au"}, priority = 52)
  @Description("Опубликовать \"Сведений о порядке и месте ознакомления с проектом плана реструктуризации\"")
  public void testViewDraftRestructuringPlan() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getHelperBase().selectDebtor();
    app.getHelperBase().selectCourtCase("message");
    app.getNewMessagePage().selectTypeMessage("Сведения о порядке и месте ознакомления с проектом плана реструктуризации");
    app.getHelperBase().clickNextButton();
    app.getHelperBase().setAddressForCorrespondence();
    app.getCreateMessagePage().inputPlaceOfFamiliarization();
    app.getCreateMessagePage().fillTextField();
    app.getCreateMessagePage().saveMessage();
    app.getSignMessagePage().signMessage();
    app.getSignMessagePage().payFromPersonalAccount();
  }
*/
  /* @Test(priority = 53)
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




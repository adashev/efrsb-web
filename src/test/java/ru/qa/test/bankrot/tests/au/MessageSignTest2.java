package ru.qa.test.bankrot.tests.au;

import io.qameta.allure.Description;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.Test;
import ru.qa.test.bankrot.tests.TestBase;

public class MessageSignTest2 extends TestBase {
  private String nameForRightUnsoldAsset =
  "Объявление о наличии непроданного имущества и праве собственника имущества должника – унитарного предприятия, учредителей (участников) должника получить такое имущество";
  private String nameForSaleOrderPledgedProperty =
  "Об определении начальной продажной цены, утверждении порядка и условий проведения торгов по реализации предмета залога, порядка и условий обеспечения сохранности предмета залога";

  @Test(priority = 16)
  @Description("Тест создания и подписания Сведений о результатах инвентаризации имущества должника")
  public void testPropertyInventoryResult() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(7, 0);
    app.getCreateMessagePage().fillBasicData("Сведения о результатах инвентаризации имущества должника", "none");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 17)
  @Description("Тест создания и подписания Отчета оценщика об оценке имущества должника")
  public void testPropertyEvaluationReport() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(7, 1);
    app.getCreateMessagePage().fillBasicData("Отчет оценщика об оценке имущества должника", "none");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 18)
  @Description("Тест создания и подписания Уведомления о передаче страхового портфеля страховой организации")
  public void testTransferInsurancePortfolio() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(7, 2);
    app.getCreateMessagePage()
       .fillBasicData("Уведомление о передаче страхового портфеля страховой организации", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().fillManagingInsuranceOrgData();
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 19)
  @Description("Тест создания и подписания Сведений о кредитной организации, в которой открыт специальный банковский счет должника")
  public void testBankOpenAccountDebtor() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(7, 3);
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
    app.getNewMessagePage().selectMessageAndGoNext(8, 0);
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
    app.getNewMessagePage().selectMessageAndGoNext(8, 1);
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
    app.getNewMessagePage().selectMessageAndGoNext(8, 2);
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
    app.getNewMessagePage().selectMessageAndGoNext(8, 3);
    app.getCreateMessagePage().fillBasicData("Сообщение об отмене сообщения об объявлении торгов или сообщения о результатах торгов", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().selectMessageFromTheList("для отмены");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 24) //, dependsOnMethods = {"testAuctionMessage"}
  @Description("Тест создания и подписания Сообщения об изменении объявления о проведении торгов")
  public void testChangeAuction() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(8, 4);
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
    app.getNewMessagePage().selectMessageAndGoNext(8, 5);
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
    app.getNewMessagePage().selectMessageAndGoNext(8, 6);
    app.getCreateMessagePage().fillBasicData(nameForRightUnsoldAsset, app.getHelperBase().formCurDate);
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 27)
  @Description("Тест создания и подписания Предложения о погашении требований кредиторов путем предоставления отступного")
  public void testProcedureGrantingIndemnity() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(8, 7);
    app.getCreateMessagePage().fillBasicData("Предложение о погашении требований кредиторов путем предоставления отступного", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().fillDataForProcedureGrantingIndemn();
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }
}




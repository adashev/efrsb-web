package ru.qa.test.bankrot.tests.au;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import ru.qa.test.bankrot.tests.TestBase;

public class MessageSignTest4 extends TestBase {

  @Test(priority = 41)
  @Description("Тест создания и подписания Решения о назначении временной администрации")
  public void testAppointAdministration() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(12, 0);
    app.getCreateMessagePage().fillBasicData("Решение о назначении временной администрации", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().fillDataForAppointAdministration();
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 42)
  @Description("Тест создания и подписания Сообщения о намерении исполнить обязательства кредитной организации")
  public void testIntentionCreditOrg() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(12, 1);
    app.getCreateMessagePage().fillBasicData("Сообщение о намерении исполнить обязательства кредитной организации", "none");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 43)
  @Description("Тест создания и подписания Сообщения о признании исполнения заявителем обязательств кредитной организации несостоявшимся")
  public void testLiabilitiesCreditOrg() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(12, 2);
    app.getCreateMessagePage().fillBasicData("Сообщение о признании исполнения заявителем обязательств кредитной организации несостоявшимся", "none");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 44)
  @Description("Тест создания и подписания Сообщения об исполнении обязательств кредитной организации")
  public void testPerformanceCreditOrg() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(12, 3);
    app.getCreateMessagePage().fillBasicData("Сообщение об исполнении обязательств кредитной организации", "none");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 45)
  @Description("Тест создания и подписания Сведений о смете текущих расходов кредитной организации или иной финансовой организации")
  public void testEstimatesCurrentExpenses() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(12, 4);
    app.getCreateMessagePage().fillBasicData("Сведения о смете текущих расходов кредитной организации или иной финансовой организации", "none");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 46)
  @Description("Тест создания и подписания Сведений о скорректированной смете текущих расходов кредитной организации или иной финансовой организации")
  public void testChangeEstimatesCurrentExpenses() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(12, 5);
    app.getCreateMessagePage().fillBasicData("Сведения о скорректированной смете текущих расходов кредитной организации или иной финансовой организации", "none");
    app.getCreateMessagePage().selectMessageFromTheList("для ссылки на сведения о корректируемой смете");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 47)
  @Description("Тест создания и подписания Сведений о порядке и сроках расчетов с кредиторами")
  public void testOrderAndTimingCalculations() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(12, 6);
    app.getCreateMessagePage().fillBasicData("Сведения о порядке и сроках расчетов с кредиторами", "none");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 48)
  @Description("Тест создания и подписания Информации о ходе конкурсного производства")
  public void testInformationAboutBankruptcy() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(12, 7);
    app.getCreateMessagePage().fillBasicData("Информация о ходе конкурсного производства", "none");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 49)
  @Description("Тест создания и подписания Сведений об исполнении сметы текущих расходов и стоимости нереализованного имущества кредитной организации")
  public void testEstimatesAndUnsoldAssets() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(12, 8);
    app.getCreateMessagePage().fillBasicData("Сведения об исполнении сметы текущих расходов и стоимости нереализованного имущества кредитной организации", "none");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 50)
  @Description("Тест создания и подписания Сообщения о начале расчетов")
  public void testStartSettlement() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(12, 9);
    app.getCreateMessagePage().fillBasicData("Сообщения о начале расчетов", app.getHelperBase().formCurDate);
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }

  @Test(priority = 51)
  @Description("Тест создания и подписания Сведений о ходе инвентаризации имущества должника")
  public void testProcessInventoryDebtor() throws InterruptedException {
    app.getMessagesListPage().clickAddMessage();
    app.getNewMessagePage().selectMessageAndGoNext(12, 10);
    app.getCreateMessagePage().fillBasicData("Сведения о ходе инвентаризации имущества должника", "none");
    app.getCreateMessagePage().clickSignMessage();
    app.getSignMessagePage().signMessage();
  }
}




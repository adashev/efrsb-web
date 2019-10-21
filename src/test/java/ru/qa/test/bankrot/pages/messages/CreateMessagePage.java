package ru.qa.test.bankrot.pages.messages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qa.test.bankrot.appmanager.HelperBase;
import java.util.HashSet;
import static org.testng.Assert.assertEquals;

public class CreateMessagePage extends HelperBase {
  private String baseName = "ctl00_ctl00_ctplhMain_CentralContentPlaceHolder_ucCreateMessage_";
  private String objP = "_ObjectProxy_ctrl0_ObjectProxy";
  private String uMess = baseName+"messageListView_ctrl0_ObjectProxy_ctrl0_";
  private String transferOwnship = uMess+"TransferOwnershipRealEstateMessage"+ objP +"_ctrl0_";
  private String insuranceOrg = "View1_ctrl0_InsuranceOrganizationUc_txtInsuranceOrg";
  // By.id(baseName+"messageListView_ctrl0_ucPublisherInfo_tbCorrAddress");
// +"UnsignedMessageListView_ctrl0_ucPublisherInfo_tbCorrAddress"
  private By messageTypeHeader = By.id(baseName+"h1MessageTypeHeader");
  private HashSet<String> missingText;
  private By periodDisqualif = By
       .id(uMess +"DisqualificationArbitrationManager2Message"+ objP +"_ctrl0_Duration_ObjectProxy_ctrl0_tbYear");
  private String tradeRes = "ctl00_cplhContent_TradeResultLot1_";
  private String slC = "_ctrl0_SaleContractResultMessage_SaleContractList_";
  private String obj ="_ObjectProxy_ctrl0_ObjectMessageProxy";
  private String sprv = "View1_ctrl0_BankruptSupervisoryPersonList_txt";
  private String browser;


  public CreateMessagePage(WebDriver wd, WebDriverWait wait, Actions actions, String browser) {
    super(wd, wait, actions);
    this.browser = browser;
    missingText = new HashSet();
    missingText.add("Сообщение об изменении объявления о проведении торгов");
    missingText.add("Уведомление о проведении собрания участников строительства");
    missingText.add("Сообщение об изменении сообщения о наличии или об отсутствии признаков преднамеренного или фиктивного банкротства");
  }

  public void createAndSaveMessage(String messageType) throws InterruptedException {
    checkMessageTypeHeader(messageType);
    if("Аннулирование ранее опубликованного сообщения".equals(getElementsText(messageTypeHeader))){
      hideTextMessage();
    } else {
      if ( ! missingText.contains(messageType)){
        inputText();
      }
    }
    clickSaveMessAndCloseAlert();
  }

  public void fillBasicData(String messType, String date) throws InterruptedException {
    checkMessageTypeHeader(messType);
    inputCurrentDate(date);
    if("Аннулирование ранее опубликованного сообщения".equals(getElementsText(messageTypeHeader))){
      hideTextMessage();
    }
    inputText();
    inputTime();
  }

  @Step("проверить заголовок открывшейся карточки сообщения. Ожидается текст \"{messageType}\"")
  public void checkMessageTypeHeader(String messageType){
    assertEquals(getElementsText(messageTypeHeader), messageType);
  }

  @Step("Заполнить поле 'Скрыть текст аннулируемого сообщения'")
  public void hideTextMessage() {
    dropdownSelect(uMess +"Annul"+ objP +"_ctrl0_ddlLockStatus", "Скрыть");
  }

  @Step("выбрать тип судебного акта")
  public void selectTypeArbitralDecree() {
    dropdownSelect(uMess
     +"ArbitralDecreeForm_ObjectProxy_ctrl0_MessageContentProxy_ctrl0_DecisionTypeView_ctrl0_DecreeType", "о введении наблюдения");
  }

  @Step("задать срок дисквалификации")
  public void setPeriodOfDisqualification() {
    type(periodDisqualif, "2");
  }

  @Step("ввести данные о местах проведения и регистрации")
  public void fillMeetingAttributes() {
    type(By.id(uMess+"Meeting"+objP+"_ctrl0_MeetingDateBegin_radDatePicker_dateInput"), addDays(1));
    type(By.id(uMess+"Meeting"+objP+"_ctrl0_MeetingSite"), "ул. Место проведен., д.1");
    type(By.id(uMess+"Meeting"+objP+"_ctrl0_RegistrationSite"), "ул. Место регистр., д.2");
  }

  @Step("ввести данные о месте проведения и времени начала заседания")
  public void fillCommitteeAttributes() {
    type(By.id(uMess +"Committee"+ objP +"View1_ctrl0_MeetingSite"), "ул. Место пров., д.5");
    inputTime();
  }

  @Step("ввести данные о месте проведения и времени начала собрания")
  public void fillMeetingParticipantsBuildAttributes() {
    type(By.id(uMess +"MeetingParticipantsBuilding"+ objP +"_ctrl0_tbMeetingSite"), "ул. Место пров., д.1");
    inputTime();
  }

  public void fillTransferOwnershipRealEstateAttributes() {
    fillRightsAcquirerData();
    addConstructionInProgress();
    addLandPlot();
  }

  @Step("ввести данные о приобретателе прав на объект строительства и земельный участок")
  public void fillRightsAcquirerData() {
    type(By.id(transferOwnship +"txtAcquirerName"), "Наим. приобр.");
    type(By.id(transferOwnship +"txtAcquirerAddress"), "Адр. приобр., д5");
    type(By.id(transferOwnship +"txtAcquirerOgrn"), "1145958053009");
    type(By.id(transferOwnship +"txtAcquirerInn"), "5904645570");
  }

  @Step("добавить объект незавершенного строительства")
  public void addConstructionInProgress() {
    addFormAttribut("10051");
    click(By.id(transferOwnship +"uncompletedBuildingProjectList_btnAdd"));
  }

  @Step("добавить земельный участок")
  public void addLandPlot() {
    click(By.id(transferOwnship +"LandPlitsList_btnAdd"));
  }

  @Step("ввести данные о управляющей страховой организации")
  public void fillManagingInsuranceOrgData() {
    type(By.id(uMess +"TransferInsurancePortfolio"+ objP + insuranceOrg +"Name"), "Наим. страховой орг.");
    type(By.id(uMess +"TransferInsurancePortfolio"+ objP + insuranceOrg +"Address"), "Адр. приобр-ля, д5");
    type(By.id(uMess +"TransferInsurancePortfolio"+ objP + insuranceOrg +"Inn"), "5904645570");
    type(By.id(uMess +"TransferInsurancePortfolio"+ objP + insuranceOrg +"Ogrn"), "1145958053009");
  }

  @Step("ввести данные о кредитной организации, в которой открыт банковский счет должника")
  public void fillDataForCreditOrg() {
    type(By.id(uMess +"BankOpenAccountDebtor"+ objP +"_ctrl0_tbName"), "Наим.  орг.");
    type(By.id(uMess +"BankOpenAccountDebtor"+ objP +"_ctrl0_tnInn"), "5904645570");
    type(By.id(uMess +"BankOpenAccountDebtor"+ objP +"_ctrl0_tbOgrn"), "1145958053009");
  }

  public void addFormAttribut(String text) {
    listElements = wd.findElements(By.cssSelector(".AddForm>table>tbody>tr>td>input[type='text']"));
    listSize = listElements.size();
    for(int i = 0; i < listSize; i++) {
      if(listElements.get(i).isDisplayed()){
        listElements.get(i).click();
        listElements.get(i).sendKeys(text);
      }
    }
  }

  public void clickSaveMessAndCloseAlert() throws InterruptedException {
    clickSaveMessage();
    closeAlert("Сообщение сохранено!");
  }

  @Step("нажать кнопку 'Сохранить'")
  public void clickSaveMessage(){
    click(By.id(baseName +"btnTempSaveClick"));
  }

  @Step("Сохранить сообщение")
  public void saveMessage() {
    click(By.id(baseName +"btnToSign"));
  }

  /*public void fillInformationAboutAuction() throws InterruptedException {
    fillAuctionLot();
  }*/

  @Step("ввести данные о месте проведения торгов")
  public void fillLocation(){
    click(By.id(uMess +"Auction"+ objP +"2_ctrl0_ucAuctionContent_rblAuctionLocation_1"));
    type(By.id(uMess +"Auction"+ objP +"2_ctrl0_ucAuctionContent_txtAuctionLocation"), "Г.Торг");
  }

  @Step("ввести данные по лоту")
  public void fillAuctionLot() throws InterruptedException{
    click(By.xpath(String.format(".//*[@id='%supContent']/table/tbody/tr[2]//fieldset/input", baseName)));
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe")));
    type(By.id("ctl00_cplhContent_AuctionLot1_OrderTextBox"), "1");
    type(By.id("ctl00_cplhContent_AuctionLot1_TextTextBox"), "Описание");
    if(browser.equals(BrowserType.FIREFOX)){
      selectPropertyClassifierJS();
    } else {
      selectPropertyClassifier();
    }
    type(By.id("ctl00_cplhContent_AuctionLot1_StartPriceTextBox"), "9 500");
    type(By.id("ctl00_cplhContent_AuctionLot1_StepTextBox"), "5");
    type(By.id("ctl00_cplhContent_AuctionLot1_AdvanceTextBox"),"500");
    click(By.cssSelector("td:nth-child(1) > input"));
    wd.switchTo().defaultContent();
  }

  public void fillDataForTradeResult() throws InterruptedException {
    copyLot();
    click(By.xpath(".//*[@id='LotsClientTable']/tr[2]/td[1]"));
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe")));
//    wd.switchTo().frame(0);
    /*click(By.cssSelector("td:nth-child(1) > .blockStyle > input"));
    fillInfoAboutLot();
    selectPropertyClassifier();*/
    fillTradeResultData();
    fillWinnerData();
    click(By.cssSelector("input[type=image]"));
    wd.switchTo().defaultContent();
  }

  public void copyLot() throws InterruptedException {
    wd.findElement(By.linkText("Скопировать лоты из сообщения")).click();
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe")));
//    wd.switchTo().frame(0);
    wd.findElement(By.cssSelector(".TextCenter:nth-child(2) > .CursorHand:nth-child(1)")).click();
    wd.switchTo().defaultContent();
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe")));
//    wd.switchTo().frame(0);
    wd.findElement(By.cssSelector(".TextCenter > input")).click();
    wd.findElement(By.cssSelector("#ctl00_cplhContent_divPublishedMessageSelect > input")).click();
    wd.switchTo().defaultContent();
    Thread.sleep(300);
  }

  @Step("ввести данные о лоте (номер, описание)")
  public void fillInfoAboutLot() {
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe")));
//    wd.switchTo().frame(0);
    type(By.id(tradeRes +"OrderTextBox"), "1");
    type(By.id(tradeRes +"DescriptionTextBox"), "Описан.");
  }

  @Step("ввести данные о результатах торгов")
  public void fillTradeResultData() {
    type(By.id(tradeRes +"BestPriceTextBox"), "9 500");
    click(By.cssSelector("#BasisRow > td:nth-child(1)"));
    type(By.id(tradeRes +"BasisTextBox"), "Основ.");
  }

  @Step("ввести атрибуты победителя (юр. лица)")
  public void fillWinnerData() {
    type(By.id(tradeRes +"txtFirmName"), "Наим. ЮЛ");
    type(By.id(tradeRes +"txtFirmAddress"), "Адр. ЮЛ, д5");
    type(By.id(tradeRes +"txtFirmOgrn"), "1145958053009");
    type(By.id(tradeRes +"txtFirmInn"), "5904645570");
  }

  public void fillSaleOrderPledgedProperty() throws InterruptedException {
    click(By.cssSelector("td:nth-child(1) > .blockStyle > input"));
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe")));
//    wd.switchTo().frame(0);
    type(By.id("ctl00_cplhContent_ucLot_OrderTextBox"), "1");
    type(By.id("ctl00_cplhContent_ucLot_TextTextBox"), "описан лота");
    selectPropertyClassifier();
    type(By.id("ctl00_cplhContent_ucLot_StartPriceTextBox"), "9 500");
    click(By.cssSelector("td:nth-child(1) > input"));
  }

  @Step("выбрать классификатор имущества")
  public void selectPropertyClassifier() throws InterruptedException {
    if(browser.equals(BrowserType.FIREFOX)){
      selectPropertyClassifierJS();
    } else {
      wd.findElement(By.cssSelector("td:nth-child(2) > img")).click();
      switchFrame(0);
      wd.findElement(By.cssSelector(".rtFirst > .rtUL > .rtLI:nth-child(2) .rtChk")).click();
      wd.findElement(By.id("ctl00_cplhContent_imgbtnSelect")).click();
      switchFrame(0);
    }
  }

  @Step("выбрать классификатор имущества")
    public void selectPropertyClassifierJS() throws InterruptedException {
    String code = "var currCodes = $get('ctl00_cplhContent_AuctionLot1_tbCodes').value;"
    + "var url = (currCodes === '') ?"
    + "\"/BackOffice/Common/ClassifierWindow.aspx\" :"
    + "String.format(\"/BackOffice/Common/ClassifierWindow.aspx?codes={0}\", currCodes);"
    + "ShowModalDialog(url, Math.min(screen.width, 675), 680, null, null,"
    + "function(res) {" + "if (!res) return;"
    + "if (!($.isArray(res) && res.length === 2)) {"
    + "throw new Error(\"Classifier selection dialog result has not been correctly specified.\");" + " }"
    + "$get(\"ctl00_cplhContent_AuctionLot1_tbCodes\").value = res[1];"
    + "$get('ctl00_cplhContent_AuctionLot1_stbClassifierName').value = unescape(res[0]);"
    + "});";
    Thread.sleep(3000);
    WebElement element = wd.findElement(By.cssSelector("img[onclick='SelectClassifier();']"));
    ((JavascriptExecutor)wd).executeScript(code, element);//executeScript("SelectClassifier()")
    Thread.sleep(3000);
    switchFrame(1);
    click(By.xpath(".//*[@id='ctl00_cplhContent_radTreeView']/ul/li[1]/ul/li[2]//input"));
    click(By.id("ctl00_cplhContent_imgbtnSelect"));
    switchFrame(0);
  }

  public void switchFrame(int ind) {
    wd.switchTo().defaultContent();
    wd.switchTo().frame(ind);
  }

  @Step("указать причину изменения")
  public void fillDataForChangeAuction() throws InterruptedException {
    Thread.sleep(600);
    type(By.id(uMess+"ChangeAuctionMessage"+objP+"_ctrl0_ChangeReason"), "Причина изм.");
    type(By.id(uMess+"ChangeAuctionMessage"+objP+"_ctrl0_ucAuctionContent_txtAuctionLocation"), "изменяем Место провед.");
  }

  @Step("ввести сведения о заключении договора" )
  public void fillDataForSaleContractResult() {
    wait
  .until(ExpectedConditions.visibilityOfElementLocated(By.id(uMess+"SaleContractResultMessageNew"+obj+slC+"btnAddSaleContract")));
    type(By.id(uMess+"SaleContractResultMessageNew"+obj+slC+"txtLotNumber"), "4");
    type(By.id(uMess+"SaleContractResultMessageNew"+obj+slC+"txtContractNumber"),"5");
    type(By.id(uMess+"SaleContractResultMessageNew"+obj+slC+"txtPropertyPurchasePrice"),"9500");
    type(By.id(uMess+"SaleContractResultMessageNew"+obj+slC+"txtPurchaserName"), "Наим. покупат.");
    click(By.id(uMess+"SaleContractResultMessageNew"+obj+slC+"btnAddSaleContract"));
  }

  @Step("задать срок направления заявлений о согласии")
  public void fillDataForProcedureGrantingIndemn() {
    type(By.id(uMess+"ProcedureGrantingIndemnity"+objP+"_ctrl0_tbConsestApplicationPeriod"), "3 мес.");
  }

  @Step("ввести сведения о месте проведения и повестке собрания")
  public void fillDataForMeetingWorker() {
    type(By.id(uMess+"MeetingWorkerMessage"+objP+"_ctrl0_txtMeetingSite"), "Местро пров.");
    type(By.id(uMess+"MeetingWorkerMessage"+objP+"_ctrl0_txtNotice"), "Повестка дня");
  }

  @Step("ввести сведения о кол-ве присутствовавших работников и сумме требований")
  public void fillDataForMeetingWorkerResult() {
    type(By.id(uMess+"MeetingWorkerResultMessage"+objP+"_ctrl0_txtWorkersCount"), "8");
    type(By.id(uMess+"MeetingWorkerResultMessage"+objP+"_ctrl0_txtRequirementSumm"), "8900");
  }

  @Step("добавить участника сделки")
  public void fillDataForDealInvalid() {
    type(By.id(uMess+"DealInvalidMessage"+objP+"_ctrl0_DealParticipants_txtRussianCitizenCode"), "1145958053009");
    type(By.id(uMess+"DealInvalidMessage"+objP+"_ctrl0_DealParticipants_txtName"), "Наим. участника");
    click(By.id(uMess+"DealInvalidMessage"+objP+"_ctrl0_DealParticipants_btnAddDealParticipant"));
  }

  public void addDeclarationPerson(String type) {
    type(By.id(uMess+"DeclarationPerson"+type+"Message"+objP+sprv+"RussianCitizenCode"), "5904645570");
    type(By.id(uMess+"DeclarationPerson"+type+"Message"+objP+sprv+"Name"), "Наим. кон-лир. должника лица");
    type(By.id(uMess+"DeclarationPerson"+type+"Message"+objP+sprv+"ResponsibilityAmount"), "9500");
    click(By.className("btnAddBankruptSupervisoryPerson"));
  }

  public void fillDataForAppointAdministration() {
    type(By.id(uMess+"AppointAdministration"+"_ObjectProxy_ctrl0_MessageContentProxy_ctrl0_tbActName"), "Акт о назначении");
    type(By.id(uMess+"AppointAdministration"+"_ObjectProxy_ctrl0_MessageContentProxy_ctrl0_tbDecisionNumber"), "8");
  }

  public void fillDataForViewDraftRestructuringPlan() {
    type(By.id(uMess+"ViewDraftRestructuringPlanMessage"+objP+"_ctrl0_txtPlaceOfAcquaintance"), "Место ознаком.");
  }

  public void fillDataForViewExecRestructuringPlan() {
    type(By.id(uMess+"ViewExecRestructuringPlanMessage"+objP+"_ctrl0_txtPlaceOfAcquaintance"), "Место ознаком.");
//_ctrl0_txtPlaceOfAcquaintance
  }

  @Step(" Заполнить поле Дата решения")
  public void setDecisionDate() throws InterruptedException {
    inputCurrentDate(formCurDate);
  }

  @Step(" Заполнить поле Текст")
  public void fillTextField() {
    inputText();
  }

  @Step(" Заполнить поле Дата получения требований кредитора")
  public void setDateOfReceiptOfCreditorClaims() throws InterruptedException {
    inputCurrentDate(formCurDate);

  }

  public void selectMessageForAnnulment() throws InterruptedException {
    selectMessageFromTheList("для аннулирования");
  }
}







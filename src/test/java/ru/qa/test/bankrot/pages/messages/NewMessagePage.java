package ru.qa.test.bankrot.pages.messages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qa.test.bankrot.appmanager.HelperBase;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class NewMessagePage extends HelperBase {
  private String contentPlace = "//*[@id='ctl00_ctl00_ctplhMain_CentralContentPlaceHolder_MessageTypeSelector_";
  private String xpathMessageTypeTree = ".//*[@id='ctl00_cplhContent_MessageTypeTree']";
  private By directoryDebtorsButton = By.cssSelector(".selectable>tbody>tr>td>img[onclick=\"ChooseInsolvent();\"]");
  private By heading = By.xpath("//legend[contains(., 'Должник')]");
  private By numberCourtCase = By.xpath(contentPlace + "InsolventPicker_LegalCasesDropDownList']/option[2]");
  private By typeMessageButton = By.cssSelector(".selectable>tbody>tr>td>img[onclick=\"SelectMessageType();\"]");
  public List<WebElement> listGroups; // список групп сообщений
  public List<WebElement> listMessage; // список сообщений

  public NewMessagePage(WebDriver wd, WebDriverWait wait, Actions actions) {
    super(wd, wait, actions);
  }

  @Step("проверить открытие страницы для выбора параметров сообщения")
  public void isNewMessagePage(){
    assertEquals(getElementsText(heading), "Должник");
  }

  public void selectMessageOptionsAndGoNext(int numGroup) throws InterruptedException {
    isNewMessagePage();
    startCreateMessAndSelectGroup(numGroup);//задание параметров сообщения и выбор группы
    nextButton();
  }

  public void selectMessageOptions(int numGroup) throws InterruptedException {
    isNewMessagePage();
    startCreateMessAndSelectGroup(numGroup);//задание параметров сообщения и выбор группы
  }

  public void startCreateMessAndSelectGroup(int numbGroup) throws InterruptedException {
    startCreateMessage();
    selectGroup(numbGroup);
    /*if(numbGroup == 2) {
      closeAlert("Данный тип сообщения предназначен только для публикации не типизированных ЕФРСБ сведений, и для него невозможно будет указать связь с последующими сообщениями или опубликовать связанную информацию.");
    }*/
  }

  public void startCreateMessage() {
//  gotoDirectoryDebtors(); // вызываем справочник должников
    selectLastDebtor(); // выбираем должника
    selectCourtCase(); // выбор Номера дела
    gotoDirectoryTypeMessage();// вызываем справочник типов сообщений. В справочнике мы сможем подсчитать группы сообщений и сообщений
    createListGroups();
  }

  @Step("открыть справочник должников")
  public void gotoDirectoryDebtors() {
    actions.moveToElement(wd.findElement(directoryDebtorsButton)).build().perform();
    click(directoryDebtorsButton);
    wait.until((d) -> wd.switchTo().frame(wd.findElement(By.cssSelector("td.rwWindowContent > iframe"))));
  }

  @Step("выбрать должника")
  public void selectDebtor() { //выбираем должника в открывшемся окне справочника
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[2]/tbody/tr[3]")));
    wait.until((d) -> !wd.findElement(By.xpath("//table[2]/tbody/tr[3]/td[1]")).getText().equals(""));//ждем загрузки данных в таблицу
    click(By.xpath("//table[2]/tbody/tr[3]/td[1]"));
    wait.until((d) -> wd.switchTo().defaultContent());
  }

  @Step("выбрать должника из списка 'Последние должники'")
  public void selectLastDebtor() {
    click(By.xpath(contentPlace+"InsolventPicker_LastInsolventsList']"));
    click(By.xpath(contentPlace+"InsolventPicker_LastInsolventsList']/option[2]"));
  }

  @Step("выбрать судебное дело")
  public void selectCourtCase() {
    click(numberCourtCase);
  }

  @Step("в окне выбора типа сообщения выбрать требуемый тип")
  public void gotoDirectoryTypeMessage() {//щелкаем на кнопке вызова справочника типов сообщ.
    click(typeMessageButton);
    //дожидаемся перехода во фрейм справочника:
    wait.until((d) -> wd.switchTo().frame(wd.findElement(By.cssSelector("td.rwWindowContent > iframe"))));
  }

  public void createListGroups()  {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathMessageTypeTree+"/ul/li[3]/div/span[2]")));
    listGroups = wd.findElements(By.xpath(xpathMessageTypeTree+"/ul/li/div/span[2]"));// создаем список типов сообщений
  }

  public void selectGroup(int numberGroup) throws InterruptedException {
    Thread.sleep(250);
    createListMessages(numberGroup);//создали/воссоздали список сообщений текущей группы
    wait.until(ExpectedConditions.elementToBeClickable(listGroups.get(numberGroup)));
    boolean b = "Иное сообщение".equals(listGroups.get(numberGroup).getText());
    listGroups.get(numberGroup).click();
    Thread.sleep(150);
    if(b){
      Alert alert = wd.switchTo().alert();
      String text = alert.getText();
      assertEquals(text, "Данный тип сообщения предназначен только для публикации не типизированных ЕФРСБ сведений, и для него невозможно будет указать связь с последующими сообщениями или опубликовать связанную информацию.");
      alert.accept();
      wait.until((d) -> wd.switchTo().defaultContent());
//      closeAlert("");
    }
  }

  public void selectMessageAndGoNext(int numGroup, int numMessage) throws InterruptedException {
    selectMessageOptions(numGroup);
    selectMessage(numGroup, numMessage);
    nextButton();
  }

  public void selectMessage(int numberGroup, int numberMessage){
    createListMessages(numberGroup);//создали/воссоздали список сообщений текущей группы
    wait.until(ExpectedConditions.elementToBeClickable(listMessage.get(numberMessage)));
    listMessage.get(numberMessage).click();
  }

  public void createListMessages(int i)  { //создаем список сообщений текущей (i-й) группы
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathMessageTypeTree+"/ul/li["+(i+1)+"]/div/span[2]")));
    listMessage = wd.findElements(By.xpath(xpathMessageTypeTree+"/ul/li["+(i+1)+"]/ul/li/div/span[2]"));
  }

  @Step("нажать на кнопку 'Далее'") //нажать кнопку "Далее"
  public void nextButton() throws InterruptedException {
    Thread.sleep(60);
    wait.until((d) -> wd.switchTo().defaultContent());
    try {
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contentPlace + "SelectImageButton']")));
      actions.moveToElement(wd.findElement(By.xpath(contentPlace + "SelectImageButton']"))).build().perform();
      click(By.xpath(contentPlace+"SelectImageButton']"));
    } catch (WebDriverException e) {
      Thread.sleep(500);
      click(By.xpath(contentPlace+"SelectImageButton']"));
    }
  }
}

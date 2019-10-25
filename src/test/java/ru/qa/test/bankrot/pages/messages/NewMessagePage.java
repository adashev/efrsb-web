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
  private By typeMessageButton = By.cssSelector(".selectable>tbody>tr>td>img[onclick='SelectMessageType();']");

  public NewMessagePage(WebDriver wd, WebDriverWait wait, Actions actions) {
    super(wd, wait, actions);
  }


  /*@Step("открыть справочник должников")
  public void gotoDirectoryDebtors() {
    actions.moveToElement(wd.findElement(directoryDebtorsButton)).build().perform();
    click(directoryDebtorsButton);
    wait.until((d) -> wd.switchTo().frame(wd.findElement(By.cssSelector("td.rwWindowContent > iframe"))));
  }*/

  public void selectTypeMessage(String messType) throws InterruptedException {
    click(typeMessageButton);//кликаем кнопку вызова справочника типов сообщ.
    //дожидаемся перехода во фрейм справочника:
    wait.until((d) -> wd.switchTo().frame(wd.findElement(By.cssSelector("td.rwWindowContent > iframe"))));
    Thread.sleep(300);
    String curPath = String.format(".//span[contains(.,  '%s')]", messType);
    if(wd.findElements(By.xpath(".//ul[@class='rtUL']//li//div//"+curPath)).size()>0) {
      click(By.xpath(curPath+"/../../../..//div[@class='rtMid']/span[@class='rtIn']"));
    }
    Thread.sleep(300);
    click(By.xpath(curPath));
    if("Иное сообщение".equals(messType)) {
      closeAlert("Данный тип сообщения предназначен только для публикации не типизированных ЕФРСБ сведений, и для него невозможно будет указать связь с последующими сообщениями или опубликовать связанную информацию.");
    }
  }

  /*public void selectMessageAndGoNext() throws InterruptedException {
    selectMessageOptions();
    selectMessage();
    clickNextButton();
  }*/

  /*public void selectMessage(){
    createListMessages(99);//создали/воссоздали список сообщений текущей группы
    wait.until(ExpectedConditions.elementToBeClickable(listMessage.get(99)));
    listMessage.get(99).click();
  }*/

  /*public void createListMessages(int i)  { //создаем список сообщений текущей (i-й) группы
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathMessageTypeTree+"/ul/li["+(i+1)+"]/div/span[2]")));
    listMessage = wd.findElements(By.xpath(xpathMessageTypeTree+"/ul/li["+(i+1)+"]/ul/li/div/span[2]"));
  }*/

}

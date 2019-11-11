package ru.qa.test.bankrot.pages.messages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qa.test.bankrot.appmanager.HelperBase;

import java.util.List;

import static java.lang.String.format;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

public class NewMessagePage extends HelperBase {
  private By typeMessageButton = cssSelector(".selectable>tbody>tr>td>img[onclick='SelectMessageType();']");
  private String user;
  private String groupOkl = "Ответственность контролирующих лиц";

  public NewMessagePage(WebDriver wd, WebDriverWait wait, Actions actions, String user) {
    super(wd, wait, actions);
    this.user = user;
  }
  /*@Step("открыть справочник должников")
  public void gotoDirectoryDebtors() {
    actions.moveToElement(wd.findElement(directoryDebtorsButton)).build().perform();
    click(directoryDebtorsButton);
    wait.until((d) -> wd.switchTo().frame(wd.findElement(By.cssSelector("td.rwWindowContent > iframe"))));
  }*/

  public void selectTypeMessage(String messType) throws InterruptedException {
    System.out.println(user);
    click(typeMessageButton);//кликаем кнопку вызова справочника типов сообщ.
    //дожидаемся перехода во фрейм справочника:
    wait.until((d) -> wd.switchTo().frame(wd.findElement(cssSelector("td.rwWindowContent > iframe"))));
    Thread.sleep(200);
    String curPath = format("//span[contains(.,  '%s')]", messType);
    /*if(!user.equals("au")
      && wd.findElements(xpath(format(".//span[contains(., '%s')]", groupOkl))).size() > 0
      && wd.findElements(xpath(curPath+"/../../../..//.//span[contains(., 'Ответственность контролирующих лиц')]")).size() > 0) {
      click(xpath(".//span[@class='rtIn' and text()='Ответственность контролирующих лиц']"));
    } else */
      if (wd.findElements(By.xpath(".//ul[@class='rtUL']//li//div"+curPath)).size()>0) {
      click(By.xpath(curPath+"/../../../..//span[@class='rtIn']"));
    }
    Thread.sleep(200);
    click(By.xpath(curPath));
    if("Иное сообщение".equals(messType)) {
      closeAlert("Данный тип сообщения предназначен только для публикации не типизированных ЕФРСБ сведений, и для него невозможно будет указать связь с последующими сообщениями или опубликовать связанную информацию.");
    }
  }
}

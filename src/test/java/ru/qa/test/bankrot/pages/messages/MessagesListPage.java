package ru.qa.test.bankrot.pages.messages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qa.test.bankrot.appmanager.HelperBase;
import io.qameta.allure.Step;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.testng.Assert.assertEquals;

public class MessagesListPage extends HelperBase {
  private int trDel = 2;
  private String user;
  private By addMessButton = By.id("ctl00_ctl00_ctplhMain_CentralContentPlaceHolder_MessageListControl_CreateMessageImageLink");
  private String delMess = ".//*[@id='tblMessages']/tbody/";

  public MessagesListPage(WebDriver wd, WebDriverWait wait, Actions actions, String user) {
    super(wd, wait, actions);
    this.user = user;
  }

  @Step("нажать кнопку 'добавить сообщение'")
  public void clickAddMessage() {
    if(user.equals("sro_au")) {
      click(By.id("ctl00_ctl00_ctplhMenu_lnkSroMessage"));
  wait.until(visibilityOfElementLocated(By.id("ctl00_ctl00_ctplhMain_ctplhMain_MessageListControl_CreateMessageImageLink")));
      click(By.id("ctl00_ctl00_ctplhMain_ctplhMain_MessageListControl_CreateMessageImageLink"));
    } else {
      actions.moveToElement(wd.findElement(addMessButton)).click().perform();
    }
    /*if (wd.findElements(By.className("TelerikModalOverlay")).size() > 0) {
      actions.moveToElement(wd.findElement(By.className("TelerikModalOverlay"))).click().perform();
    }*/
//    click(addMessButton);  ctl00_ctl00_ctplhMain_ctplhMain_MessageListControl_CreateMessageImageLink
  }

  @Step("щелкнуть на ссылке 'Удалить'")
  public void clickDeleteMessage() throws InterruptedException {
    By locatorLink = By.xpath(String.format(delMess +"tr[%s]/td[9]/a[contains(., 'Удалить')]", Integer.toString(trDel)));
    wait.until(visibilityOfElementLocated(addMessButton));
    if(wd.findElements(locatorLink).size() > 0) {
//   String publisher = wd.findElement(By.xpath(String.format(delMess +"tr[%s]/td[7]", Integer.toString(trDel)))).getText();
      try {
//        wait.until((d) -> publisher.equals("Анисимов П. И."));
        click(locatorLink);
      } catch (NullPointerException e){
//        wait.until((d) -> publisher.equals("Анисимов П. И."));
        click(locatorLink);
      } try {
        closeAlert("Вы действительно хотите удалить сообщение?");
      } catch (WebDriverException e1) {
        System.out.println("не удалось закрыть алерт");
      }
    } else {
      trDel++;
    }
  }
}

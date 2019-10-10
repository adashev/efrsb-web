package ru.qa.test.bankrot.pages.messages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qa.test.bankrot.appmanager.HelperBase;
import io.qameta.allure.Step;

import static org.testng.Assert.assertEquals;

public class MessagesListPage extends HelperBase {
  private int trDel = 2;
  private By addMessButton = By.id("ctl00_ctl00_ctplhMain_CentralContentPlaceHolder_MessageListControl_CreateMessageImageLink");
  //  private By deleteMessageLink = By.cssSelector("td[data-element-type=\"td-delete-message\"]>a");
  private String delMess = ".//*[@id='tblMessages']/tbody/";


  public MessagesListPage(WebDriver wd, WebDriverWait wait, Actions actions) {
    super(wd, wait, actions);
  }

  @Step("нажать кнопку 'добавить сообщение'")
  public void clickAddMessage() throws InterruptedException { // нажатие на кнопку "добавить сообщение"
    if (wd.findElements(By.className("TelerikModalOverlay")).size() > 0) {
      actions.moveToElement(wd.findElement(By.className("TelerikModalOverlay"))).click().perform();
    }
//  actions.moveToElement(wd.findElement(addMessButton)).build().perform();
    click(addMessButton);
  }

  @Step("щелкнуть на ссылке 'Удалить'")
  public void clickDeleteMessage() throws InterruptedException {
    By locatorLink = By.xpath(String.format(delMess +"tr[%s]/td[9]/a[contains(., 'Удалить')]", Integer.toString(trDel)));
    wait.until(ExpectedConditions.visibilityOfElementLocated(addMessButton));
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

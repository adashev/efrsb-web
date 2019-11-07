package ru.qa.test.bankrot.appmanager;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SessionHelper extends HelperBase {
  private By closeNotification = By.cssSelector("a.rwCloseButton");

  public SessionHelper(WebDriver wd, WebDriverWait wait, Actions actions) {
    super(wd, wait, actions);
  }

  @Step("открыть контур {baseUrl}")
  public void openBaseUrl(String baseUrl) {
    wd.get(baseUrl);
  }

  @Step("выполнить авторизацию в АРМ {user}")
  public void login(String login, String password, String user) {
    type(By.id("ctl00_PrivateOffice1_tbLogin"), login);
    type(By.id("ctl00_PrivateOffice1_tbPassword"), password);
    wd.findElement(By.id("ctl00_PrivateOffice1_tbPassword")).sendKeys(Keys.ENTER);

//    click(By.cssSelector("[title='Войти']"));
  }

  @Step("закрыть увемление о версиях поддерживаемых браузеров")
  public void closeStartNotification() {//закрыть увемл-е о прекращении поддержки старых браузеров
    if (wd.findElements(By.className("TelerikModalOverlay")).size() > 0) {
      actions.moveToElement(wd.findElement(By.className("TelerikModalOverlay"))).click().perform();
    }
    click(closeNotification);
  }

  public void gotoReportList() {
    click(By.id("ctl00_ctl00_ctplhMenu_HyperLink12"));

  }
}

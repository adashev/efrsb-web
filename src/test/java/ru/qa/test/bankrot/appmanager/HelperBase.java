package ru.qa.test.bankrot.appmanager;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class HelperBase {
  public WebDriverWait wait;
  public WebDriver wd;
  public Actions actions;
  private Date currentDate;
  private Date requiredDate;
  private SimpleDateFormat formatForDate;
  public String formCurDate;
  private String formRequiredDate;
  private Calendar cal;
  public WebElement el;
  public List<WebElement> listElements;
  public int listSize;
  private By textArea = By.tagName("textarea");
  private By inputDate = By.cssSelector("[data-item='dateInput']");
  public String auctionNotif = "Возможно, в сообщении указана не вся необходимая по Закону информация или указанная информация не точна:\n" + "Сообщение о проведении торгов должно быть опубликовано минимум за 30 дней до даты начала торгов.\n" + "Если Вы уверены, что вся необходимая информация в сообщении указана, сообщение может быть сохранено и опубликовано в текущем виде.";


  public HelperBase(WebDriver wd, WebDriverWait wait, Actions actions) {
    this.wd = wd;
    this.wait = wait;
    this.actions = actions;
    this.cal = Calendar.getInstance();
    this.currentDate = cal.getTime(); //получили текущую дату
    this.formatForDate = new SimpleDateFormat("dd.MM.yyyy");
    this.formCurDate = formatForDate.format(currentDate);//получили текущую дату в формате "dd.MM.yyyy"
  }

  public String addDays(int amount) {
    cal.add(Calendar.DAY_OF_MONTH, amount);
    requiredDate = cal.getTime();
    formRequiredDate = formatForDate.format(requiredDate);
    return formRequiredDate;
  }

  public void click(By locator) {
    wait.until(ExpectedConditions.elementToBeClickable(locator));
    wd.findElement(locator).click();
  }

  public void dropDownListSelect(By locator, String selectText) {
      wait.until(ExpectedConditions.elementToBeClickable(locator));
      new Select(wd.findElement(locator)).selectByVisibleText(selectText);
  }

  public void type(By locator, String text) {
    if (text != null) {
      wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
      String existingText = wd.findElement(locator).getAttribute("value");
      if (! text.equals(existingText)) {
        wd.findElement(locator).click();
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }

  @Step("закрыть алерт \"{textAlertArg}\"")
  public void closeAlertWithPause(String textAlertArg) throws InterruptedException {
    Thread.sleep(250);
    Alert alert = wait.until((d) -> wd.switchTo().alert());
    String text = alert.getText();
    assertEquals(text, textAlertArg);
    alert.accept();
    wait.until((d) -> wd.switchTo().defaultContent());
  }

  @Step("закрыть алерт \"{textAlert}\"")
  public void closeAlert(String textAlert) throws InterruptedException {
    Thread.sleep(70);
    Alert alert = wait.until((d) -> wd.switchTo().alert());
    String text = alert.getText();
    assertEquals(text, textAlert);
    alert.accept();
    wait.until((d) -> wd.switchTo().defaultContent());
  }

  @Step("закрыть алерт об успешной оплате публикации")
  public void closeAlertPay() throws InterruptedException {
    Thread.sleep(70);
    Alert alert = wait.until((d) -> wd.switchTo().alert());
    String text = alert.getText();
    assert text.startsWith("Публикация сообщения №");
    assert text.endsWith("успешно оплачена.");
    alert.accept();
    wait.until((d) -> wd.switchTo().defaultContent());
  }

  public String getElementsText(By locator) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    return wd.findElement(locator).getText();
  }

  public void dropdownSelect(String locator, String selectableValue){
    wd.findElement(By.id(locator)).click();
    {
      el = wd.findElement(By.id(locator));
      el.findElement(By.xpath(String.format("//option[. = '%s']", selectableValue))).click();
    }
  }

  @Step("заполнить текстовые поля")
  public void inputText() {
    listElements = wd.findElements(textArea);
    listSize = listElements.size();
    for(int i = 0; i < listSize; i++) {
      if(listElements.get(i).isDisplayed()){
        listElements.get(i).click();
        listElements.get(i).clear();
        listElements.get(i).sendKeys("ткт");
      }
    }
  }

  @Step("заполнить поля с датами")
  public void inputCurrentDate(String date) {
    listElements = wd.findElements(inputDate);
    listSize = listElements.size();
    for(int i = 0; i < listSize; i++) {
      if(listElements.get(i).isDisplayed()){
        listElements.get(i).click();
        listElements.get(i).clear();
        listElements.get(i).sendKeys(date);
      }
    }
  }

  @Step("задать требуемое время")
  public void inputTime() {
    listElements = wd.findElements(By.cssSelector("[class='riTextBox riEnabled']"));
    listSize = listElements.size();
    int hour = 8;
    for(int i = 0; i < listSize; i++) {
      if(listElements.get(i).isDisplayed()){
        listElements.get(i).click();
        listElements.get(i).sendKeys(Integer.toString(hour));
        hour++;
      }
    }
  }

  @Step("выбрать сообщение {target}")
  public void selectMessageFromTheList(String target) throws InterruptedException {
    click(By.cssSelector("img[title='Обзор']"));
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe")));
    /*wd.switchTo().frame(0);
    Thread.sleep(200);*/
    if(wd.findElements(By.xpath(".//*[@id='tblResults']/tbody/tr[2]/td[1]")).size() > 0){
      click(By.xpath(".//*[@id='tblResults']/tbody/tr[2]/td[1]"));
      Thread.sleep(200);
      wd.switchTo().defaultContent();
    } else {
      listIsEmpty(target);
    }
  }

  public void listIsEmpty(String target){
    wd.switchTo().defaultContent();
    click(By.cssSelector("a[class='rwCloseButton']"));
    assert "1".equals("0") : String.format("Список сообщений %s пуст", target);
  }

  @Step("выбрать сообщение {target}")
  public void selectMessageFromListWithConfirm(String target) throws InterruptedException {
    click(By.cssSelector("img[title='Обзор']"));
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe")));
    /*wd.switchTo().frame(0);
    Thread.sleep(300);*/
    if(wd.findElements(By.xpath(".//*[@id='tblResults']/tbody/tr[2]/td[1]")).size() > 0) {
      click(By.xpath(".//*[@id='tblResults']/tbody/tr[2]/td[1]"));
    } else {
      listIsEmpty(target);
    }
    click(By.cssSelector("#ctl00_cplhContent_divPublishedMessageSelect>input"));
    Thread.sleep(200);
    wd.switchTo().defaultContent();
    Thread.sleep(200);
  }
}

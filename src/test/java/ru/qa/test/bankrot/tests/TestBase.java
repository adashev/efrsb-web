package ru.qa.test.bankrot.tests;

import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.annotations.Video;
import org.openqa.selenium.remote.BrowserType;
import org.testng.ITestContext;
import org.testng.annotations.*;
import ru.qa.test.bankrot.appmanager.ApplicationManager;

import static ru.qa.test.bankrot.appmanager.ApplicationManager.wd;

//http://internetka.in.ua/selenium-screen-recordering/

@Listeners({MyTestListener.class}) // , VideoListener.class
public class TestBase {
  protected final ApplicationManager app = new ApplicationManager(System
          .getProperty("browser", BrowserType.CHROME));
                      // CHROME  FIREFOX


  @BeforeTest(description = "Инициализация браузера и авторизация в АРМ")
  @Parameters("browserParal")
  public void setUp(ITestContext context, @Optional("chrome") String browserParal) throws Exception {
    app.init(browserParal);
    context.setAttribute("app", app);
  }

  @BeforeClass
  public void setClass() {
    app.refreshPageObjects();
  }

  @AfterMethod
  public void returnMessagesList() throws InterruptedException { //вернуться на страницу со списком сообщений
    Thread.sleep(200);
    wd.get(String.format("http://bankruptcytest.devel.ifx/BackOffice/%s/MessagesList.aspx", app.urlSection));
  }

  @AfterTest(description = "Закрыть браузер")
  public void tearDown() {
    app.stop();
  }
}
package ru.qa.test.bankrot.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.ITestContext;
import org.testng.annotations.*;
import ru.qa.test.bankrot.appmanager.ApplicationManager;

import static ru.qa.test.bankrot.appmanager.ApplicationManager.wd;

@Listeners({MyTestListener.class})
public class TestBase {
  protected final ApplicationManager app = new ApplicationManager(System
          .getProperty("browser", BrowserType.CHROME));
                      // CHROME  FIREFOX
  @BeforeTest(description = "Инициализация браузера и авторизация в АРМ")
  public void setUp(ITestContext context) throws Exception {
    app.init();
    context.setAttribute("app", app);
  }

  @BeforeClass
  public void setClass() {
    app.refreshPageObjects();
  }

  @AfterMethod
  public void returnMessagesList() throws InterruptedException { //вернуться на страницу со списком сообщений
    Thread.sleep(20);
    wd.get(String.format("%s/BackOffice/%s/MessagesList.aspx", app.baseUrl,  app.urlUserSection));
  }

  @AfterTest(description = "Закрыть браузер")
  public void tearDown() {
    app.stop();
  }
}
package ru.qa.test.bankrot.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.ITestContext;
import org.testng.annotations.*;
import ru.qa.test.bankrot.appmanager.ApplicationManager;


@Listeners({MyTestListener.class})
public class TestBase {
  protected final ApplicationManager app = new ApplicationManager(System
          .getProperty("browser", BrowserType.CHROME));
                      // CHROME  FIREFOX
  @BeforeTest(alwaysRun = true)
  public void setUp(ITestContext context) throws Exception {//, description = "Инициализация браузера и авторизация в АРМ"
    app.init();
    context.setAttribute("app", app);
  }

  @BeforeClass(alwaysRun = true)
  public void setClass() {
    app.refreshPageObjects();
  }

  @AfterMethod(alwaysRun = true)
  public void returnMessagesList() throws InterruptedException { //вернуться на страницу со списком сообщений
    Thread.sleep(30);
    app.wd.get(String.format("%s/BackOffice/%s/MessagesList.aspx", app.baseUrl,  app.section));
  }

  @AfterTest(alwaysRun = true, description = "Закрыть браузер")
  public void tearDown() {
    app.stop();
  }
}
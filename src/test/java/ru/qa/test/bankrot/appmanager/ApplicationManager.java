package ru.qa.test.bankrot.appmanager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qa.test.bankrot.model.Account;
import ru.qa.test.bankrot.model.Target;
import ru.qa.test.bankrot.model.User;
import ru.qa.test.bankrot.pages.messages.CreateMessagePage;
import ru.qa.test.bankrot.pages.messages.MessagesListPage;
import ru.qa.test.bankrot.pages.messages.NewMessagePage;
import ru.qa.test.bankrot.pages.messages.SignMessagePage;
import ru.qa.test.bankrot.pages.report.CreateReportPage;
import ru.qa.test.bankrot.pages.report.NewReportPage;
import ru.qa.test.bankrot.pages.report.ReportListPage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class ApplicationManager {
  public final Properties propertiesTarget;
  public final Properties propertiesUser;
  public final Properties propertiesContour;
  public WebDriver wd; //static
  public WebDriverWait wait;
  public Actions actions;
  public String browser;
  public SessionHelper sessionHelper;
  public HelperBase helperBase;
  public MessagesListPage messagesListPage;
  public NewMessagePage newMessagePage;
  public CreateMessagePage createMessagePage;
  public SignMessagePage signMessagePage;
  public ReportListPage reportListPage;
  public NewReportPage newReportPage;
  public CreateReportPage createReportPage;
  public String login;
  public String password;
  public String baseUrl;
  public String certificate;
  public String section;
  public String user;

  public String serverType;
  public String serverURI;
  public boolean enableVNC;

  public ApplicationManager(String browser, String user) throws IOException {
    this.browser = browser;
    this.user = user;
    propertiesTarget = new Properties(); //6.10
    propertiesUser = new Properties();
    propertiesContour = new Properties();
    String contur = System.getProperty("contur", "release");
    String target = System.getProperty("target", "local");

    ObjectMapper objectMapper = new ObjectMapper();
    List<Account> accounts = objectMapper.readValue(new File("config/accounts.json"), new TypeReference<List<Account>>(){ });
    for (Account account : accounts) {
      if (contur.equals(account.getConturname())) {
        baseUrl = account.getBaseurl();
        certificate = account.getCertificate();
        List<User> users = account.getUsers();
        for (User userObject : users) {
          if (user.equals(userObject.getName())) {
            login = userObject.getLogin();
            password = userObject.getPassword();
            section = userObject.getSection();
            break;
          }
        }
      }
    }

    List<Target> targets = objectMapper.readValue(new File("config/targets.json"), new TypeReference<List<Target>>(){ });
    for (Target targ : targets) {
      if (target.equals(targ.getServerType())) {
        serverType = targ.getServerType();
        serverURI = targ.getServerURI();
        enableVNC = targ.getEnableVNC();
        break;
      }
    }
  }

  public void init() throws IOException {
   /* String target = System.getProperty("target", "local"); // пока оставляю property
    propertiesTarget.load(new FileReader(new File(String.format("config/targets/%s.properties", target))));*/
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("unexpectedAlertBehaviour", "accept");
    //   firefox.exe -ProfileManager
    ChromeOptions optionsChrome = new ChromeOptions();
    FirefoxOptions optionsFirefox = new FirefoxOptions();
    if ("local".equals(serverType)) {
      if (browser.equals(BrowserType.FIREFOX)) {
        final FirefoxProfile profile1 = new FirefoxProfile(new File("src/test/resources/uhw5g46i.test"));
        profile1.addExtension(new File("src/test/resources/blitz_smart_card_plugin-1.1.14-an+fx.xpi"));
        wd = new FirefoxDriver(optionsFirefox.setProfile(profile1));
      } else if (browser.equals(BrowserType.CHROME)) {
        optionsChrome.addArguments("user-data-dir=C:/Users/adashev/AppData/Local/Google/Chrome/User Data/Default");
        wd = new ChromeDriver(optionsChrome);
      } else if (browser.equals(BrowserType.IE)) {
        wd = new InternetExplorerDriver();
      }
    } else if ("remote".equals(serverType)) {
      if (browser.equals(BrowserType.FIREFOX)) {
        final FirefoxProfile profile = new FirefoxProfile(new File("src/test/resources/uhw5g46i.test"));
        capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
        wd = new RemoteWebDriver(new URL(serverURI), capabilities);

      } else if (browser.equals(BrowserType.CHROME)) {
        optionsChrome.setCapability("browserName", "chrome");
        optionsChrome.setCapability("version", "");
        optionsChrome.addArguments("user-data-dir=C:/Users/User/AppData/Local/Google/Chrome/User Data/Default");
        wd = new RemoteWebDriver(new URL(serverURI), optionsChrome);
      } else if (browser.equals(BrowserType.IE)) {
        capabilities.setBrowserName(browser);
        wd = new RemoteWebDriver(new URL(serverURI), capabilities);
      }
    } else if ("selenoid".equals(serverType)) {
      capabilities.setCapability("enableVNC", enableVNC);
      capabilities.setCapability("enableVideo", false);
      capabilities.setVersion("selenoid_chrome_77.0_csp");
      optionsChrome.addArguments("--load-extension=/var/blitz");
      capabilities.setCapability(ChromeOptions.CAPABILITY, optionsChrome);
      /*capabilities.setVersion("74.0");
      capabilities.setCapability("enableVNC", true);
      capabilities.setCapability("enableVideo", false);*/
      wd = new RemoteWebDriver(URI.create(serverURI).toURL(), capabilities);
      //propertiesTarget.getProperty("selenium.server")
    } else {
      System.out.println("selenium-server not defined");
    }
    wait = new WebDriverWait(wd, 14);
    actions = new Actions(wd);
    wd.manage().window().maximize();
    sessionHelper = new SessionHelper(wd, wait, actions);
    sessionHelper.openBaseUrl(baseUrl); //json
    sessionHelper.login(login, password, user); //json
    sessionHelper.closeStartNotification();
  }

  public void refreshPageObjects() {
    helperBase = new HelperBase(wd, wait, actions);
    messagesListPage = new MessagesListPage(wd, wait, actions, user);
    newMessagePage = new NewMessagePage(wd, wait, actions, user);
    createMessagePage = new CreateMessagePage(wd, wait, actions, browser);
    signMessagePage = new SignMessagePage(wd, wait, actions, certificate);
    reportListPage = new ReportListPage(wd, wait, actions);
    newReportPage = new NewReportPage(wd, wait, actions);
    createReportPage = new CreateReportPage(wd, wait, actions);
  }

  public HelperBase getHelperBase() {
    return helperBase;
  }

  public MessagesListPage getMessagesListPage() {
    return messagesListPage;
  }

  public NewMessagePage getNewMessagePage() {
    return newMessagePage;
  }

  public CreateMessagePage getCreateMessagePage() {
    return createMessagePage;
  }

  public SignMessagePage getSignMessagePage() {
    return signMessagePage;
  }

  public ReportListPage getReportListPage() {
    return reportListPage;
  }

  public NewReportPage getNewReportPage() {
    return newReportPage;
  }

  public CreateReportPage getCreateReportPage() {
    return createReportPage;
  }

  public byte[] takeScreenshot() {
    try {
      Alert alert = wd.switchTo().alert();
      alert.accept();
    } catch (NoAlertPresentException e) {
      e.printStackTrace();
    }
    return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
  }

  public void stop() {
    wd.quit();
  }
}


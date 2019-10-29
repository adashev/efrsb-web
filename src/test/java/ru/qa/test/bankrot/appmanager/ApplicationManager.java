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
  public static WebDriver wd; //static
  public static WebDriverWait wait;
  public static Actions actions;
  public static String browser;
  public SessionHelper sessionHelper;
  public HelperBase helperBase;
  public MessagesListPage messagesListPage;
  public NewMessagePage newMessagePage;
  public CreateMessagePage createMessagePage;
  public SignMessagePage signMessagePage;
  public ReportListPage reportListPage;
  public NewReportPage newReportPage;
  public CreateReportPage createReportPage;
  public String certificateName;
  public static String login;
  public static String password;
  public static String baseUrl;
  public static String certificate;
  public static String section;

  public ApplicationManager(String browser) {
    this.browser = browser;
    propertiesTarget = new Properties(); //6.10
    propertiesUser = new Properties();
    propertiesContour = new Properties();
  }

  public void init() throws IOException {
    String contur = System.getProperty("contur", "release");
    String user = System.getProperty("user", "au");
    System.out.println("from comline: " +contur + "  " + user);

    ObjectMapper objectMapper = new ObjectMapper();
    List<Account> accounts = objectMapper.readValue(new File("config/accounts.json"), new TypeReference<List<Account>>(){});
    for(Account account : accounts){
      if(contur.equals(account.getConturname())) {
        baseUrl = account.getBaseurl();
        certificate = account.getCertificate();
        List<User> users = account.getUsers();
        for(User userObject : users){
          if(user.equals(userObject.getName())){
            login = userObject.getLogin();
            password = userObject.getPassword();
            section = userObject.getSection();
            break;
          }
        }
      }
    }

    String target = System.getProperty("target", "local"); // пока оставляю property
    propertiesTarget.load(new FileReader(new File(String.format("config/targets/%s.properties", target))));
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("unexpectedAlertBehaviour", "accept");
    //   firefox.exe -ProfileManager
    ChromeOptions optionsChrome = new ChromeOptions();
    FirefoxOptions optionsFirefox = new FirefoxOptions();

    if ("local".equals(propertiesTarget.getProperty("server.type"))) {
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
    } else if ("remote".equals(propertiesTarget.getProperty("server.type"))) {
      if(browser.equals(BrowserType.FIREFOX)){
        final FirefoxProfile profile = new FirefoxProfile(new File("src/test/resources/uhw5g46i.test"));
        capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
        wd = new RemoteWebDriver(new URL(propertiesTarget.getProperty("selenium.server")), capabilities);
//      wd = new RemoteWebDriver(URI.create(properties.getProperty("selenium.server")).toURL(), capabilities);
      } else if (browser.equals(BrowserType.CHROME)) { //gradlew clean -Pbrowser=chrome -Ptarget=remote testMessage
        optionsChrome.setCapability("browserName", "chrome");
        optionsChrome.setCapability("version", "");
        optionsChrome.addArguments("user-data-dir=C:/Users/User/AppData/Local/Google/Chrome/User Data/Default");
        wd = new RemoteWebDriver(new URL(propertiesTarget.getProperty("selenium.server")), optionsChrome);
      } else if (browser.equals(BrowserType.IE)) {
        capabilities.setBrowserName(browser);
        wd = new RemoteWebDriver(new URL(propertiesTarget.getProperty("selenium.server")), capabilities);
      }
    } else if ("selenoid".equals(propertiesTarget.getProperty("server.type"))) {
      capabilities.setBrowserName(browser);
      capabilities.setCapability("enableVNC", true);
      capabilities.setCapability("enableVideo", false);
      capabilities.setVersion("selenoid_chrome_77.0_csp");
      optionsChrome.addArguments("--load-extension=/var/blitz");
      capabilities.setCapability(ChromeOptions.CAPABILITY, optionsChrome);

      wd = new RemoteWebDriver(URI.create(propertiesTarget.getProperty("selenium.server")).toURL(), capabilities);
    } else {
      System.out.println("selenium-server not defined");
    }
    wait = new WebDriverWait(wd, 14); // waitTime вместо константы в рамках 6.10
    actions = new Actions(wd);
    wd.manage().window().maximize();
    sessionHelper = new SessionHelper(wd, wait, actions);
    sessionHelper.openBaseUrl(baseUrl); //json
    sessionHelper.login(login, password); //json
    sessionHelper.closeStartNotification();
  }

  public void refreshPageObjects(){
    helperBase = new HelperBase(wd, wait, actions);
    messagesListPage = new MessagesListPage(wd, wait, actions);
    newMessagePage = new NewMessagePage(wd, wait, actions);
    createMessagePage = new CreateMessagePage(wd, wait, actions, browser);
    signMessagePage = new SignMessagePage(wd, wait, actions, certificate);
    reportListPage = new ReportListPage(wd, wait, actions);
    newReportPage = new NewReportPage(wd, wait, actions);
    createReportPage = new CreateReportPage(wd, wait, actions);

  }

  public HelperBase getHelperBase() {return helperBase;}
  public MessagesListPage getMessagesListPage() {  return messagesListPage;  }
  public NewMessagePage getNewMessagePage() {  return newMessagePage; }
  public CreateMessagePage getCreateMessagePage() {return createMessagePage;}
  public SignMessagePage getSignMessagePage() {return signMessagePage;}
  public ReportListPage getReportListPage() {return reportListPage;}
  public NewReportPage getNewReportPage() {return newReportPage;}
  public CreateReportPage getCreateReportPage() {return createReportPage;}

  public byte[] takeScreenshot() {
    try {
      Alert alert = wd.switchTo().alert();
      alert.accept();
    } catch (NoAlertPresentException e) { e.printStackTrace(); }
    return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
  }

  public void stop() {   wd.quit(); }
}


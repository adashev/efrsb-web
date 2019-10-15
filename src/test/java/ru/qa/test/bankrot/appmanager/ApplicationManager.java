package ru.qa.test.bankrot.appmanager;

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
import ru.qa.test.bankrot.pages.messages.CreateMessagePage;
import ru.qa.test.bankrot.pages.messages.MessagesListPage;
import ru.qa.test.bankrot.pages.messages.NewMessagePage;
import ru.qa.test.bankrot.pages.messages.SignMessagePage;
import ru.qa.test.bankrot.pages.report.NewReportPage;
import ru.qa.test.bankrot.pages.report.ReportListPage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Properties;

public class ApplicationManager {
  public final Properties propertiesTarget;
  public final Properties propertiesRole;
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
  public String certificateName;
  public static String urlSection;


  public ApplicationManager(String browser) {
    this.browser = browser;
    propertiesTarget = new Properties(); //6.10
    propertiesRole = new Properties();
    propertiesContour = new Properties();
  }

  public void init(String browserParal) throws IOException {
//    String login = System.getProperty("login"); //6.10

    String target = System.getProperty("target", "local"); //6.10
    propertiesTarget.load(new FileReader(new File(String.format("config/targets/%s.properties", target))));

    String contur = System.getProperty("contur", "test");
    propertiesContour.load(new FileReader(new File(String.format("config/conturs/%s.properties", contur))));
    certificateName = propertiesContour.getProperty("certificate");

    String role = System.getProperty("role", "AU");
    propertiesRole.load(new FileReader(new File(String.format("config/roles/%s.properties", role))));
    urlSection = propertiesRole.getProperty("url.section");

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("unexpectedAlertBehaviour", "accept");
    //   firefox.exe -ProfileManager
    ChromeOptions optionsChrome = new ChromeOptions();
    FirefoxOptions optionsFirefox = new FirefoxOptions();

    if(browserParal.equals("chrome_paral")){
      optionsChrome.addExtensions(new File("src/test/resources/Blitz-Smart-Card-Plugin-Chrome_v1.1.8.crx"));
      wd = new ChromeDriver(optionsChrome);//чтобы парал.запуск работал нужно с wd, wait и actions снять static
    } else {
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
//          wd = new RemoteWebDriver(URI.create(properties.getProperty("selenium.server")).toURL(), capabilities);
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
        wd = new RemoteWebDriver(URI.create(propertiesTarget.getProperty("selenium.server")).toURL(), capabilities);
      } else {
        System.out.println("selenium-server not defined");
      }
    }
    wait = new WebDriverWait(wd, 17); // waitTime вместо константы в рамках 6.10
    actions = new Actions(wd);
    wd.manage().window().maximize();
    sessionHelper = new SessionHelper(wd, wait, actions);
    sessionHelper.openBaseUrl(propertiesContour.getProperty("baseUrl"));//baseUrl в IE должен быть вкл. в "Надежные сайты"
    sessionHelper.login(propertiesRole.getProperty("login"), propertiesRole.getProperty("password"));
    sessionHelper.closeStartNotification();
  }

  public void refreshPageObjects(){
    helperBase = new HelperBase(wd, wait, actions);
    messagesListPage = new MessagesListPage(wd, wait, actions);
    newMessagePage = new NewMessagePage(wd, wait, actions);
    createMessagePage = new CreateMessagePage(wd, wait, actions, browser);
    signMessagePage = new SignMessagePage(wd, wait, actions, certificateName);
    reportListPage = new ReportListPage(wd, wait, actions);
    newReportPage = new NewReportPage(wd, wait, actions);
  }

  public HelperBase getHelperBase() {return helperBase;}
  public MessagesListPage getMessagesListPage() {  return messagesListPage;  }
  public NewMessagePage getNewMessagePage() {  return newMessagePage; }
  public CreateMessagePage getCreateMessagePage() {return createMessagePage;}
  public SignMessagePage getSignMessagePage() {return signMessagePage;}
  public ReportListPage getReportListPage() {return reportListPage;}
  public NewReportPage getNewReportPage() {return newReportPage;}

  public byte[] takeScreenshot() {
    try {
      Alert alert = wd.switchTo().alert();
      alert.accept();
    } catch (NoAlertPresentException e) { e.printStackTrace(); }
    return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
  }

  public void stop() {   wd.quit(); }
}


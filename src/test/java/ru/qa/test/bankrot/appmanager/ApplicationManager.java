package ru.qa.test.bankrot.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qa.test.bankrot.pages.messages.CreateMessage;
import ru.qa.test.bankrot.pages.messages.MessagesListPage;
import ru.qa.test.bankrot.pages.messages.NewMessagePage;
import ru.qa.test.bankrot.pages.messages.SignMessage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Properties;

public class ApplicationManager {
  public final Properties properties;
  public static WebDriver wd; //static
  public static WebDriverWait wait;
  public static Actions actions;
  public static String browser;
  public SessionHelper sessionHelper;
  public HelperBase helperBase;
  public MessagesListPage messagesListPage;
  public NewMessagePage newMessagePage;
  public CreateMessage createMessage;
  public SignMessage signMessage;
  public String certificateName;


  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties(); //6.10
  }

  public void init(String browserParal) throws IOException {
    String target = System.getProperty("target", "local"); //6.10
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    certificateName = properties.getProperty("certificate.name");
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("unexpectedAlertBehaviour", "accept");
    //   firefox.exe -ProfileManager
    ChromeOptions optionsChrome = new ChromeOptions();
    FirefoxOptions optionsFirefox = new FirefoxOptions();

    if(browserParal.equals("chrome_paral")){
      optionsChrome.addExtensions(new File("src/test/resources/Blitz-Smart-Card-Plugin-Chrome_v1.1.8.crx"));
      wd = new ChromeDriver(optionsChrome);//чтобы парал.запуск работал нужно с wd, wait и actions снять static
    } else {
      if ("local".equals(properties.getProperty("server.type"))) {
        if (browser.equals(BrowserType.FIREFOX)) {
          optionsFirefox.setProfile(new ProfilesIni().getProfile("test"));//Webdriver
          wd = new FirefoxDriver(optionsFirefox);
          wd.manage().deleteAllCookies();
        } else if (browser.equals(BrowserType.CHROME)) {
          optionsChrome.addArguments("user-data-dir=C:/Users/adashev/AppData/Local/Google/Chrome/User Data/Default");
          wd = new ChromeDriver(optionsChrome);
        } else if (browser.equals(BrowserType.IE)) {
          wd = new InternetExplorerDriver();
        }

      } else if ("remote".equals(properties.getProperty("server.type"))) {
        if(browser.equals(BrowserType.FIREFOX)){
          final FirefoxProfile profile = new FirefoxProfile(new File("src/test/resources/lwxxrqde.default"));
          capabilities = DesiredCapabilities.firefox();
          capabilities.setCapability(FirefoxDriver.PROFILE, profile);
          wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
//          wd = new RemoteWebDriver(URI.create(properties.getProperty("selenium.server")).toURL(), capabilities);
        } else if (browser.equals(BrowserType.CHROME)) { //gradlew clean -Pbrowser=chrome -Ptarget=remote testMessage
          optionsChrome.setCapability("browserName", "chrome");
          optionsChrome.setCapability("version", "");
          optionsChrome.addArguments("user-data-dir=C:/Users/User/AppData/Local/Google/Chrome/User Data/Default");
          wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), optionsChrome);
        } else if (browser.equals(BrowserType.IE)) {
          capabilities.setBrowserName(browser);
          wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
        }
      } else if ("selenoid".equals(properties.getProperty("server.type"))) {
        capabilities.setBrowserName(browser);
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        wd = new RemoteWebDriver(URI.create(properties.getProperty("selenium.server")).toURL(), capabilities);
      } else {
        System.out.println("selenium-server not defined");
      }
    }
    wait = new WebDriverWait(wd, 17); // waitTime вместо константы в рамках 6.10
    actions = new Actions(wd);
    wd.manage().window().maximize();
    sessionHelper = new SessionHelper(wd, wait, actions);
    sessionHelper.openBaseUrl(properties.getProperty("web.baseUrl"));//baseUrl в IE должен быть вкл. в "Надежные сайты"
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    sessionHelper.closeStartNotification();
   /* helperBase = new HelperBase(wd, wait, actions);
    messagesListPage = new MessagesListPage(wd, wait, actions);
    newMessagePage = new NewMessagePage(wd, wait, actions);
    createMessage = new CreateMessage(wd, wait, actions);
    signMessage = new SignMessage(wd, wait, actions, certificateName);*/
  }

  public void refreshPageObjects(){
    helperBase = new HelperBase(wd, wait, actions);
    messagesListPage = new MessagesListPage(wd, wait, actions);
    newMessagePage = new NewMessagePage(wd, wait, actions);
    createMessage = new CreateMessage(wd, wait, actions, browser);
    signMessage = new SignMessage(wd, wait, actions, certificateName);
  }

  public HelperBase getHelperBase() {return helperBase;}
  public MessagesListPage getMessagesListPage() {  return messagesListPage;  }
  public NewMessagePage getNewMessagePage() {  return newMessagePage; }
  public CreateMessage getCreateMessage() {return createMessage;}
  public SignMessage getSignMessage() {return signMessage;}

  public byte[] takeScreenshot() {
    try {
      Alert alert = wd.switchTo().alert();
      alert.accept();
    } catch (NoAlertPresentException e) { e.printStackTrace(); }
    return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
  }

  public void stop() {   wd.quit(); }
}


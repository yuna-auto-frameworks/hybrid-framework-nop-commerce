 package commons;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
	private WebDriver driver;
	protected final Log log;
	
	@BeforeSuite
	public void initBeforeSuite() {
		deleteAllureReportFileInFolder();
	}
	
	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}
	private String projectPath = System.getProperty("user.dir");
	
	protected WebDriver getBrowserDriver(String browserName) {
		if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			} else if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			} else if(browserName.equals("edge")) {
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe.");
			driver = new EdgeDriver();
			} else if(browserName.equals("ie")) {
			System.setProperty("webdriver.ie.driver", projectPath + "\\browserDrivers\\IEDriverServer.exe.");
			driver = new InternetExplorerDriver();
				throw new RuntimeException("Browser name invalid.");
			}
		
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get(GlobalConstants.PORTAL_PAGE_URL);
		return driver;
	}
	
	protected WebDriver getBrowserDriver(String browserName, String appUrl) {
		if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			} else if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			} else if(browserName.equals("edge")) {
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe.");
			driver = new EdgeDriver();
			} else if(browserName.equals("ie")) {
			System.setProperty("webdriver.ie.driver", projectPath + "\\browserDrivers\\IEDriverServer.exe.");
			driver = new InternetExplorerDriver();
				throw new RuntimeException("Browser name invalid.");
			}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get(appUrl);
		return driver;
	}
	
	public WebDriver getDriverInstance() {
		return this.driver;
		
	}
	protected int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
		
	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
				if (condition == true) {
					log.info(" -------------------------- PASSED -------------------------- ");
				} else {
					log.info(" -------------------------- FAILED -------------------------- ");
				}
				Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

		// Add lỗi vào ReportNG
		VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
		Reporter.getCurrentTestResult().setThrowable(e);
	}
	return pass;
}

	protected boolean verifyTrue(boolean condition) {
	return checkTrue(condition);
}

	private boolean checkFailed(boolean condition) {
	boolean pass = true;
	try {
		if (condition == false) {
			log.info(" -------------------------- PASSED -------------------------- ");
		} else {
			log.info(" -------------------------- FAILED -------------------------- ");
		}
		Assert.assertFalse(condition);
	} catch (Throwable e) {
		pass = false;
		VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
		Reporter.getCurrentTestResult().setThrowable(e);
	}
	return pass;
}

	protected boolean verifyFalse(boolean condition) {
	return checkFailed(condition);
}

	private boolean checkEquals(Object actual, Object expected) {
	boolean pass = true;
	try {
		Assert.assertEquals(actual, expected);
		log.info(" -------------------------- PASSED -------------------------- ");
	} catch (Throwable e) {
		pass = false;
		log.info(" -------------------------- FAILED -------------------------- ");
		VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
		Reporter.getCurrentTestResult().setThrowable(e);
	}
	return pass;
}

	protected boolean verifyEquals(Object actual, Object expected) {
	return checkEquals(actual, expected);
}
	
	public void deleteAllureReportFileInFolder() {
		try {
			String pathFolderDownload = GlobalConstants.PROJECT_PATH + "/allure-json";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println(listOfFiles[i].getName());
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
	protected void cleanBrowserAndDriver() {
		if (driver!= null) {
			driver.manage().deleteAllCookies();
			driver.quit();
		}
	}
}

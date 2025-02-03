package hooks;

import org.openqa.selenium.WebDriver;

import config.WebDriverFactory;
import core.ProjectManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

/**
 * Hooks - Supports parallel execution.
 */
public class Hooks {

	private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

	@BeforeAll
	public static void setupExecution() {
		ProjectManager.initializeFramework();
	}

	@Before
	public void setupBrowser() {
		WebDriver driver = WebDriverFactory.getDriver();
		threadLocalDriver.set(driver);
	}

	@After
	public void cleanupBrowser() {
		if (threadLocalDriver.get() != null) {
			threadLocalDriver.get().quit();
			threadLocalDriver.remove();
		}
	}

	@AfterAll
	public static void cleanupExecution() {
		System.out.println("Final Cleanup...");
		ProjectManager.shutdownFramework();
	}

	public static WebDriver getDriver() {
		return threadLocalDriver.get();
	}
}

package factory;

import com.microsoft.playwright.*;


import java.awt.*;
import java.util.Properties;

import static helper.PropertiesHelper.loadFile;

public class DriverFactory {
    // Implement parallel testing with ThreadLocal
    public static Playwright playwright;
    public static Browser browser;
    public static BrowserContext context;
    public static Page page;

    public static String browserName;
    public static Properties setUp = loadFile();

    public static void getPage(boolean headless) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        if (setUp == null || setUp.isEmpty()) {
            System.out.println("Failed to load config.properties");
            return;
        } else {
            System.out.println("config.properties file has been loaded successfully");
        }

        browserName = System.getenv("BROWSER") != null && !System.getenv("BROWSER").isEmpty()
                ? System.getenv("BROWSER")
                : setUp.getProperty("BROWSER");

        setUp.setProperty("BROWSER", browserName);

        if (playwright == null) {
            playwright = Playwright.create();
        }

        if (browser == null) {
            BrowserType browserTypeInstance = switch (browserName.toLowerCase()) {
                case "chromium" -> playwright.chromium();
                case "firefox" -> playwright.firefox();
                default -> playwright.webkit();
            };
            browser = browserTypeInstance.launch(new BrowserType.LaunchOptions().setHeadless(headless));
        }

        if (context == null) {
            context = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
        }

        if (page == null) {
            page = context.newPage();
        }
    }


    public static void close(){
        if (page != null){
            page.close();
            page = null;
        }
        if (context != null){
            context.close();
            context = null;
        }
        if (browser != null){
            browser.close();
            browser = null;
        }
        if (playwright != null){
            playwright.close();
            playwright = null;
        }
    }
}

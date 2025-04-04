package factory;

import com.microsoft.playwright.*;

import java.awt.*;

public class DriverFactory {
    public static Playwright playwright;
    public static Browser browser;
    public static BrowserContext context;
    public static Page page;

    public static void getPage(String browserType, boolean headless){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        if (playwright == null){
            playwright = Playwright.create();
        }

        if (browser == null){
            BrowserType browserTypeInstance = switch (browserType.toLowerCase()) {
                case "firefox" -> playwright.firefox();
                case "webkit" -> playwright.webkit();
                default -> playwright.chromium();
            };
            browser = browserTypeInstance.launch(new BrowserType.LaunchOptions().setHeadless(headless));
        }

        if (context == null){
            context = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
        }

        if (page == null){
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

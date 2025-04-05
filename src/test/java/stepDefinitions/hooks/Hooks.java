package stepDefinitions.hooks;

import com.microsoft.playwright.Page;
import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks extends DriverFactory {

    @Before
    public void setUp(){
        getPage(false);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            takeScreenshot(scenario.getName());
        }
        close();
    }

    private void takeScreenshot(String scenarioName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotName = "reports/" + scenarioName.replace(" ", "_") + "_" + timestamp + ".png";

        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotName)));
    }
}
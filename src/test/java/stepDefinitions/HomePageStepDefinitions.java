package stepDefinitions;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;

public class HomePageStepDefinitions extends DriverFactory {
    private static HomePage homePage;

    @Given("I navigate to the webdriveruniversity homepage")
    public void iNavigateToTheWebdriveruniversityHomepage() {
        homePage = new HomePage(page);
        homePage.navigateTo("https://webdriveruniversity.com");
    }

    @When("I click on the contact us button")
    public void iClickOnTheContactUsButton() {
        // homePage.click("a[id='contact-us'] h4");
        page = context.waitForPage(() -> {
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("CONTACT US Contact Us Form")).click();
        });

        page.bringToFront();

        String actualContactUsPage = homePage.getActualPageLabel("h2[name='contactme']");
        Assert.assertEquals(actualContactUsPage, "CONTACT US");
    }
}
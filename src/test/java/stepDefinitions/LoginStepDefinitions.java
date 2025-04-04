package stepDefinitions;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;

public class LoginStepDefinitions extends DriverFactory {
    private final LoginPage loginPage = new LoginPage(page);
    private String alertText;

    @When("I click the login button to navigate to the login page")
    public void iClickTheLoginButtonToNavigateToTheLoginPage() {
        page = context.waitForPage(() -> {
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("LOGIN PORTAL Login Portal")).click();
        });

        page.bringToFront();
    }

    @When("I enter a valid username {}")
    public void iEnterAValidUsername(String userName) {
        loginPage.typeUsername(userName);
    }

    @And("I enter a valid password {}")
    public void iEnterAValidPassword(String password) {
        loginPage.typePassword(password);
    }

    @And("I click the login button")
    public void iClickTheLoginButton() {
        alertText = loginPage.clickLoginButton();
    }

    @Then("I should be presented with an alert box which contains text {}")
    public void iShouldBePresentedWithAnAlertBoxWhichContainsText(String alertMessage) {
        Assert.assertEquals(alertText, alertMessage, "The alert does not match expected alert message!");
    }
}

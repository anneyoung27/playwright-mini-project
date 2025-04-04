package stepDefinitions;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.ContactUsPage;

public class ContactUsStepDefinitions extends DriverFactory {
    private final ContactUsPage contactUsPage = new ContactUsPage(page);

    @And("I type a first name")
    public void iTypeAFirstName() {
        contactUsPage.typeFirstName("Injas");
    }

    @And("I type a last name")
    public void iTypeALastName() {
        contactUsPage.typeLastName("Mahendra Berutu");
    }

    @And("I type an email address")
    public void iTypeAnEmailAddress() {
        contactUsPage.typeEmail("mahendraqa27@gmail.com");
    }

    @And("I type a comment")
    public void iTypeAComment() {
        contactUsPage.typeComment("****COMMENT****");
    }

    @And("I click on the submit button")
    public void iClickOnTheSubmitButton() {
         contactUsPage.clickSubmitButton();
    }

    @Then("I should be presented with a successful contact us submission message")
    public void iShouldBePresentedWithASuccessfulContactUsSubmissionMessage() {
        String actualSuccessMessage = contactUsPage.verifySuccessSubmitMessage("div[id='contact_reply'] h1");
        Assert.assertEquals(actualSuccessMessage, "Thank You for your Message!");
    }

    @Then("I should be presented error inline message")
    public void iShouldBePresentedErrorInlineMessage() {
        Assert.assertTrue(contactUsPage.errorInlineMessage(), "The body text doesn't match the expected error message");
    }
}

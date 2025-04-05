package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import factory.DriverFactory;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactUsPage extends DriverFactory {

    public ContactUsPage(Page contactUs){
        page = contactUs;
    }

    public void typeFirstName(String value){
        page.getByPlaceholder("First Name").fill(value);
    }

    public void typeLastName(String value){
        page.getByPlaceholder("Last Name").fill(value);
    }

    public void typeEmail(String value){
        page.getByPlaceholder("Email Address").fill(value);
    }

    public void typeComment(String value){
        page.getByPlaceholder("Comments").fill(value);
    }

    public void clickSubmitButton(){
        Page.WaitForSelectorOptions options = new Page.WaitForSelectorOptions().setTimeout(Integer.parseInt(setUp.getProperty("EXPLICIT_WAIT")));
        page.waitForSelector("input[value='SUBMIT']", options);

        page.click("input[value='SUBMIT']");
    }

    public String verifySuccessSubmitMessage(String actualElement) {
        Locator locator = page.locator(actualElement);

        // Wait for the element to be visible before interacting
        locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE)
                .setTimeout(Integer.parseInt(setUp.getProperty("EXPLICIT_WAIT"))));

        return locator.textContent();
    }


    public boolean errorInlineMessage(){
        page.waitForSelector("body");

        Locator bodyElement = page.locator("body");
        String bodyText = bodyElement.textContent();

        Pattern pattern = Pattern.compile("Error: (all fields are required|Invalid email address)");
        Matcher matcher = pattern.matcher(bodyText);

        return matcher.find();
    }


}
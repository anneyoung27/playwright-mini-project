package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import factory.DriverFactory;

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
        Page.WaitForSelectorOptions options = new Page.WaitForSelectorOptions().setTimeout(1000);
        page.waitForSelector("input[value='SUBMIT']", options);

        page.click("input[value='SUBMIT']");
    }

    public String verifySuccessSubmitMessage(String actualElement){
        page.waitForSelector(actualElement, new Page.WaitForSelectorOptions().setTimeout(1000));

        Locator locator = page.locator(actualElement);
        locator.isVisible();

        return page.textContent(actualElement);
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
package pages;

import com.microsoft.playwright.Page;
import factory.DriverFactory;

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
        page.click("input[value='SUBMIT']");
    }

    public String verifySuccessSubmitMessage(String actualElement){
        return page.textContent(actualElement);
    }


}
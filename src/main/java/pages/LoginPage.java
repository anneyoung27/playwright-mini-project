package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import factory.DriverFactory;

public class LoginPage extends DriverFactory {
    private String alertText;

    public LoginPage(Page loginPage){
        page = loginPage;
    }

    public void typeUsername(String value){
        page.getByPlaceholder("Username").fill(value);
    }

    public void typePassword(String value){
        page.getByPlaceholder("Password").fill(value);
    }

    public String clickLoginButton(){
        page.onceDialog(dialog -> {
            alertText = dialog.message();
            dialog.accept();
        });

        Locator locator = page.locator("//button[@id='login-button']");
        locator.hover();
        locator.click(new Locator.ClickOptions().setForce(true));

        return alertText;
    }
}

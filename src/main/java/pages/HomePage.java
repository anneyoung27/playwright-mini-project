package pages;

import com.microsoft.playwright.Page;
import factory.DriverFactory;

public class HomePage extends DriverFactory {

    public HomePage(Page homePage){
        page = homePage;
    }

    public void navigateTo(String url){
        page.navigate(url);
    }

    public String getTitle(){
        return page.title();
    }

    public String getActualPageLabel(String element){
        return page.textContent(element);
    }
}

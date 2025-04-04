package stepDefinitions.hooks;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends DriverFactory {

    @Before
    public void setUp(){
        getPage("chromium", false);
    }

    @After
    public void tearDown(){
        close();
    }
}

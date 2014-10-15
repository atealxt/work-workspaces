package seleniumdemo.easyctax;

import seleniumdemo.TestBase;

public class EasyCTAXLogin extends TestBase {

    @Override
    public String getUrl() {
        return "https://www.easyctax.com/";
    }

    @Override
    public boolean runInIE() {
        return false;
    }

    @Override
    public boolean stopBrowserAfterTest() {
        return false;
    }

    @Override
    public void execute() {
        selenium.open("/easyCTAX/login.jsp");
        selenium.waitForPageToLoad("30000");
        selenium.type("j_username", "testselenium");
        selenium.type("j_password", "testselenium");
        selenium.click("//input[@type='submit']");
        selenium.waitForPageToLoad("30000");
    }

}

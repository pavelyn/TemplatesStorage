package templateTestng.actions;

import templateTestng.driver.DriverManager;

public class BrowserActions {

    public static void navigateTo(String url) {
        DriverManager.getDriver().get(url);
    }
}

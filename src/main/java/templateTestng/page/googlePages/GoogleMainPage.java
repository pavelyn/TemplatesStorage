package templateTestng.page.googlePages;


import templateTestng.actions.PageElementActions;
import templateTestng.driver.DriverManager;

public class GoogleMainPage {
    private static final String searchInput = "xpath;//input[@name='q']";
    private static final String toolsButton = "id;hdtb-tls";
    private static final String speechElement = "xpath;/html/body/div[1]/div[3]/form/div[1]";

    public PageElementActions googleMainPage(){
        return PageElementActions.getInstance();
    }

    public PageElementActions getSearchInput(){
        PageElementActions.getInstance().initialize(searchInput, DriverManager.getDriver());
        return PageElementActions.getInstance();
    }

    public PageElementActions getToolsButton(){
        PageElementActions.getInstance().initialize(toolsButton, DriverManager.getDriver());
        return PageElementActions.getInstance();
    }

    public PageElementActions getSpeechElement(){
        PageElementActions.getInstance().initialize(speechElement, DriverManager.getDriver());
        return PageElementActions.getInstance();
    }
}

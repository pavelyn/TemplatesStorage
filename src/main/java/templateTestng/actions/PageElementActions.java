package templateTestng.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class PageElementActions {
    protected static final int DEFAULT_ELEMENT_WAIT_TIME_S = 10;
    private WebDriver driver;
    private String dataOfLocator;
    private String typeOfLocator;

    public void initialize(String element, WebDriver driver) {
        splitElement(element);
        this.driver = driver;
    }

    private static PageElementActions instance;

    public PageElementActions() {
        // nothing to do this time
    }

    static {
        instance = new PageElementActions();
    }

    public static PageElementActions getInstance() {
        return instance;
    }

    public void clean() {
        waitUntilVisibilityOfElementLocated(DEFAULT_ELEMENT_WAIT_TIME_S).clear();
    }

    public void sendKeys( String keysToSend ) {
        waitUntilVisibilityOfElementLocated(DEFAULT_ELEMENT_WAIT_TIME_S).sendKeys( keysToSend );

    }

    public void sendKeys( Keys keysToSend ) {
        waitUntilVisibilityOfElementLocated(DEFAULT_ELEMENT_WAIT_TIME_S).sendKeys( keysToSend );

    }

    public void click() {
        waitUntilElementToBeClickable(DEFAULT_ELEMENT_WAIT_TIME_S).click();
    }

    public void moveToElement() {
        Actions actions = new Actions(driver);
        try {
            actions.moveToElement( waitUntilElementToBeClickable(DEFAULT_ELEMENT_WAIT_TIME_S)).perform();
        } catch (org.openqa.selenium.interactions.MoveTargetOutOfBoundsException ex) { //when element outside the bounds of the current view port
            scrollElementIntoView();
        }
    }

    private WebElement waitUntilVisibilityOfElementLocated(int _secondsToWait) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(_secondsToWait));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(bySelector(dataOfLocator, typeOfLocator)));

        return element;
    }

    private WebElement waitUntilElementToBeClickable(int _secondsToWait) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(_secondsToWait));
        WebElement element =  wait.until(ExpectedConditions.elementToBeClickable(bySelector(dataOfLocator, typeOfLocator)));

        return element;
    }

    private void scrollElementIntoView() {
        WebElement element = driver.findElement(bySelector(dataOfLocator, typeOfLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private void splitElement(String element) {
        String[] elementList = element.split(";");

        this.dataOfLocator = elementList[1];
        this.typeOfLocator = elementList[0];
    }

    private By bySelector(String selector, String selectorType) {
        switch (selectorType){
            case "xpath":
                return By.xpath(selector);
            case "css":
                return By.cssSelector(selector);
            case "id":
                return By.id(selector);
            default:
                throw new ElementNotInteractableException("Incorrect selector type " + selectorType);
        }
    }
}

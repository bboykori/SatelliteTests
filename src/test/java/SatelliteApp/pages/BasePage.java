package SatelliteApp.pages;

import Base.BaseSeleniumTest;
import Base.helpers.MySeleniumMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends BaseSeleniumTest {

    @FindBy(xpath = "//div[@class='ui-pnotify-text']")
    private WebElement alertPopup;

    public String getAlertMessage(){
        MySeleniumMethods.waitForWebElement(alertPopup,40,driver);
        return alertPopup.getText();
    }


    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}

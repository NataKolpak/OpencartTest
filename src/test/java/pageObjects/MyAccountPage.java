package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MyAccountPage extends BasePage {
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    //MyAccount header
    @FindBy(xpath = "//h2[normalize-space()='My Account']")
    WebElement txtMyAccountHeading;

    //MyAccount link location
    @FindBy(xpath = "//li//span[normalize-space()='My Account']")
    WebElement lnkMyAccount;

    //Logout option on the page right corner
    @FindBy(xpath = "//li[@class='dropdown open']//a[text()='Logout']")
    WebElement lnkLogoutFromDropDown;

    //Logout option in the top user account menu
    @FindBy(xpath = "//aside[@id='column-right']//a[text()='Logout']")
    WebElement lnkLogoutRightBottomCorner;

    public boolean myAccountPageVerification() {
        try {
            return txtMyAccountHeading.isDisplayed();
        } catch (Exception e) {
            return (false);
        }
    }

    public void clickLogoutViaDropDown() throws InterruptedException {
        lnkMyAccount.click();
        Thread.sleep(1000);
        lnkLogoutFromDropDown.click();
    }

    public void clickLogoutRightSideOptions() {
        lnkLogoutRightBottomCorner.click();
    }
}

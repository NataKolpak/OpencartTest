package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmailAddress;

    @FindBy(xpath = "//input[@name='password']")
    WebElement txtPwd;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement btnSubmit;

    public void enterTxtEmailAddress(String name) { txtEmailAddress.sendKeys(name); }
    public void enterTxtPwd(String password) { txtPwd.sendKeys(password); }
    public void clickSubmit() { btnSubmit.click(); }
}

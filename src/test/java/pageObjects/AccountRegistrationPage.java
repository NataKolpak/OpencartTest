package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

    WebDriver driver;

    public AccountRegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "input-firstname")
    WebElement txtFirstName;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement txtLastName;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmail;

    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement txtTelephone;

    @FindBy(id = "input-password")
    WebElement txtPswrd;

    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement txtPswdConfirm;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement chckPrivPolicy;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//h1[contains(.,'Your Account Has')]")
    WebElement txtRegistConfirmation;

    public void setFirstName(String fname) {
        txtFirstName.sendKeys(fname);
    }

    public void setLastName(String lname) {
        txtLastName.sendKeys(lname);
    }

    public void setEmail(String email) {
        txtEmail.sendKeys(email);
    }

    public void setTelephone(String phone) {
        txtTelephone.sendKeys(phone);
    }

    public void setPswd(String password) {
        txtPswrd.sendKeys(password);
    }

    public void setPwdConfirm(String password) {txtPswdConfirm.sendKeys(password);}

    public void setPrivPolicy() {
        chckPrivPolicy.click();
    }

    public void clickContinue() {
        //solution1
        btnContinue.click();

        //solution2
//        btnContinue.submit();

        //solution3
//        Actions act = new Actions(driver);
//        act.moveToElement(btnContinue).click().perform();

        //solution4
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].click();", btnContinue);

        //solution5
//        btnContinue.sendKeys(Keys.RETURN);

        //solution6
//        WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();

    }

    public String getConfirmationMsg() {
        try {
            return txtRegistConfirmation.getText();
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}

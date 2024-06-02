package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
    public void loginDDT(String email, String password, String expResult) {
        logger.info("**** STARTING TC_003_LoginDDT ****");
        try {

            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccount();
            homePage.clickLogin();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.enterTxtEmailAddress(email);
            loginPage.enterTxtPwd(password);
            loginPage.clickSubmit();

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean targetPage = myAccountPage.myAccountPageVerification();

            if (expResult.equalsIgnoreCase("Valid")) {
                if (targetPage) {
                    myAccountPage.clickLogoutRightSideOptions();
                    Assert.assertTrue(true);
                } else {
                    Assert.fail();
                }
            }

            if (expResult.equalsIgnoreCase("Invalid")) {
                if (targetPage) {
                    myAccountPage.clickLogoutRightSideOptions();
                    Assert.fail();
                } else {
                    Assert.assertTrue(true);
                }
            }
        } catch (Exception e) {
            Assert.fail("failed main test");
        }
        logger.info("**** END TC_003_LoginDDT ****");
    }
}

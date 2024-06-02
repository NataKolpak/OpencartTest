package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

    @Test(groups = {"sanity", "master"} )
    public void verifyLogin() {
        logger.info("**** STARTING verifyLogin ****");
        logger.debug(" application logs started ");

        try {
            HomePage homePage = new HomePage(driver);
            logger.info("clicking Login.");
            homePage.clickMyAccount();
            homePage.clickLogin();

            LoginPage loginPage = new LoginPage(driver);
            logger.info("entering valid credentials.");
            loginPage.enterTxtEmailAddress(setEmailFromFile());
            loginPage.enterTxtPwd(setPasswordFromFile());
            logger.info("submitting credentials.");
            loginPage.clickSubmit();

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            logger.info("verifying successful login.");

            if (myAccountPage.myAccountPageVerification()) {
                logger.info("login test passed.");
                Assert.assertTrue(true);
            } else {
                logger.info("login failed.");
                Assert.fail();
            }

        } catch (Exception e) {
            logger.error("TC_002_LoginTest failed.");
            Assert.fail();
        }
        logger.info("**** TC_002_LoginTest ****");
        logger.debug("end of application logs.");
    }

}

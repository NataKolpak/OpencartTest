package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_001_AccountRegistrationTest extends BaseClass {

        @Test(groups = {"regression", "master"} )
        public void verifyAccountRegistration () throws InterruptedException {
        logger.info("**** STARTING verifyAccountRegistration**** ");
        logger.debug("application logs started...");

        try {
                HomePage hp = new HomePage(driver);
                hp.clickMyAccount();
                logger.info("Clicked on \"myAccount link\"");
                hp.clickRegister();
                logger.info("Clicked on \"Registration\" link");
                Thread.sleep(3000);

                logger.info("Entering customer details.");
                AccountRegistrationPage accRegPage = new AccountRegistrationPage(driver);
                accRegPage.setFirstName(randomString().toUpperCase());
                accRegPage.setLastName(randomString().toUpperCase());
                accRegPage.setEmail(randomString() + "@mail.com");
                accRegPage.setTelephone(randomNumber());

                String password = randomAlphaNumeric();
                accRegPage.setPswd(password);
                accRegPage.setPwdConfirm(password);

                accRegPage.setPrivPolicy();
                accRegPage.clickContinue();
                logger.info("Clicked on Continue.");

                String confMsg = accRegPage.getConfirmationMsg();
                logger.info("Validating expected message.");
                if (confMsg.equals("Your Account Has Been Created!")) {
                        logger.info("test passed.");
                        Assert.assertTrue(true);
                } else {
                        logger.error("test failed.");
                        Assert.fail();
                }
        }
        catch (Exception e) {
                logger.error("TC_001_AccountRegistrationTest failed.");
                Assert.fail();
        }
        logger.info("**** ENDING TC_001_AccountRegistrationTest ****");
        logger.debug("application logs ended...");
        }
}

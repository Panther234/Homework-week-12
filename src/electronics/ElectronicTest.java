package electronics;

import browsertesting.Utility;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.Random;

public class ElectronicTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() throws InterruptedException {
        mouseHoverOnly(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Electronics ']"));
        mouseHoverClick(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Cell phones ']"));
        verifyText("Cell phones", getTextFromElement(By.xpath("//h1[contains(text(),'Cell phones')]")), "Error-Message not match");
    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {
        verifyUserShouldNavigateToCellPhonesPageSuccessfully();
        clickOnElement(By.xpath("//a[contains(text(),'List')]"));
        sendKeysToElement(By.xpath("//a[contains(text(),'Nokia Lumia 1020')]"), Keys.ENTER);
        verifyText("Nokia Lumia 1020", getTextFromElement(By.xpath("//h1[contains(text(),'Nokia Lumia 1020')]")), "Product name not displayed");
        verifyText("$349.00", getTextFromElement(By.xpath("//span[contains(text(),' $349.00 ')]")), "Error. Price not displayed");
        sendKeysToElement(By.xpath("//input[@id='product_enteredQuantity_20']"), Keys.BACK_SPACE + "2");
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-20']"));
        verifyText("The product has been added to your shopping cart",getTextFromElement(By.xpath("//p[contains(.,'The product has been added to your shopping cart')]")),"Error, Message not displayed");
        clickOnElement(By.xpath("//span[@title='Close']"));
        mouseHoverOnly(By.xpath("//span[contains(text(),'Shopping cart')]"));
        mouseHoverClick(By.xpath("//button[contains(text(),'Go to cart')]"));
        verifyText("Shopping cart", getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]")), "Error, Message not displayed as expected");
        verifyText("(2)", getTextFromElement(By.xpath("//span[contains(text(),'(2)')]")), "Error, Message not displayed as expected");
        verifyText("$698.00", getTextFromElement(By.xpath("//span[contains(text(),'$698.00')]")), "Error, Message not displayed as expected");
        clickOnElement(By.id("termsofservice"));
        clickOnElement(By.xpath("//button[@id='checkout']"));
        verifyText("Welcome, Please Sign In!", getTextFromElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]")), "Error");
        clickOnElement(By.xpath("//button[contains(text(),'Register')]"));
        verifyText("Register", getTextFromElement(By.xpath("//h1[contains(text(),'Register')]")), "Error, Message not displayed as expected");
        clickOnElement(By.xpath("//input[@id='gender-male']"));
        sendTextToElement(By.xpath("//input[@id='FirstName']"),"Bob");
        sendTextToElement(By.xpath("//input[@id='LastName']"),"Alexa");
        driver.findElement(By.id("Email")).click();
        Random randomEmail = new Random();
        int randomInt = randomEmail. nextInt(1000);
        driver.findElement(By.id("Email")).sendKeys("username"+ randomInt +"@gmail.com");
        clickOnElement(By.xpath("//input[@id='Newsletter']"));
        sendTextToElement(By.xpath("//input[@id='Password']"),"abc123");
        sendTextToElement(By.xpath("//input[@id='ConfirmPassword']"),"abc123");
        clickOnElement(By.xpath("//button[@id='register-button']"));
        verifyText("Your registration completed", getTextFromElement(By.xpath("//div[contains(text(),'Your registration completed')]")),"Error-Registration faild");
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));
        verifyText("Shopping cart", getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]")),"Error");
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        clickOnElement(By.xpath("//button[@id='checkout']"));
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_FirstName']"),"Bob");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_LastName']"),"Alexa");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "United Kingdom");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "London");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "69 Haydon drive");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "HA1 1AA");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "01234567890");
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));
        clickOnElement(By.xpath("//input[@id='shippingoption_2']"));
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']"));
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='CreditCardType']"), "Visa");
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "Bob Alexa");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "2222 4000 7000 0005");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='ExpireMonth']"), "12");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='ExpireYear']"), "2035");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "123");
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));
        verifyText("Payment Method:", getTextFromElement(By.xpath("//span[contains(text(),'Payment Method:')]")), "Error, Message not displayed as expected");
        verifyText("Credit Card", getTextFromElement(By.xpath("//span[contains(text(),'Credit Card')]")), "Error");
        verifyText("Shipping Method:", getTextFromElement(By.xpath("//span[contains(text(),'Shipping Method:')]")), "Error");
        verifyText("2nd Day Air", getTextFromElement(By.xpath("//span[contains(.,'2nd Day Air')]")), "Error");
        verifyText("$698.00", getTextFromElement(By.xpath("//span[@class='value-summary']//strong[contains(text(),'$698.00')]")), "Error");
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));
        verifyText("Thank you", getTextFromElement(By.xpath("//h1[contains(text(),'Thank you')]")), "Error");
        verifyText("Your order has been successfully processed!", getTextFromElement(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]")), "Error");
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));
        verifyText("Welcome to our store", getTextFromElement(By.xpath("//h2[contains(text(),'Welcome to our store')]")), "Error");
        clickOnElement(By.xpath("//a[contains(text(),'Log out')]"));
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://demo.nopcommerce.com/" );
    }
    @After
    public void close(){
        closeBrowser();
    }
}





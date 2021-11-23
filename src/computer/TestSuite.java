package computer;

import browsertesting.Utility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class TestSuite extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() {
        clickOnElement(By.xpath("//a[@href='/computers']"));
        clickOnElement(By.xpath("//img[contains(@title,'Show products in category Desktops')]"));
        selectByVisibleTextFromDropDown(By.name("products-orderby"), "Name: Z to A");
        verifyText("Name: Z to A", getTextFromElement(By.xpath("//option[contains(text(),'Name: Z to A')]")), "Product is not in Descending order");
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        clickOnElement(By.xpath("//a[@href='/computers']"));
        clickOnElement(By.xpath("//img[contains(@title,'Show products in category Desktops')]"));
        selectByVisibleTextFromDropDown(By.name("products-orderby"), "Name: A to Z");
        sendKeysToElement(By.xpath("//div[@class='item-grid']//div[1]//div[1]//div[2]//div[3]//div[2]//button[1]"), Keys.ENTER);
        verifyText("Build your own computer", getTextFromElement(By.xpath("//h1[contains(text(),'Build your own computer')]")),"Message not found");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='product_attribute_1']"), "2.2 GHz Intel Pentium Dual-Core E2200");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='product_attribute_2']"), "8GB [+$60.00]");
        clickOnElement(By.xpath("//input[@id='product_attribute_3_7']"));
        clickOnElement(By.xpath("//input[@id='product_attribute_4_9']"));
        clickOnElement(By.xpath("//input[@id='product_attribute_5_12']"));
        verifyText("$1,475.00",getTextFromElement(By.xpath("//span[contains(text(),'$1,475.00')]")),"Total does not match");
        sendKeysToElement(By.xpath("//button[@id='add-to-cart-button-1']"), Keys.ENTER);
        verifyText("The product has been added to your shopping cart",getTextFromElement(By.xpath("//p[contains(.,'The product has been added to your shopping cart')]")),"Error, Message not displayed");
        clickOnElement(By.xpath("//span[@title='Close']"));
        mouseHoverOnly(By.xpath("//span[contains(text(),'Shopping cart')]"));
        mouseHoverClick(By.xpath("//button[contains(text(),'Go to cart')]"));
        verifyText("Shopping cart", getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]")), "Error, Message not displayed as expected");
        sendKeysToElement(By.xpath("//input[contains(@id, 'itemquantity')]"), Keys.BACK_SPACE + "2");
        clickOnElement(By.xpath("//button[text()='Update shopping cart']"));
        verifyText("$2,950.00", getTextFromElement(By.className("product-subtotal")), "Incorrect price");
        clickOnElement(By.id("termsofservice"));
        clickOnElement(By.id("checkout"));
        verifyText("Welcome, Please Sign In!", getTextFromElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]")), null);
        clickOnElement(By.xpath("//button[contains(text(),'Checkout as Guest')]"));
        sendKeysToElement(By.xpath("//input[@id='BillingNewAddress_FirstName']"), "bob");
        sendKeysToElement(By.xpath("//input[@id='BillingNewAddress_LastName']"), "alexa");
        sendKeysToElement(By.xpath("//input[@id='BillingNewAddress_Email']"), "abc@realestates.com");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "United Kingdom");
        sendKeysToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "London");
        sendKeysToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "69 Haydon Drive");
        sendKeysToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "HA0 2LD");
        sendKeysToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "+44123456789");
        clickOnElement(By.cssSelector("div.master-wrapper-page:nth-child(6) div.master-wrapper-content div.master-column-wrapper div.center-1 div.page.checkout-page div.page-body.checkout-data ol.opc li.tab-section.allow.active:nth-child(1) div.step.a-item div.buttons:nth-child(2) > button.button-1.new-address-next-step-button:nth-child(4)"));
        clickOnElement(By.id("shippingoption_1"));
        clickOnElement(By.cssSelector("div.master-wrapper-page:nth-child(6) div.master-wrapper-content div.master-column-wrapper div.center-1 div.page.checkout-page div.page-body.checkout-data ol.opc li.tab-section.allow.active:nth-child(3) div.step.a-item form:nth-child(1) div.buttons:nth-child(2) > button.button-1.shipping-method-next-step-button"));
        clickOnElement(By.id("paymentmethod_1"));
        clickOnElement(By.cssSelector("div.master-wrapper-page:nth-child(6) div.master-wrapper-content div.master-column-wrapper div.center-1 div.page.checkout-page div.page-body.checkout-data ol.opc li.tab-section.allow.active:nth-child(4) div.step.a-item div.buttons:nth-child(2) > button.button-1.payment-method-next-step-button"));
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='CreditCardType']"), "Master card");
        sendKeysToElement(By.xpath("//input[@id='CardholderName']"), "bob alexa");
        sendKeysToElement(By.xpath("//input[@id='CardNumber']"), "2222 4000 7000 0005");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='ExpireMonth']"), "12");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='ExpireYear']"), "2035");
        sendKeysToElement(By.xpath("//input[@id='CardCode']"), "123");
        clickOnElement(By.cssSelector("div.master-wrapper-page:nth-child(6) div.master-wrapper-content div.master-column-wrapper div.center-1 div.page.checkout-page div.page-body.checkout-data ol.opc li.tab-section.allow.active:nth-child(5) div.step.a-item div.buttons:nth-child(2) > button.button-1.payment-info-next-step-button"));
        verifyText("Credit Card", getTextFromElement(By.xpath("//span[contains(text(),'Credit Card')]")),"Error");
        verifyText("Next Day Air", getTextFromElement(By.xpath("//span[contains(text(),'Next Day Air')]")),"Error");
        verifyText("$2,950.00", getTextFromElement(By.xpath("//span[@class='value-summary']//strong[contains(text(),'$2,950.00')]")),"Error");
        clickOnElement(By.cssSelector("div.master-wrapper-page:nth-child(6) div.master-wrapper-content div.master-column-wrapper div.center-1 div.page.checkout-page div.page-body.checkout-data ol.opc li.tab-section.allow.active:nth-child(6) div.step.a-item div.buttons:nth-child(2) > button.button-1.confirm-order-next-step-button"));
        verifyText("Thank you", getTextFromElement(By.xpath("//h1[contains(text(),'Thank you')]")), null);
        verifyText("Your order has been successfully processed!", getTextFromElement(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]")), null);
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));
        verifyText("Welcome to our store", getTextFromElement(By.xpath("//h2[contains(text(),'Welcome to our store')]")), null);
    }
    @After
    public void close(){
        closeBrowser();
    }
}

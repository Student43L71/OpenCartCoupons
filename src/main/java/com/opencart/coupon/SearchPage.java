package com.opencart.coupon;

import org.openqa.selenium.*;
import org.junit.jupiter.params.provider.Arguments;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class SearchPage extends BasePage{

    //elements

    @FindBy(css = "#product-list [class='col mb-3']:nth-of-type(2) .button-group [type='submit']:nth-of-type(1)")
    private WebElement clickAddToCartButton;

    @FindBy(css = "div:nth-of-type(2) > .product-thumb h4 > a")
    private WebElement selectedProductName;

    @FindBy(xpath = "//*//div[@id='product-list']/div[2]//span[@class='price-new']")
    private WebElement selectedProductPrice;

    @FindBy(xpath = "//*//div[@id='product-list']/div[2]//span[@class='price-tax']")
    private WebElement selectedProductPriceNoTax;

    @FindBy(xpath = "//*//div[@id='header-cart']")
    private WebElement shoppingCartButton;

    @FindBy(css = ".dropdown-menu.dropdown-menu-end.p-2.show p > a:nth-of-type(1)")
    private WebElement viewCartLinkInShoppingButton;

    public SearchPage(WebDriver driver) {
        super(driver);
    }


    public void clickOntoAddToCartButton() {
        clickAddToCartButton.click();
    }

    public String getSelectedProductName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.visibilityOf(selectedProductName));
        return selectedProductName.getText();
    }

    public String getSelectedProductPrice() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.visibilityOf(selectedProductPrice));
        return selectedProductPrice.getText();
    }

    public void clickOnShoppingCartButton() {
        // Wait for the annoying alert to disappear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".alert.alert-success.alert-dismissible")));

        // JS script for scrolling shopping cart button into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", shoppingCartButton);

        // Wait for shopping cart element to be clickable
        WebDriverWait pause = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement clickableCartButton = pause.until(ExpectedConditions.elementToBeClickable(shoppingCartButton));

        shoppingCartButton.click();
    }

    public void clickOnViewCartLinkInShoppingButton() {
        viewCartLinkInShoppingButton.click();
    }

    public String getSelectedProductPriceWithNoTax(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.visibilityOf(selectedProductPriceNoTax));
        String priceWithNoTax = selectedProductPriceNoTax.getText().trim();
        // Extracting only the price without tax
        String[] parts = priceWithNoTax.split("Ex Tax:");
        return parts[1].trim();
    }


}

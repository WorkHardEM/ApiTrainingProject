package com.socks.ui.test;

import com.codeborne.selenide.WebDriverRunner;
import com.socks.api.services.CartApiService;
import com.socks.ui.CatalogPage;
import com.socks.ui.ShoppingCartPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.sleep;

public class ShoppingCartTest extends BaseUITest {

    private CartApiService cartApiService = new CartApiService();

    @Test
    public void userCanAddItemToCartFromCatalog() {
        CatalogPage.open()
        .addItemByIndex(0)
        .goToCart();

        at(ShoppingCartPage.class).totalAmount().shouldHave(exactText("$104.98"));
    }

    @Test
    public void testCanDeleteItemFromCart() {
        ShoppingCartPage.open();

        sleep(500);

        String cookies = WebDriverRunner.getWebDriver().manage().getCookieNamed("md.sid").getValue();

        cartApiService.addItemToCart("03fef6ac-1896-4ce8-bd69-b798f85c6e0b", cookies);

        ShoppingCartPage.open()
        .deleteItem()
        .totalAmount().shouldHave(exactText("$0.00"));
    }

    @AfterMethod
    public void tearDown() {
        closeWebDriver();
    }
}

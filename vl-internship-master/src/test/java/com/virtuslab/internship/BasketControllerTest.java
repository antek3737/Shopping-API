package com.virtuslab.internship;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BasketControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void shouldReturnReceiptWhenBasketIsPosted() throws Exception {
        Basket basket = new Basket();
        ProductDb productDb = new ProductDb();
        basket.addProduct(productDb.getProduct("Milk"));
        basket.addProduct(productDb.getProduct("Bread"));

        var recipe = new ReceiptGenerator().generate(basket);

        var response = this.restTemplate.postForObject("http://localhost:" + port + "/basket", basket, Receipt.class);

        Assertions.assertEquals(recipe, response);
    }
}
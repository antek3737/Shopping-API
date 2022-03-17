package com.virtuslab.internship.controllers;

import com.virtuslab.internship.services.BasketService;
import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.receipt.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasketController {

    private final BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping("/basket")
    public Receipt getReceipt(@RequestBody Basket basket) {
        return basketService.getReceiptForABasket(basket);
    }
}





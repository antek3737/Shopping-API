package com.virtuslab.internship.services;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.Discount;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import org.springframework.stereotype.Service;

@Service
public class BasketService {
    public Receipt getReceiptForABasket(Basket basket) {
        return Discount.applyDiscount(new ReceiptGenerator().generate(basket));
    }
}

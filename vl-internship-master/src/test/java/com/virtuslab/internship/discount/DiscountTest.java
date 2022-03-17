package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
class DiscountTest {

    @Test
    void shouldApplyBothDiscountsWhenPriceIsGraterThanFiftyAndItHasAtLeastThreeGrainProducts() {
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        var cereals = productDb.getProduct("Cereals");
        var steak = productDb.getProduct("Steak");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 1));
        receiptEntries.add(new ReceiptEntry(cereals, 2));
        receiptEntries.add(new ReceiptEntry(steak, 1));


        var receipt = new Receipt(receiptEntries);
        var receiptAfterDiscount = Discount.applyDiscount(receipt);
        var expectedTotalPrice = bread.price().add(cereals.price().multiply(BigDecimal.valueOf(2))).add(steak.price()).multiply(BigDecimal.valueOf(0.765));

        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(2, receiptAfterDiscount.discounts().size());
    }

    @Test
    void shouldApplyOnlyTenPercentDiscountWhenPriceIsGraterThanFiftyAndItHasNoGrainProducts() {
        var productDb = new ProductDb();
        var apple = productDb.getProduct("Apple");
        var milk = productDb.getProduct("Milk");
        var steak = productDb.getProduct("Steak");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(apple, 1));
        receiptEntries.add(new ReceiptEntry(milk, 1));
        receiptEntries.add(new ReceiptEntry(steak, 1));

        var receipt = new Receipt(receiptEntries);
        var receiptAfterDiscount = Discount.applyDiscount(receipt);
        var expectedTotalPrice = apple.price().add(milk.price()).add(steak.price()).multiply(BigDecimal.valueOf(0.90));

        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(1, receiptAfterDiscount.discounts().size());
    }

    @Test
    void shouldApplyOnlyTenPercentDiscountWhenPriceIsGraterThanFiftyAndItHasOnlyOneGrainProduct() {
        var productDb = new ProductDb();
        var apple = productDb.getProduct("Apple");
        var bread = productDb.getProduct("Bread");
        var steak = productDb.getProduct("Steak");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(apple, 1));
        receiptEntries.add(new ReceiptEntry(bread, 1));
        receiptEntries.add(new ReceiptEntry(steak, 1));

        var receipt = new Receipt(receiptEntries);
        var receiptAfterDiscount = Discount.applyDiscount(receipt);
        var expectedTotalPrice = apple.price().add(bread.price()).add(steak.price()).multiply(BigDecimal.valueOf(0.90));

        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(1, receiptAfterDiscount.discounts().size());
    }

    @Test
    void shouldApplyOnlyTenPercentDiscountWhenPriceIsGraterThanFiftyAndItHasOnlyTwoGrainProducts() {
        var productDb = new ProductDb();
        var cereals = productDb.getProduct("Cereals");
        var bread = productDb.getProduct("Bread");
        var steak = productDb.getProduct("Steak");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(cereals, 1));
        receiptEntries.add(new ReceiptEntry(bread, 1));
        receiptEntries.add(new ReceiptEntry(steak, 1));

        var receipt = new Receipt(receiptEntries);
        var receiptAfterDiscount = Discount.applyDiscount(receipt);
        var expectedTotalPrice = cereals.price().add(bread.price()).add(steak.price()).multiply(BigDecimal.valueOf(0.90));

        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(1, receiptAfterDiscount.discounts().size());
    }

    @Test
    void shouldApplyOnlyFifteenPercentDiscountWhenPriceIsSmallerThanFiftyAndItThreeGrainProducts() {
        var productDb = new ProductDb();
        var cereals = productDb.getProduct("Cereals");
        var bread = productDb.getProduct("Bread");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(cereals, 1));
        receiptEntries.add(new ReceiptEntry(bread, 2));

        var receipt = new Receipt(receiptEntries);
        var receiptAfterDiscount = Discount.applyDiscount(receipt);
        var expectedTotalPrice = cereals.price().add(bread.price().multiply(BigDecimal.valueOf(2))).multiply(BigDecimal.valueOf(0.85));

        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(1, receiptAfterDiscount.discounts().size());
    }

    @Test
    void shouldNotApplyAnyDiscountWhenPriceIsSmallerThanFiftyAndItHasNoGrainProducts() {
        var productDb = new ProductDb();
        var apple = productDb.getProduct("Apple");
        var cheese = productDb.getProduct("Cheese");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(apple, 1));
        receiptEntries.add(new ReceiptEntry(cheese, 2));

        var receipt = new Receipt(receiptEntries);
        var receiptAfterDiscount = Discount.applyDiscount(receipt);
        var expectedTotalPrice = apple.price().add(cheese.price().multiply(BigDecimal.valueOf(2)));

        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(0, receiptAfterDiscount.discounts().size());
    }

    @Test
    void shouldNotApplyAnyDiscountWhenPriceIsSmallerThanFiftyAndItHasOnlyOneGrainProduct() {
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        var cheese = productDb.getProduct("Cheese");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 1));
        receiptEntries.add(new ReceiptEntry(cheese, 2));

        var receipt = new Receipt(receiptEntries);
        var receiptAfterDiscount = Discount.applyDiscount(receipt);
        var expectedTotalPrice = bread.price().add(cheese.price().multiply(BigDecimal.valueOf(2)));

        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(0, receiptAfterDiscount.discounts().size());
    }

    @Test
    void shouldNotApplyAnyDiscountWhenPriceIsSmallerThanFiftyAndItHasOnlyTwoGrainProducts() {
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        var cheese = productDb.getProduct("Cheese");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 2));
        receiptEntries.add(new ReceiptEntry(cheese, 1));

        var receipt = new Receipt(receiptEntries);
        var receiptAfterDiscount = Discount.applyDiscount(receipt);
        var expectedTotalPrice = cheese.price().add(bread.price().multiply(BigDecimal.valueOf(2)));

        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(0, receiptAfterDiscount.discounts().size());
    }

}

package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;

public class Discount {

    public static FifteenPercentDiscount fifteenPercentDiscount = new FifteenPercentDiscount();
    public static TenPercentDiscount tenPercentDiscount = new TenPercentDiscount();

    public static Receipt applyDiscount(Receipt receipt){
        return tenPercentDiscount.apply(fifteenPercentDiscount.apply(receipt));
    }
}

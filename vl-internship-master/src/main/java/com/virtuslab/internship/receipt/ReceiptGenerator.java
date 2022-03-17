package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
        var receipt = getReceipt(basket);
        return receipt;
    }

    private Receipt getReceipt(Basket basket) {
        HashMap<Product,Integer> mapOfProductsAndAmount = (HashMap<Product, Integer>) getMapOfProductsAndTheirAmountsFromABasket(basket);

        List<ReceiptEntry> receiptEntries = new ArrayList<>();

        for (Product product : mapOfProductsAndAmount.keySet()) {
            receiptEntries.add(new ReceiptEntry(product, mapOfProductsAndAmount.get(product)));
        }

        return new Receipt(receiptEntries);
    }

    private Map<Product,Integer> getMapOfProductsAndTheirAmountsFromABasket(Basket basket){

        HashMap<Product,Integer> mapOfProductsAndAmount = new HashMap<>(basket.getProducts().size());

        for (Product product : basket.getProducts()) {
            if(mapOfProductsAndAmount.containsKey(product)){
                mapOfProductsAndAmount.put(product,mapOfProductsAndAmount.get(product)+1);
            }else{
                mapOfProductsAndAmount.put(product,1);
            }
        }
        return mapOfProductsAndAmount;
    }
}

package it.gabrieletondi.salestaxes;

import java.math.BigDecimal;

public class BasketItem {
    private final BigDecimal taxedPrice;
    private int quantity;
    private String productName;

    public BigDecimal getTaxedPrice() {
        return taxedPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return productName;
    }

    public BasketItem(String productName, int quantity, BigDecimal taxedPrice) {
        this.taxedPrice = taxedPrice;
        this.quantity = quantity;
        this.productName = productName;
    }
}

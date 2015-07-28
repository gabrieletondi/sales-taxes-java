package it.gabrieletondi.salestaxes;

import java.math.BigDecimal;

public class SaleItem {
    private final String productName;
    private final BigDecimal neatPrice;
    private final String quantity;

    public String getProductName() {
        return productName;
    }

    public BigDecimal getNeatPrice() {
        return neatPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public SaleItem(String productName, BigDecimal neatPrice, String quantity) {
        this.productName = productName;
        this.neatPrice = neatPrice;
        this.quantity = quantity;
    }
}

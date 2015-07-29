package it.gabrieletondi.salestaxes.receipt;

import java.math.BigDecimal;

public class ReceiptItem {
    private final BigDecimal taxedPrice;
    private int quantity;
    private String productName;
    private boolean isImported;

    public BigDecimal getTaxedPrice() {
        return taxedPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return productName;
    }

    public boolean isImported() {
        return isImported;
    }

    public ReceiptItem(String productName, int quantity, BigDecimal taxedPrice, boolean isImported) {
        this.taxedPrice = taxedPrice;
        this.quantity = quantity;
        this.productName = productName;
        this.isImported = isImported;
    }
}

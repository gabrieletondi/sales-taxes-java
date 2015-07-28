package it.gabrieletondi.salestaxes;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SaleItem {
    private final String productName;
    private final BigDecimal netPrice;
    private final String quantity;

    public String getProductName() {
        return productName;
    }

    public BigDecimal getNetPrice() {
        return netPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public SaleItem(String productName, BigDecimal netPrice, String quantity) {
        this.productName = productName;
        this.netPrice = netPrice;
        this.quantity = quantity;
    }

    public static SaleItem fromSellCommand(String sellCommand) {
        Pattern pattern = Pattern.compile("^(\\d)\\s([a-zA-Z\\s]*)\\sat\\s(\\d+\\.\\d{2}?)");

        Matcher matcher = pattern.matcher(sellCommand);
        matcher.matches();

        String quantity = matcher.group(1);
        String productName = matcher.group(2);
        BigDecimal netPrice = new BigDecimal(matcher.group(3));

        return new SaleItem(productName, netPrice, quantity);
    }
}

package it.gabrieletondi.salestaxes;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SaleItem {
    public static final String NOT_IMPORTED_PATTERN = "^(\\d)\\s([a-zA-Z\\s]*)\\sat\\s(\\d+\\.\\d{2}?)";
    public static final String IMPORTED_PATTERN = "^(\\d)\\s(imported)\\s([a-zA-Z\\s]*)\\sat\\s(\\d+\\.\\d{2}?)";

    private final String productName;
    private final BigDecimal netPrice;
    private final int quantity;
    private boolean isImported;

    public String getProductName() {
        return productName;
    }

    public BigDecimal getNetPrice() {
        return netPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public SaleItem(String productName, BigDecimal netPrice, int quantity, boolean isImported) {
        this.productName = productName;
        this.netPrice = netPrice;
        this.quantity = quantity;
        this.isImported = isImported;
    }

    public static SaleItem fromSellCommand(String sellCommand) {
        Pattern pattern = Pattern.compile(IMPORTED_PATTERN);
        Matcher matcher = pattern.matcher(sellCommand);

        if (matcher.matches()) {
            String quantity = matcher.group(1);
            String productName = matcher.group(3);
            BigDecimal netPrice = new BigDecimal(matcher.group(4));

            return new SaleItem(productName, netPrice, Integer.parseInt(quantity), true);
        }

        return parseNotImported(sellCommand);
    }

    private static SaleItem parseNotImported(String sellCommand) {
        Pattern pattern = Pattern.compile(NOT_IMPORTED_PATTERN);
        Matcher matcher = pattern.matcher(sellCommand);

        matcher.matches();
        String quantity = matcher.group(1);
        String productName = matcher.group(2);
        BigDecimal netPrice = new BigDecimal(matcher.group(3));

        return new SaleItem(productName, netPrice, Integer.parseInt(quantity), false);
    }

    public boolean isImported() {
        return isImported;
    }
}

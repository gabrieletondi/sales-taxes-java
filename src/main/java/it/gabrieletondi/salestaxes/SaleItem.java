package it.gabrieletondi.salestaxes;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SaleItem {
    public static final String COMMAND_PATTERN = "^(\\d)\\s([a-zA-Z\\s]*)\\sat\\s(\\d+\\.\\d{2}?)";

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

    public boolean isImported() {
        return isImported;
    }

    public SaleItem(String productName, BigDecimal netPrice, int quantity, boolean isImported) {
        this.productName = productName;
        this.netPrice = netPrice;
        this.quantity = quantity;
        this.isImported = isImported;
    }

    public static SaleItem fromSellCommand(String sellCommand) {
        Pattern pattern = Pattern.compile(COMMAND_PATTERN);
        Matcher matcher = pattern.matcher(sellCommand);

        matcher.matches();
        String quantity = matcher.group(1);
        String productName = matcher.group(2);
        BigDecimal netPrice = new BigDecimal(matcher.group(3));

        boolean isImported = isImported(productName);
        if (isImported)
            productName = sanitizeImportedProductName(productName);

        return new SaleItem(productName, netPrice, Integer.parseInt(quantity), isImported);
    }

    private static String sanitizeImportedProductName(String productName) {
        productName = productName.replace("imported", "");
        productName = productName.replace("  ", " ");
        return productName.trim();
    }

    private static boolean isImported(String productName) {
        return productName.contains("imported");
    }


}

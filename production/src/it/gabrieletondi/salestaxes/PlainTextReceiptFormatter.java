package it.gabrieletondi.salestaxes;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class PlainTextReceiptFormatter {
    public String format(Receipt receipt) {
        String result = "";

        for(ReceiptItem item : receipt.getItems())
            result += formatReceiptItem(item);

        result += "Sales Taxes: " + formatDecimal(receipt.getSalesTaxes()) + "\n" +
                "Total: " + formatDecimal(receipt.getTotal());

        return result;
    }

    private String formatReceiptItem(ReceiptItem item) {
        String source = "";

        if (item.isImported())
            source = "imported ";

        return item.getQuantity() + " " + source + item.getProductName() + ": " + formatDecimal(item.getTaxedPrice()) + "\n";
    }

    private String formatDecimal(BigDecimal value) {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);

        return decimalFormat.format(value);
    }
}

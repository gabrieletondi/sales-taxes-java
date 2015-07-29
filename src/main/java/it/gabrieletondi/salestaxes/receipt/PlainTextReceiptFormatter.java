package it.gabrieletondi.salestaxes.receipt;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class PlainTextReceiptFormatter {
    public String format(Receipt receipt) {
        StringBuilder result = new StringBuilder();

        for(ReceiptItem item : receipt.getItems())
            result.append(formatReceiptItem(item));

        result.append(formatTail(receipt));

        return result.toString();
    }

    private String formatTail(Receipt receipt) {
        return "Sales Taxes: " + formatDecimal(receipt.getSalesTaxes()) + "\n" +
                "Total: " + formatDecimal(receipt.getTotal());
    }

    private String formatReceiptItem(ReceiptItem item) {
        return item.getQuantity() + " " + importedLabel(item) + item.getProductName() + ": " + formatDecimal(item.getTaxedPrice()) + "\n";
    }

    private String importedLabel(ReceiptItem item) {
        if (item.isImported())
            return "imported ";

        return "";
    }

    private String formatDecimal(BigDecimal value) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00#");
        return decimalFormat.format(value);
    }
}

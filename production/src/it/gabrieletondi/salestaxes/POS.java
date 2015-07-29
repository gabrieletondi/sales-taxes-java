package it.gabrieletondi.salestaxes;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class POS {

    private Basket basket;

    public POS(InMemoryWithDefaultTaxPolicy taxPolicy) {
        this.basket = new Basket(taxPolicy);
    }

    public void sell(String sellCommand) {
        SaleItem saleItem = SaleItem.fromSellCommand(sellCommand);
        basket.add(saleItem);
    }

    public String receipt() {
        String receipt = "";

        for(BasketItem item : basket.getItems())
            receipt += formatReceiptItem(item);

        receipt += "Sales Taxes: " + formatDecimal(basket.getSalesTaxes()) + "\n" +
                "Total: " + formatDecimal(basket.getTotal());

        return receipt;
    }

    private String formatReceiptItem(BasketItem item) {
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

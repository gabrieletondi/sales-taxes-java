package it.gabrieletondi.salestaxes;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class POS {

    private final InMemoryWithDefaultTaxPolicy taxPolicy;
    private List<SaleItem> saleItems;

    public POS(InMemoryWithDefaultTaxPolicy taxPolicy) {
        this.taxPolicy = taxPolicy;
        this.saleItems = new ArrayList<SaleItem>();
    }

    public void sell(String sellCommand) {
        saleItems.add(SaleItem.fromSellCommand(sellCommand));
    }

    public String receipt() {
        String receipt = "";
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal salesTaxes = BigDecimal.ZERO;

        for (SaleItem saleItem : saleItems) {
            BigDecimal netPrice = saleItem.getNetPrice();
            Tax tax = taxPolicy.forItemName(saleItem.getProductName());
            BigDecimal taxAmount = tax.dutyAmount(netPrice);

            BigDecimal taxedPrice = netPrice.add(taxAmount);
            total = total.add(taxedPrice);
            salesTaxes = salesTaxes.add(taxAmount);

            DecimalFormat decimalFormat = new DecimalFormat();
            decimalFormat.setMaximumFractionDigits(2);
            decimalFormat.setMinimumFractionDigits(2);

            receipt += saleItem.getQuantity() + " " + saleItem.getProductName() + ": " + decimalFormat.format(taxedPrice) + "\n";

        }

        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);

        receipt += "Sales Taxes: " + decimalFormat.format(salesTaxes) + "\n" +
                "Total: " + decimalFormat.format(total);

        return receipt;
    }

}

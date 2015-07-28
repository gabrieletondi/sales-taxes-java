package it.gabrieletondi.salestaxes;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class POS {

    private final InMemoryWithDefaultTaxPolicy taxPolicy;
    private SaleItem saleItem;

    public POS(InMemoryWithDefaultTaxPolicy taxPolicy) {
        this.taxPolicy = taxPolicy;
    }

    public void sell(String sellCommand) {
        saleItem = SaleItem.fromSellCommand(sellCommand);
    }

    public String receipt() {
        BigDecimal netPrice = saleItem.getNetPrice();
        Tax tax = taxPolicy.forItemName(saleItem.getProductName());
        BigDecimal taxAmount = tax.dutyAmount(netPrice);

        BigDecimal taxedPrice = netPrice.add(taxAmount);
        BigDecimal salesTaxes = taxedPrice.subtract(saleItem.getNetPrice());

        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);

        return saleItem.getQuantity() + " " + saleItem.getProductName() + " : " + decimalFormat.format(taxedPrice) + "\n" +
                "Sales Taxes: " + decimalFormat.format(salesTaxes) + "\n" +
                "Total: " + decimalFormat.format(taxedPrice);
    }

}

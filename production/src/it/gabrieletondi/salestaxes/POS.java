package it.gabrieletondi.salestaxes;

import java.math.BigDecimal;

public class POS {

    private final InMemoryWithDefaultTaxPolicy taxPolicy;
    private SaleItem saleItem;

    public POS(InMemoryWithDefaultTaxPolicy taxPolicy) {
        this.taxPolicy = taxPolicy;
    }

    public void sellItem(String sellCommand) {
        saleItem = SaleItem.fromSellCommand(sellCommand);
    }

    public String receipt() {
        BigDecimal netPrice = saleItem.getNetPrice();
        BigDecimal taxAmount = taxPolicy.forItemName(saleItem.getProductName()).applyTo(netPrice);

        double roundedTax = Math.ceil(taxAmount.doubleValue() * 20) / 20;

        BigDecimal taxedPrice = netPrice.add(new BigDecimal(roundedTax));
        BigDecimal salesTaxes = taxedPrice.subtract(saleItem.getNetPrice());

        return saleItem.getQuantity() + " " + saleItem.getProductName() + " : " + taxedPrice.toString() + "\n" +
                "Sales Taxes: " + salesTaxes.toString() + "\n" +
                "Total: " + taxedPrice.toString();
    }

}

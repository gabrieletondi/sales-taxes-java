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
        Tax tax = taxPolicy.forItemName(saleItem.getProductName());
        BigDecimal taxAmount = tax.dutyAmount(netPrice);

        TaxRounding rounding = new TaxRounding();

        BigDecimal taxedPrice = netPrice.add(rounding.round(taxAmount));
        BigDecimal salesTaxes = taxedPrice.subtract(saleItem.getNetPrice());

        return saleItem.getQuantity() + " " + saleItem.getProductName() + " : " + taxedPrice.toString() + "\n" +
                "Sales Taxes: " + salesTaxes.toString() + "\n" +
                "Total: " + taxedPrice.toString();
    }

}

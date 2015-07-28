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
        BigDecimal taxedPrice = applyTax(taxPolicy.forItemName(saleItem.getProductName()));
        BigDecimal salesTaxes = taxedPrice.subtract(saleItem.getNetPrice());

        return saleItem.getQuantity() + " " + saleItem.getProductName() + " : " + taxedPrice.toString() + "\n" +
                "Sales Taxes: " + salesTaxes.toString() + "\n" +
                "Total: " + taxedPrice.toString();
    }

    private BigDecimal applyTax(Tax tax) {
        BigDecimal price = saleItem.getNetPrice();
        BigDecimal taxAmount = price.divide(new BigDecimal(100)).multiply(new BigDecimal(tax.getRate()));

        double roundedTax = Math.ceil(taxAmount.doubleValue() * 20) / 20;

        return price.add(new BigDecimal(roundedTax));
    }
}

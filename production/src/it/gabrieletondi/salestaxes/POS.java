package it.gabrieletondi.salestaxes;

import java.math.BigDecimal;

public class POS {

    private SaleItem saleItem;

    public void sellItem(String sellCommand) {
        saleItem = SaleItem.fromSellCommand(sellCommand);
    }

    public String receipt() {
        BigDecimal taxedPrice = applyTax(taxRateFor(saleItem));
        BigDecimal salesTaxes = taxedPrice.subtract(saleItem.getNetPrice());

        return saleItem.getQuantity() + " " + saleItem.getProductName() + " : " + taxedPrice.toString() + "\n" +
                "Sales Taxes: " + salesTaxes.toString() + "\n" +
                "Total: " + taxedPrice.toString();
    }

    private Tax taxRateFor(SaleItem saleItem) {
        if ("book".equalsIgnoreCase(saleItem.getProductName()))
            return new Tax(0);

        return new Tax(10);
    }

    private BigDecimal applyTax(Tax tax) {
        BigDecimal price = saleItem.getNetPrice();
        BigDecimal taxAmount = price.divide(new BigDecimal(100)).multiply(new BigDecimal(tax.getRate()));

        double roundedTax = Math.ceil(taxAmount.doubleValue() * 20) / 20;

        return price.add(new BigDecimal(roundedTax));
    }
}

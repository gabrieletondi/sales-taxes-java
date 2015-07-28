package it.gabrieletondi.salestaxes;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class POS {

    private SaleItem saleItem;

    public void sellItem(String sellCommand) {
        saleItem = parseCommand(sellCommand);
    }

    private SaleItem parseCommand(String sellCommand) {
        Pattern pattern = Pattern.compile("^(\\d)\\s([a-zA-Z\\s]*)\\sat\\s(\\d+\\.\\d{2}?)");

        Matcher matcher = pattern.matcher(sellCommand);
        matcher.matches();

        String quantity = matcher.group(1);
        String productName = matcher.group(2);
        BigDecimal price = new BigDecimal(matcher.group(3));

        return new SaleItem(productName, price, quantity);
    }

    public String receipt() {
        BigDecimal taxedPrice = applyTax(taxRateFor(saleItem));
        BigDecimal salesTaxes = taxedPrice.subtract(saleItem.getNeatPrice());

        return saleItem.getQuantity() + " " + saleItem.getProductName() + " : " + taxedPrice.toString() + "\n" +
                "Sales Taxes: " + salesTaxes.toString() + "\n" +
                "Total: " + taxedPrice.toString();
    }

    private int taxRateFor(SaleItem saleItem) {
        if ("book".equalsIgnoreCase(saleItem.getProductName()))
            return 0;

        return 10;
    }

    private BigDecimal applyTax(int goodTaxRate) {
        BigDecimal price = saleItem.getNeatPrice();
        BigDecimal tax = price.divide(new BigDecimal(100)).multiply(new BigDecimal(goodTaxRate));

        double roundedTax = Math.ceil(tax.doubleValue() * 20) / 20;

        return price.add(new BigDecimal(roundedTax));
    }
}

package it.gabrieletondi.salestaxes.receipt;

import it.gabrieletondi.salestaxes.catalog.CartItem;
import it.gabrieletondi.salestaxes.tax.Tax;
import it.gabrieletondi.salestaxes.tax.TaxPolicy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private final TaxPolicy taxPolicy;
    private BigDecimal total;
    private BigDecimal salesTaxes;
    private List<ReceiptItem> items;

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getSalesTaxes() {
        return salesTaxes;
    }

    public List<ReceiptItem> getItems() {
        return items;
    }

    public Receipt(TaxPolicy taxPolicy) {
        this.taxPolicy = taxPolicy;
        this.total = BigDecimal.ZERO;
        this.salesTaxes = BigDecimal.ZERO;
        this.items = new ArrayList<ReceiptItem>();
    }

    public void add(CartItem cartItem) {
        Tax tax = taxPolicy.forItem(cartItem);
        BigDecimal taxAmount = tax.dutyAmount(cartItem.getNetPrice()).multiply(BigDecimal.valueOf(cartItem.getQuantity()));
        BigDecimal taxedAmount = cartItem.getNetPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())).add(taxAmount);

        ReceiptItem receiptItem = new ReceiptItem(cartItem.getProductName(), cartItem.getQuantity(), taxedAmount, cartItem.isImported());
        items.add(receiptItem);

        total = total.add(taxedAmount);
        salesTaxes = salesTaxes.add(taxAmount);
    }

}

package it.gabrieletondi.salestaxes.receipt;

import it.gabrieletondi.salestaxes.catalog.ShelfItem;
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

    public void add(ShelfItem shelfItem) {
        Tax tax = taxPolicy.forItem(shelfItem);
        BigDecimal taxAmount = tax.dutyAmount(shelfItem.getNetPrice()).multiply(BigDecimal.valueOf(shelfItem.getQuantity()));
        BigDecimal taxedAmount = shelfItem.getNetPrice().multiply(BigDecimal.valueOf(shelfItem.getQuantity())).add(taxAmount);

        ReceiptItem receiptItem = new ReceiptItem(shelfItem.getProductName(), shelfItem.getQuantity(), taxedAmount, shelfItem.isImported());
        items.add(receiptItem);

        total = total.add(taxedAmount);
        salesTaxes = salesTaxes.add(taxAmount);
    }

}

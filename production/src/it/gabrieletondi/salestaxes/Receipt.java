package it.gabrieletondi.salestaxes;

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

    public void add(SaleItem saleItem) {
        Tax tax = taxPolicy.forItem(saleItem);
        BigDecimal taxAmount = tax.dutyAmount(saleItem.getNetPrice());
        BigDecimal taxedAmount = saleItem.getNetPrice().add(taxAmount);

        ReceiptItem receiptItem = new ReceiptItem(saleItem.getProductName(), saleItem.getQuantity(), taxedAmount, saleItem.isImported());
        items.add(receiptItem);

        total = total.add(taxedAmount);
        salesTaxes = salesTaxes.add(taxAmount);
    }

}

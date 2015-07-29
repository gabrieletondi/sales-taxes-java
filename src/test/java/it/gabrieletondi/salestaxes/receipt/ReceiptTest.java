package it.gabrieletondi.salestaxes.receipt;

import it.gabrieletondi.salestaxes.catalog.ShelfItem;
import it.gabrieletondi.salestaxes.tax.NeverlandTaxPolicyFactory;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReceiptTest {

    private Receipt receipt;

    @Before
    public void setUp() throws Exception {
        receipt = new Receipt(NeverlandTaxPolicyFactory.build());
    }

    @Test
    public void emptyReceipt() throws Exception {
        assertEquals(BigDecimal.ZERO, receipt.getTotal());
        assertEquals(BigDecimal.ZERO, receipt.getSalesTaxes());
    }

    @Test
    public void addItemsToReceipt() throws Exception {
        receipt.add(new ShelfItem("product 1", new BigDecimal("3.44"), 2, false, null));
        receipt.add(new ShelfItem("product 2", new BigDecimal("0.35"), 1, true, null));

        List<ReceiptItem> items = receipt.getItems();

        assertEquals(2, items.size());
        ReceiptItem firstItem = items.get(0);
        ReceiptItem secondItem = items.get(1);

        assertEquals(new BigDecimal("7.58"), firstItem.getTaxedPrice());
        assertEquals(2, firstItem.getQuantity());
        assertEquals("product 1", firstItem.getProductName());

        assertEquals(new BigDecimal("0.45"), secondItem.getTaxedPrice());
        assertEquals(1, secondItem.getQuantity());
        assertEquals("product 2", secondItem.getProductName());
        assertTrue(secondItem.isImported());
    }

    @Test
    public void calculatesTotals() throws Exception {
        receipt.add(new ShelfItem("product 1", new BigDecimal("12.34"), 2, false, null));
        receipt.add(new ShelfItem("product 2", new BigDecimal("2.23"), 1, false, null));

        assertEquals(new BigDecimal("29.66"), receipt.getTotal());
        assertEquals(new BigDecimal("2.75"), receipt.getSalesTaxes());
    }
}

package it.gabrieletondi.salestaxes.receipt;

import it.gabrieletondi.salestaxes.catalog.CartItem;
import it.gabrieletondi.salestaxes.doubles.FixedTaxAmountPolicy;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReceiptTest {

    private Receipt receipt;

    @Before
    public void setUp() throws Exception {
        receipt = new Receipt(new FixedTaxAmountPolicy(BigDecimal.ONE));
    }

    @Test
    public void emptyReceipt() throws Exception {
        assertEquals(BigDecimal.ZERO, receipt.getTotal());
        assertEquals(BigDecimal.ZERO, receipt.getSalesTaxes());
    }

    @Test
    public void addItemsToReceipt() throws Exception {
        receipt.add(new CartItem("product 1", BigDecimal.ONE, 0, false, null));
        receipt.add(new CartItem("product 2", BigDecimal.ONE, 0, false, null));

        List<ReceiptItem> items = receipt.getItems();

        assertEquals(2, items.size());
        ReceiptItem firstItem = items.get(0);
        ReceiptItem secondItem = items.get(1);

        assertEquals("product 1", firstItem.getProductName());
        assertEquals("product 2", secondItem.getProductName());
    }

    @Test
    public void calculatesTotals() throws Exception {
        receipt.add(new CartItem("product 1", new BigDecimal("12.34"), 2, false, null));
        receipt.add(new CartItem("product 2", new BigDecimal("2.23"), 1, false, null));

        assertEquals(new BigDecimal("29.91"), receipt.getTotal());
        assertEquals(new BigDecimal("3"), receipt.getSalesTaxes());
    }
}

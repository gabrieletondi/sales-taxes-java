package it.gabrieletondi.salestaxes;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SaleItemTest {

    @Test
    public void singleWordItem() throws Exception {
        SaleItem item = SaleItem.fromSellCommand("1 book at 12.49");

        assertEquals(1, item.getQuantity());
        assertEquals("book", item.getProductName());
        assertEquals(new BigDecimal("12.49"), item.getNetPrice());
    }

    @Test
    public void multipleWordItem() throws Exception {
        SaleItem item = SaleItem.fromSellCommand("1 item with multiple words at 12.49");

        assertEquals(1, item.getQuantity());
        assertEquals("item with multiple words", item.getProductName());
        assertEquals(new BigDecimal("12.49"), item.getNetPrice());
    }

    @Test
    public void importedItem() throws Exception {
        SaleItem item = SaleItem.fromSellCommand("1 imported item with multiple words at 12.49");

        assertEquals(1, item.getQuantity());
        assertEquals("item with multiple words", item.getProductName());
        assertEquals(new BigDecimal("12.49"), item.getNetPrice());
        assertTrue(item.isImported());
    }
}

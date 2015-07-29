package it.gabrieletondi.salestaxes.receipt;

import it.gabrieletondi.salestaxes.catalog.CartItem;
import it.gabrieletondi.salestaxes.doubles.NoTaxPolicy;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PlainTextReceiptFormatterTest {

    @Test
    public void multipleLinesReceipt() throws Exception {
        Receipt receipt = new Receipt(new NoTaxPolicy());
        CartItem firstItem = new CartItem("item1", new BigDecimal("10.02"), 1, true, null);
        receipt.add(firstItem);
        CartItem secondItem = new CartItem("item2", new BigDecimal("2.30"), 1, false, null);
        receipt.add(secondItem);

        PlainTextReceiptFormatter formatter = new PlainTextReceiptFormatter();

        String expected = "1 imported item1: 10.02\n" +
                "1 item2: 2.30\n" +
                "Sales Taxes: 0.00\n" +
                "Total: 12.32";

        assertEquals(expected, formatter.format(receipt));
    }
}

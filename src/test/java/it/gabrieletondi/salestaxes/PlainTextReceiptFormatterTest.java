package it.gabrieletondi.salestaxes;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PlainTextReceiptFormatterTest {

    @Test
    public void multipleLinesReceipt() throws Exception {
        Receipt receipt = new Receipt(NeverlandTaxPolicyFactory.build());
        ShelfItem firstItem = new ShelfItem("item1", new BigDecimal("10.02"), 1, true, null);
        receipt.add(firstItem);
        ShelfItem secondItem = new ShelfItem("item2", new BigDecimal("2.30"), 1, false, null);
        receipt.add(secondItem);

        PlainTextReceiptFormatter formatter = new PlainTextReceiptFormatter();

        String expected = "1 imported item1: 11.62\n" +
                "1 item2: 2.55\n" +
                "Sales Taxes: 1.85\n" +
                "Total: 14.17";

        assertEquals(expected, formatter.format(receipt));
    }
}

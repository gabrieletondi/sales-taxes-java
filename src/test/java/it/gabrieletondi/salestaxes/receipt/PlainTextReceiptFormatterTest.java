package it.gabrieletondi.salestaxes.receipt;

import it.gabrieletondi.salestaxes.catalog.CartItem;
import it.gabrieletondi.salestaxes.doubles.FixedTaxAmountPolicy;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PlainTextReceiptFormatterTest {

    @Test
    public void multipleLinesReceipt() throws Exception {
        Receipt receipt = new Receipt(new FixedTaxAmountPolicy(BigDecimal.ONE));
        CartItem firstItem = new CartItem("item1", new BigDecimal("10.02"), 1, true, null);
        receipt.add(firstItem);
        CartItem secondItem = new CartItem("item2", new BigDecimal("2.30"), 1, false, null);
        receipt.add(secondItem);

        PlainTextReceiptFormatter formatter = new PlainTextReceiptFormatter();

        String expected = "1 imported item1: 11.02\n" +
                "1 item2: 3.30\n" +
                "Sales Taxes: 2.00\n" +
                "Total: 14.32";

        assertEquals(expected, formatter.format(receipt));
    }
}

package it.gabrieletondi.salestaxes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class POSTest {

    @Test
    public void sellOneTaxFreeNotImportedItem() throws Exception {
        POS pos = new POS();
        pos.sellItem("1 book at 12.49");

        String receipt = pos.receipt();

        String expected = "1 book : 12.49\n" +
                "Sales Taxes: 0.00\n" +
                "Total: 12.49";
        assertEquals(expected, receipt);
    }

    @Test
    public void sellOneStandardTaxNotImportedItem() throws Exception {
        POS pos = new POS();
        pos.sellItem("1 musical CD at 14.99");

        String receipt = pos.receipt();

        String expected = "1 musical CD : 16.49\n" +
                "Sales Taxes: 1.50\n" +
                "Total: 16.49";
        assertEquals(expected, receipt);
    }

}

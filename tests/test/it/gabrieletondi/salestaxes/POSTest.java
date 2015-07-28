package it.gabrieletondi.salestaxes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class POSTest {

    private InMemoryWithDefaultTaxPolicy taxPolicy;
    private POS pos;

    @Before
    public void setUp() {
        taxPolicy = NeverlandTaxPolicyFactory.build();
        pos = new POS(taxPolicy);
    }

    @Test
    public void sellOneTaxFreeNotImportedItem() throws Exception {
        pos.sell("1 book at 12.49");

        String receipt = pos.receipt();

        String expected = "1 book: 12.49\n" +
                "Sales Taxes: 0.00\n" +
                "Total: 12.49";
        assertEquals(expected, receipt);
    }

    @Test
    public void sellOneStandardTaxNotImportedItem() throws Exception {
        pos.sell("1 musical CD at 14.99");

        String receipt = pos.receipt();

        String expected = "1 musical CD: 16.49\n" +
                "Sales Taxes: 1.50\n" +
                "Total: 16.49";
        assertEquals(expected, receipt);
    }

    @Test
    public void sellMultipleNotImportedItems() throws Exception {
        pos.sell("1 book at 12.49");
        pos.sell("1 music CD at 14.99");
        pos.sell("1 chocolate bar at 0.85");

        String receipt = pos.receipt();

        String expected = "1 book: 12.49\n" +
                "1 music CD: 16.49\n" +
                "1 chocolate bar: 0.85\n" +
                "Sales Taxes: 1.50\n" +
                "Total: 29.83";
        assertEquals(expected, receipt);
    }

}

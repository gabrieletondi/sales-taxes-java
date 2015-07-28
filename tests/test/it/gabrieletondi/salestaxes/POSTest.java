package it.gabrieletondi.salestaxes;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class POSTest {

    private InMemoryWithDefaultTaxPolicy taxPolicy;
    private POS pos;

    @Before
    public void setUp() {
        Map<String, Tax> specificRules = new HashMap<String, Tax>();
        specificRules.put("book", Tax.withRate(0));
        taxPolicy = new InMemoryWithDefaultTaxPolicy(Tax.withRate(10), specificRules);
        pos = new POS(taxPolicy);
    }

    @Test
    public void sellOneTaxFreeNotImportedItem() throws Exception {
        pos.sellItem("1 book at 12.49");

        String receipt = pos.receipt();

        String expected = "1 book : 12.49\n" +
                "Sales Taxes: 0.00\n" +
                "Total: 12.49";
        assertEquals(expected, receipt);
    }

    @Test
    public void sellOneStandardTaxNotImportedItem() throws Exception {
        pos.sellItem("1 musical CD at 14.99");

        String receipt = pos.receipt();

        String expected = "1 musical CD : 16.49\n" +
                "Sales Taxes: 1.50\n" +
                "Total: 16.49";
        assertEquals(expected, receipt);
    }

}

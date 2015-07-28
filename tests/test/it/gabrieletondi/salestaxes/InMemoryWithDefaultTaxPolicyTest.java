package it.gabrieletondi.salestaxes;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class InMemoryWithDefaultTaxPolicyTest {

    public static final Tax DEFAULT_TAX = Tax.withRate(10);
    public static final Tax SPECIFIC_TAX = Tax.withRate(15);

    @Test
    public void defaultRate() throws Exception {
        InMemoryWithDefaultTaxPolicy policy = new InMemoryWithDefaultTaxPolicy(DEFAULT_TAX, new HashMap<String, Tax>());

        Tax actualTax = policy.forItemName("any item name");

        assertEquals(DEFAULT_TAX, actualTax);
    }

    @Test
    public void specificRule() throws Exception {
        HashMap<String, Tax> specificRules = new HashMap<String, Tax>();
        specificRules.put("specific product", SPECIFIC_TAX);
        InMemoryWithDefaultTaxPolicy policy = new InMemoryWithDefaultTaxPolicy(DEFAULT_TAX, specificRules);

        Tax actualTax = policy.forItemName("specific product");

        assertEquals(SPECIFIC_TAX, actualTax);
    }
}
